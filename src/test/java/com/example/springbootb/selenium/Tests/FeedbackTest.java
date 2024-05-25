package com.example.springbootb.selenium.Tests;

import com.example.springbootb.selenium.PageObjects.FeedbackPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import static org.assertj.core.api.Assertions.assertThat;

public class FeedbackTest {
    private WebDriver webDriver;
    private FeedbackPage feedbackPage;
    @BeforeAll
    public static void setupClass() {
        // No need to setup SafariDriver as it comes with macOS
    }
    @BeforeEach
    public void openHomePage() {
        SafariOptions options = new SafariOptions();
        this.webDriver = new SafariDriver(options);
        this.feedbackPage = new FeedbackPage(this.webDriver);
        this.feedbackPage.goToFeedbackPage();
        this.feedbackPage.goFullScreen();
    }

    @Test
    void testAllValidFields() {
        assertThat(this.feedbackPage.findNameField()).isNotNull();
        assertThat(this.feedbackPage.findEmailField()).isNotNull();
        assertThat(this.feedbackPage.findFeedbackField()).isNotNull();
    }
    @Test
    void testValidFeedback() {
        this.feedbackPage.findNameField().sendKeys("userF userL");
        this.feedbackPage.findEmailField().sendKeys("user@test.com");
        this.feedbackPage.findFeedbackField().sendKeys("This is default feedback");
        this.feedbackPage.clickSubmit();
        assertThat(this.feedbackPage.findSuccessfulFeedbackMessage()).isTrue();
    }

    @AfterEach
    public void tearDown() {
        this.webDriver.quit();
    }
}
