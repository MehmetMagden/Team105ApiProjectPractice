@APIUS015
Feature: As an administrator, I want to be able to access customer addresses registered in the system, add new addresses and update existing addresses via API connection.

  @APIUS015_TC001
  Scenario: When a GET request body containing valid authorization information is sent to the /api/profile/allAddressList endpoint, it should be verified that the status code returned is 200 and the message information is "success".
    * Api kullanicisi "api,profile,allAddressList" path parametreleri set eder
    * Api kullanicisi response kaydeder
    * Api kullanicisi donen status kodun 200 oldugunu test eder
    * Api kullanicisi donen "message" information 'nun "success" oldugunu test eder

  @APIUS015_TC005
  Scenario: When a PATCH request body containing valid authorization information and required data is sent to the /api/profile/addressUpdate/(x) endpoint, it should be verified that the status code returned is 202 and the message information in the response body is "address updated successfully".

    * Api kullanicisi "api,profile,addressUpdate,12" path parametreleri set eder
    * Api kullanicisi addressUpdate PATCH yapmak icin valid body hazirlar
    * Api kullanicisi PATCH yaparak response kaydeder
    * Api user tests that returned status code after PATCH process is 202
    * Api user tests that after PATCH process message informatin in response bosy is "address updated successfully"

  @APIUS015_TC006
  Scenario: When a PATCH request body containing valid authorization information and incorrect data is sent to the /api/profile/addressUpdate/(x) endpoint, it should be verified that the status code returned is 404 and the message information in the response body is "address not found".

    * Api kullanicisi "api,profile,addressUpdate,75" path parametreleri set eder
    * Api kullanicisi addressUpdate PATCH yapmak icin body hazirlar
    * Api kullanicisi PATCH yaparak response kaydeder
    * Api user tests that returned status code after PATCH process is 404
    * Api user tests that after PATCH process message informatin in response bosy is "not found"



