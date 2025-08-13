package com.Hotel.API.tests;

import com.Hotel.API.base.Base;
import com.Hotel.API.config.Configuration;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.Hotel.API.utils.*;
import com.aventstack.extentreports.Status;

import java.util.Map;
import io.restassured.response.Response;

public class Update_usernumber_NEGATIVE extends Base {
	 SoftAssert softAssert = new SoftAssert();
	@Test
	public void updateUserNumber_Negative() {
	int userId = Configuration.userId2;
    String phoneNumber = Configuration.phoneNumber;
    System.out.println("------------------UPDATE_USERNUMBER---------------------\n");
    Response response = requestSpec()
    		.log().all()
            .contentType("application/x-www-form-urlencoded")
            .formParam("userId", userId)
            .formParam("phoneNumber", phoneNumber)
            .when()
            .put("/updateUserNumber");
    System.out.println("Response Body for UPDATE_USERNUMBER:\n" + response.getBody().asPrettyString());
    System.out.println("\n***********************************************************************\n");
    validateBasicJsonResponse(response, 404);
    
    try {
    	Map<String, Object> user = response.jsonPath()
    	        .getMap("find { it.userId == " + userId + " }");

    	softAssert.assertNotNull(user, "User ID " + userId + " should exist");
        if (user != null) {
        	softAssert.assertEquals(String.valueOf(user.get("phoneNumber")), phoneNumber,
                    "Phone number should match expected value");
        }
    } catch (Exception e) {
    	softAssert.fail("Response is not valid JSON");
    }

    softAssert.assertAll();
    test.log(Status.PASS, "Update usernumber validation passed");
	}
}
