import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import pojo.LoginResponse;
import pojo.OrderDetail;
import pojo.Orders;
import pojo.LoginRequest;

import static io.restassured.RestAssured.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;

public class ECommerceAPITest {

	public static void main(String[] args) {
		
		RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
		.setContentType(ContentType.JSON).build();
		
		LoginRequest loginRequest = new LoginRequest();
		loginRequest.setUserEmail("felipealmeida@test.com");
		loginRequest.setUserPassword("Hello@123");
		
		
		RequestSpecification reqLogin = given().log().all().spec(req).body(loginRequest);
		LoginResponse loginResponse = reqLogin.when().post("api/ecom/auth/login").then().extract().response()
				.as(LoginResponse.class);
		System.out.println(loginResponse.getToken());
		String token = loginResponse.getToken();
		System.out.println(loginResponse.getUserId());
		String userId = loginResponse.getUserId();
		
		// Add Product
		
		RequestSpecification addProductBaseReq = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addHeader("Authorization", token)
				.build();
		
		RequestSpecification reqAddProduct = given().log().all().spec(addProductBaseReq)
		.param("productName", "Macbook Air")
		.param("productAddedBy", userId)
		.param("productCategory", "Technology")
		.param("productSubCategory", "notebooks")
		.param("productPrice", "11500")
		.param("productDescription", "Apple Macbook Air M2")
		.param("productFor", "nerds")
		.multiPart("productImage", new File("/Users/felipealmeida/Downloads/macbookairm2.png"));
		
		String addProductResponse = reqAddProduct.when().post("/api/ecom/product/add-product").andReturn()
		.then().log().all().extract().response().asString();
		JsonPath js = new JsonPath(addProductResponse);
		String productId = js.get("productId");
		
		// Create Order
		RequestSpecification createOrderBaseReq = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addHeader("Authorization", token).setContentType(ContentType.JSON)
				.build();
		
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setCountry("Brazil");
		orderDetail.setProductOrderedId(productId);
		
		List<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();
		orderDetailList.add(orderDetail);
		Orders orders = new Orders();
		orders.setOrders(orderDetailList);
		
		RequestSpecification createOrderReq = given().log().all().spec(createOrderBaseReq).body(orders);
		
		String responseAddOrder = createOrderReq.when().post("/api/ecom/order/create-order").then().log().all().extract().response().asString();
		System.out.println(responseAddOrder);
		
		// Delete Order
		
		RequestSpecification deleteProductBaseReq = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addHeader("Authorization", token).setContentType(ContentType.JSON)
				.build();
		
		RequestSpecification deleteProdReq = given().log().all().spec(deleteProductBaseReq).pathParam("productId", productId);
		
		String deleteProductResponse = deleteProdReq.when().delete("/api/ecom/product/delete-product/{productId}").then().log().all()
		.extract().response().asString();
		
		JsonPath js1 = new JsonPath(deleteProductResponse);
		String deletedMessage = js1.get("message");
		
		Assert.assertEquals("Product Deleted Successfully", deletedMessage);
	}

}
