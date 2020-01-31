package br.com.homefasion_api_test_restassure.shared;

import static br.com.homefasion_api_test_restassure.conf.ConfiguracaoPrincipal.ENDPOINT_PRINCIPAL_PARA_TESTE;

public class VendaEndPoints {

    private VendaEndPoints() {}

    public static final String GET_VENDA = ENDPOINT_PRINCIPAL_PARA_TESTE+"venda/";

    public static final String GET_VENDA_NO_AR = GET_VENDA +"servidor";

    public static final String GET_VENDA_ESPECIFICA = GET_VENDA +"374";

    public static final String GET_VENDAS_POR_USUARIO = GET_VENDA +"usuario/155";

}
