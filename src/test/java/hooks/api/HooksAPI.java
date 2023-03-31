package hooks.api;

import io.cucumber.java.Before;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import utilities.Authentication;
import utilities.ConfigReader;

public class HooksAPI {

    public static RequestSpecification spec;
    public static String token;
    public static JSONObject reqBody = new JSONObject();

    @Before (order = 0)
    public void setUpApi(){
        spec = new RequestSpecBuilder().setBaseUri(ConfigReader.getProperty("base_url")).build(); //https://trendlifebuy.com/
    }

    @Before (order = 1)
    public void beforeGenerateToken(){
        token = Authentication.generateToken();
        System.out.println("tokenhook = " + token);  //tokenhook = 1309|C02fadh9Z9pvM5hVWNR09LVgRWKul9H2lkvfbc1F
    }



}
