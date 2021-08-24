package br.com.homefasion_api_test_restassured.conf;

import io.restassured.http.ContentType;

public interface Configs {
    String APP_BASE_URL = "http://localhost";
    Integer APP_PORT = 8080;
    String APP_BASE_PATH = "";
    String USER_FOR_TEST = "usuario1";
    String PASSWORD_FOR_TEST = "senha1";
    ContentType APP_CONTENT_TYPE = ContentType.JSON;
    Long MAX_TIMEOUT = 4000L;

}
