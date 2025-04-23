

import io.restassured.RestAssured;
import io.restassured.response.Response;
import pojo.Location;
import pojo.addPlace;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

public class SerializeTest {

	public static void main(String[] args) {
		RestAssured.baseURI="https://rahulshettyacademy.com";
		
		addPlace p = new addPlace();
		p.setAccuracy(50);
		p.setAddress("20, side layout, pojo class01");
		p.setLanguage("PT-BR");
		p.setPhone_number("(11)123 123 123");
		p.setWebsite("https://rahulshettyacademy.com");
		p.setName("Frontline house");
		
		List<String> myList = new ArrayList<String>();
		myList.add("Shoe Park");
		myList.add("shop");
		p.setTypes(myList);
		
		Location l = new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		p.setLocation(l);
		
		Response response = given().queryParam("key", "qaclick123").body(p)
		.when().post("/maps/api/place/add/json")
		.then().assertThat().statusCode(200).extract().response();
		
		String responseString = response.asString();
		System.out.println(responseString);

	}

}
