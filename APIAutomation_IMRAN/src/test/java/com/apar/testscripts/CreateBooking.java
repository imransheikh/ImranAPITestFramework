package com.apar.testscripts;

import static io.restassured.RestAssured.given;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.apar.utils.ContextKey;
import com.apar.utils.PropertyReader;
import com.apar.utils.Resources;
import com.apar.utils.ResponseData;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import java.io.File;
import java.io.IOException;



public class CreateBooking {

	@Test (groups = "create")
	public void createBooking() throws IOException {

		// getting the payload
		File payloadFile = new File("src/main/resources/payloads/createBooking.json");
		
		// Setting the base url
		String baseURL = PropertyReader.getProperty("base_url");

		// Setting the end point url
		RestAssured.baseURI = baseURL + Resources.booking;

		// Posting the request
		Response response = given()
				.contentType(ContentType.JSON)
					.log()
						.all()
							.body(payloadFile)
								.when()
									.post();

		//printing the response
		System.out.println("Response is: " + response.body().asString());
		
		// Storing the booking id in the context
		ContextKey.bookingId = response.jsonPath().get("bookingid");
		
		
		// Getting the response
		JsonPath jsPath = response.jsonPath();
		String firstName = jsPath.get("booking.firstname");
		String lastName = jsPath.get("booking.lastname");

		
		// Verify that status code 200 should be displayed
		Assert.assertEquals(ResponseData.statusCode200, response.statusCode(), "Status code should be 200");
		// Verify the first name
		Assert.assertEquals(firstName, "testFirstName");
		// Verify the last name
		Assert.assertEquals(lastName, "lastName");

	}

}
