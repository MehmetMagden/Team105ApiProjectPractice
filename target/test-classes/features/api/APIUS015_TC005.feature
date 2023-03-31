@API015
Feature: Admin Address

  @API015_005
  Scenario:

    * Api kullanicisi "api,profile,addressUpdate,75" path parametreleri set eder
    * Api kullanicisi addressUpdate PATCH yapmak icin body hazirlar
    * Api kullanicisi PATCH yaparak response kaydeder
    * Api user tests that returned status code after PATCH process is 404
    * Api user tests that after PATCH process message informatin in response bosy is "not found"