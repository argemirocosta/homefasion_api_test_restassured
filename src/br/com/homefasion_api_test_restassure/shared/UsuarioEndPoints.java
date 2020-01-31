package br.com.homefasion_api_test_restassure.shared;

import static br.com.homefasion_api_test_restassure.conf.ConfiguracaoPrincipal.ENDPOINT_PRINCIPAL_PARA_TESTE;

public class UsuarioEndPoints {

    private UsuarioEndPoints() {}

    public static final String GET_USUARIO = ENDPOINT_PRINCIPAL_PARA_TESTE+"usuarios/";

    public static final String GET_USUARIO_NO_AR = GET_USUARIO +"servidor";

    public static final String GET_USUARIO_ESPECIFICO = GET_USUARIO +"155/";

}
