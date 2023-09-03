package com.accenture.utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class RestUtils {

    public Response getResponsePayload(String userName, String password, String URI) {
        return RestAssured.given().auth().preemptive().basic(userName, password).get(URI);
    }
}
