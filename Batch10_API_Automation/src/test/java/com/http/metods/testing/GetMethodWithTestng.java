package com.http.metods.testing;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GetMethodWithTestng {
	Response response;

	@BeforeTest
	public void setup() {

		response = RestAssured.get("http://dummy.restapiexample.com/api/v1/employees");
	}

	@Test(priority=2)
	public void test1() {
		//Test status
		response.prettyPrint();// JSON
		// Basic validation //status code
		System.out.println("Status code = " + response.statusCode()); // validate
		// status code 200 or not
		Assert.assertEquals(response.statusCode(), 200, "Status code validation for 200"); // response time
	}
		
	@Test(priority=1)
	public void test2() {
		//Basic validation
		System.out.println("Response time in ms =" + response.getTime());
		Assert.assertTrue(response.getTime() > 500);
		System.out.println("Data type is json or nor =" + response.contentType());

		System.out.println("");
		System.out.println("JSON Header= " + response.getHeaders());
		System.out.println("");
		response.getBody().prettyPrint();
	}
		
	@Test(priority=0)
	public void test3() {
		// validate data
				JsonPath jsonPath = response.jsonPath();
				Assert.assertTrue(jsonPath.get("status").toString() != null, "Status should not be null");
				Assert.assertTrue(jsonPath.get("data").toString() != null, "Data should not be null");

				System.out.println("Status value from JSON =" + jsonPath.get("status").toString());

				Assert.assertEquals(jsonPath.get("status").toString(), "success");
	}
	@AfterTest
	public void teardown() {
		//close
		
	}

	}


