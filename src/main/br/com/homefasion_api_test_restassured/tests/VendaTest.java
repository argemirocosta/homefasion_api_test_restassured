package br.com.homefasion_api_test_restassured.tests;

import br.com.homefasion_api_test_restassured.categories.NegativeTest;
import br.com.homefasion_api_test_restassured.categories.PositiveTest;
import br.com.homefasion_api_test_restassured.categories.SmokeTest;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static br.com.homefasion_api_test_restassured.conf.Configs.*;
import static br.com.homefasion_api_test_restassured.shared.VendaEndPoints.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class VendaTest {

    @Test
    @Category({PositiveTest.class})
    public void deveVerificarSeVendaNoAr(){
        given()
                    .auth().basic(USER_FOR_TEST, PASSWORD_FOR_TEST)
                .when()
                    .get(GET_VENDA_NO_AR)
                .then()
                    .statusCode(200)
                    .time(lessThan(3000L))
                    .body(is("Servidor no ar"));
    }

    @Test
    @Category({NegativeTest.class})
    public void naoDeveAcessarSemAutenticacao(){
        Response response = RestAssured.request(Method.GET, GET_VENDA_NO_AR);

        assertEquals(401, response.getStatusCode());
    }

    @Test
    @Category({PositiveTest.class, SmokeTest.class})
    public void deveListarTodosAsVendas(){
        given()
                    .auth().basic(USER_FOR_TEST, PASSWORD_FOR_TEST)
                .when()
                    .get(VENDA)
                .then()
                    .statusCode(200)
                    .body("id[0]", is(202))
                    .body("valor[0]", is(207f));
    }

    @Test
    @Category({PositiveTest.class, SmokeTest.class})
    public void deveListarVendaEspecifica(){
        given()
                    .auth().basic(USER_FOR_TEST, PASSWORD_FOR_TEST)
                .when()
                    .get(VENDA_ESPECIFICA)
                .then()
                    .statusCode(200)
                    .body("id", is(374))
                    .body("cliente.id", is(234))
                    .body("qtd", is(2))
                    .body("valor", is(105.5f))
                    .body("data", is("01/01/2020"))
                    .body("idUsuario", is(155))
                    .body("cancelada", is(true));
    }

    @Test
    @Category({PositiveTest.class})
    public void deveListarVendaPorUsuario(){
        given()
                    .auth().basic(USER_FOR_TEST, PASSWORD_FOR_TEST)
                .when()
                    .get(GET_VENDAS_POR_USUARIO)
                .then()
                    .statusCode(200)
                    .body("id[0]", is(370))
                    .body("qtd[0]", is(2))
                    .body("valor[0]", is(105.5f));
    }

    @Test
    @Category({PositiveTest.class})
    public void deveRealizarVenda(){
        given()
                    .contentType("application/json")
                    .auth().basic(USER_FOR_TEST, PASSWORD_FOR_TEST)
                    .body("{ \"idCliente\": 234, \"valor\": 300, \"qtd\": 2, \"data\": \"01/01/2020\", \"idUsuario\": 155, \"cancelada\": \"false\", \"dataHoraCancelamento\": null }")
                .when()
                    .post(VENDA)
                .then()
                    .statusCode(201);
    }

    @Test
    @Category({PositiveTest.class})
    public void deveRealizarCancelamentoDeVenda(){
        given()
                    .contentType("application/json")
                    .auth().basic(USER_FOR_TEST, PASSWORD_FOR_TEST)
                    .pathParam("codigoVenda", 382)
                .when()
                    .put(PUT_VENDA_CANCELAR+"{codigoVenda}")
                .then()
                    .statusCode(204);
    }

}
