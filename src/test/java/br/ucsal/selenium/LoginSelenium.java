package br.ucsal.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class LoginSelenium {

    private static WebDriver driver;


    public void entrar() {

        System.setProperty("webdriver.chrome.driver", ".\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);


    }

    //@Test
    public void inicio(String link) {
        driver.get(link);
       
    }


    public void informarEmail(String email) {
        System.out.println("Chegou email");
        WebElement formEmail = driver.findElement(By.id("email"));
        formEmail.sendKeys("murillo@email.com");

    }


    public void informarSenha(String senha) {
        System.out.println("Chegou senha");
        WebElement formSenha = driver.findElement(By.id("senha"));
        formSenha.sendKeys("12345");
        System.out.println("Chegou btn");
        WebElement btnEntrar = driver.findElement(By.className("btnLogin"));
        btnEntrar.sendKeys(Keys.ENTER);

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }


    public String obterResultado() {
        System.out.println("Chegou resultador");
        return driver.getCurrentUrl();
    }


    public static void teardown() {
        System.out.println("Chegou EXIT");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.quit();

    }
}
