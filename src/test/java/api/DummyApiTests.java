package api;

import BaseCLasses.FileData;
import api.DataApi.dummyapi.io.*;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import spec.SpecificationDummyApi;

import java.io.IOException;
import java.util.List;

import static io.restassured.RestAssured.given;
@Ignore
public class DummyApiTests extends SpecificationDummyApi {
    /**
     * Этот набор тестов в Крыму запускать с VPN подключением (Например Германия)
     */

    private final static String URL = "https://dummyapi.io/data/v1";

    @Before
    public void setFilter() {
        RestAssured.filters(new AllureRestAssured());

    }

    @Test
    public void checkGetUsersListAndTestID() {
        installSpecification(requestSpec(URL), specResponseOK200());
        String checkStringID = "60d0fe";
        List<GetUserList> users = given()
                .when()
                .get("/user")
                .then().log().body()
                .extract().body().jsonPath().getList("data", GetUserList.class);
        Assert.assertNotNull(users);
        users.stream().forEach(x -> Assert.assertTrue(x.getId().contains(checkStringID)));
    }

    @Test
    public void checkGetUserID() {
        String id = "60d0fe4f5311236168a109d1";
        installSpecification(requestSpec(URL), specResponseOK200());
        GetUserID getUser = given()
                .when()
                .get("/user/" + id)
                .then().log().body()
                .extract().body().as(GetUserID.class);
        Assert.assertNotNull(getUser);
        Assert.assertEquals("Id пользователя не найден", id, getUser.getId());
    }

    @Test
    public void checkGetUserIdError() {
        installSpecification(requestSpec(URL), specResponseError400());
        String error = "PARAMS_NOT_VALID";
        String id = "12345";
        GetUserError userIDError = given()
                .when()
                .get("/user/" + id)
                .then().log().body()
                .extract().as(GetUserError.class);
        Assert.assertNotNull(userIDError);
        Assert.assertEquals(error, userIDError.getError());
    }

    @Test
    public void checkPicturesUrl() {
        installSpecification(requestSpec(URL), specResponseOK200());
        String https = "https://randomuser.me/api/portraits";
        List<GetUserList> users = given()
                .when()
                .get("/user")
                .then().log().body()
                .extract().body().jsonPath().getList("data", GetUserList.class);
        users.stream().forEach(x -> Assert.assertTrue(x.getPicture().contains("https")));

    }

    /**
     * Создание нового пользователя
     *
     * @throws IOException
     */
    @Test
    public void checkCreateUser() throws IOException {
        installSpecification(requestSpec(URL), specResponseOK200());
        FileData fileData = new FileData();
        CreateUserReq create = new CreateUserReq("koskv3", "lastname3", "create.user3@mail.ru");
        CreateUserResp newUser;
        newUser = given()
                .body(create)
                .when()
                .post("/user/create")
                .then().log().all()
                .extract().as(CreateUserResp.class);

        fileData.saveID(newUser.getId()); // сохраняем ID нового пользователя

        System.out.println("Новый созданный ID: " + newUser.getId());
        Assert.assertNotNull(newUser);
        Assert.assertEquals(create.getFirstName(), newUser.getFirstName());


    }

    @Test
    public void putUpdateUser() throws IOException {
        installSpecification(requestSpec(URL),specResponseOK200());
        String id = "60d0fe4f5311236168a109d6";
        PutUpdateUserReq updateUser = new PutUpdateUserReq("TestName","TestLastName");
        String idCreatedUser = new FileData().openAndRead();
        PutUpdateUserResp update = given()
                .body(updateUser)
                .when()
                .put("/user/" + id)
                .then().log().body()
                .extract().as(PutUpdateUserResp.class);
        Assert.assertNotNull(update);

    }

    /**
     * Удаление созданного пользователя
     * Перед удалением создать юзера. Выполнить checkCreateUser()
     *
     * @throws IOException
     */
    @Test
    public void checkDeleteUser() throws IOException {
        installSpecification(requestSpec(URL), specResponseOK200());
        String idCreatedUser = new FileData().openAndRead();
        DeleteIdUser idUser = given()
                .when()
                .delete("/user/" + idCreatedUser)
                .then().log().body()
                .extract().as(DeleteIdUser.class);
        Assert.assertNotNull(idUser);
        Assert.assertEquals(idCreatedUser, idUser.getId());
    }
}
