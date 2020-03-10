package br.com.homefasion_api_test_restassure.resources;

import br.com.homefasion_api_test_restassure.categories.NegativeTest;
import br.com.homefasion_api_test_restassure.categories.PositiveTest;
import br.com.homefasion_api_test_restassure.categories.SmokeTest;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static br.com.homefasion_api_test_restassure.conf.ConfiguracaoPrincipal.*;
import static br.com.homefasion_api_test_restassure.shared.UsuarioEndPoints.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;

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
    @Category({NegativeTest.class})
    public void naoDeveAcessarSemAutenticacao(){
        Response response = RestAssured.request(Method.GET, GET_USUARIO_NO_AR);

        assertEquals(401, response.getStatusCode());
    }

    @Test
    @Category({PositiveTest.class, SmokeTest.class})
    public void deveListarTodosOsUsuario(){
        given()
                    .log().all()
                    .auth().basic(USUARIO_TESTE_LOGIN, SENHA_TESTE_LOGIN)
                .when()
                    .request(Method.GET, USUARIO)
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
                    .request(Method.GET, USUARIO_ESPECIFICO)
                .then()
                    .statusCode(200)
                    .body("id", is(155))
                    .body("nome", is("TESTEUSER"))
                    .body("login", is("TESTEUSER"))
                    .body("senha", is("1"))
                    .body("ativo", is(true));
    }

    @Test
    @Category({PositiveTest.class})
    public void deveSalvarUsuario(){
        given()
                    .log().all()
                    .contentType("application/json")
                    .auth().basic(USUARIO_TESTE_LOGIN, SENHA_TESTE_LOGIN)
                    .body("{ \"nome\": \"Teste API 04\", \"login\": \"testeapi04\", \"senha\": \"1\", \"ativo\": true }")
                .when()
                    .request(Method.POST, USUARIO)
                .then()
                    .statusCode(201);
    }

    @Test
    @Category({NegativeTest.class})
    public void naoDeveSalvarUsuarioSemNome(){
        given()
                    .log().all()
                    .contentType("application/json")
                    .auth().basic(USUARIO_TESTE_LOGIN, SENHA_TESTE_LOGIN)
                    .body("{ \"login\": \"testeapi04\", \"senha\": \"1\", \"ativo\": true }")
                .when()
                    .request(Method.POST, USUARIO)
                .then()
                    .statusCode(400);
    }

    @Test
    @Category({PositiveTest.class})
    public void deveAlterarUsuario(){
        given()
                    .log().all()
                    .contentType("application/json")
                    .auth().basic(USUARIO_TESTE_LOGIN, SENHA_TESTE_LOGIN)
                    .body("{ \"nome\": \"Teste API 06\", \"login\": \"testeapi06\", \"senha\": \"1\", \"ativo\": true }")
                .when()
                    .put(USUARIO_ESPECIFICO)
                .then()
                    .statusCode(204);
    }

    @Test
    @Category({NegativeTest.class})
    public void naoDeveAlterarUsuarioSemNome(){
        given()
                    .log().all()
                    .contentType("application/json")
                    .auth().basic(USUARIO_TESTE_LOGIN, SENHA_TESTE_LOGIN)
                    .body("{ \"login\": \"testeapi04\", \"senha\": \"1\", \"ativo\": true }")
                .when()
                    .request(Method.PUT, USUARIO_ESPECIFICO)
                .then()
                    .statusCode(500);
    }

    @Test
    @Category({PositiveTest.class})
    public void deveRemoverUsuario(){
        given()
                    .log().all()
                    .contentType("application/json")
                    .auth().basic(USUARIO_TESTE_LOGIN, SENHA_TESTE_LOGIN)
                    .pathParam("id", 183)
                .when()
                    .delete(USUARIO+"{id}")
                .then()
                    .statusCode(204);
    }

    @Test
    @Category({NegativeTest.class})
    public void naoDeveRemoverUsuarioQueNaoExiste(){
        given()
                    .log().all()
                    .contentType("application/json")
                    .auth().basic(USUARIO_TESTE_LOGIN, SENHA_TESTE_LOGIN)
                    .pathParam("id", 183)
                .when()
                    .delete(USUARIO+"{id}")
                .then()
                    .statusCode(404);
    }
}
