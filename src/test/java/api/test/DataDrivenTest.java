package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndpoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DataDrivenTest {
	
	
	
	@Test(priority = 1, dataProvider = "Data", dataProviderClass = DataProviders.class)
	public void testCreateUser(String UserId, String UserName, String FirstName, String LastName, String Email, String Password, String Phone, String Status ) {
		User userPayload = new User();
		
		userPayload.setId(Integer.parseInt(UserId));
		userPayload.setUsername(UserName);
		userPayload.setFirstName(FirstName);
		userPayload.setLastName(LastName);
		userPayload.setEmail(Email);;
		userPayload.setPassword(Password);
		userPayload.setPhone(Phone);
		userPayload.setUserStatus(Integer.parseInt(Status));
		
		Response response = UserEndpoints.addUser(userPayload);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	
	@Test(priority = 2, dataProvider = "UserNames", dataProviderClass = DataProviders.class)
	public void testGetUser(String UserName ) {
		User userPayload = new User();
		
		
		userPayload.setUsername(UserName);
		
		
		Response response = UserEndpoints.getUser(userPayload.getUsername());
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	
	
	@Test(priority = 3, dataProvider = "UserNames", dataProviderClass = DataProviders.class)
	public void testDeleteUser(String UserName ) {
		User userPayload = new User();
		
		
		userPayload.setUsername(UserName);
		
		
		Response response = UserEndpoints.deleteUser(userPayload.getUsername());
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		Response responseDeleteUser = UserEndpoints.getUser(userPayload.getUsername());
		responseDeleteUser.then().log().all();
		
		Assert.assertEquals(responseDeleteUser.getStatusCode(), 404);
	}

}
