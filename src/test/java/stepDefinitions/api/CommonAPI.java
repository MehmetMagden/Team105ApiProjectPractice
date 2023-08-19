package stepDefinitions.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import pojos.AdminCouponPojo;
import pojos.CouponDetail;
import utilities.ConfigReader;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    AdminCouponPojo respPojo;
    AdminCouponPojo expPojo;


    @Given("Api kullanicisi sistemde {string} olarak giris yapar")
    public void api_kullanicisi_sistemde_olarak_giris_yapar(String authenticatication) {
        if( authenticatication.equals("admin")){

            spec = new RequestSpecBuilder().setBaseUri(ConfigReader.getProperty("base_url")).build();

            spec.pathParams("pp1","api","pp2","login");

            Map<String,Object> dataCredentials = new HashMap<>();

            dataCredentials.put("email",ConfigReader.getProperty("adminEmail"));  //"email",admin200@trendlifebuy.com
            dataCredentials.put("password",ConfigReader.getProperty("password"));

            Response response = given()
                    .contentType(ContentType.JSON)
                    .spec(spec)
                    .when()
                    .body(dataCredentials)
                    .post("{pp1}/{pp2}");

            JsonPath jsonResponse = response.jsonPath();


            String token = jsonResponse.getString("token");

            HooksAPI.token= token;


        }else {

            spec = new RequestSpecBuilder().setBaseUri(ConfigReader.getProperty("base_url")).build();

            spec.pathParams("pp1","api","pp2","login");

            Map<String,Object> dataCredentials = new HashMap<>();

            dataCredentials.put("email",ConfigReader.getProperty("userEmail"));  //"email",admin200@trendlifebuy.com
            dataCredentials.put("password",ConfigReader.getProperty("password"));

            Response response = given()
                    .contentType(ContentType.JSON)
                    .spec(spec)
                    .when()
                    .body(dataCredentials)
                    .post("{pp1}/{pp2}");

            JsonPath jsonResponse = response.jsonPath();

            String token = jsonResponse.getString("token");

            HooksAPI.token= token;



        }

    }


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
                .accept(ContentType.JSON)
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
            System.out.println(responseBody);
        }

    }
    @Given("Api user tests that returned status code after PATCH process is {int}")
    public void api_user_tests_that_returned_status_code_after_patch_process_is(Integer statuscode) {
        if (statuscode==404) {
            Assert.assertTrue(responseBody.contains("status code: 404"));
        }else {
             response.then().assertThat().statusCode(statuscode);
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

    @Given("Api kullanicisi sadece id bilgisi iceren ve idsi {int} olan bir body hazirlar")
    public void api_kullanicisi_sadece_id_bilgisi_iceren_ve_idsi_olan_bir_body_hazirlar(Integer int1) {

        HooksAPI.reqBody.put("id",2);
    }



    @Given("Api user donen body icindeki degerlerini test eder")
    public void api_user_donen_body_icindeki_degerlerini_test_eder() throws JsonProcessingException {

         respPojo = response.as(AdminCouponPojo.class);

         Assert.assertEquals(expPojo.getMessage(),respPojo.getMessage());
         Assert.assertEquals(expPojo.getAdditionalProperties(),respPojo.getAdditionalProperties());
         Assert.assertEquals(expPojo.getCouponDetails().get(0).getId(),respPojo.getCouponDetails().get(0).getId());
         Assert.assertEquals(expPojo.getCouponDetails().get(0).getTitle(),respPojo.getCouponDetails().get(0).getTitle());
         Assert.assertEquals(expPojo.getCouponDetails().get(0).getCouponCode(),respPojo.getCouponDetails().get(0).getCouponCode());
         Assert.assertEquals(expPojo.getCouponDetails().get(0).getCouponType(),respPojo.getCouponDetails().get(0).getCouponType());
         Assert.assertEquals(expPojo.getCouponDetails().get(0).getStartDate(),respPojo.getCouponDetails().get(0).getStartDate());
         Assert.assertEquals(expPojo.getCouponDetails().get(0).getEndDate(),respPojo.getCouponDetails().get(0).getEndDate());
         Assert.assertEquals(expPojo.getCouponDetails().get(0).getDiscount(),respPojo.getCouponDetails().get(0).getDiscount());
         Assert.assertEquals(expPojo.getCouponDetails().get(0).getDiscountType(),respPojo.getCouponDetails().get(0).getDiscountType());
         Assert.assertEquals(expPojo.getCouponDetails().get(0).getMinimumShopping(),respPojo.getCouponDetails().get(0).getMinimumShopping());
         Assert.assertEquals(expPojo.getCouponDetails().get(0).getMaximumDiscount(),respPojo.getCouponDetails().get(0).getMaximumDiscount());
         Assert.assertEquals(expPojo.getCouponDetails().get(0).getCreatedBy(),respPojo.getCouponDetails().get(0).getCreatedBy());
         Assert.assertEquals(expPojo.getCouponDetails().get(0).getUpdatedBy(),respPojo.getCouponDetails().get(0).getUpdatedBy());
         Assert.assertEquals(expPojo.getCouponDetails().get(0).getIsExpire(),respPojo.getCouponDetails().get(0).getIsExpire());
         Assert.assertEquals(expPojo.getCouponDetails().get(0).getIsMultipleBuy(),respPojo.getCouponDetails().get(0).getIsMultipleBuy());
         Assert.assertEquals(expPojo.getCouponDetails().get(0).getCreatedAt(),respPojo.getCouponDetails().get(0).getCreatedAt());
         Assert.assertEquals(expPojo.getCouponDetails().get(0).getUpdatedAt(),respPojo.getCouponDetails().get(0).getUpdatedAt());





//        ObjectMapper objectMapper = new ObjectMapper();
//
//        AdminCouponPojo actualResponse =objectMapper.readValue(response.getBody().asString(),AdminCouponPojo.class);
//
//         Assert.assertEquals(expPojo,actualResponse);

         //pojos.AdminCouponPojo<AdminCouponPojo{couponDetails=[CouponDetail{id=2, title='Orderder', couponCode='ordered', couponType=2, startDate='2021-02-26', endDate='2025-03-30', discount=10, discountType=0, minimumShopping=1, maximumDiscount=null, createdBy=740, updatedBy=740, isExpire=0, isMultipleBuy=1, createdAt='2021-11-16T18:59:20.000000Z', updatedAt='2023-03-29T22:04:18.000000Z', additionalProperties={}}], message='success', additionalProperties={}}
         //pojos.AdminCouponPojo<AdminCouponPojo{couponDetails=[CouponDetail{id=2, title='Orderder', couponCode='ordered', couponType=2, startDate='2021-02-26', endDate='2025-03-30', discount=10, discountType=0, minimumShopping=1, maximumDiscount=null, createdBy=740, updatedBy=740, isExpire=0, isMultipleBuy=1, createdAt='2021-11-16T18:59:20.000000Z', updatedAt='2023-03-29T22:04:18.000000Z', additionalProperties={}}], message='success', additionalProperties={}}
    }

    @Given("Api user beklenen degerler ile AdminCouponPojo olusturur")
    public void api_user_beklenen_degerler_ile_admin_coupon_pojo_olusturur() {

         expPojo = new AdminCouponPojo();

        CouponDetail expCouponDetail = new CouponDetail();

        expCouponDetail.setId(2);
        expCouponDetail.setTitle("Orderder");
        expCouponDetail.setCouponCode("ordered");
        expCouponDetail.setCouponType(2);
        expCouponDetail.setStartDate("2021-02-26");
        expCouponDetail.setEndDate("2025-03-30");
        expCouponDetail.setDiscount(10);
        expCouponDetail.setDiscountType(0);
        expCouponDetail.setMinimumShopping(1);
        expCouponDetail.setMaximumDiscount(null);
        expCouponDetail.setCreatedBy(870);
        expCouponDetail.setUpdatedBy(870);
        expCouponDetail.setIsExpire(0);
        expCouponDetail.setIsMultipleBuy(1);
        expCouponDetail.setCreatedAt("2021-11-16T18:59:20.000000Z");
        expCouponDetail.setUpdatedAt("2023-04-01T18:44:13.000000Z");

        List<CouponDetail> couponDetails = new ArrayList<>();
        couponDetails.add(expCouponDetail);
        expPojo.setCouponDetails(couponDetails);
        expPojo.setMessage("success");

        System.out.println(expPojo);

    }







}
