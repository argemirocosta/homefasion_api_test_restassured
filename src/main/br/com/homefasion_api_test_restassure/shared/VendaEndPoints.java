package br.com.homefasion_api_test_restassure.shared;

import static br.com.homefasion_api_test_restassure.conf.ConfiguracaoPrincipal.ENDPOINT_PRINCIPAL_PARA_TESTE;

public class VendaEndPoints {

    private VendaEndPoints() {}

    public static final String VENDA = ENDPOINT_PRINCIPAL_PARA_TESTE+"venda/";

    public static final String GET_VENDA_NO_AR = VENDA +"servidor";

    public static final String VENDA_ESPECIFICA = VENDA +"374";

    public static final String GET_VENDAS_POR_USUARIO = VENDA +"usuario/155";

    public static final String PUT_VENDA_CANCELAR = VENDA +"cancela/";

}
