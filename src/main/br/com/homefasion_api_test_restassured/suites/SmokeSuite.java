package br.com.homefasion_api_test_restassured.suites;

import br.com.homefasion_api_test_restassured.categories.SmokeTest;
import br.com.homefasion_api_test_restassured.tests.ClienteTest;
import br.com.homefasion_api_test_restassured.tests.PagamentoTest;
import br.com.homefasion_api_test_restassured.tests.UsuarioTest;
import br.com.homefasion_api_test_restassured.tests.VendaTest;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Categories.class)
@Suite.SuiteClasses({
        ClienteTest.class,
        UsuarioTest.class,
        VendaTest.class,
        PagamentoTest.class
})
@Categories.IncludeCategory({SmokeTest.class})

public class SmokeSuite {
}
