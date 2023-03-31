@APIUS014 @API
Feature: APIUS014 As a user, I want to be able to register, view, update and delete addresses in my profile via API connection.

  @APIUS014_TC005
  Scenario: APIUS014_TC005 When a PATCH request body containing valid authorization information and required data is sent to the /api/profile/customerAddressUpdate/(x) endpoint, it should be verified that the status code returned is 202 and the message information in the response body is "address updated successfully".

    * Api kullanicisi sistemde "user" olarak giris yapar
    * Api kullanicisi "api,profile,customerAddressUpdate,389" path parametreleri set eder
    * Api kullanicisi customerAddressUpdate PATCH yapmak icin valid body hazirlar
    * Api kullanicisi PATCH yaparak response kaydeder
    * Api user tests that returned status code after PATCH process is 202
    * Api user tests that after PATCH process message informatin in response bosy is "address updated successfully"