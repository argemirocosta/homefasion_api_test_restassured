package br.com.homefasion_api_test_restassured.shared;

public class PagamentoEndPoints {

    private PagamentoEndPoints() {}

    public static final String PAGAMENTO = "pagamento/";

    public static final String GET_PAGAMENTO_NO_AR = PAGAMENTO +"servidor";

    public static final String PAGAMENTO_ESPECIFICO = PAGAMENTO +"374";

    public static final String GET_PAGAMENTOS_POR_CLIENTE = PAGAMENTO +"cliente/79";

    public static final String GET_PAGAMENTOS_POR_VENDA = PAGAMENTO +"venda/167";

    public static final String PUT_PAGAMENTO_CANCELAR = PAGAMENTO +"cancela/";

}
