package com.Hotel.API.tests;

import com.Hotel.API.base.Base;
import com.Hotel.API.config.Configuration;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.Hotel.API.utils.*;
import com.aventstack.extentreports.Status;

import java.util.List;
import java.util.Map;
import io.restassured.response.Response;
public class Delete_user_by_id extends Base{
	 SoftAssert softAssert = new SoftAssert();
	@Test
	public void deleteUserById_Positive() {
	    int deletedUserId = Configuration.userId3;

	    Response response = requestSpec()
	            .when()
	            .delete("/deleteUserById/" + deletedUserId);
	    
	    System.out.println("Response Body for DELETE_USER:\n" + response.getBody().asPrettyString());
	    System.out.println("---------------------------------------------------------------------");
	    validateBasicJsonResponse(response, 200);
	    
	    try {
	        List<Map<String, Object>> data = response.jsonPath().getList("$");
	        boolean userExists = data.stream()
	                .anyMatch(user -> String.valueOf(user.get("userId")).equals(deletedUserId));

	        softAssert.assertFalse(userExists, "Deleted userId " + deletedUserId + " should not exist");

	    } catch (Exception e) {
	        softAssert.fail("Response is not valid JSON or unexpected format");
	    }

	    softAssert.assertAll();
	    test.log(Status.PASS, "Deleted user validation completed");
	}

}
