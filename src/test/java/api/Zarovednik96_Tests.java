package api;

import api.Zapovednik96.ForgetEmail;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import spec.Specifications;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.responseSpecification;

public class Zarovednik96_Tests extends Specifications {
    private final static String URL = "https://itgro-dev1-z.stage.ext.itgro.dev";
    private final static String LOGIN = "79788065898";
    private final static String PASSWORD = "260805";
    private String idOrder = "";
    private String idItem = "312882";

    @BeforeTest
    public void setFilter() {
        RestAssured.filters(new AllureRestAssured());
    }

    /**
     * Тест успешной авторизации
     */
    @Test (priority = 4)
    public void loginSuccessTest() {
        installSpecification(requestSpec(URL), specResponseOK200());
        Boolean authorized = given().contentType("multipart/form-data")
                .multiPart("login", "79788065898")
                .multiPart("password", "260805")
                .when()
                .post(URL + "/api/personal/user/auth/")
                .then().log().all()
                .extract().path("value.authorized");
        Assert.assertTrue(authorized);
    }

    /**
     * Получить питомцев пользователя - Тест
     */
    @Test (priority = 4)
    public void getPets() {
        installSpecification(requestSpec(URL), specResponseOK200());
        List<String> resp = given()
                .auth()
                .preemptive().basic("79788065898", "260805")
                .when()
                .get(URL + "/api/sale/pet/get/")
                .then().log().all()
                .extract().body().path("value.pets[0].id");
        System.out.println(resp);
    }

    /**
     * Отправка запроса на восстановление пароля при указании Email - Тест
     */
    @Test (priority = 4)
    public void forgetEmail() {
        installSpecification(requestSpec(URL), specResponseOK200());
        List<String> forgetEmail = given()
                .body("\"email\": \"koskv@list.ru\"")
                .when()
                .post(URL + "/api/personal/user/forgot/")
                .then().log().all()
                .extract().path("value");
        System.out.println(forgetEmail);
    //    Assert.assertTrue(forgetEmail.getValue().contains("Не заполнено обязательное поле `Email`"), "Строка не содержится в ответе");
    }

    /**
     * Восстановление пароля если не указать Email - Тест
     */
    @Test (priority = 4)
    public void emailSHoudBeExistTest() {
        installSpecification(requestSpec(URL), specResponseOK200());
        List<String> forgetEmail = given()
                .contentType("multipart/form-data")
                .multiPart("email", "koskv@list.ru")
                .when()
                .get(URL + "/api/personal/user/forgot/")
                .then().log().all()
                .extract().path("value");
             Assert.assertTrue(forgetEmail.contains("Не заполнено обязательное поле `Email`"), "Строка не содержится в ответе");
    }

    /**
     * Тест НЕ успешной авторизации без пароля
     */
    @Test (priority = 4)
    public void ErrorPassTest() {
        installSpecification(requestSpec(URL), specResponseOK200());
        List<String> error = given().contentType("multipart/form-data")
                .multiPart("login", "79788065898")
                .multiPart("password", "")
                .post(URL + "/api/personal/user/auth/")
                .then().log().all()
                .extract().path("value");
        Assert.assertTrue(error.contains("Не заполнено обязательное поле `Пароль`"));
    }

    /**
     * Тест НЕ успешной авторизации без логина
     */
    @Test (priority = 4)
    public void ErrorLoginTest() {
        installSpecification(requestSpec(URL), specResponseOK200());
        List<String> error = given().contentType("multipart/form-data")
                .multiPart("login", "")
                .multiPart("password", "260805")
                .post(URL + "/api/personal/user/auth/")
                .then().log().all()
                .extract().path("value");
        Assert.assertTrue(error.contains("Не заполнено обязательное поле `Логин`"));
    }

    /**
     * Проверка ответа по свободному времени приема
     */
    @Test (priority = 4)
    public void getFreeTimeSlots() {
        installSpecification(requestSpec(URL), specResponseOK200());
        List<ArrayList> list = given()
                .when()
                .get(URL + "/api/vet_cabinets/cabinet/getintervals/197")
                .then().log().all()
                .extract().path("value.intervals");
        System.out.println(list.size());
        Assert.assertTrue(list.size() > 1);
    }

    /**
     * Запрос вет клиник по городу - Тест
     */
    @Test (priority = 4)
    public void getShopCityFilter() {
        installSpecification(requestSpec(URL), specResponseOK200());
        String list = given()
                .param("SHOP_CITY", "Екатеринбург")
                .when()
                .get(URL + "/api/sale/vet/get/")
                .then().log().all()
                .extract().path("value.vet[1].SHOP_CITY");
        System.out.println(list);
        Assert.assertEquals("Екатеринбург", list);
    }

    /**
     * Запись к ветеринару, если время занято - Тест
     */
    @Test (priority = 4)
    public void postCreateAppointmentTimeIsBusy() {
        installSpecification(requestSpec(URL), specResponseOK200());
        String error = given()
                .auth()
                .preemptive().basic("79788065898", "260805")
                .contentType("multipart/form-data")
                .multiPart("acceptPolicy", true)
                .multiPart("comment", "comment")
                .multiPart("cabinetId", "197")
                .multiPart("pet_id", "458715")
                .multiPart("date", "2023-02-08T08:00:00")
                .when()
                .post(URL + "/api/vet_cabinets/record/create/")
                .then().log().all()
                 .extract().path("value");
        Assert.assertTrue(error.contains("Время занято"));
    }

    //------------------- Работа с корзиной -----------------------------------------
    /**
     * Добавить товар в корзину
     */
    @Test (priority = 1)
    public void addItemToBasket(){
        installSpecification(requestSpec(URL),specResponseOK200());
        String count = "/1/";
        String prodId = given()
                .auth()
                .preemptive().basic("79788065898", "260805")
                .when()
                .get(URL + "/api/sale/basket/add/" + idItem + count)
                .then().log().all()
                .extract().body().path("value.basket[0].ID");
        idOrder = prodId;
        System.out.println(idOrder);
        Assert.assertFalse(prodId.isEmpty());
    }

    /**
     * Просмотр корзины с товарами, зарегистрированного пользователя
     */
    @Test (priority = 2)
    public void getFromBasketOrder(){
        installSpecification(requestSpec(URL),specResponseOK200());
        String resp = given()
                .auth()
                .preemptive().basic("79788065898", "260805")
                .when()
                .get(URL + "/api/sale/basket/get/")
                .then().log().all()
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("96_CartSchema.json"))
                .extract().path("value.basket[0].ID");
        Assert.assertTrue(resp.contains(idOrder));
    }

    /**
     * Удаление товара из корзины - Тест
     */
    @Test (priority = 3)
    public void deleteFromBasketOrder(){
        installSpecification(requestSpec(URL),specResponseOK200());
        Integer resp = given()
                .auth()
                .preemptive().basic("79788065898", "260805")
                .when()
                .get(URL + "/api/sale/basket/delete/" + idOrder)
                .then().log().all()
               // .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("96_CartSchema.json"))
                .extract().path("value.total.QUANTITY");
        System.out.println(resp);
        Assert.assertEquals(resp,0);
    }








}


