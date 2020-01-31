package br.com.homefasion_api_test_restassure.shared;

import static br.com.homefasion_api_test_restassure.conf.ConfiguracaoPrincipal.ENDPOINT_PRINCIPAL_PARA_TESTE;

public class PagamentoEndPoints {

    private PagamentoEndPoints() {}

    public static final String GET_PAGAMENTO = ENDPOINT_PRINCIPAL_PARA_TESTE+"pagamento/";

    public static final String GET_PAGAMENTO_NO_AR = GET_PAGAMENTO +"servidor";

    public static final String GET_PAGAMENTO_ESPECIFICO = GET_PAGAMENTO +"374";

    public static final String GET_PAGAMENTOS_POR_CLIENTE = GET_PAGAMENTO +"cliente/79";

    public static final String GET_PAGAMENTOS_POR_VENDA = GET_PAGAMENTO +"venda/167";

}
