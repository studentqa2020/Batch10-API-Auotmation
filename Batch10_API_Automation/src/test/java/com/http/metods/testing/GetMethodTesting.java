package com.http.metods.testing;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GetMethodTesting {

	public static void main(String[] args) {
		
		Response response = RestAssured.get("http://dummy.restapiexample.com/api/v1/employees");
		System.out.println(response.prettyPrint());//JSON
		
	}
	
}
