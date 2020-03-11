package br.com.homefasion_api_test_restassure.resources;

import br.com.homefasion_api_test_restassure.categories.NegativeTest;
import br.com.homefasion_api_test_restassure.categories.PositiveTest;
import br.com.homefasion_api_test_restassure.categories.SmokeTest;
import br.com.homefasion_api_test_restassure.dto.ClienteCadastrarDTO;
import br.com.homefasion_api_test_restassure.dto.UsuarioDTO;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static br.com.homefasion_api_test_restassure.conf.ConfiguracaoPrincipal.*;
import static br.com.homefasion_api_test_restassure.shared.ClienteEndPoints.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;


public class ClienteTest {

    @Test
    @Category({PositiveTest.class})
    public void deveVerificarSeClienteNoAr(){
        given()
                    .log().all()
                    .auth().basic(USUARIO_TESTE_LOGIN, SENHA_TESTE_LOGIN)
                .when()
                    .request(Method.GET, GET_CLIENTE_NO_AR)
                .then()
                    .statusCode(200)
                    .body(is("Servidor no ar"));
    }

    @Test
    @Category({NegativeTest.class})
    public void naoDeveAcessarSemAutenticacao(){
        Response response = RestAssured.request(Method.GET, GET_CLIENTE_NO_AR);

        assertEquals(401, response.getStatusCode());
    }

    @Test
    @Category({PositiveTest.class, SmokeTest.class})
    public void deveListarTodosOsClientes(){
        given()
                    .log().all()
                    .auth().basic(USUARIO_TESTE_LOGIN, SENHA_TESTE_LOGIN)
                .when()
                    .request(Method.GET, CLIENTE)
                .then()
                    .statusCode(200)
                    .body("content.id", contains(43, 44, 45, 46, 47, 48, 49, 50, 51, 52));
    }

    @Test
    @Category({PositiveTest.class, SmokeTest.class})
    public void deveListarClienteEspecifico(){
        given()
                    .log().all()
                    .auth().basic(USUARIO_TESTE_LOGIN, SENHA_TESTE_LOGIN)
                .when()
                    .request(Method.GET, GET_CLIENTE_ESPECIFICO)
                .then()
                    .statusCode(200)
                    .body("id", is(234))
                    .body("nome", is("Teste Alteracao 03"))
                    .body("cpf", is("90451718054"))
                    .body("telefone1", is(987654321));
    }

    @Test
    @Category({PositiveTest.class})
    public void deveListarClientesPorUsuario(){
        given()
                    .log().all()
                    .auth().basic(USUARIO_TESTE_LOGIN, SENHA_TESTE_LOGIN)
                .when()
                    .request(Method.GET, GET_CLIENTES_POR_USUARIO)
                .then()
                    .statusCode(200)
                    .body("nome[1]", is("DRA. ISIS NOGUEIRA"));
    }

    @Test
    @Category({PositiveTest.class})
    public void deveListarClientesPorNomeUsuario(){
        given()
                    .log().all()
                    .auth().basic(USUARIO_TESTE_LOGIN, SENHA_TESTE_LOGIN)
                .when()
                    .request(Method.GET, GET_CLIENTES_POR_NOME_USUARIO)
                .then()
                    .statusCode(200)
                    .body("$", hasSize(3));
    }

    @Test
    @Category({PositiveTest.class})
    public void deveSalvarCliente(){
        UsuarioDTO usuario = new UsuarioDTO(155);
        ClienteCadastrarDTO cliente = new ClienteCadastrarDTO("TESTE 109", "90451718054", 987654321, usuario);

        given()
                    .log().all()
                    .contentType("application/json")
                    .auth().basic(USUARIO_TESTE_LOGIN, SENHA_TESTE_LOGIN)
                    .body(cliente)
                .when()
                    .request(Method.POST, CLIENTE)
                .then()
                    .statusCode(201);
    }

    @Test
    @Category({NegativeTest.class})
    public void naoDeveSalvarClienteSemNome(){

        UsuarioDTO usuario = new UsuarioDTO(155);
        ClienteCadastrarDTO cliente = new ClienteCadastrarDTO(null, "90451718054", 987654321, usuario);

        given()
                    .log().all()
                    .contentType("application/json")
                    .auth().basic(USUARIO_TESTE_LOGIN, SENHA_TESTE_LOGIN)
                    .body(cliente)
                .when()
                    .request(Method.POST, CLIENTE)
                .then()
                    .statusCode(400);
    }

    @Test
    @Category({PositiveTest.class})
    public void deveAlterarCliente(){

        UsuarioDTO usuario = new UsuarioDTO(155);
        ClienteCadastrarDTO cliente = new ClienteCadastrarDTO("TESTE 109 alterado", "90451718054", 987654321, usuario);

        given()
                    .log().all()
                    .contentType("application/json")
                    .auth().basic(USUARIO_TESTE_LOGIN, SENHA_TESTE_LOGIN)
                    .body(cliente)
                    .pathParam("id", 296)
                .when()
                    .put(CLIENTE+"{id}")
                .then()
                    .statusCode(204);
    }

    @Test
    @Category({NegativeTest.class})
    public void naoDeveAlterarClienteSemNome(){

        UsuarioDTO usuario = new UsuarioDTO(155);
        ClienteCadastrarDTO cliente = new ClienteCadastrarDTO(null, "90451718054", 987654321, usuario);

        given()
                    .log().all()
                    .contentType("application/json")
                    .auth().basic(USUARIO_TESTE_LOGIN, SENHA_TESTE_LOGIN)
                    .body(cliente)
                    .pathParam("id", 234)
                .when()
                    .put(CLIENTE+"{id}")
                .then()
                    .statusCode(500);
    }

    @Test
    @Category({PositiveTest.class})
    public void deveRemoverCliente(){
        given()
                    .log().all()
                    .contentType("application/json")
                    .auth().basic(USUARIO_TESTE_LOGIN, SENHA_TESTE_LOGIN)
                    .pathParam("id", 296)
                .when()
                    .delete(CLIENTE+"{id}")
                .then()
                    .statusCode(204);
    }

    @Test
    @Category({NegativeTest.class})
    public void naoDeveRemoverClienteQueNaoExiste(){
        given()
                    .log().all()
                    .contentType("application/json")
                    .auth().basic(USUARIO_TESTE_LOGIN, SENHA_TESTE_LOGIN)
                    .pathParam("id", 234)
                .when()
                    .delete(CLIENTE+"{id}")
                .then()
                    .statusCode(500);
    }

}

