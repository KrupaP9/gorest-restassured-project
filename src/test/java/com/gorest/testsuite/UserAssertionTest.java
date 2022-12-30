package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class UserAssertionTest {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {

        RestAssured.baseURI = "https://gorest.co.in/";
        RestAssured.basePath = "public/v2";

        response = given()

                .queryParam("page", 1)
                .queryParam("per_page", 20)
                .when()
                .get("/users")
                .then().statusCode(200);

    }
    //----------------------------------------all dynemic data keep changing names and Id---------------------------------------//
//    1. Verify the if the total record is 20
    @Test
    public void verifyTotal(){
        response.body("size()",equalTo(20));
    }

//     2. Verify the if the name of id = 5487 is equal to ”Hamsini Trivedi”
    @Test
    public void verifyName(){
       response.body("findAll{it.id==4913}.name",hasItem("Dr. Suryakanta Bandopadhyay"));
    }
//  3. Check the single ‘Name’ in the Array list ()
@Test
public void verifySingleName(){

    response.body("[2].name",equalTo("Chandni Joshi"));

}
//4. Check the multiple ‘Names’ in the ArrayList
@Test
public void verifyMultipleName(){

    response.body("name",hasItems("Chandni Joshi","Bhagvan Joshi"));

}

//5. Verify the email of userid = 5240 is equal “aanjaneya_banerjee@bauch-luettgen.com”
@Test
public void verifyEmailId(){
     response.body("findAll{it.id==4910}.email",hasItem("joshi_bhagvan@hirthe.info"));


}
//  6. Verify the status is “Active” of user name is “Chandi Deshpande”
@Test
public void verifystatus(){
    response.body("findAll{it.name=='Rageswari Ahluwalia'}.status",hasItem("active"));


}
// 7. Verify the Gender = male of user name is Chaitan Chaturvedi
@Test
public void verifyGender(){
    response.body("findAll{it.name=='Yogendra Bhattacharya'}.gender",hasItem("male"));


}
}
