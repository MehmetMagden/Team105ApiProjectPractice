@APIUS013
Feature: As an administrator, I want to be able to access and update coupon details via API link.

  @APIUS013_TC002
  Scenario: When a GET request body containing valid authorization information and required data (coupon id) is sent to the /api/coupon/couponDetails endpoint, the data in the response body (id, title ,coupon_code, coupon_type, start_date, end_date, discount, discount_type, minimum_shopping, maximum_discount) , created_by, updated_by, is_expire, is_multiple_buy, created_at, updated_at ) should be verified.
    * Api kullanicisi "api,coupon,couponDetails" path parametreleri set eder
    * Api kullanicisi sadece id bilgisi iceren ve idsi 2 olan bir body hazirlar
    * Api kullanicisi GET yaparak response kaydeder
    * Api user tests that returned status code after PATCH process is 200
    * Api user beklenen degerler ile AdminCouponPojo olusturur
    * Api user donen body icindeki degerlerini test eder


