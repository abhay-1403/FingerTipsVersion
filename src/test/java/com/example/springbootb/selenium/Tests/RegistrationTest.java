package com.example.springbootb.selenium.Tests;

import com.example.springbootb.selenium.PageObjects.RegistrationPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;

@Sql({"/data.sql"})
class RegistrationTest {
    private WebDriver webDriver;
    private RegistrationPage registrationPage;

    @BeforeAll
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void openHomePage() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        this.webDriver = new ChromeDriver(options);
        this.registrationPage = new RegistrationPage(this.webDriver);
        this.registrationPage.goToRegistrationPage();
        this.registrationPage.goFullScreen();
    }

    @Test
    void testAllFieldsVisible() {
        assertThat(this.registrationPage.findFirstNameField()).isNotNull();
        assertThat(this.registrationPage.findLastNameField()).isNotNull();
        assertThat(this.registrationPage.findEmailField()).isNotNull();
        assertThat(this.registrationPage.findPasswordField()).isNotNull();
    }
    @Test
    void testValidRegistration() {
        this.registrationPage.findFirstNameField().sendKeys("Person1FName");
        this.registrationPage.findLastNameField().sendKeys("Person1LName");
        this.registrationPage.findEmailField().sendKeys("person1@email.com");
        this.registrationPage.findPasswordField().sendKeys("password");
        this.registrationPage.clickSubmit();
        assertThat(this.registrationPage.findSuccessfulRegistrationMessage()).isNotNull();
    }
    @Test
    void testRegistrationOfExistingEmail() {
        this.registrationPage.findFirstNameField().sendKeys("Person2FName");
        this.registrationPage.findLastNameField().sendKeys("Person2LName");
        this.registrationPage.findEmailField().sendKeys("person1@email.com");
        this.registrationPage.findPasswordField().sendKeys("password2");
        this.registrationPage.clickSubmit();
        assertThat(this.registrationPage.findEmailErrorMessage()).isNotNull();
        assertThat(this.registrationPage.findEmailErrorMessage().getText()).isEqualTo("There is already a user with the same email");
    }
    @Test
    void testLoginHereLink() {
        this.registrationPage.clickLoginHere();
        assertThat(this.webDriver.getCurrentUrl()).isEqualTo(this.registrationPage.getBaseUrl() + "/adminLogin");
    }
    @AfterEach
    void tearDown() {
        if(this.webDriver != null) {
            this.webDriver.quit();
        }
    }
}
