package api;

import api.DataPetstoreAPI.*;
import com.github.fge.jsonschema.messages.JsonSchemaValidationBundle;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import spec.Specifications;

import java.util.List;

import static io.restassured.RestAssured.given;


public class PetSHopTests extends Specifications {
    /**
     * End Points
     */
    private static final String URL = "https://petstore.swagger.io/v2";
    private static final String getPetUrl = "/pet/";
    private static final String getPetsOnStatusUrl = "/pet/findByStatus";
    @BeforeTest
    public void setFilter() {
        RestAssured.filters(new AllureRestAssured());
    }

    /**
     * Get pet on ID Test
     */
    @Test
    public void getPetId() {
        installSpecification(requestSpec(URL), specResponseOK200());
        String id = "5";
        PetClass petClass = given()
                .when()
                .get(URL + getPetUrl + id)
                .then().log().all()
                .extract().body().as(PetClass.class);
        System.out.println(petClass.getName());
        Assert.assertTrue(petClass.getName().contains("doggie"), "Pet с заданным Id не найден! "); // проверка возвращаемого значения в Responce
    }

    /**
     * Проверка ошибки если запись не найдена
     */
    @Test
    public void petNotFoundTest() {
        installSpecification(requestSpec(URL), specResponseError404());
        String id = "987978";
        PetNotFoundClass petClass = given()
                .when()
                .get(URL + getPetUrl + id)
                .then().log().all()
                .extract().body().as(PetNotFoundClass.class);
        System.out.println(petClass.getMessage());
        Assert.assertTrue(petClass.getMessage().contains("Pet not found"), "Ошибка не отобразилась.");
    }

    /**
     * Проверим что все записи имеют статус Available
     */
    @Test
    public void getAvailablePets() {
        installSpecification(requestSpec(URL), specResponseOK200());
        List<PetClass> availablePets = given()
                .param("status", "available")
                .when()
                .get(URL + getPetsOnStatusUrl)
                .then().log().all()
                .extract().body().jsonPath().getList("", PetClass.class);
        Assert.assertNotNull(availablePets, "Ответ пуст."); // Проверим, что ответ не пустой
        System.out.println(availablePets);
        // доделать тест
        availablePets.stream().forEach(x -> Assert.assertTrue(x.getStatus().contains("available"), "Не все записи в статусе - available!")); // проверим статус каждой записи
    }

    /**
     * Проверим что все записи имеют статус Pending
     */
    @Test
    public void getPendingPets() {
        installSpecification(requestSpec(URL), specResponseOK200());
        List<PetClass> availablePets = given()
                .param("status", "pending")
                .when()
                .get(URL + getPetsOnStatusUrl)
                .then().log().all()
                .extract().body().jsonPath().getList("", PetClass.class);
        Assert.assertNotNull(availablePets, "Ответ пуст."); // Проверим, что ответ не пустой
        System.out.println(availablePets);
        // доделать тест
        availablePets.stream().forEach(x -> Assert.assertTrue(x.getStatus().contains("pending"), "Не все записи в статусе - pending!")); // проверим статус каждой записи
    }

    /**
     * Проверим что все записи имеют статус SOld
     */
    @Test
    public void getSoldPets() {
        installSpecification(requestSpec(URL), specResponseOK200());
        List<PetClass> availablePets = given()
                .param("status", "sold")
                .when()
                .get(URL + getPetsOnStatusUrl)
                .then().log().all()
                .extract().body().jsonPath().getList("", PetClass.class);
        Assert.assertNotNull(availablePets, "Ответ пуст."); // Проверим, что ответ не пустой
        availablePets.stream().forEach(x -> Assert.assertTrue(x.getStatus().contains("sold"), "Не все записи в статусе - sold!")); // проверим статус каждой записи
    }

    // доделать Extract Body
    @Test (priority = 1)
    public void addPetTest() {
        installSpecification(requestSpec(URL), specResponseOK200());
        PetAddClass newPet = new PetAddClass(2608, "NameCat", "available");
        PetAddResponceClass petAdd = given()
                .body(newPet)
                .when()
                .post(URL + "/pet")
                .then().log().all()
                .extract().body().as(PetAddResponceClass.class);
        System.out.println(petAdd);
        Assert.assertTrue(petAdd.getName().contains("NameCat"), "Питомец не добавлен");
    }

    /**
     * Тест Json Schema
     */
    @Test
    public void addPetSchemaTest() {
        installSpecification(requestSpec(URL), specResponseOK200());
        PetAddClass newPet = new PetAddClass(2608, "NameCat", "available");
        given()
                .body(newPet)
                .when()
                .post(URL + "/pet")
                .then().log().all()
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("addPetSchema.json"));
    }

    @Test (priority = 2)
    public void updateNamePetTest() {
        installSpecification(requestSpec(URL), specResponseOK200());
        String idNewPet = "2608";
        UpdNamePetClass updReq = new UpdNamePetClass(2608, "Chapa", "available");
        UpdatePetResponce updResp = given()
                .body(updReq)
                .when()
                .put(URL + getPetUrl)
                .then().log().all()
                .extract().body().as(UpdatePetResponce.class);
        Assert.assertNotNull(updResp);
        Assert.assertTrue(updResp.getName().contains("Chapa"), "Имя питомца изменено");
        System.out.println(updResp.getName());
    }


}
