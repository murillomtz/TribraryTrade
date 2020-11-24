package br.ucsal.selenium;

import org.jbehave.core.annotations.*;
import org.junit.Assert;

public class SeleniumJbeHave {

    LoginSelenium login = new LoginSelenium();

    @BeforeStory
    public void scenarioSetup() {
        login.entrar();
    }

    @AfterStory
    public void scenarioAfter() {
        login.teardown();
    }


    @Given("o site com o link $link")
    public void inicar(String link) {
        login.inicio(link);

    }

    @When("informo um email $email")
    public void selecionarEmail(String email) {
        login.informarEmail(email);
    }

    @When("informo uma senha $senha")
    public void selecionarSenha(String senha) {
        login.informarSenha(senha);
    }


    @Then("um link esperado é $linkEsperado")
    public void verificarLink(String linkEsperado) {
        String linkObtido = login.obterResultado();

        System.out.println("LINK ESPERADO" + linkObtido);
        Assert.assertEquals(linkEsperado, linkObtido);
    }
}
