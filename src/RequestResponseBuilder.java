import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.Location;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class RequestResponseBuilder {
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

        RequestSpecification req= new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").setContentType(ContentType.JSON)
                .addQueryParam("key","qaclick").build();
        ResponseSpecification rsp= new ResponseSpecBuilder().expectStatusCode(201).build();

        Response res = given().spec(req)
                .body(ap)
                .when().post("/maps/api/place/add/json")
                .then().spec(rsp).extract().response();
        System.out.println("Response is :"+res.asString());
    }
}
