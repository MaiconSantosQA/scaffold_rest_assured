package org.facily.core_commerce;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.facily.core_commerce.core.BaseTest;
import org.facily.core_commerce.factory.Seller;
import org.facily.core_commerce.utils.Utils;
import org.json.simple.parser.ParseException;
import org.junit.Test;

import com.github.javafaker.Faker;

public class Seller_api extends BaseTest {
	
	static String APP_ROTA = "/branches/{branch_id}/sellers";
	static String APP_ROTA_GET = "/branches/{branch_id}/sellers/{seller_id}";
	static String APP_ROTA_PATCH = "/branches/{branch_id}/sellers/{seller_id}";
	static String APP_ROTA_DELETE = "/branches/{branch_id}/sellers/{seller_id}";
	
	Utils utils = new Utils();
	Faker faker = new Faker();
	String branch_id;
	String seller_id;
	
	String name = faker.dragonBall().character();
	String fulfillment_type[] = {"1p", "2p", "3p", "xb", "xd", "digital", "full"};
	String foreignkey = faker.name().fullName();
	

	@Test  
	public void create_seller() throws ParseException {

		Seller seller = new Seller(name, fulfillment_type[0]);
		
		given()
			.body(seller)
			.pathParam("branch_id", branch_id)
		.when()
			.post(APP_ROTA)
		.then()
			.statusCode(201)
			.body("name", is(name))
			.body("fulfillment_type", is(fulfillment_type[0]))
			.body("active", is(true))
			.body("id", notNullValue())
			.body("synced_at", notNullValue())
			.body("updated_at", notNullValue())
			.log().all();
	}
	
	
	
	// ------------------- GET -----------------------
	
	@Test  
	public void get_seller() throws ParseException {
        String name_seller = faker.name().fullName();
        
        seller_id = utils.create_seller_default_return_id(branch_id,name_seller, fulfillment_type[2]);
		
		given()
			.pathParam("branch_id", branch_id)
			.pathParam("seller_id", seller_id)
		.when()
			.get(APP_ROTA_GET)
		.then()
			.statusCode(200)
			.body("name", is(name_seller))
			.body("fulfillment_type", is(fulfillment_type[2]))
			.body("active", is(true))
			.body("id", notNullValue())
			.body("synced_at", notNullValue())
			.body("updated_at", notNullValue())
			.log().all();
	}
	
	
	// -------------------------- UPDATE -----------------------
	
		@Test  
		public void update_seller() throws ParseException {
	        
			String name_update = faker.dragonBall().character();
			
			seller_id = utils.create_seller_default_return_id(branch_id,name, fulfillment_type[2]);
			Seller seller = new Seller(name_update, fulfillment_type[3]);
				
			given()
				.pathParam("branch_id", branch_id)
				.pathParam("seller_id", seller_id)
				.body(seller)
			.when()
				.patch(APP_ROTA_PATCH)
			.then()
				.statusCode(204)
				.log().all();
			
			utils.validate_seller(branch_id, seller_id, name_update,fulfillment_type[3]);
		}
		

	
	 // --------------------- DELETE ----------------------

		@Test  
		public void delete_seller() throws ParseException {
	        
			seller_id = utils.create_seller_default_return_id(branch_id,name, fulfillment_type[2]);

			given()
				.pathParam("branch_id", branch_id)
				.pathParam("seller_id", seller_id)
			.when()
				.delete(APP_ROTA_DELETE)
			.then()
				.statusCode(204)
				.log().all();
		}
		
	
}