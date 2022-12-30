package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PostsAssertionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {

        RestAssured.baseURI = "https://gorest.co.in/";
        RestAssured.basePath = "public/v2";

        response = given()

                .queryParam("page", 1)
                .queryParam("per_page", 25)
                .when()
                .get("/posts")
                .then().statusCode(200);

    }
// 1. Verify the if the total record is 25
 @Test
   public void verifyTotal(){
     response.body("size()",equalTo(25));
   }
//2. Verify the if the title of id = 2405 is equal to Cado carpo vinco animi modi vacuus cenaculum..
@Test
public void verifyTitle(){
    response.body("findAll{it.id==2405}.title",hasItem("Cado carpo vinco animi modi vacuus cenaculum."));
}
//  3. Check the single user_id in the Array list (4907)
@Test
public void verifyUserId(){
    response.body("[1].user_id",equalTo(4907));
}

//4. Check the multiple ids in the ArrayList (2404, 2403,)
@Test
public void verifyMultipleId(){
    response.body("id",hasItems(2404,2403));
}

//5. Verify the body of userid = 4897 is equal to
    @Test
    public void verifyBody(){
        response.body("findAll{it.user_id==4897}.body",hasItem("Catena caput auctus. Traho dolorem nihil. Adfero curatio audeo. Aurum tamdiu subnecto. Depulso eligendi creo. Sint est sed. Sponte ulterius via. Volaticus cubicularis vulgo. Saepe recusandae chirographum. Stabilis audax amet. Cohors tutamen teneo. Vel sapiente rem. Ulciscor adultus stipes. Tenuis thermae sed. Turpis apostolus taceo. Acervus abduco atrocitas. Appono color tripudio. Defleo ad virga. Combibo qui quidem. Tibi deputo subseco. Voluptatem temptatio adnuo."));
    }

}
