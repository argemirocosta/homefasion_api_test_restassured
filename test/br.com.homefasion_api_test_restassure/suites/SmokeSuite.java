package br.com.homefasion_api_test_restassure.suites;

import br.com.homefasion_api_test_restassure.categories.SmokeTest;
import br.com.homefasion_api_test_restassure.resources.ClienteTest;
import br.com.homefasion_api_test_restassure.resources.PagamentoTest;
import br.com.homefasion_api_test_restassure.resources.UsuarioTest;
import br.com.homefasion_api_test_restassure.resources.VendaTest;
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
