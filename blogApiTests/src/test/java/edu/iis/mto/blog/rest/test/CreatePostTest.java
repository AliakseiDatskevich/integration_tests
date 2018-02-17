package edu.iis.mto.blog.rest.test;

import org.apache.http.HttpStatus;
import org.json.JSONObject;
import org.junit.Test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;

public class CreatePostTest extends FunctionalTests {

	@Test
	public void confirmedUserShouldAddPost() {
		JSONObject jsonObject = new JSONObject().put("entry", "Entry");
		RestAssured.given().accept(ContentType.JSON).header("Content-Type", "application/json;charset=UTF-8")
				.body(jsonObject.toString()).expect().log().all().statusCode(HttpStatus.SC_CREATED).when()
				.post("/blog/user/1/post");
	}

	@Test
	public void unconfirmedUserShouldNotAddPost() {
		JSONObject jsonObject = new JSONObject().put("entry", "Entry");
		RestAssured.given().accept(ContentType.JSON).header("Content-Type", "application/json;charset=UTF-8")
				.body(jsonObject.toString()).expect().log().all().statusCode(HttpStatus.SC_BAD_REQUEST).when()
				.post("/blog/user/2/post");
	}
}