package utilities;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ReusableMethods {
    static RequestSpecification spec;

    static public String authenticationAdmin(){



//  i) home page link
            spec = new RequestSpecBuilder().setBaseUri("https://qa.wonderworldcollege.com").build();

//  ii ) setting  the parameters
            spec.pathParams("pp1","api","pp2","getToken");
            String fullPath = "/{pp1}/{pp2}";  //   /api/getToken


/*
{
    "email": "resul.ciftci@admin.wonderworldcollege.com",
    "password": "wonderworld123"
}
 */

            JSONObject reqBody = new JSONObject(); // we created an empty body
            reqBody.put("email","resul.ciftci@admin.wonderworldcollege.com");
            reqBody.put("password","wonderworld123");



            //3 ) sending the request and storing the response
            Response response = given()
                    .contentType(ContentType.JSON)  // We are using JSON format
                    .spec(spec)                     // creating a stage for link and parameters
                    .headers(
                          //  "Authorization","Bearer "+ token,  // entered token in our request
                            "Content-Type",ContentType.JSON, // we are sending data by using JSON format
                            "Accept",ContentType.JSON               // We will only accept JSON format
                    )

                    .when()
                    .body(reqBody.toString())  // if a body required in the request we need to write it here/
                    .log().all()   // this will print what we are sending to API
                    .post(fullPath);

            //response.prettyPrint();

        JsonPath jpPath = response.jsonPath();

        //System.out.println(jpPath.getString("token"));
        String token = jpPath.getString("token");


        return token;

    }
}
