@API015
Feature: As an administrator, I want to be able to access customer addresses registered in the system, add new addresses and update existing addresses via API connection.

  @API015_TC006
  Scenario:

    * Api kullanicisi "api,profile,addressUpdate,75" path parametreleri set eder
    * Api kullanicisi addressUpdate PATCH yapmak icin body hazirlar
    * Api kullanicisi PATCH yaparak response kaydeder
    * Api user tests that returned status code after PATCH process is 404
    * Api user tests that after PATCH process message informatin in response bosy is "not found"