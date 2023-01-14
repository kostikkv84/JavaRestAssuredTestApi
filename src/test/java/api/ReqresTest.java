package api;

import io.restassured.http.ContentType;
import org.junit.Assert;
import org.junit.Test;
import spec.Specifications;

import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

public class ReqresTest extends Specifications {
    private final static String URL = "https://reqres.in";

    /**
     * проверка, что в ссылке с аватаром, содержится id записи.
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

    @Test
    public void checkEmail() {
        List<UserData> users = given()
                .when()
                .contentType(ContentType.JSON)
                .get(URL+ "/api/users?page=2")
                .then().log().all()
                .extract().body().jsonPath().getList("data", UserData.class);
        List<String> emails = users.stream().map(UserData::getEmail).collect(Collectors.toList());
        for(int i=0;i<emails.size();i++){
            Assert.assertTrue(emails.get(i).contains("@reqres.in"));
        }
    }



}
