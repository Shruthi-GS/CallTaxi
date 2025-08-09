package com.Hotel.API.base;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import  com.Hotel.API.config.Configuration;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class Base {

	    protected static String accessToken;

	    @BeforeClass(alwaysRun = true)
	    public void authenticate() {
	        RestAssured.baseURI = Configuration.AUTH_BASE_URI;

	        // Step 1: Get Auth Code
	        String authCode =given()
	                        .header("Accept", "application/json")
	                        .header("Content-Type", "application/x-www-form-urlencoded")
	                        .formParam("username", Configuration.username)
	                        .formParam("password", Configuration.password)
	                        .when()
	                        .post("/login")
	                        .then()
	                        .statusCode(200)
	                        .contentType(containsString("application/json"))
	                        .body("auth_code", notNullValue())
	                        .extract()
	                        .path("auth_code");
	        Assert.assertNotNull(authCode, "Auth code should not be null");
	        System.out.println("Authorization code obtained successfully: "+authCode);
	        
	        // Step 2: Get Access Token
	        accessToken =
	                given()
	                        .header("Accept", "application/json")
	                        .header("Content-Type", "application/x-www-form-urlencoded")
	                        .formParam("auth_code", authCode)
	                        .when()
	                        .post("/token")
	                        .then()
	                        .statusCode(200)
	                        .contentType(containsString("application/json"))
	                        .body("access_token", notNullValue())
	                        .extract()
	                        .path("access_token");
	        Assert.assertNotNull(accessToken, "Access token should not be null");
	        System.out.println("Access token obtained successfully: "+accessToken);
	    }

	    public RequestSpecification requestSpec() {
	        return RestAssured.given()
	                .baseUri(Configuration.USER_SERVICE_BASE_URI)
	                .header("Accept", "application/json")
	                .header("Authorization", "Bearer " + accessToken);
	    }
	}
