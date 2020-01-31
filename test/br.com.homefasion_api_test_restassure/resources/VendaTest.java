package br.com.homefasion_api_test_restassure.resources;

import io.restassured.http.Method;
import org.junit.Test;

import static br.com.homefasion_api_test_restassure.conf.ConfiguracaoPrincipal.*;
import static br.com.homefasion_api_test_restassure.shared.VendaEndPoints.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

public class VendaTest {

    @Test
    public void deveVerificarSeVendaNoAr(){
        given()
                .log().all()
                .auth().basic(USUARIO_TESTE_LOGIN, SENHA_TESTE_LOGIN)
                .when()
                .request(Method.GET, GET_VENDA_NO_AR)
                .then()
                .statusCode(200)
                .body(is("Servidor no ar"));
    }

    @Test
    public void deveListarTodosAsVendas(){
        given()
                .log().all()
                .auth().basic(USUARIO_TESTE_LOGIN, SENHA_TESTE_LOGIN)
                .when()
                .request(Method.GET, GET_VENDA)
                .then()
                .statusCode(200)
                .body("id[0]", is(202));
    }

    @Test
    public void deveListarVendaEspecifica(){
        given()
                .log().all()
                .auth().basic(USUARIO_TESTE_LOGIN, SENHA_TESTE_LOGIN)
                .when()
                .request(Method.GET, GET_VENDA_ESPECIFICA)
                .then()
                .statusCode(200)
                .body("id", is(374))
                .body("cliente.id", is(234))
                .body("qtd", is(2))
                .body("data", is("01/01/2020"))
                .body("idUsuario", is(155))
                .body("cancelada", is(true));
    }

    @Test
    public void deveListarVendaPorUsuario(){
        given()
                .log().all()
                .auth().basic(USUARIO_TESTE_LOGIN, SENHA_TESTE_LOGIN)
                .when()
                .request(Method.GET, GET_VENDAS_POR_USUARIO)
                .then()
                .statusCode(200)
                .body("id[0]", is(370))
                .body("qtd[0]", is(2));
    }
}
