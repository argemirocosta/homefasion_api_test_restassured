package br.com.homefasion_api_test_restassure.resources;

import io.restassured.http.Method;
import org.junit.Test;

import static br.com.homefasion_api_test_restassure.conf.ConfiguracaoPrincipal.*;
import static br.com.homefasion_api_test_restassure.shared.PagamentoEndPoints.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

public class PagamentoTest {

    @Test
    public void deveVerificarSePagamentoNoAr(){
        given()
                .log().all()
                .auth().basic(USUARIO_TESTE_LOGIN, SENHA_TESTE_LOGIN)
                .when()
                .request(Method.GET, GET_PAGAMENTO_NO_AR)
                .then()
                .statusCode(200)
                .body(is("Servidor no ar"));
    }

    @Test
    public void deveListarTodosOsPagamento(){
        given()
                .log().all()
                .auth().basic(USUARIO_TESTE_LOGIN, SENHA_TESTE_LOGIN)
                .when()
                .request(Method.GET, GET_PAGAMENTO)
                .then()
                .statusCode(200)
                .body("id[0]", is(305))
                .body("valorPago[0]", is(37f));
    }

    @Test
    public void deveListarPagamentoEspecifico(){
        given()
                .log().all()
                .auth().basic(USUARIO_TESTE_LOGIN, SENHA_TESTE_LOGIN)
                .when()
                .request(Method.GET, GET_PAGAMENTO_ESPECIFICO)
                .then()
                .statusCode(200)
                .body("id", is(374))
                .body("idVenda", is(179))
                .body("valorPago", is(37f))
                .body("dataPagamento", is("18/04/2018"))
                .body("usuario.id", is(1));
    }

    @Test
    public void deveListarPagamentoPorCliente(){
        given()
                .log().all()
                .auth().basic(USUARIO_TESTE_LOGIN, SENHA_TESTE_LOGIN)
                .when()
                .request(Method.GET, GET_PAGAMENTOS_POR_CLIENTE)
                .then()
                .statusCode(200)
                .body("id[0]", is(336))
                .body("idVenda[0]", is(167))
                .body("valorPago[0]", is(80f));
    }

    @Test
    public void deveListarPagamentoPorVenda(){
        given()
                .log().all()
                .auth().basic(USUARIO_TESTE_LOGIN, SENHA_TESTE_LOGIN)
                .when()
                .request(Method.GET, GET_PAGAMENTOS_POR_VENDA)
                .then()
                .statusCode(200)
                .body("id[0]", is(336))
                .body("idVenda[0]", is(167))
                .body("dataPagamento[0]", is("27/02/2018"))
                .body("valorPago[0]", is(80f));
    }
}
