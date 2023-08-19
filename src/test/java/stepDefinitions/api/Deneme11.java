package stepDefinitions.api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Deneme11 {



static RequestSpecification spec;

@Test
public void test01(){
   spec= new RequestSpecBuilder().setBaseUri("https://qa.wonderworldcollege.com").build();

   //String url = "https://qa.wonderworldcollege.com";
   String token = "mcQo4NwTnRGctreDZTtS44oRT2hxip";


      spec.pathParams("pp1","api","pp2","alumniEventsByDateRange");
      //Response response = given().when().get(url);
      String fullPath = "/{pp1}/{pp2}";


      // örnek body :
//    {
//           "start": "2021-01-14 00:00:00",
//           "end": "2023-03-15 23:59:00"
//     }
   // soru : start: "2021-01-14 00:00:00", end: "2023-03-15 23:59:00"
   JSONObject reqBody = new JSONObject();

   reqBody.put("start" , "2021-01-14 00:00:00");
   reqBody.put("end" , "2023-03-15 23:59:00");

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
            .body(reqBody.toString())
            .log().all()
            .post(fullPath);

//   Response response = given().spec(specDummy).when().get("/{pp1}/{pp2}/{pp3}/{pp4}");
//
   response.prettyPrint();



}



}
