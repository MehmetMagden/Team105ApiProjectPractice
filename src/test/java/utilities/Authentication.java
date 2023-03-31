package utilities;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Authentication {

    private static RequestSpecification spec;


    public static String generateToken(){

        spec = new RequestSpecBuilder().setBaseUri(ConfigReader.getProperty("base_url")).build();

        spec.pathParams("pp1","api","pp2","login");

        Map<String,Object> dataCredentials = new HashMap<>();

        dataCredentials.put("email",ConfigReader.getProperty("email"));  //"email",admin200@trendlifebuy.com
        dataCredentials.put("password",ConfigReader.getProperty("password"));
/*

 POST : https://trendlifebuy.com/api/login

 {
            "email": "admin200@trendlifebuy.com",
            "password": "Trendlife123"

  }

 */




        Response response = given()
                .contentType(ContentType.JSON)
                .spec(spec)
                .when()
                .body(dataCredentials)
                .post("{pp1}/{pp2}");


        //System.out.println("response");

        //System.out.println("Authentication generate token");
       // response.prettyPrint();
        //System.out.println("Authentication generate token bitti");
        /*
        {
    "token": "1309|C02fadh9Z9pvM5hVWNR09LVgRWKul9H2lkvfbc1F",
    "message": "Successfully logged In"}
         */

        JsonPath jsonResponse = response.jsonPath();

        String token = jsonResponse.getString("token");

        return token;
    }

}
