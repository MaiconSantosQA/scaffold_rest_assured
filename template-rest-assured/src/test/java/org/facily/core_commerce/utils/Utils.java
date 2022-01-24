package org.facily.core_commerce.utils;

import static io.restassured.RestAssured.given;

import org.facily.core_commerce.factory.Seller;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;

public class Utils {
	
	static String APP_ROTA_SELLER_POST = "/branches/{branch_id}/sellers";
	static String APP_ROTA_SELLER_GET = "/branches/{branch_id}/sellers/{seller_id}";
	static String APP_ROTA_SELLER_DELETE = "/branches/{branch_id}/sellers/{seller_id}";

	public String create_seller_default_return_id(String branch_id,String name,String fulfillment_type) throws ParseException {
		Response response;
		
		Seller seller = new Seller(name, fulfillment_type);

		response = 
				given()
					.body(seller)
					.pathParam("branch_id", branch_id)
				.when()
					.post(APP_ROTA_SELLER_POST);
		
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = (JSONObject) jsonParser.parse(response.getBody().asString());
		String id = (String) jsonObject.get("id");

		return id;
	}
	
	public void validate_seller(String branch_id,String seller_id, String name, String fulfillment_type) {

		given()
			.pathParam("branch_id", branch_id)
			.pathParam("seller_id", seller_id)
		.when()
			.get(APP_ROTA_SELLER_GET)
		.then()
			.body("name", is(name))
			.body("fulfillment_type", is(fulfillment_type))
			.log().all();
	}
}
