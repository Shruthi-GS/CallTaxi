package com.Hotel.API.tests;
import com.Hotel.API.base.Base;
import com.Hotel.API.utils.ExtentReport;
import com.aventstack.extentreports.Status;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static org.hamcrest.Matchers.*;

public class View_user_list extends Base{
	 SoftAssert softAssert = new SoftAssert();
	@Test
	public void viewUserList() {
	    Response response = requestSpec()
	            .when()
	            .get("/viewUserList");

	    System.out.println("Response Body for VIEW_USER_LIST:\n" + response.getBody().asPrettyString());
	    System.out.println("---------------------------------------------------------------------");	    
	    validateBasicJsonResponse(response, 200);
	    
	    if (response.getContentType().contains("json")) {
	        java.util.List<Object> jsonData = response.jsonPath().getList("$");
	        softAssert.assertTrue(jsonData instanceof java.util.List, "Response should be an array");
	        softAssert.assertTrue(jsonData.size() > 0, "User list should not be empty");
	    }
	    softAssert.assertAll();
	    test.log(Status.PASS, "View user validation passed");
	}

}
