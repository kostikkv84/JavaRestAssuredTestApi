package api;

import api.DataReqresIn.*;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.junit.Assert;

import org.junit.Before;
import org.junit.Test;
import spec.Specifications;


import java.time.Clock;
import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.responseSpecification;

public class ReqresInTest extends Specifications {

    private final static String URL = "https://reqres.in";

    @Before
    public void setFilter () {
        RestAssured.filters(new AllureRestAssured());
    }

    /**
     * Get запрос - проверка, что в ссылке с аватаром, содержится id записи.
     */
    @Test
    public void checkAvatarAndId(){
        installSpecification(requestSpec(URL),specResponseOK200());
        List<UserData> users = given()
                .when()
                .get("/api/users?page=2") // http метод. получаем json
                .then().log().all()// записываем все в лог
                // извлекаем body и записываем блок data в созданный класс (в те переменные).
                .extract().body().jsonPath().getList("data",UserData.class);
        //1. Выбираем все аватары и все id
        users.stream().forEach(x-> Assert.assertTrue(x.getAvatar().contains(x.getId().toString())));
        //2. то же самое через цикл for
        /*       for (UserData x : users) {
            Assert.assertTrue(x.getAvatar().contains(x.getId().toString()));
        }*/
        //3. Два списка и сравниваем в цикле
     /*   List<String> avatars = users.stream().map(UserData::getAvatar).collect(Collectors.toList());
        List<String> ids = users.stream().map(x->x.getId().toString()).collect(Collectors.toList());
        for (int i=0; i < users.size(); i++){
            Assert.assertTrue(avatars.get(i).contains(ids.get(i))); */

        }

    /**
     * отдельная обработка запроса на проверку email, для тренировки.
     */
    @Test
    public void checkEmail() {
        installSpecification(requestSpec(URL),specResponseOK200());
        List<UserData> users = given()
                .when()
                .get("/api/users?page=2")
                .then().log().all()
                .extract().body().jsonPath().getList("data", UserData.class);
        users.stream().forEach(x->Assert.assertTrue("Окончание email не соответствет",x.getEmail().contains("@reqres.in")));
    //2. Вариант через новый список и проверка в цикле
    /*    List<String> emails = users.stream().map(UserData::getEmail).collect(Collectors.toList());
        for(int i=0;i<emails.size();i++){
            Assert.assertTrue(emails.get(i).contains("@reqres.in"));
        }*/
    }

    /**
     * Post запросы
     */
    @Test
    public void successRegisterTest(){
        installSpecification(requestSpec(URL),specResponseOK200());
        // определяем переменные с ожидаемым результатом
        Integer id = 4;
        String token = "QpwL5tke4Pnpja7X4";
        Register user = new Register("eve.holt@reqres.in","pistol");
        SuccessReg successReg = given()
                // в метод пост вставляем тело из класса запроса
                .body(user)
                .when()
                .post("/api/register")
                .then().log().all()
                .extract().as(SuccessReg.class); // так как ответ простой без Root можно сразу записать данные в класс
        Assert.assertEquals("Проверка полученного ID",id,successReg.getId());
        Assert.assertEquals("Проверка полученного токена",token,successReg.getToken());
    }

    @Test
    public void unsuccessfulRegisterTest(){
        installSpecification(requestSpec(URL),specResponseError400());
        String error = "Missing password";
        Register user = new Register("sydney@fife",""); // передаем в запрос только Email
        RegisterError regError = given()
                .body(user)
                .when()
                .post("/api/register")// Post запрос на адрес
                .then().log().all()
                .extract().as(RegisterError.class);
        Assert.assertEquals(error,regError.getError());
    }

    @Test
    public void loginTest(){
        installSpecification(requestSpec(URL),specResponseOK200());
        String token = "QpwL5tke4Pnpja7X4";
        Login user = new Login("eve.holt@reqres.in","cityslicka"); // создали тело запроса
        //Делаем запрос
        SuccessLogin successLogin = given()//записали ответ в переменную
                .body(user)
                .when()
                .post("/api/login")
                .then().log().all()
                .extract().as(SuccessLogin.class);
        Assert.assertEquals("Ожидаемый токен соответствует токену в ответе",token,successLogin.getToken());
    }

    @Test
    public void unsuccessfulLoginTest (){
        installSpecification(requestSpec(URL),specResponseError400());
        String error = "Missing password";
        //Делаем тело запроса
        Login user = new Login("peter@klaven","");
        //Делаем запрос и записываем ответ в переменную
        LoginError loginError = given()
                .body(user)
                .when()
                .post("/api/login")
                .then().log().all()
                .extract().as(LoginError.class);
        System.out.println(loginError.getError());
        Assert.assertEquals("Результат не соответствует ожидаемому",error,loginError.getError());
    }
    /**
     * Put запрос
     */
    @Test
    public void updateUserTest(){
        installSpecification(requestSpec(URL),specResponseOK200());
        UpdateUser updateUser = new UpdateUser("morpheus","zion resident");
        UpdateUserResponce updateResponce = given()
                .body(updateUser)
                .when()
                .put("/api/users/2")
                .then().log().all()
                .extract().as(UpdateUserResponce.class);
        Assert.assertEquals(updateUser.getName(),updateResponce.getName());
        Assert.assertEquals(updateUser.getJob(),updateResponce.getJob());
    }

    /**
     * Проверить, что ответ с отсортированными годами.
     */
    @Test
    public void yearSortedTest () {
        installSpecification(requestSpec(URL),specResponseOK200());
        List<ResourceListResponse> list = given() // получаем ответ
                .when()
                .get("/api/unknown")
                .then().log().all()
                .extract().body().jsonPath().getList("data", ResourceListResponse.class);
        //создаем список из годов. Получаем года из ответа и добавляем в коллекцию с типом Integer.
        List<Integer> years = list.stream().map(ResourceListResponse::getYear).collect(Collectors.toList());
        List<Integer> yearsSorted = years.stream().sorted().collect(Collectors.toList());
       Assert.assertEquals(yearsSorted,years);
        System.out.println(years);
        System.out.println(yearsSorted);

    }
    /**
     * Delete запрос
     */
    @Test
    public void deleteTest(){
        installSpecification(requestSpec(URL),specResponseOK204());
       // не передаем тело запроса и не получаем ответ
        given().delete("/api/users/2").then().log().all();
    }

    @Test
    public void timeUpdateTest(){
        installSpecification(requestSpec(URL),specResponseOK200());
        UpdateUser user = new UpdateUser("morpheus","zion resident");
        UpdateUserResponce time = given()
                .body(user)
                .when()
                .put("/api/users/2")
                .then().log().all()
                .extract().as(UpdateUserResponce.class);
        String regex = "(.{13})$";
        // заменяем полученное системное время, удаляем последние 13 символов.
        String currentTime = Clock.systemUTC().instant().toString().replaceAll(regex,"");
        Assert.assertTrue(time.getUpdatedAt().contains(currentTime));
        System.out.println("Время сейчас " + currentTime);
        System.out.println("Время из ответа " + time.getUpdatedAt());
    }

}
