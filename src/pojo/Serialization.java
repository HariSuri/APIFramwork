package pojo;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class Serialization {
    public static void main(String[] args){
        RestAssured.baseURI = "https://rahulshettyacademy.com";
        AddPlace ap = new AddPlace();
        ap.setAccuracy(50);
        ap.setAddress("Krpuram");
        ap.setLanguage("Kannada");
        Location lc = new Location();
        lc.setLat(-38.383494);
        lc.setLng(33.427362);
        ap.setLocation(lc);
        ap.setName("Suresh");
        ap.setPhone_number("(+91) 983 893 3937");
        ap.setWebsite("http://google.com");
        List<String> list = new ArrayList<>();
        list.add("shoe park");
        list.add("shop");
        ap.setTypes(list);

        Response res = given().queryParam("key","qaclick123")
                .body(ap)
                .when().post("/maps/api/place/add/json")
                .then().assertThat().statusCode(200).extract().response();
        System.out.println("Response is :"+res.asString());
    }
}