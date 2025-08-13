package com.Hotel.API.base;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.notNullValue;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import com.Hotel.API.config.Configuration;
import com.Hotel.API.utils.ExtentReport;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.asserts.SoftAssert;

public class Base {

    static ExtentSparkReporter spark;
    static ExtentReports extReports;
    public static ExtentTest test,test1;
    protected static String accessToken;
    protected SoftAssert softAssert;

    @BeforeSuite(alwaysRun = true)
    public static void beforeAll() {
        spark = new ExtentSparkReporter(Configuration.EXTENT_REPORT_PATH);
        extReports = new ExtentReports();
        extReports.attachReporter(spark);
        test1 = extReports.createTest("Suite Setup");
    }

    @AfterSuite(alwaysRun = true)
    public static void afterAll() {
        extReports.flush();
    }

    @BeforeMethod(alwaysRun = true)
    public void startTest(Method method) {
        test = extReports.createTest(method.getName());
        softAssert = new SoftAssert();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            ExtentReport.generateReport(test, Status.FAIL, result.getThrowable().getMessage());
        }
        softAssert.assertAll();
    }

    @BeforeTest(alwaysRun = true)
    public void authenticate() {
        RestAssured.baseURI = Configuration.AUTH_BASE_URI;

        String authCode = given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/x-www-form-urlencoded")
                .formParam("username", Configuration.username)
                .formParam("password", Configuration.password)
                .when()
                .post("/login")
                .then()
                .assertThat()
                .statusCode(200)
                .contentType(containsString("application/json"))
                .body("auth_code", notNullValue())
                .extract()
                .path("auth_code");
        if (authCode != null) {
            test1.log(Status.PASS, "Authorization code obtained successfully: " + authCode);
            System.out.println("Authorization code obtained successfully: " + authCode);
        } else {
            test1.log(Status.FAIL, "Authorization code is null");
        }
        Assert.assertNotNull(authCode, "Auth code should not be null");

	    System.out.println("---------------------------------------------------------------------");

        accessToken = given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/x-www-form-urlencoded")
                .formParam("auth_code", authCode)
                .when()
                .post("/token")
                .then()
                .assertThat()
                .statusCode(200)
                .contentType(containsString("application/json"))
                .body("access_token", notNullValue())
                .extract()
                .path("access_token");
        
        if (accessToken != null) {
            test1.log(Status.PASS, "Access token obtained successfully: " + accessToken);
            System.out.println("Access token obtained successfully: " + accessToken);
        } else {
            test1.log(Status.FAIL, "Access token is null");
        }
        Assert.assertNotNull(accessToken, "Access token should not be null");

	    System.out.println("---------------------------------------------------------------------");
	    }

    public RequestSpecification requestSpec() {
        return RestAssured.given()
                .baseUri(Configuration.USER_SERVICE_BASE_URI)
                .header("Accept", "application/json")
                .header("Authorization", "Bearer " + accessToken);
    }

    public void validateBasicJsonResponse(Response response, int expectedStatus) {
        boolean statusOk = response.getStatusCode() == expectedStatus;
        softAssert.assertEquals(response.getStatusCode(), expectedStatus,
                "Status code should be " + expectedStatus);
        if (statusOk) {
            test.log(Status.PASS, "Status code is as expected: " + expectedStatus);
        } else {
            test.log(Status.FAIL, "Status code mismatch. Expected: " + expectedStatus + ", Found: " + response.getStatusCode());
        }

        String contentType = response.getHeader("Content-Type");
        boolean contentTypeOk = contentType != null && contentType.contains("application/json");
        softAssert.assertTrue(contentTypeOk, "Content-Type should be application/json");
        if (contentTypeOk) {
            test.log(Status.PASS, "Content-Type validated: " + contentType);
        } else {
            test.log(Status.FAIL, "Invalid Content-Type: " + contentType);
        }

        String responseBody = response.getBody().asString();
        boolean bodyOk = !responseBody.isEmpty();
        softAssert.assertFalse(responseBody.isEmpty(), "Response body should not be empty");
        if (bodyOk) {
            test.log(Status.PASS, "Response body is not empty");
        } else {
            test.log(Status.FAIL, "Response body is empty");
        }
    }
}