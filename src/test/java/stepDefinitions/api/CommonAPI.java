package stepDefinitions.api;

import hooks.api.HooksAPI;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Assert;
import utilities.ConfigReader;


import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasEntry;

public class CommonAPI {


    public static String fullPath;
    Response response;

    /*
       given().queryParam("CUSTOMER_ID","68195")
           .queryParam("PASSWORD","1234!")
           .queryParam("Account_No","1")
           .when().get("http://demo.guru99.com/V4/sinkministatement.php").then().log()
           .body();
     */

    /*
            response.
                then().
                assertThat().
                statusCode(201).
                contentType("application/json").
                body("title", equalTo("API")).
                body("userId",lessThan(100)).
                body("body",Matchers.containsString("API"));
     */


    @Given("Api kullanicisi {string} path parametreleri set eder")
    public void api_kullanicisi_path_parametreleri_set_eder(String rawPaths) {
        // rawPaths = "api,profile,allCountries"

      //  spec.pathParams("pp1","api","pp2","login");
      //  Response response = given().when().get("{pp1}/{pp2}");
        
        String [] paths = rawPaths.split(",");
        StringBuilder tempPath = new StringBuilder("{");

        for (int i = 0; i < paths.length; i++) {
            String key = "pp" + i; //pp0 //pp1
            String value = paths[i].trim(); // api  //profile
            HooksAPI.spec.pathParam(key,value);
            
            tempPath.append(key + "}/{");
        }
        tempPath.deleteCharAt(tempPath.lastIndexOf("{"));
        tempPath.deleteCharAt(tempPath.lastIndexOf("/"));

        //"api,profile,allCountries"
        //System.out.println("tempPath = " + tempPath); //tempPath = {pp0}/{pp1}/{pp2}

        fullPath = tempPath.toString();
    }



    @Given("Api kullanicisi email ve password girer.")
    public void api_kullanicisi_email_ve_password_girer() {

        String email = ConfigReader.getProperty("email");
        String password = ConfigReader.getProperty("password");

        /*
        {
             "email": "admin@gmail.com",
              "password": "123123123"
        }
         */

        JSONObject reqBody = new JSONObject();

        reqBody.put("email",email);
        reqBody.put("password",password);

        response = given()
                .contentType(ContentType.JSON)
                .spec(HooksAPI.spec)
                .when()
                .body(reqBody.toString())
                .post(fullPath);

        response.prettyPrint();

    }

    @Given("Api kullanicisi response kaydeder")
    public void api_kullanicisi_response_kaydeder() {

        response = given()
                .headers("Authorization","Bearer " + HooksAPI.token)
                .contentType(ContentType.JSON)
                .spec(HooksAPI.spec)
                .when()
                .get(fullPath);


        response.prettyPrint();

        /*
        {
    "addresses": [
        {
            "id": 1,
            "code": "AF",
            "name": "Afghanistan"
        },
        {...
         */

    }

    @Given("Api kullanicisi donen status kodun {int} oldugunu test eder")
    public void api_kullanicisi_donen_status_kodun_oldugunu_test_eder(Integer expStatusCode) {
        response.then().assertThat().statusCode(expStatusCode);
    }


//    @Given("Api kullanicisi donen {string} 'nun {string} oldugunu test eder")
//    public void api_kullanicisi_donen_nun_oldugunu_test_eder(String actualData, String expMessage) {
//
//        JSONObject expBody = new JSONObject();
//        expBody.put(actualData,expMessage);
//
//        JsonPath resJsonPath = response.jsonPath();
//
//
//        Assert.assertEquals(expBody.get(actualData),resJsonPath.get(actualData));
//    }

    @Given("Api kullanicisi donen {string} information 'nun {string} oldugunu test eder")
    public void api_kullanicisi_donen_information_nun_oldugunu_test_eder(String actualData, String expMessage) {
        JSONObject expBody = new JSONObject();
        expBody.put(actualData,expMessage);

        JsonPath resJsonPath = response.jsonPath();


        Assert.assertEquals(expBody.get(actualData),resJsonPath.get(actualData));
    }

    @Given("Api kullanicisi POST yapmak icin body hazirlar")
    public void api_kullanicisi_post_yapmak_icin_body_hazirlar() {




    }

    public static String responseBody ;
    static RequestSpecification spec = new RequestSpecBuilder().setBaseUri("https://trendlifebuy.com/api/profile/addressUpdate/75").build();

    @Given("Api kullanicisi POST yaparak response kaydeder")
    public void api_kullanicisi_post_yaparak_response_kaydeder() {





//        System.out.println(HooksAPI.reqBody.toString());
//
//        RestAssured.baseURI =ConfigReader.getProperty("base_url")+"/api/profile/addressUpdate/75";
//        //RestAssured.baseURI ="https://trendlifebuy.com/api/profile/addressUpdatee/75";
//
//        RequestSpecification request = RestAssured.given();
//        request.spec(HooksAPI.spec);
//
//        request.header("Authorization","Bearer "+HooksAPI.token)
//                .header("Content-Type","application/json");
//
//       response = request.body(HooksAPI.reqBody.toString()).log().all().patch(fullPath);

//        System.out.println("Bakalim neler ogrenebiliyoruz");
//        System.out.println(response.getStatusCode());
//        //System.out.println(response.body().asString());


        //.prettyPrint();



    }
    @Given("Api user tests that returned status code is {int}")
    public void api_user_tests_that_returned_status_code_is(Integer int1) {


        Assert.assertTrue(responseBody.contains("status code: 404"));

        /*
        int statusCode= given().queryParam("CUSTOMER_ID","68195")
           .queryParam("PASSWORD","1234!")
           .queryParam("Account_No","1")
           .when().get("http://demo.guru99.com/V4/sinkministatement.php").getStatusCode();
         */

    }
    @Given("Api user tests that {string} informatin in response bosy is {string}")
    public void api_user_tests_that_informatin_in_response_bosy_is(String string, String string2) {

        Assert.assertTrue(responseBody.contains("Not Found"));

    }

    @Given("Api kullanicisi addressUpdate PATCH yapmak icin body hazirlar")
    public void api_kullanicisi_address_update_patch_yapmak_icin_body_hazirlar() {

                /*
          "city": "labore",
  "state": "omnis",
  "country": "unde",
  "postal_code": "saepe",
  "address_type":"11"
         */
        HooksAPI.reqBody.put("customer_id",16);
        HooksAPI.reqBody.put("name","eos");
        HooksAPI.reqBody.put("email","d@d.com");
        HooksAPI.reqBody.put("address","11");
        HooksAPI.reqBody.put("phone","ullam");
        HooksAPI.reqBody.put("city","labore");
        HooksAPI.reqBody.put("state","omnis");
        HooksAPI.reqBody.put("country","unde");
        HooksAPI.reqBody.put("postal_code","saepe");
        HooksAPI.reqBody.put("address_type","11");

       // System.out.println(HooksAPI.reqBody);

    }
    @Given("Api kullanicisi PATCH yaparak response kaydeder")
    public void api_kullanicisi_patch_yaparak_response_kaydeder() {

        try {


            response = given()
                    //.header("Content-Type","application/json")
                    .contentType(ContentType.JSON)
                    .spec(HooksAPI.spec)
                    .headers(
                            "Authorization",
                            "Bearer " + HooksAPI.token,
                            "Content-Type",
                            ContentType.JSON,
                            "Accept",
                            ContentType.JSON)
                    //.header("Content-Type","application/json")
                    .when()
                    .body(HooksAPI.reqBody.toString())
                    .log().all()
                    .patch(fullPath);


        } catch (Exception e) {
            responseBody=e.getMessage();
        }

    }
    @Given("Api user tests that returned status code after PATCH process is {int}")
    public void api_user_tests_that_returned_status_code_after_patch_process_is(Integer statuscode) {
        if (statuscode==404) {
            Assert.assertTrue(responseBody.contains("status code: 404"));
        }else {
             response.then().statusCode(statuscode);
        }

    }
    @Given("Api user tests that after PATCH process message informatin in response bosy is {string}")
    public void api_user_tests_that_after_patch_process_message_informatin_in_response_bosy_is(String returnedMessage) {
        if (returnedMessage.equalsIgnoreCase("Not Found")) {
            Assert.assertTrue(responseBody.contains("Not Found"));
        }else {
            response.then().assertThat().body("message",equalTo(returnedMessage));
        }

    }


    @Given("Api kullanicisi country, state, city bilgileri icin gerekli id {int} ile GET body hazirlar")
    public void api_kullanicisi_country_state_city_bilgileri_icin_gerekli_id_ile_get_body_hazirlar(Integer id) {
        HooksAPI.reqBody.put("state_id", id);
    }
    @Given("Api kullanicisi GET yaparak response kaydeder")
    public void api_kullanicisi_get_yaparak_response_kaydeder() {

        try {


            response = given()
                    //.header("Content-Type","application/json")
                    .contentType(ContentType.JSON)
                    .spec(HooksAPI.spec)
                    .headers(
                            "Authorization",
                            "Bearer " + HooksAPI.token,
                            "Content-Type",
                            ContentType.JSON,
                            "Accept",
                            ContentType.JSON)
                    //.header("Content-Type","application/json")
                    .when()
                    .body(HooksAPI.reqBody.toString())
                    .log().all()
                    .get(fullPath);


        } catch (Exception e) {
            responseBody=e.getMessage();
        }

    }
    @Given("Api kullanicisi donen responda idsi {int} olan veri için {string} infosunun {string} oldugunu test eder")
    public void api_kullanicisi_donen_responda_idsi_olan_veri_için_infosunun_oldugunu_test_eder(Integer id, String name, String isim) {

        response.prettyPrint();
//        HashMap<String,Object> respMap = response.as(HashMap.class);
//        System.out.println("respMap = " + respMap);
//
//        System.out.println(respMap.get("addresses"));

//        response.then()
//                .assertThat()
//                .body("addresses",
//                        hasItem(
//                                allOf(
//                                        //hasEntry("id",53), // inanılmaz ama value için sayı  kabul etmiyor. illa string olacak
//                                        hasEntry(name, isim)
//                                )
//                        )
//                );

        Map<String, Object> expected = new HashMap<String, Object>();
        expected.put("id", id);
        expected.put(name, isim);

        response.then().assertThat().body("addresses", Matchers.hasItem(expected));

    }

    @Given("Api kullanicisi addressUpdate PATCH yapmak icin valid body hazirlar")
    public void api_kullanicisi_address_update_patch_yapmak_icin_valid_body_hazirlar() {

        HooksAPI.reqBody.put("customer_id",38);
        HooksAPI.reqBody.put("name","eos");
        HooksAPI.reqBody.put("email","d@d.com");
        HooksAPI.reqBody.put("address","11");
        HooksAPI.reqBody.put("phone","ullam");
        HooksAPI.reqBody.put("city","labore");
        HooksAPI.reqBody.put("state","omnis");
        HooksAPI.reqBody.put("country","unde");
        HooksAPI.reqBody.put("postal_code","saepe");
        HooksAPI.reqBody.put("address_type","11");

        // System.out.println(HooksAPI.reqBody);

    }

    @Given("Api kullanicisi customerAddressUpdate PATCH yapmak icin valid body hazirlar")
    public void api_kullanicisi_customer_address_update_patch_yapmak_icin_valid_body_hazirlar() {

        HooksAPI.reqBody.put("name","eos");
        HooksAPI.reqBody.put("email","d@d.com");
        HooksAPI.reqBody.put("address","11");
        HooksAPI.reqBody.put("phone","ullam");
        HooksAPI.reqBody.put("city","labore");
        HooksAPI.reqBody.put("state","omnis");
        HooksAPI.reqBody.put("country","unde");
        HooksAPI.reqBody.put("postal_code","saepe");
        HooksAPI.reqBody.put("address_type","11");
    }


}
