package com.example.springbootb.selenium.Tests;

import com.example.springbootb.selenium.PageObjects.AdminPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.assertj.core.api.Assertions.assertThat;

public class AdminTest {
    private WebDriver webDriver;
    private AdminPage adminPage;

    @BeforeAll
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }
    @BeforeEach
    public void openHomePage() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        this.webDriver = new ChromeDriver(options);
        this.adminPage = new AdminPage(this.webDriver);
        this.adminPage.goToAdminPage();
        this.adminPage.goFullScreen();
    }

    @Test
    void testAllFieldsVisible() {
        assertThat(this.adminPage.findEmailField()).isNotNull();
        assertThat(this.adminPage.findPasswordField()).isNotNull();
    }

    @Test
    void testAdminLogin() {
        this.adminPage.findEmailField().sendKeys("admin@test.com");
        this.adminPage.findPasswordField().sendKeys("adminpass");
        this.adminPage.clickSubmit();
        assertThat(this.adminPage.getCurrentUrl()).isEqualTo(this.adminPage.getBaseUrl()+"/admin/posts");
    }

    @Test
    public void testSignUpLinkWorking() {
        this.adminPage.clickSignUp();
        assertThat(this.adminPage.getCurrentUrl()).isEqualTo(this.adminPage.getBaseUrl()+"/register");
    }

    @AfterEach
    public void tearDown() {
        this.webDriver.quit();
    }
}
