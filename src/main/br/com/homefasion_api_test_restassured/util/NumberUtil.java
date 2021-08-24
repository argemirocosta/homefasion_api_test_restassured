package br.com.homefasion_api_test_restassured.util;

import java.util.Random;

public class NumberUtil {

    public static String gerarNumeroRandomico(){
        int valorMinimo = 100;
        int valorMaximo = 1000;
        Random random = new Random();
        Integer numeroGerado = random.nextInt(valorMinimo) + valorMaximo;

        return numeroGerado.toString();

    }

}
