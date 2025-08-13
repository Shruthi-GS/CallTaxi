package com.Hotel.API.tests;

import com.Hotel.API.base.Base;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.Hotel.API.utils.*;
import com.aventstack.extentreports.Status;

import java.util.Map;
import static org.hamcrest.Matchers.*;
import io.restassured.response.Response;
public class Add_user extends Base{
	 SoftAssert softAssert = new SoftAssert();
	@Test
	public void addUser() {
		System.out.println("------------------ADD_USER---------------------\n");
	    Map<String, String> userData = ExcelReader.getUserData("Sheet1", 1);

	    Response response = requestSpec()
	            .contentType("application/x-www-form-urlencoded")
	            .formParam("userId", userData.get("userId"))
	            .formParam("emailId", userData.get("emailId"))
	            .formParam("aadhaarNumber", userData.get("aadhaarNumber"))
	            .formParam("firstName", userData.get("firstName"))
	            .formParam("lastName", userData.get("lastName"))
	            .formParam("phoneNumber", userData.get("phoneNumber"))
	            .formParam("gender", userData.get("gender"))
	            .when()
	            .post("/addUser");

	    System.out.println("Response Body for ADD_USER:\n" + response.getBody().asPrettyString());
	    System.out.println("\n***********************************************************************\n");

	    validateBasicJsonResponse(response, 200);
	    
	    java.util.List<Integer> userIds = response.jsonPath().getList("userId");
	    softAssert.assertTrue(userIds.contains(Integer.parseInt(userData.get("userId"))),
	            "Response should contain userId: " + userData.get("userId"));
	    softAssert.assertAll();
	    test.log(Status.PASS, "Add user validation passed");

	}
}
