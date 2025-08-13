package com.Hotel.API.tests;
import com.Hotel.API.base.Base;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import  com.Hotel.API.config.Configuration;
import com.Hotel.API.utils.ExtentReport;
import com.aventstack.extentreports.Status;

import static org.hamcrest.Matchers.*;

import java.util.List;


public class View_user_by_gender extends Base{
	 SoftAssert softAssert = new SoftAssert();
	@Test
	public void viewUserByGender_Positive() {
	  String gender = Configuration.validGender;

	    Response response = requestSpec()
	            .when()
	            .get("/viewUserByGender/" + gender);

	    System.out.println("Response Body for VIEW_USER_BY_GENDER:\n" + response.getBody().asPrettyString());
	    System.out.println("---------------------------------------------------------------------");
	    validateBasicJsonResponse(response, 200);
	    
	    List<String> genders = response.jsonPath().getList("gender");
	    softAssert.assertTrue(!genders.isEmpty(), "Gender list should not be empty");
	    for (String g : genders) {
	    	softAssert.assertEquals(g, gender, "User gender should match requested gender");
	    }

	    softAssert.assertAll();
	    test.log(Status.PASS, "View user validation passed");
	}
}
