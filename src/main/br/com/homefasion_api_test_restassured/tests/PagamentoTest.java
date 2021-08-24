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

import static br.com.homefasion_api_test_restassured.shared.PagamentoEndPoints.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class PagamentoTest extends BaseTest {

    @Test
    @Category({PositiveTest.class})
    public void deveVerificarSePagamentoNoAr(){
        given()
                    .log().all()
                    .auth().basic(USER_FOR_TEST, PASSWORD_FOR_TEST)
                .when()
                    .request(Method.GET, GET_PAGAMENTO_NO_AR)
                .then()
                    .statusCode(200)
                    .time(lessThan(3000L))
                    .body(is("Servidor no ar"));
    }

    @Test
    @Category({NegativeTest.class})
    public void naoDeveAcessarSemAutenticacao(){
        Response response = RestAssured.request(Method.GET, GET_PAGAMENTO_NO_AR);

        assertEquals(401, response.getStatusCode());
    }

    @Test
    @Category({PositiveTest.class, SmokeTest.class})
    public void deveListarTodosOsPagamento(){
        given()
                    .log().all()
                    .auth().basic(USER_FOR_TEST, PASSWORD_FOR_TEST)
                .when()
                    .request(Method.GET, PAGAMENTO)
                .then()
                    .statusCode(200)
                    .body("id[0]", is(305))
                    .body("valorPago[0]", is(37f));
    }

    @Test
    @Category({PositiveTest.class, SmokeTest.class})
    public void deveListarPagamentoEspecifico(){
        given()
                    .log().all()
                    .auth().basic(USER_FOR_TEST, PASSWORD_FOR_TEST)
                .when()
                    .request(Method.GET, PAGAMENTO_ESPECIFICO)
                .then()
                    .statusCode(200)
                    .body("id", is(374))
                    .body("idVenda", is(179))
                    .body("valorPago", is(37f))
                    .body("dataPagamento", is("18/04/2018"))
                    .body("usuario.id", is(1));
    }

    @Test
    @Category({PositiveTest.class})
    public void deveListarPagamentoPorCliente(){
        given()
                    .log().all()
                    .auth().basic(USER_FOR_TEST, PASSWORD_FOR_TEST)
                .when()
                    .request(Method.GET, GET_PAGAMENTOS_POR_CLIENTE)
                .then()
                    .statusCode(200)
                    .body("id[0]", is(336))
                    .body("idVenda[0]", is(167))
                    .body("valorPago[0]", is(80f));
    }

    @Test
    @Category({PositiveTest.class, SmokeTest.class})
    public void deveListarPagamentoPorVenda(){
        given()
                    .log().all()
                    .auth().basic(USER_FOR_TEST, PASSWORD_FOR_TEST)
                .when()
                    .request(Method.GET, GET_PAGAMENTOS_POR_VENDA)
                .then()
                    .statusCode(200)
                    .body("id[0]", is(336))
                    .body("idVenda[0]", is(167))
                    .body("dataPagamento[0]", is("27/02/2018"))
                    .body("valorPago[0]", is(80f));
    }

    @Test
    @Category({PositiveTest.class})
    public void deveRealizarPagamento(){
        given()
                    .log().all()
                    .contentType("application/json")
                    .auth().basic(USER_FOR_TEST, PASSWORD_FOR_TEST)
                    .body("{\"idVenda\": 374, \"valorPago\": 1,\"dataPagamento\": \"09/03/2020\",\"idUsuario\": \"155\", \"cancelada\": \"false\", \"dataHoraCancelamento\": null }")
                .when()
                    .request(Method.POST, PAGAMENTO)
                .then()
                    .statusCode(201);
    }

    @Test
    @Category({NegativeTest.class})
    public void naoDeveRealizarPagamentoSemVenda(){
        given()
                    .log().all()
                    .contentType("application/json")
                    .auth().basic(USER_FOR_TEST, PASSWORD_FOR_TEST)
                    .body("{\"valorPago\": 1,\"dataPagamento\": \"09/03/2020\",\"idUsuario\": \"155\", \"cancelada\": \"false\", \"dataHoraCancelamento\": null }")
                .when()
                    .request(Method.POST, PAGAMENTO)
                .then()
                    .statusCode(400);
    }

    @Test
    @Category({PositiveTest.class})
    public void deveRealizarCancelamentoDePagamento(){
        given()
                    .log().all()
                    .contentType("application/json")
                    .auth().basic(USER_FOR_TEST, PASSWORD_FOR_TEST)
                    .pathParam("codigoPagamento", 793)
                .when()
                    .put(PUT_PAGAMENTO_CANCELAR+"{codigoPagamento}")
                .then()
                    .statusCode(204);
    }


}
