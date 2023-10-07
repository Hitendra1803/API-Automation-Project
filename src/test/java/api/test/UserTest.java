package api.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndpoints;
import api.payload.User;
import io.restassured.response.Response;

public class UserTest {
	
	Faker faker;
	User userPayload;
	
	
	@BeforeClass
	public void setupData() {
		faker = new Faker();
		userPayload = new User();
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().emailAddress());
		userPayload.setPassword(faker.internet().password());
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		userPayload.setUserStatus(faker.number().hashCode());
	}
	
	
	@Test(priority = 1)
	public void createUserTest() {
		Response response = UserEndpoints.addUser(userPayload);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}
	
	@Test(priority = 2)
	public void getUserTest() {
		Response response = UserEndpoints.getUser(this.userPayload.getUsername());
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority = 3)
	public void updateUserTest() {
		
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		
		Response response = UserEndpoints.updateUser(userPayload, this.userPayload.getUsername());
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		Response responseUpdateUser = UserEndpoints.getUser(this.userPayload.getUsername());
		responseUpdateUser.then().log().all();
		
		Assert.assertEquals(responseUpdateUser.getStatusCode(), 200);
	}
	
	@Test(priority = 4)
	public void deleteUserTest() {
		Response response = UserEndpoints.deleteUser(this.userPayload.getUsername());
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		Response responseDeleteUser = UserEndpoints.getUser(this.userPayload.getUsername());
		responseDeleteUser.then().log().all();
		
		Assert.assertEquals(responseDeleteUser.getStatusCode(), 404);
	}
	

}
