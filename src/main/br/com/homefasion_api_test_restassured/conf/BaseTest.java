package br.com.homefasion_api_test_restassured.conf;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import org.hamcrest.Matchers;
import org.junit.BeforeClass;

public class BaseTest implements Configs {

    @BeforeClass
    public static void setUp(){
        RestAssured.baseURI = APP_BASE_URL;
        RestAssured.port = APP_PORT;
        RestAssured.basePath = APP_BASE_PATH;

        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setContentType(APP_CONTENT_TYPE);
        RestAssured.requestSpecification = requestSpecBuilder.build();

        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder();
        responseSpecBuilder.expectResponseTime(Matchers.lessThan(MAX_TIMEOUT));
        RestAssured.responseSpecification = responseSpecBuilder.build();

        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

    }

}
