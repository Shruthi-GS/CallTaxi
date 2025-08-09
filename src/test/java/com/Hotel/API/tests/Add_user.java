package com.Hotel.API.tests;

import com.Hotel.API.base.Base;
import org.testng.annotations.Test;
import com.Hotel.API.utils.*;
import java.util.Map;
import static org.hamcrest.Matchers.*;

public class Add_user extends Base{


	    @Test
	    public void addUser() {
	        Map<String, String> userData = ExcelReader.getUserData("Sheet1", 1);

	        requestSpec()
	                .contentType("application/x-www-form-urlencoded")
	                .formParam("userId", userData.get("userId"))
	                .formParam("emailId", userData.get("emailId"))
	                .formParam("aadhaarNumber", userData.get("aadhaarNumber"))
	                .formParam("firstName", userData.get("firstName"))
	                .formParam("lastName", userData.get("lastName"))
	                .formParam("phoneNumber", userData.get("phoneNumber"))
	                .formParam("gender", userData.get("gender"))
	                .when()
	                .post("/addUser")
	                .then()
	                .statusCode(200)
	                .contentType(containsString("application/json"))
	                .body("userId", hasItem(Integer.parseInt(userData.get("userId"))))
	                .body(not(empty()));
	    }

//	    @Test(priority = 2)
//	    public void viewUserList() {
//	        requestSpec()
//	        .when()
//	                .get("/viewUserList")
//	        .then()
//	                .statusCode(200)
//	                .contentType(containsString("application/json"))
//	                .body(not(empty()))
//	                .body("size()", greaterThan(0));
//	    }
//
//	    @Test(priority = 3)
//	    public void viewUserById() {
//	        Map<String, String> userData = ExcelReader.getUserData("Sheet1", 1);
//
//	        requestSpec()
//	        .when()
//	                .get("/viewUserById/" + userData.get("userId"))
//	        .then()
//	                .statusCode(200)
//	                .contentType(containsString("application/json"))
//	                .body("userId", equalTo(Integer.parseInt(userData.get("userId"))))
//	                .body(not(empty()));
//	    }
//
//	    @Test(priority = 4)
//	    public void viewUserByGender() {
//	        Map<String, String> userData = ExcelReader.getUserData("Users", 1);
//
//	        requestSpec()
//	        .when()
//	                .get("/viewUserByGender/" + userData.get("gender"))
//	        .then()
//	                .statusCode(200)
//	                .contentType(containsString("application/json"))
//	                .body(not(empty()))
//	                .body("gender", everyItem(equalTo(userData.get("gender"))));
//	    }
	}