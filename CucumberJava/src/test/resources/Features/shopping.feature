Feature: feature to test search functionality and retrieve product info

  @Test1
  Scenario: Get product info from amazon
    Given browser is open
    And user is on home page
    When user search up product
    And clicks on product link
    Then user is successfully navigated to product page
    
   @Test2
   Scenario: Filter product before searching
   	Given browser is open
   	And user is on home page
   	When user search up product
   	And enters refined search
   	Then user is displayed with better matching products
