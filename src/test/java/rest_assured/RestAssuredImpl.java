package rest_assured;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.Map;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class RestAssuredImpl {

    @Test
    public void get(){
        Response response = RestAssured.get("http://reqres.in/api/users/2");
        int statusCode = response.getStatusCode();
        System.out.println("statusCode = " + statusCode);
        final String last_name = response.getBody().jsonPath().get("data.last_name").toString();
        System.out.println("last_name = " + last_name);
    }

    @Test
    public void getWithQueryParam(){
        given()
                .with()
                .contentType(ContentType.JSON)
                .param("page","3")
        .when()
                .get("https://reqres.in/api/users")
        .then()
                .statusCode(200)
                .log().body();
    }

    @Test
    public void getWithParam(){
        given()
                .with()
                .contentType(ContentType.JSON)
                .param("id","2")
        .when()
                .get("https://reqres.in/api/users")
        .then()
//                .log().body();
                .body("data.first_name",equalTo("Janet"));
    }

    @Test
    public void get0(){
        given()
                .get("https://reqres.in/api/users")
        .then()
                .body("data.first_name",hasItems("Eve","Emma2","Charles"))
                .body("data.first_name[0]",equalTo("George"));
    }

    @Test
    public void post(){
//        Map<String , String > postBody=new HashMap<>();
//        postBody.put("name","Ali");
//        postBody.put("job","Tester");
//        System.out.println("postBody = " + postBody);
        JSONObject reqBody=new JSONObject();
        reqBody.put("name","Ali");
        reqBody.put("job","Tester");
//        System.out.println("reqBody = " + reqBody);

        given()
                .with()
                .contentType(ContentType.JSON)
//                .body("{\"name\":\"Ali\"" +
//                        ",\"job\":\"QAEngineer\"}")
                .body(reqBody)
        .when()
                .post("https://reqres.in/api/users")
        .then()
                .log().body()
                .body("name",is("Ali"))
                .statusCode(201);
    }

    @Test
    public void delete(){
        given().with()
                .param("id","2")
        .when()
                .delete("https://reqres.in/api/users")
        .then()
                .log().body()
                .statusCode(204);
    }

    @Test
    public void put(){
        Map<String , String > postBody=new HashMap<>();
        postBody.put("name","Ali");
        postBody.put("job","Tester");
        JSONObject reqBody=new JSONObject(postBody);
        given()
                .with()
                .contentType(ContentType.JSON)
                .body(reqBody)
        .when()
                .put("https://reqres.in/api/users/2")
        .then()
                .log().body()
                .body("name",is("Ali"))
                .statusCode(200);
    }

    @Test
    public void patch(){
        Map<String , String > postBody=new HashMap<>();
        postBody.put("name","Ali");
        JSONObject reqBody=new JSONObject(postBody);
        given()
                .with()
                .contentType(ContentType.JSON)
                .body(reqBody)
        .when()
                .patch("https://reqres.in/api/users/2")
        .then()
                .log().body()
                .body("name",is("Ali"))
                .statusCode(200);
    }
}
