package api;

import BaseCLasses.FileData;
import api.DataPetstoreAPI.*;
import freemarker.core.CSSOutputFormat;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.Assert;
import org.junit.Test;
import spec.Specifications;

import java.util.List;

import static io.restassured.RestAssured.given;


public class PetSHopTests extends Specifications {

    private static final String URL = "https://petstore.swagger.io/v2";
    private static String getPetUrl = "/pet/";
    private static String getPetsOnStatusUrl = "/pet/findByStatus";

    /**
     * Get pet on ID Test
     */
    @Test
    public void getPetId() {
        installSpecification(requestSpec(URL),specResponseOK200());
        String id = "5";
        PetClass petClass = given()
                .when()
                .get(URL + getPetUrl + id)
                .then().log().all()
                .extract().body().as(PetClass.class);
        System.out.println(petClass.getName());
        Assert.assertTrue("Pet с заданным Id не найден! ", petClass.getName().contains("doggie")); // проверка возвращаемого значения в Responce
    }

    /**
     * Проверка ошибки если запись не найдена
     */
    @Test
    public void petNotFoundTest(){
        installSpecification(requestSpec(URL),specResponseError404());
        String id = "987978";
        PetNotFoundClass petClass = given()
                .when()
                .get(URL + getPetUrl + id)
                .then().log().all()
                .extract().body().as(PetNotFoundClass.class);
        System.out.println(petClass.getMessage());
        Assert.assertTrue("Ошибка не отобразилась.", petClass.getMessage().contains("Pet not found"));
    }

    /**
     * Проверим что все записи имеют статус Available
     */
    @Test
    public void getAvailablePets() {
        installSpecification(requestSpec(URL),specResponseOK200());
        List<PetClass> availablePets = given()
                .param("status", "available")
                .when()
                .get(URL + getPetsOnStatusUrl)
                .then().log().all()
                .extract().body().jsonPath().getList("", PetClass.class);
        Assert.assertNotNull("Ответ пуст.", availablePets); // Проверим, что ответ не пустой
        System.out.println(availablePets);
    // доделать тест
      availablePets.stream().forEach(x-> Assert.assertTrue("Не все записи в статусе - available!", x.getStatus().contains("available"))); // проверим статус каждой записи
    }

    /**
     * Проверим что все записи имеют статус Pending
     */
    @Test
    public void getPendingPets(){
        installSpecification(requestSpec(URL),specResponseOK200());
        List<PetClass> availablePets = given()
                .param("status", "pending")
                .when()
                .get(URL + getPetsOnStatusUrl)
                .then().log().all()
                .extract().body().jsonPath().getList("", PetClass.class);
        Assert.assertNotNull("Ответ пуст.", availablePets); // Проверим, что ответ не пустой
        System.out.println(availablePets);
        // доделать тест
        availablePets.stream().forEach(x-> Assert.assertTrue("Не все записи в статусе - pending!", x.getStatus().contains("pending"))); // проверим статус каждой записи
    }

    /**
     * Проверим что все записи имеют статус SOld
     */
    @Test
    public void getSoldPets(){
        installSpecification(requestSpec(URL),specResponseOK200());
        List<PetClass> availablePets = given()
                .param("status", "sold")
                .when()
                .get(URL + getPetsOnStatusUrl)
                .then().log().all()
                .extract().body().jsonPath().getList("", PetClass.class);
        Assert.assertNotNull("Ответ пуст.", availablePets); // Проверим, что ответ не пустой
        System.out.println(availablePets);
        availablePets.stream().forEach(x-> Assert.assertTrue("Не все записи в статусе - sold!", x.getStatus().contains("sold"))); // проверим статус каждой записи
    }
    // доделать Extract Body
    @Test
    public void addPetTest(){
        installSpecification(requestSpec(URL),specResponseOK200());
        PetAddClass newPet = new PetAddClass(2608, "NameCat","available");
        PetAddResponceClass petAdd = given()
                .body(newPet)
                .when()
                .post(URL + "/pet")
                .then().log().all()
                .extract().body(). as(PetAddResponceClass.class);
        System.out.println(petAdd);
        Assert.assertTrue("Питомец не добавлен", petAdd.getName().contains("NameCat"));
    }

    @Test
    public void updateNamePetTest (){
        installSpecification(requestSpec(URL),specResponseOK200());
        UpdNamePetClass updReq = new UpdNamePetClass(2608,"Chappy","available");
        UpdatePetResponce updResp = given()
                .body(updReq)
                .when()
                .put(URL+getPetUrl)
                .then().log().all()
                .extract().body().as(UpdatePetResponce.class);
        Assert.assertNotNull(updResp);
        Assert.assertTrue("Имя питомца изменено", updResp.getName().contains("Chappy"));
        System.out.println(updResp.getName());
    }

}
