package br.com.homefasion_api_test_restassured.tests;

import br.com.homefasion_api_test_restassured.categories.NegativeTest;
import br.com.homefasion_api_test_restassured.categories.PositiveTest;
import br.com.homefasion_api_test_restassured.categories.SmokeTest;
import br.com.homefasion_api_test_restassured.conf.BaseTest;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.HashMap;
import java.util.Map;

import static br.com.homefasion_api_test_restassured.shared.UsuarioEndPoints.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class UsuarioTest extends BaseTest {

    @Test
    @Category({PositiveTest.class})
    public void deveVerificarSeUsuarioNoAr(){
        given()
                    .log().all()
                    .auth().basic(USER_FOR_TEST, PASSWORD_FOR_TEST)
                .when()
                    .request(Method.GET, GET_USUARIO_NO_AR)
                .then()
                    .statusCode(200)
                    .time(lessThan(1700L))
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
                    .auth().basic(USER_FOR_TEST, PASSWORD_FOR_TEST)
                .when()
                    .request(Method.GET, USUARIO)
                .then()
                    .statusCode(200)
                    .body("id[0]", is(159));
    }

    @Test
    @Category({PositiveTest.class, SmokeTest.class})
    public void deveListarUsuarioEspecifico(){
        given()
                    .log().all()
                    .auth().basic(USER_FOR_TEST, PASSWORD_FOR_TEST)
                    .pathParam("id", 159)
                .when()
                    .request(Method.GET, USUARIO+"{id}")
                .then()
                    .statusCode(200)
                    .body("id", is(159))
                    .body("nome", is("SR. MARCOS LOPES"))
                    .body("login", is("DAVI.ALVES"))
                    .body("senha", is("roberto"))
                    .body("ativo", is(true));
    }

    @Test
    @Category({PositiveTest.class})
    public void deveSalvarUsuario(){
        Map<String, Object> params = new HashMap<>();
        params.put("nome", "Teste API 05");
        params.put("login", "testeapi05");
        params.put("senha", "1");
        params.put("ativo", true);

        given()
                    .log().all()
                    .contentType("application/json")
                    .auth().basic(USER_FOR_TEST, PASSWORD_FOR_TEST)
                    .body(params)
                .when()
                    .request(Method.POST, USUARIO)
                .then()
                    .statusCode(201);
    }

    @Test
    @Category({NegativeTest.class})
    public void naoDeveSalvarUsuarioSemNome(){
        Map<String, Object> params = new HashMap<>();
        params.put("login", "testeapi05");
        params.put("senha", "1");
        params.put("ativo", true);

        given()
                    .log().all()
                    .contentType("application/json")
                    .auth().basic(USER_FOR_TEST, PASSWORD_FOR_TEST)
                    .body(params)
                .when()
                    .request(Method.POST, USUARIO)
                .then()
                    .statusCode(400);
    }

    @Test
    @Category({PositiveTest.class})
    public void deveAlterarUsuario(){
        Map<String, Object> params = new HashMap<>();
        params.put("nome", "Teste API Alterando usuário 01");
        params.put("login", "testeapi");
        params.put("senha", "1");
        params.put("ativo", true);

        given()
                    .log().all()
                    .contentType("application/json")
                    .auth().basic(USER_FOR_TEST, PASSWORD_FOR_TEST)
                    .body(params)
                    .pathParam("id", 155)
                .when()
                    .put(USUARIO+"{id}")
                .then()
                    .statusCode(204);
    }

    @Test
    @Category({NegativeTest.class})
    public void naoDeveAlterarUsuarioSemNome(){
        Map<String, Object> params = new HashMap<>();
        params.put("login", "testeapi05");
        params.put("senha", "1");
        params.put("ativo", true);

        given()
                    .log().all()
                    .contentType("application/json")
                    .auth().basic(USER_FOR_TEST, PASSWORD_FOR_TEST)
                    .body(params)
                    .pathParam("id", 155)
                .when()
                    .put(USUARIO+"{id}")
                .then()
                    .statusCode(500);
    }

    @Test
    @Category({PositiveTest.class})
    public void deveRemoverUsuario(){
        given()
                    .log().all()
                    .contentType("application/json")
                    .auth().basic(USER_FOR_TEST, PASSWORD_FOR_TEST)
                    .pathParam("id", 184)
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
                    .auth().basic(USER_FOR_TEST, PASSWORD_FOR_TEST)
                    .pathParam("id", 184)
                .when()
                    .delete(USUARIO+"{id}")
                .then()
                    .statusCode(404);
    }
}
