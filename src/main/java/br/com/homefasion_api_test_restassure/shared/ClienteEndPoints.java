package br.com.homefasion_api_test_restassure.shared;

import static br.com.homefasion_api_test_restassure.shared.config.ConfiguracaoPrincipal.*;

public class ClienteEndPoints {

    private ClienteEndPoints() {}

    public static final String GET_CLIENTE = ENDPOINT_PRINCIPAL_PARA_TESTE+"cliente/";

    public static final String GET_CLIENTE_NO_AR = GET_CLIENTE +"servidor";

    public static final String GET_CLIENTE_ESPECIFICO = GET_CLIENTE +"241/";

    public static final String GET_CLIENTES_POR_USUARIO = GET_CLIENTE +"usuario/155";

    public static final String GET_CLIENTES_POR_NOME_USUARIO = GET_CLIENTE +"usuario/nome/ARGEMIRO";

}
