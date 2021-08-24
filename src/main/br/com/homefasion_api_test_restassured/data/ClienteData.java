package br.com.homefasion_api_test_restassured.data;

import br.com.homefasion_api_test_restassured.conf.BaseTest;
import io.restassured.http.Method;

import java.util.ArrayList;

import static br.com.homefasion_api_test_restassured.shared.ClienteEndPoints.CLIENTE;
import static io.restassured.RestAssured.given;

public class ClienteData extends BaseTest {

    public static String dataForRemoveCliente(){
        ArrayList<Integer> clientes =
                given()
                        .auth().basic(USER_FOR_TEST, PASSWORD_FOR_TEST)
                        .when()
                        .get("/cliente?page=0&size=10&sort=id,desc")
                        .then()
                        .statusCode(200)
                        .extract().path("content.id");

        return clientes.get(0).toString();

    }

}
