package api;

import io.restassured.path.json.JsonPath;
//import org.asynchttpclient.Response;
import org.junit.Test;
import spec.Specifications;

import java.net.URL;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

public class ReqresWithOutPojo extends Specifications {
    private final static String URL = "https://reqres.in";

   /* @Test
    public void checkAvatarNpPojoTest(){
        installSpecification(requestSpec(URL),specResponseOK200());
        Response responce = (Response) given() // 
                .when()
                .get("/api/users?page=2")
                .then().log().all()
                .body("page",equalTo(2))
                .body("data.id",notNullValue())
                .body("data.email",notNullValue())
                .body("data.first_name",notNullValue())
                .body("data.last_name",notNullValue())
                .body("data.avatar",notNullValue())
                .extract().response();
        JsonPath jsonPath = responce.jsonPath();
    }*/
}
