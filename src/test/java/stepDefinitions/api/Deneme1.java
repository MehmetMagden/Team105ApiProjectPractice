package stepDefinitions.api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Deneme1 {



static RequestSpecification spec;

@Test
public void test01(){
   spec= new RequestSpecBuilder().setBaseUri("https://www.heallifehospital.com").build();

   String url = "https://www.heallifehospital.com";
   String token = "iDUdxuXcxgs5TO5bcRhOOL9jSOrgGG";


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
//   response.prettyPrint();

}



}
