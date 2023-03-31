@APIUS016 @API
Feature: APIUS016 As an administrator, I want to be able to access the country, state and city information and details of customer addresses registered in the system via API connection.

  @APIUS016_TC009
  Scenario: APIUS016_TC009 When the valid authorization information and state_id are entered in the /api/profile/stateCities endpoint and 3669 is sent in the Get request body, it should be verified that the "name" information of the city with the id number 40325 is "Kizilcahamam" in the response body.

    *Api kullanicisi sistemde "admin" olarak giris yapar
    * Api kullanicisi "api,profile,stateCities" path parametreleri set eder
    * Api kullanicisi country, state, city bilgileri icin gerekli id 3669 ile GET body hazirlar
    * Api kullanicisi GET yaparak response kaydeder
    * Api kullanicisi donen status kodun 200 oldugunu test eder
    * Api kullanicisi donen responda idsi 40325 olan veri için "name" infosunun "Kizilcahamam" oldugunu test eder