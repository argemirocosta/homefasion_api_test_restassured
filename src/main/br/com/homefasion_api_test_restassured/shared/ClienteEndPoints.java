package br.com.homefasion_api_test_restassured.shared;

public class ClienteEndPoints {

    private ClienteEndPoints() {}

    public static final String CLIENTE = "cliente/";

    public static final String GET_CLIENTE_NO_AR = CLIENTE +"servidor";

    public static final String GET_CLIENTE_ESPECIFICO = CLIENTE +"234/";

    public static final String GET_CLIENTES_POR_USUARIO = CLIENTE +"usuario/155";

    public static final String GET_CLIENTES_POR_NOME_USUARIO = CLIENTE +"usuario/nome/ARGEMIRO";

}
