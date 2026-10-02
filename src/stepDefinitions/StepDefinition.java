package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import java.util.ArrayList;
import java.util.List;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import pojo.AddPlace;
import pojo.Location;
import pojo.Serialization;

public class StepDefinition {

        RequestSpecification req;
        Response res;
        String placeid;
    @Given("Add place api request payload {string} {string} {string}")
    public void add_place_api_request_payload(String name, String language, String address) {
        RestAssured.baseURI = "https://rahulshettyacademy.com";
        AddPlace ap = new AddPlace();
        ap.setAccuracy(50);
        ap.setAddress(address);
        ap.setLanguage(language);
        Location lc = new Location();
        lc.setLat(-38.383494);
        lc.setLng(33.427362);
        ap.setLocation(lc);
        ap.setName(name);
        ap.setPhone_number("(+91) 983 893 3937");
        ap.setWebsite("http://google.com");
        List<String> list = new ArrayList<>();
        list.add("shoe park");
        list.add("shop");
        ap.setTypes(list);

        req = given().queryParam("key","qaclick123")
                .body(ap);

    }
    @When("user calls {string} with post request")
    public void user_calls_with_post_request(String string) {
         res = req.when().post("/maps/api/place/add/json")
                 .then().assertThat().statusCode(200).extract().response();

    }
    @Then("api call is success with {int} status code")
    public void api_call_is_success_with_status_code(int expstatuscode) {

        assertEquals(res.getStatusCode(),expstatuscode);
    }
    @Then("{string} in response is {string}")
    public void in_response_is(String keyValue, String expectedValue) {
    String resp=res.asString();
    System.out.println("response is :"+resp);
    JsonPath js = new JsonPath(resp);
        String scope =js.get(keyValue).toString();
        placeid = js.getString("place_id");
        System.out.println("place_id is: "+placeid);
        assertEquals(js.get(keyValue).toString(), expectedValue);
    }


    @And("Verify placeid in GET resonse against {string}")
    public void verifyPlaceidInGETResonseAgainst(String name) {
       String getresponse = given().queryParam("key","qaclick123")
                .queryParam("place_id",placeid)
                .when().get("/maps/api/place/get/json")
                .then().extract().response().asString();
        System.out.println("getapi response"+getresponse);
        JsonPath js1 = new JsonPath(getresponse);
       String actname = js1.getString("name");
       System.out.println("actual name: "+name);
      assertEquals(name,actname);


    }
}
