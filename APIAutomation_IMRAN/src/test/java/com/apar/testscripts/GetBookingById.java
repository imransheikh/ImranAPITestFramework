package com.apar.testscripts;


import org.testng.Assert;
import org.testng.annotations.Test;

import com.apar.utils.ContextKey;
import com.apar.utils.PropertyReader;
import com.apar.utils.Resources;
import com.apar.utils.ResponseData;
import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;


public class GetBookingById {
	
	

	@Test (groups = "fetch", dependsOnGroups = "create")
	public void getBookDetails()
	{
		// Setting the base url
				String baseURL = PropertyReader.getProperty("base_url");
				//1405
				
		// Setting the end point url
				RestAssured.baseURI = baseURL + Resources.booking+"/"+ContextKey.bookingId;

				// Posting the request
				Response response = given()
						.contentType(ContentType.JSON)
							.log()
								.all()
									.when()
										.get();

				//printing the response
				System.out.println("Response is: " + response.body().asString());
				

				// Getting the response
				JsonPath jsPath = response.jsonPath();
				String firstName = jsPath.get("firstname");
				String lastName = jsPath.get("lastname");

				
				// Verify that status code 200 should be displayed
				Assert.assertEquals(ResponseData.statusCode200, response.statusCode(), "Status code should be 200");
				// Verify the first name
				Assert.assertEquals(firstName, "testFirstName");
				// Verify the last name
				Assert.assertEquals(lastName, "lastName");

}
}
