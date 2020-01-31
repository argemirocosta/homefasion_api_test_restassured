package br.com.homefasion_api_test_restassure.resources;

import br.com.homefasion_api_test_restassure.categories.PositiveTest;
import br.com.homefasion_api_test_restassure.categories.SmokeTest;
import io.restassured.http.Method;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static br.com.homefasion_api_test_restassure.conf.ConfiguracaoPrincipal.*;
import static br.com.homefasion_api_test_restassure.shared.UsuarioEndPoints.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

public class UsuarioTest {

    @Test
    @Category({PositiveTest.class})
    public void deveVerificarSeUsuarioNoAr(){
        given()
                .log().all()
                .auth().basic(USUARIO_TESTE_LOGIN, SENHA_TESTE_LOGIN)
                .when()
                .request(Method.GET, GET_USUARIO_NO_AR)
                .then()
                .statusCode(200)
                .body(is("Servidor no ar"));
    }

    @Test
    @Category({PositiveTest.class, SmokeTest.class})
    public void deveListarTodosOsUsuario(){
        given()
                .log().all()
                .auth().basic(USUARIO_TESTE_LOGIN, SENHA_TESTE_LOGIN)
                .when()
                .request(Method.GET, GET_USUARIO)
                .then()
                .statusCode(200)
                .body("id[0]", is(158));
    }

    @Test
    @Category({PositiveTest.class, SmokeTest.class})
    public void deveListarUsuarioEspecifico(){
        given()
                .log().all()
                .auth().basic(USUARIO_TESTE_LOGIN, SENHA_TESTE_LOGIN)
                .when()
                .request(Method.GET, GET_USUARIO_ESPECIFICO)
                .then()
                .statusCode(200)
                .body("id", is(155))
                .body("nome", is("TESTEUSER"))
                .body("login", is("TESTEUSER"))
                .body("senha", is("1"))
                .body("ativo", is(true));
    }
}
