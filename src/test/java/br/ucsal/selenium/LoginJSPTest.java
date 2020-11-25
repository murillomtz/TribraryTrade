package br.ucsal.selenium;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * ****ATENCAO*****
 * <p>
 * ESSE TESTE SÓ DEVE SER FEITO
 * COM O PROJETO EM EXECUCAO
 * CASO CONTRARIO, FALHARA
 */
public class LoginJSPTest {

    private static WebDriver driver;

    @Before
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @After
    public static void teardown() {
        driver.quit();
    }


    @Test
    public void testarPesquisa() throws InterruptedException {
// Abrir página do Investing.com
        driver.get("http://localhost:8081/login.jsp");


        // Preencher o input de "Pesquisar no site..."
        WebElement formEmail = driver.findElement(By.id("email"));
        WebElement formSenha = driver.findElement(By.id("senha"));
        WebElement btnEntrar = driver.findElement(By.className("btnLogin"));

        formEmail.sendKeys("murillo@email.com");
        formSenha.sendKeys("12345");
        btnEntrar.sendKeys(Keys.ENTER);

        // Obter o conteúdo da página
        Thread.sleep(5000);
        String conteudo = driver.getPageSource();

        // Verificar se retorno inclui "Cogna Educação"

        Assert.assertEquals("http://localhost:8081/Login", driver.getCurrentUrl());
    }
}
