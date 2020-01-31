package br.com.homefasion_api_test_restassure.resources;

import io.restassured.http.Method;
import org.junit.Test;

import static br.com.homefasion_api_test_restassure.conf.ConfiguracaoPrincipal.SENHA_TESTE_LOGIN;
import static br.com.homefasion_api_test_restassure.conf.ConfiguracaoPrincipal.USUARIO_TESTE_LOGIN;
import static br.com.homefasion_api_test_restassure.shared.UsuarioEndPoints.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;

public class UsuarioTest {

    @Test
    public void deveVerificarSeClienteNoAr(){
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
    public void deveListarTodosOsClientes(){
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
    public void deveListarClienteEspecifico(){
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
