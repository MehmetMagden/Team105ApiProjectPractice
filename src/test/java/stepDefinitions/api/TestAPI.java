package stepDefinitions.api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import pojos.TestPojo;
import utilities.ReusableMethods;

import static io.restassured.RestAssured.given;

public class TestAPI {

    static RequestSpecification spec;

    @Test
    public void test01(){


        //api/alumniEventsByDateRange endpoint'ine gecerli authorization bilgileri
        // ve dogru datalar
        // body : (start: "2021-01-14 00:00:00", end: "2023-03-15 23:59:00") iceren bir POST body gönderildiğinde
        // dönen response bodydeki list içinde dataların  (

        // id'si = "1", olan veri içeriğindeki

        // test that
        // title: "Covid-19 Seminar",
        // event_for: "class",
        // session_id:"16",
        // class_id: "1",
        // section: "[\"1\"]",
        // from_date: "2021-03-01 00:00:00",
        // to_date: "2021-03-16 00:00:00",
        // note: "COVID-19 is the disease caused by a new coronavirus called SARS-CoV-2.  WHO first learned of this new virus on 31 December 2019, following a report of a cluster of cases of â€˜viral pneumoniaâ€™ in Wuhan, Peopleâ€™s Republic of China.",
        // photo: "",
        // is_active: "0",
        // event_notification_message: "GAVI'S RESPONSE\r\nRespond and protect: With COVID-19 now reported in almost all Gavi-eligible countries, the Vaccine Alliance is providing immediate funding to health systems, enabling countries to protect health care workers, perform vital surveillance and training, and purchase diagnostic tests.\r\n\r\nMaintain, restore and strengthen: Gavi will support countries to adapt and restart immunisation services, rebuild community trust and catch up those who have been missed both before and during the pandemic, while also investing in strengthening immunisation systems to be more resilient and responsive to the communities they serve.\r\n\r\nEnsure global equitable access: Gavi is co-leading COVAX, the global effort to securing a global response to COVID-19 that is effective and fair, using its unique expertise to help identify and rapidly accelerate development, production and delivery of COVID-19 vaccines so that anyone that needs them, gets them.",
        // show_onwebsite: "0"
        // ve created_at: "2021-03-23 02:54:29"

        //String token = "qpn82WsDiWYDPPVGJZUA2WwAKG4Bov";
        String token = ReusableMethods.authenticationAdmin();

        //  1 ) String token = "T6123sIlh8BC3REvY8NX4s0ne1vPrr"; // It has 30 mins limit
        //  2 )  we will create the link
        //      i) home page link
        //      ii ) setting  the parameters
        // 3 ) sending the request and storing the response
        // 4 ) Testing

//  i) home page link
        spec = new RequestSpecBuilder().setBaseUri("https://qa.wonderworldcollege.com").build();

//  ii ) setting  the parameters
        spec.pathParams("pp1","api","pp2","alumniEventsByDateRange");
        String fullPath = "/{pp1}/{pp2}";  //   /api/alumniEventsByDateRange

        /*
         ******* critical
         *
         * we need to add a body to this request
         * we will create a json OBject and we will add all info in it. And we will send the request with this body
         */


        /*
        {
        "start": "2021-01-14 00:00:00",
        "end": "2023-03-15 23:59:00"
}
         */
        JSONObject reqBody = new JSONObject(); // we created an empty body
        reqBody.put("start","2021-01-14 00:00:00");
        reqBody.put("end","2023-03-15 23:59:00");



        //3 ) sending the request and storing the response
        Response response = given()
                .contentType(ContentType.JSON)  // We are using JSON format
                .spec(spec)                     // creating a stage for link and parameters
                .headers(
                        "Authorization","Bearer "+ token,  // entered token in our request
                        "Content-Type",ContentType.JSON, // we are sending data by using JSON format
                        "Accept",ContentType.JSON               // We will only accept JSON format
                )

                .when()
                .body(reqBody.toString())  // if a body required in the request we need to write it here/
                .log().all()   // this will print what we are sending to API
                .post(fullPath);


        //response.prettyPrint();

        // 4 ) Testing

        //// To validate that the status code is 200
        //// and the response message is "Success"

//        response
//                .then()
//                .assertThat()
//                .statusCode(200)
//                .body("message", Matchers.equalTo("Success"))
//        ;

        TestPojo responseBody = response.as(TestPojo.class);

        // id'si = "1", olan veri içeriğindeki

        // test that
        // title: "Covid-19 Seminar",
        // event_for: "class",
        // session_id:"16",
        // class_id: "1",
        // section: "[\"1\"]",
        // from_date: "2021-03-01 00:00:00",
        // to_date: "2021-03-16 00:00:00",
        // note: "COVID-19 is the disease caused by a new coronavirus called SARS-CoV-2.  WHO first learned of this new virus on 31 December 2019, following a report of a cluster of cases of â€˜viral pneumoniaâ€™ in Wuhan, Peopleâ€™s Republic of China.",
        // photo: "",
        // is_active: "0",
        // event_notification_message: "GAVI'S RESPONSE\r\nRespond and protect: With COVID-19 now reported in almost all Gavi-eligible countries, the Vaccine Alliance is providing immediate funding to health systems, enabling countries to protect health care workers, perform vital surveillance and training, and purchase diagnostic tests.\r\n\r\nMaintain, restore and strengthen: Gavi will support countries to adapt and restart immunisation services, rebuild community trust and catch up those who have been missed both before and during the pandemic, while also investing in strengthening immunisation systems to be more resilient and responsive to the communities they serve.\r\n\r\nEnsure global equitable access: Gavi is co-leading COVAX, the global effort to securing a global response to COVID-19 that is effective and fair, using its unique expertise to help identify and rapidly accelerate development, production and delivery of COVID-19 vaccines so that anyone that needs them, gets them.",
        // show_onwebsite: "0"
        // ve created_at: "2021-03-23 02:54:29"



        for (int i = 0; i <responseBody.getLists().size() ; i++) {

            if (responseBody.getLists().get(i).getId().equals("1")){

                Assert.assertEquals(responseBody.getLists().get(i).getTitle(),"Covid-19 Seminar");
                Assert.assertEquals(responseBody.getLists().get(i).getEventFor(),"class");
                Assert.assertEquals(responseBody.getLists().get(i).getSessionId(),"16");
                Assert.assertEquals(responseBody.getLists().get(i).getClassId(),"1");
                //Assert.assertEquals(responseBody.getLists().get(i).getSection(),"[\\\"1\\\"]");
                Assert.assertEquals(responseBody.getLists().get(i).getFromDate(),"2021-03-01 00:00:00");
                Assert.assertEquals(responseBody.getLists().get(i).getToDate(),"2021-03-16 00:00:00");
                Assert.assertEquals(responseBody.getLists().get(i).getNote(),"COVID-19 is the disease caused by a new coronavirus called SARS-CoV-2.  WHO first learned of this new virus on 31 December 2019, following a report of a cluster of cases of â€˜viral pneumoniaâ€™ in Wuhan, Peopleâ€™s Republic of China.");
                Assert.assertEquals(responseBody.getLists().get(i).getPhoto(),"");
                Assert.assertEquals(responseBody.getLists().get(i).getIsActive(),"0");

            }

        }




    }


}
