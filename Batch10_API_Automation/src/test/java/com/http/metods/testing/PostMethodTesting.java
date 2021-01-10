package com.http.metods.testing;

import org.testng.Assert;

import com.google.gson.JsonObject;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PostMethodTesting {
	
	public static void main(String[] args) {
		
		JsonObject json = new JsonObject();
		json.addProperty("Boro", "Queens");
		json.addProperty("City", "New York");
		json.addProperty("Country", "USA");
		json.addProperty("id", "200");
		
		
		RequestSpecification rs = RestAssured.given();
		rs.header("Content-type", "application/json");//info

		rs.body(json.toString());//data
		
		Response response = rs.post("http://dummy.restapiexample.com/api/v1/create");
		
		 System.out.println("Status code = "+response.statusCode()); // validate
		  //status code 200 or not 
		  Assert.assertEquals(response.statusCode(), 200,
		  "Status code validation for 200"); // response time
		  
		  System.out.println("Response time in ms ="+response.getTime());
		  Assert.assertTrue(response.getTime()> 500);
		  System.out.println("Data type is json or nor ="+response.contentType());
		  
		  System.out.println("");
		  System.out.println("JSON Header= "+response.getHeaders());
		  System.out.println("");
		  response.getBody().prettyPrint();
		 
		//validate data
			JsonPath jsonPath =response.jsonPath();
			Assert.assertTrue(jsonPath.get("status").toString()!=null ,"Status should not be null");
			Assert.assertTrue(jsonPath.get("data").toString()!=null,"Data should not be null");
			
			System.out.println("Status value from JSON ="+jsonPath.get("status").toString());
			
			Assert.assertEquals(jsonPath.get("status").toString(), "success");
			
	}

}
