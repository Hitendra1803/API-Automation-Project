package api.endpoints;

import static io.restassured.RestAssured.*;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndpoints {
	
	
	//Add new user
	public static Response addUser(User payload) {
		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON)
				.body(payload)
				.when()
				.post(APIsRoutes.post_url);
		
		return response;
	}
	
	//Update user
		public static Response updateUser(User payload, String username) {
			Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON)
					.pathParam("username", username)
					.body(payload)
					.when()
					.put(APIsRoutes.put_url);
			
			return response;
		}
		
		//Get user
		public static Response getUser(String username) {
			Response response = given().accept(ContentType.JSON)
					.pathParam("username", username)
					.when()
					.get(APIsRoutes.get_url);
			
			return response;
		}
		
		//Delete user
		public static Response deleteUser(String username) {
			Response response = given().accept(ContentType.JSON)
					.pathParam("username", username)
					.when()
					.delete(APIsRoutes.delete_url);
			
			return response;
		}

}
