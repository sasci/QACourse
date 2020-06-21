package rest_assured.restApiFramework;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class RestSpecificationImpl {

    RequestSpecification requestSpecification=new RequestSpecBuilder()
            .setBaseUri("https://reqres.in")
            .setContentType(ContentType.JSON)
            .setBasePath("/api/users/{id}")
            .build();


    RequestSpecification requestSpec=new RequestSpecBuilder()
            .setBaseUri("https://reqres.in")
            .setContentType(ContentType.JSON)
            .setBasePath("/api/users")
            .build();

    ResponseSpecification responseSpec=new ResponseSpecBuilder()
            .expectStatusCode(200)
            .expectResponseTime(lessThan(5000L))
            .expectBody("$",hasKey("data"))
            .build();

    ValidatableResponse resp=
            given()
            .baseUri("https://reqres.in")
            .contentType(ContentType.JSON)
            .when().get().then();

    @Test
    void reqtest2() {
        given()
                .spec(requestSpecification)
                .pathParam("id","6")
                .when()
                .get()
                .then().log().body();
    }

    @Test
    void requestSpecTest() {
        given()
                .spec(requestSpec)
                .pathParam("id",2)
                .when()
                .get();
    }

    @Test
    void responseSpecTest() {
        given()
                .spec(requestSpec)
                .get()
                .then()
                .spec(responseSpec)
                .log().body()
                .body("data.first_name",hasItems("Emma"));

    }

    @Test
    void responseSpecTest2() {
        given()
                .spec(requestSpec)
                .get()
                .then()
                .spec(responseSpec)
                .body("data{it.id<4}}",hasItems("5"));
    }

    /*
    Logical(allOf,anyOf ...)
    Object(equalTo,instanceOf ...)
    Collections(hasItem,hasKey...)
    Number(greaterThan,lessThan...)
    Text(startsWith,endsWith,containsString...)
     */

    @Test
    void responseSpecTest3() {
        given()
                .spec(requestSpec)
                .then()
                .spec(responseSpec)
                .body("store.book.price",allOf(lessThan(50)))
                .body("store.book.price.min()",greaterThan(5));

    }

    @Test
    void validatableResp() {
        resp.body("store.book.size()",
                equalTo(resp.extract().body().path("store.books_count")));
    }
}
