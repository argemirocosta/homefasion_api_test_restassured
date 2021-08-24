package br.com.homefasion_api_test_restassured.data;

import br.com.homefasion_api_test_restassured.conf.BaseTest;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;

public class UsuarioData extends BaseTest {

    public static String dataForRemoveUsuario(){
        ArrayList<Integer> usuarios =
                given()
                        .auth().basic(USER_FOR_TEST, PASSWORD_FOR_TEST)
                        .when()
                        .get("http://localhost:8080/usuarios?page=0&size=10&sort=id,desc")
                        .then()
                        .statusCode(200)
                        .extract().path("id");

        return usuarios.get(0).toString();

    }

}
