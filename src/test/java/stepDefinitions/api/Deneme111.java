package stepDefinitions.api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.testng.annotations.Test;
import pojos.AdminCouponPojo;
import pojos.Deneme111Pojo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Deneme111 {



static RequestSpecification spec;

@Test
public void test01(){
   spec= new RequestSpecBuilder().setBaseUri("https://qa.wonderworldcollege.com").build();

   //String url = "https://qa.wonderworldcollege.com";
   String token = "Q5CmQaobWEPwQANoFGa7jnwrYKti0P";


      spec.pathParams("pp1","api","pp2","alumniEventsByDateRange");
      //Response response = given().when().get(url);
      String fullPath = "/{pp1}/{pp2}";


      // örnek req body :
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
  // response.prettyPrint();

   // expected :
   //id'si = "1", olan veri içeriğindeki
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
   // ve created_at: "2021-03-23 02:54:29" olduğu ) doğrulanmalı

   // Map<String, Object> expected = new HashMap<String, Object>();
   //        expected.put("id", id);
   //        expected.put(name, isim);
   //
   //        response.then().assertThat().body("addresses", Matchers.hasItem(expected));

    Map<String, Object> expected = new HashMap<String, Object>();
    //JSONObject expected = new JSONObject();
           expected.put("id", 1);
           expected.put("title", "Covid-19 Seminar");
           expected.put("event_for", "class");
           expected.put("session_id", 16);
           expected.put("class_id", 1);
           expected.put("section", "[\\\"1\\\"]");
           expected.put("from_date", "2021-03-01 00:00:00");
           expected.put("to_date", "2021-03-16 00:00:00");
           expected.put("note", "COVID-19 is the disease caused by a new coronavirus called SARS-CoV-2.  WHO first learned of this new virus on 31 December 2019, following a report of a cluster of cases of â€˜viral pneumoniaâ€™ in Wuhan, Peopleâ€™s Republic of China.");
           expected.put("photo", "");
           expected.put("is_active", 0);
//           expected.put("event_notification_message", "GAVI'S RESPONSE\r\nRespond and protect: With COVID-19 now reported in almost all Gavi-eligible countries, the Vaccine Alliance is providing immediate funding to health systems, enabling countries to protect health care workers, perform vital surveillance and training, and purchase diagnostic tests.\r\n\r\nMaintain, restore and strengthen: Gavi will support countries to adapt and restart immunisation services, rebuild community trust and catch up those who have been missed both before and during the pandemic, while also investing in strengthening immunisation systems to be more resilient and responsive to the communities they serve.\r\n\r\nEnsure global equitable access: Gavi is co-leading COVAX, the global effort to securing a global response to COVID-19 that is effective and fair, using its unique expertise to help identify and rapidly accelerate development, production and delivery of COVID-19 vaccines so that anyone that needs them, gets them.");
           expected.put("event_notification_message",   "GAVI'S RESPONSE\r\nRespond and protect: With COVID-19 now reported in almost all Gavi-eligible countries, the Vaccine Alliance is providing immediate funding to health systems, enabling countries to protect health care workers, perform vital surveillance and training, and purchase diagnostic tests.\r\n\r\nMaintain, restore and strengthen: Gavi will support countries to adapt and restart immunisation services, rebuild community trust and catch up those who have been missed both before and during the pandemic, while also investing in strengthening immunisation systems to be more resilient and responsive to the communities they serve.\r\n\r\nEnsure global equitable access: Gavi is co-leading COVAX, the global effort to securing a global response to COVID-19 that is effective and fair, using its unique expertise to help identify and rapidly accelerate development, production and delivery of COVID-19 vaccines so that anyone that needs them, gets them.");
           expected.put("show_onwebsite", "0");
           expected.put("created_at", "2021-03-23 02:54:29");


           //response.then().assertThat().body("lists", Matchers.hasItem(expected));

   // actual :
   // {
   //            "id": "1",
   //            "title": "Covid-19 Seminar",
   //            "event_for": "class",
   //            "session_id": "16",
   //            "class_id": "1",
   //            "section": "[\"1\"]",
   //            "from_date": "2021-03-01 00:00:00",
   //            "to_date": "2021-03-16 00:00:00",
   //            "note": "COVID-19 is the disease caused by a new coronavirus called SARS-CoV-2.  WHO first learned of this new virus on 31 December 2019, following a report of a cluster of cases of â€˜viral pneumoniaâ€™ in Wuhan, Peopleâ€™s Republic of China.",
   //            "photo": "",
   //            "is_active": "0",
   //            "event_notification_message": "GAVI'S RESPONSE\r\nRespond and protect: With COVID-19 now reported in almost all Gavi-eligible countries, the Vaccine Alliance is providing immediate funding to health systems, enabling countries to protect health care workers, perform vital surveillance and training, and purchase diagnostic tests.\r\n\r\nMaintain, restore and strengthen: Gavi will support countries to adapt and restart immunisation services, rebuild community trust and catch up those who have been missed both before and during the pandemic, while also investing in strengthening immunisation systems to be more resilient and responsive to the communities they serve.\r\n\r\nEnsure global equitable access: Gavi is co-leading COVAX, the global effort to securing a global response to COVID-19 that is effective and fair, using its unique expertise to help identify and rapidly accelerate development, production and delivery of COVID-19 vaccines so that anyone that needs them, gets them.",
   //            "show_onwebsite": "0",
   //            "created_at": "2021-03-23 02:54:29"
   //        }


/*
{
        "firstName":"John",
        "lastName":"doe",
        "address":{
                    "streetAddress":"naist street",
                    "city":"Nara",
                    "postalCode":"630-0192"
                    },
        "age":26,
        "phoneNumbers":[
                        {
                           "number":"0123-4567-8888",
                           "type":"iPhone"
                         },
                        {
                            "number":"0123-4567-8910",
                            "type":"home"
                         }
                        ]
          }


    System.out.println("Isim : " + kisiBilgisi.get("firstName"));
    System.out.println("Soyisim : " + kisiBilgisi.get("lastName"));
    System.out.println("Yas : " + kisiBilgisi.get("age"));
    System.out.println("Sokak adi : " + kisiBilgisi.getJSONObject("address").get("streetAddress"));
    System.out.println("Sehir : " + kisiBilgisi.getJSONObject("address").get("city"));
    System.out.println("Posta kodu : " + kisiBilgisi.getJSONObject("address").get("postalCode"));
    System.out.println("Tel no : " + kisiBilgisi
            .getJSONArray("phoneNumbers")
            .getJSONObject(0)
            .get("number"));
    System.out.println("Tel turu : " + kisiBilgisi
            .getJSONArray("phoneNumbers")
            .getJSONObject(0)
            .get("type"));
    System.out.println("Tel no : " + kisiBilgisi
            .getJSONArray("phoneNumbers")
            .getJSONObject(1)
            .get("number"));
    System.out.println("Tel turu : " + kisiBilgisi
            .getJSONArray("phoneNumbers")
            .getJSONObject(1)
            .get("type"));
 */

    JsonPath respJP = response.jsonPath();
    //System.out.println(respJP.prettyPrint());


    //System.out.println(respJP.get("$0").toString());
    //System.out.println(respJP.getJsonObject("lists").toString());

//    List<String> allList= (List<String>)respJP.getJsonObject("lists");
//    System.out.println(allList);
//    System.out.println("*************");
//    System.out.println(allList.get(1));
//
//    System.out.println("*************");
//    System.out.println(allList.get(0));

    //works
    //System.out.println(respJP.getList("lists").get(0));
    //System.out.println("***************");
    //System.out.println(expected);

    //.out.println(expected ==  respJP.getList("lists").get(0));

    //respPojo = response.as(AdminCouponPojo.class);

    Deneme111Pojo den = response.as(Deneme111Pojo.class);
    System.out.println("***********");
    System.out.println("***********");
    System.out.println("den" +den);




    //response.prettyPrint();
    System.out.println("***********");
    System.out.println("***********");
    //System.out.println(den.getLists().get(1).getId());

    // expected :
    //id'si = "1", olan veri içeriğindeki
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
    // ve created_at: "2021-03-23 02:54:29" olduğu ) doğrulanmalı

    for (int i = 0; i <den.getLists().size() ; i++) {

        if (den.getLists().get(i).getId().equals("1")){
            System.out.println(i);
            System.out.println("title"+den.getLists().get(i).getTitle());
            System.out.println("event"+den.getLists().get(i).getEventFor());
            System.out.println("session ID"+den.getLists().get(i).getSessionId());
            System.out.println("ClassID"+den.getLists().get(i).getClassId());
            System.out.println("Section"+den.getLists().get(i).getSection());
            System.out.println("FromDate"+den.getLists().get(i).getFromDate());
            System.out.println("To date"+den.getLists().get(i).getToDate());
            System.out.println("GetPhoto"+den.getLists().get(i).getPhoto());
            System.out.println("getSection"+den.getLists().get(i).getSection());
            System.out.println("IsActive"+den.getLists().get(i).getIsActive());

            System.out.println("/////");
            System.out.println("Message"+den.getLists().get(i).getEventNotificationMessage());
            System.out.println("Note"+den.getLists().get(i).getNote());
            System.out.println("Show"+den.getLists().get(i).getShowOnwebsite());
            System.out.println("CreatedAt"+den.getLists().get(i).getCreatedAt());


        }

    }
}



}
