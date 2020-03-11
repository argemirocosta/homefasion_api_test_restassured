package br.com.homefasion_api_test_restassure.shared;

import static br.com.homefasion_api_test_restassure.conf.ConfiguracaoPrincipal.*;

public class ClienteEndPoints {

    private ClienteEndPoints() {}

    public static final String CLIENTE = ENDPOINT_PRINCIPAL_PARA_TESTE+"cliente/";

    public static final String GET_CLIENTE_NO_AR = CLIENTE +"servidor";

    public static final String GET_CLIENTE_ESPECIFICO = CLIENTE +"234/";

    public static final String GET_CLIENTES_POR_USUARIO = CLIENTE +"usuario/155";

    public static final String GET_CLIENTES_POR_NOME_USUARIO = CLIENTE +"usuario/nome/ARGEMIRO";

}
