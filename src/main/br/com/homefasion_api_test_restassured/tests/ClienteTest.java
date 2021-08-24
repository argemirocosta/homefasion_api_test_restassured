package br.com.homefasion_api_test_restassured.tests;

import br.com.homefasion_api_test_restassured.categories.NegativeTest;
import br.com.homefasion_api_test_restassured.categories.PositiveTest;
import br.com.homefasion_api_test_restassured.categories.SmokeTest;
import br.com.homefasion_api_test_restassured.conf.BaseTest;
import br.com.homefasion_api_test_restassured.data.ClienteData;
import br.com.homefasion_api_test_restassured.dto.ClienteCadastrarDTO;
import br.com.homefasion_api_test_restassured.dto.UsuarioDTO;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static br.com.homefasion_api_test_restassured.shared.ClienteEndPoints.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;


public class ClienteTest extends BaseTest {

    @Test
    @Category({PositiveTest.class})
    public void deveVerificarSeClienteNoAr(){
        given()
                    .auth().basic(USER_FOR_TEST, PASSWORD_FOR_TEST)
                .when()
                    .get(GET_CLIENTE_NO_AR)
                .then()
                    .statusCode(200)
                    .body(is("Servidor no ar"));
    }

    @Test
    @Category({NegativeTest.class})
    public void naoDeveAcessarSemAutenticacao(){
        Response response = RestAssured.get(GET_CLIENTE_NO_AR);

        assertEquals(401, response.getStatusCode());
    }

    @Test
    @Category({PositiveTest.class, SmokeTest.class})
    public void deveListarTodosOsClientes(){
        given()
                    .auth().basic(USER_FOR_TEST, PASSWORD_FOR_TEST)
                .when()
                    .get(CLIENTE)
                .then()
                    .statusCode(200)
                    .body("content.id", contains(43, 44, 45, 46, 47, 48, 49, 50, 51, 52));
    }

    @Test
    @Category({PositiveTest.class, SmokeTest.class})
    public void deveListarClienteEspecifico(){
        given()
                    .auth().basic(USER_FOR_TEST, PASSWORD_FOR_TEST)
                .when()
                    .get(GET_CLIENTE_ESPECIFICO)
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
                    .auth().basic(USER_FOR_TEST, PASSWORD_FOR_TEST)
                .when()
                    .get(GET_CLIENTES_POR_USUARIO)
                .then()
                    .statusCode(200)
                    .body("$", notNullValue());
    }

    @Test
    @Category({PositiveTest.class})
    public void deveListarClientesPorNomeUsuario(){
        given()
                    .auth().basic(USER_FOR_TEST, PASSWORD_FOR_TEST)
                .when()
                    .get(GET_CLIENTES_POR_NOME_USUARIO)
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
                    .contentType("application/json")
                    .auth().basic(USER_FOR_TEST, PASSWORD_FOR_TEST)
                    .body(cliente)
                .when()
                    .post(CLIENTE)
                .then()
                    .statusCode(201);
    }

    @Test
    @Category({NegativeTest.class})
    public void naoDeveSalvarClienteSemNome(){

        UsuarioDTO usuario = new UsuarioDTO(155);
        ClienteCadastrarDTO cliente = new ClienteCadastrarDTO(null, "90451718054", 987654321, usuario);

        given()
                    .contentType("application/json")
                    .auth().basic(USER_FOR_TEST, PASSWORD_FOR_TEST)
                    .body(cliente)
                .when()
                    .post(CLIENTE)
                .then()
                    .statusCode(400);
    }

    @Test
    @Category({PositiveTest.class})
    public void deveAlterarCliente(){

        UsuarioDTO usuario = new UsuarioDTO(155);
        ClienteCadastrarDTO cliente = new ClienteCadastrarDTO("TESTE 109 alterado", "90451718054", 987654321, usuario);

        given()
                    .contentType("application/json")
                    .auth().basic(USER_FOR_TEST, PASSWORD_FOR_TEST)
                    .body(cliente)
                    .pathParam("id", 109)
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
                    .contentType("application/json")
                    .auth().basic(USER_FOR_TEST, PASSWORD_FOR_TEST)
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
                    .contentType("application/json")
                    .auth().basic(USER_FOR_TEST, PASSWORD_FOR_TEST)
                .when()
                    .delete(CLIENTE+ ClienteData.dataForRemoveCliente())
                .then()
                    .statusCode(204);
    }

    @Test
    @Category({NegativeTest.class})
    public void naoDeveRemoverClienteQueNaoExiste(){
        given()
                    .contentType("application/json")
                    .auth().basic(USER_FOR_TEST, PASSWORD_FOR_TEST)
                    .pathParam("id", 234)
                .when()
                    .delete(CLIENTE+"{id}")
                .then()
                    .statusCode(500);
    }

}

