package com.example.springbootb.selenium.Tests;

import com.example.springbootb.selenium.PageObjects.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginTest {
    private WebDriver webDriver;
    private LoginPage loginPage;

    @BeforeAll
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }
    @BeforeEach
    public void openHomePage() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        this.webDriver = new ChromeDriver(options);
        this.loginPage = new LoginPage(this.webDriver);
        this.loginPage.goToAdminPage();
        this.loginPage.goFullScreen();
    }

    @Test
    void testAllFieldsVisible() {
        assertThat(this.loginPage.findEmailField()).isNotNull();
        assertThat(this.loginPage.findPasswordField()).isNotNull();
    }

    @Test
    void testAdminLogin() {
        this.loginPage.findEmailField().sendKeys("admin@test.com");
        this.loginPage.findPasswordField().sendKeys("adminpass");
        this.loginPage.clickSubmit();
        assertThat(this.loginPage.getCurrentUrl()).isEqualTo(this.loginPage.getBaseUrl()+"/admin/posts");
    }

    @Test
    void testUserLogin() {
        this.loginPage.findEmailField().sendKeys("user@test.com");
        this.loginPage.findPasswordField().sendKeys("userpass");
        this.loginPage.clickSubmit();
        assertThat(this.loginPage.getCurrentUrl()).isEqualTo(this.loginPage.getBaseUrl() + "/admin/posts");
    }

    @Test
    void testSignUpLinkWorking() {
        this.loginPage.clickSignUp();
        assertThat(this.loginPage.getCurrentUrl()).isEqualTo(this.loginPage.getBaseUrl()+"/register");
    }

    @AfterEach
    public void tearDown() {
        this.webDriver.quit();
    }
}
