package com.gorest.crudtest;

import com.gorest.model.UserPojo;
import com.gorest.testbase.TestBase;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class UserCRUDTest extends TestBase {
    static ValidatableResponse response;


    @Test
    public void verifyUserCreatedSuccessfully() {

        UserPojo userPojo = new UserPojo();
        userPojo.setName("Kay");
        userPojo.setEmail(getRandomValue() + "@gmail.com");
        userPojo.setGender("female");
        userPojo.setStatus("active");
        Response response = given()
                .header("Authorization", "Bearer a58066525e0288b799ff232d3b04af9c015d09d7207a3585eb51c1acc8ba118b")
                .header("Content-Type", "application/json")
                .header("Connection", "keep-alive")
                .when()
                .body(userPojo)
                .post("/users");
        response.prettyPrint();
        response.then().statusCode(201);
    }

    @Test
    public void verifyUsergetSuccessfully() {


        Response response = given()
                .header("Authorization", "Bearer a58066525e0288b799ff232d3b04af9c015d09d7207a3585eb51c1acc8ba118b")
                .header("Connection", "keep-alive")
                .when()

                .get("/users");
        response.prettyPrint();
        response.then().statusCode(200);

    }

    @Test
    public void verifyUserUpdateSuccessfully() {
        UserPojo userPojo = new UserPojo();

        userPojo.setName("Kay");
        userPojo.setEmail(getRandomValue() + "@gmail.com");
        userPojo.setGender("female");
        userPojo.setStatus("Inactive");
        Response response = given()
                .header("Authorization", "Bearer a58066525e0288b799ff232d3b04af9c015d09d7207a3585eb51c1acc8ba118b")
                .header("Content-Type", "application/json")
                .header("Connection", "keep-alive")
                .when()
                .body(userPojo)
                .put("/users/17842");
        response.prettyPrint();
        response.then().statusCode(200);

    }

    @Test
    public void VerifyUserDeleteSuccessfully() {
        Response response = given()
                .header("Authorization", "Bearer a58066525e0288b799ff232d3b04af9c015d09d7207a3585eb51c1acc8ba118b")
                .header("Connection", "keep-alive")
                .when()
                .delete("/users/17842");
        response.prettyPrint();
        response.then().statusCode(204);


    }
}


