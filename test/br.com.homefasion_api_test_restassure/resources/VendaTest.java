package br.com.homefasion_api_test_restassure.resources;

import br.com.homefasion_api_test_restassure.categories.PositiveTest;
import br.com.homefasion_api_test_restassure.categories.SmokeTest;
import io.restassured.http.Method;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static br.com.homefasion_api_test_restassure.conf.ConfiguracaoPrincipal.*;
import static br.com.homefasion_api_test_restassure.shared.VendaEndPoints.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

public class VendaTest {

    @Test
    @Category({PositiveTest.class})
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
    @Category({PositiveTest.class, SmokeTest.class})
    public void deveListarTodosAsVendas(){
        given()
                .log().all()
                .auth().basic(USUARIO_TESTE_LOGIN, SENHA_TESTE_LOGIN)
                .when()
                .request(Method.GET, GET_VENDA)
                .then()
                .statusCode(200)
                .body("id[0]", is(202))
                .body("valor[0]", is(207f));
    }

    @Test
    @Category({PositiveTest.class, SmokeTest.class})
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
                .body("valor", is(105.5f))
                .body("data", is("01/01/2020"))
                .body("idUsuario", is(155))
                .body("cancelada", is(true));
    }

    @Test
    @Category({PositiveTest.class})
    public void deveListarVendaPorUsuario(){
        given()
                .log().all()
                .auth().basic(USUARIO_TESTE_LOGIN, SENHA_TESTE_LOGIN)
                .when()
                .request(Method.GET, GET_VENDAS_POR_USUARIO)
                .then()
                .statusCode(200)
                .body("id[0]", is(370))
                .body("qtd[0]", is(2))
                .body("valor[0]", is(105.5f));
    }
}
