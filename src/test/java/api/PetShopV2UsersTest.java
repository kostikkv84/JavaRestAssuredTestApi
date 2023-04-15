package api;

import api.PetShopUsers.UserAdd;
import api.PetShopUsers.UserAddResponce;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import spec.Specifications;

import static io.restassured.RestAssured.given;

public class PetShopV2UsersTest extends Specifications {

    private final String URL = "https://petstore.swagger.io/v2/";

    @BeforeTest
    public void setFilter() {
        RestAssured.filters(new AllureRestAssured());
    }



    public void testUserAdd() {
        installSpecification(requestSpec(URL),specResponseOK200());
       // UserAddResponce userAddResp = new UserAddResponce("")

    }
}
