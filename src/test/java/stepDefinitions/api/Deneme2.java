package stepDefinitions.api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Deneme2 {



static RequestSpecification spec;

@Test
public void test01(){
   spec= new RequestSpecBuilder().setBaseUri("https://www.heallifehospital.com").build();

   String url = "https://www.heallifehospital.com";
   String token = "3SB3sDsWJbIQQL9ZkjmQoCHoiHoSA8";


      spec.pathParams("pp1","api","pp2","ipdList");
      //Response response = given().when().get(url);
      String fullPath = "/{pp1}/{pp2}";

    Response  response = given()
            //.header("Content-Type","application/json")
            .contentType(ContentType.JSON)
            .spec(spec)
            .headers(
                    "Authorization",
                    "Bearer " + token,
                    "Content-Type",
                    ContentType.JSON,
                    "Accept",
                    ContentType.JSON)
            //.header("Content-Type","application/json")
            .when()

            .log().all()
            .get(fullPath);

//   Response response = given().spec(specDummy).when().get("/{pp1}/{pp2}/{pp3}/{pp4}");
//
   response.prettyPrint();

   response
           .then()
           .assertThat()
           .statusCode(200)
           .body("message", Matchers.equalTo("Success"));


   Map<String, Object> expected = new HashMap<String, Object>();
   expected.put("id", "13");
   expected.put("patient_name", "Marsh Martin");

   response.then().assertThat().body("lists", Matchers.hasItem(expected));

}



}
