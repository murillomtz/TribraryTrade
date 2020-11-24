package br.ucsal.jbehave;


import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class JbehaveStoryOne {

    private WebDriver driver;

    //@AfterScenario VERIFICAR
    @Given("estou em um site com o link $link")
    public void getLink(String link) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(link);

    }

    @When("informo o email $email")
    public void informarEmail(String email) {
        WebElement formEmail = driver.findElement(By.id("email"));
        formEmail.sendKeys(email);
    }

    @When("informo a senha $senha")
    public void informarSenha(String senha) {
        WebElement formSenha = driver.findElement(By.id("senha"));
        formSenha.sendKeys(senha);
    }

    @Then("o link esperado é $link")
    public void verificarSituacaoAluno(String link) throws InterruptedException {
        WebElement btnEntrar = driver.findElement(By.className("btnLogin"));
        btnEntrar.sendKeys(Keys.ENTER);

        Thread.sleep(5000);
        String conteudo = driver.getPageSource();

        Assertions.assertEquals(link, driver.getCurrentUrl());
        driver.quit();
    }

}
