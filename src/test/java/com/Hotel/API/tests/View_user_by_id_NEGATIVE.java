package com.Hotel.API.tests;
import com.Hotel.API.base.Base;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import  com.Hotel.API.config.Configuration;
import com.Hotel.API.utils.ExtentReport;
import com.aventstack.extentreports.Status;

import static org.hamcrest.Matchers.*;

public class View_user_by_id_NEGATIVE extends Base{
	 SoftAssert softAssert = new SoftAssert();
	int userId = Configuration.userId1;
    @Test
    public void viewUserById_Negative() {
    System.out.println("------------------VIEW_USER_BY_ID---------------------\n");
	 Response response = requestSpec()
			 .log().all()
             .when()
             .get("/viewUserById/" + userId);
	 System.out.println("Response Body for VIEW_USER_BY_ID:\n" + response.getBody().asPrettyString());
	 System.out.println("\n***********************************************************************\n");	    
	 validateBasicJsonResponse(response, 404);
	 try {
         Integer returnedId = response.jsonPath().getInt("userId");
         if (returnedId != null) {
        	 softAssert.assertEquals(String.valueOf(returnedId), userId,"Returned userId should match input");
         }
    } catch (Exception e) {
    	softAssert.fail("Response is not valid JSON");
    }
	 softAssert.assertAll();
	 test.log(Status.PASS, "View user validation passed");
    }
}
