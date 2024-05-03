package com.example.springbootb.selenium.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RegistrationPage {
    private WebDriver webDriver;
    private String baseUrl = "http://127.0.0.1:5000";

    public RegistrationPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }
    public void goToRegistrationPage() {
        this.webDriver.get(this.baseUrl+"/register");
    }
    public void enterEmail(String email) {
        this.webDriver.findElement(By.id("email")).sendKeys(email);
    }

    public WebElement findFirstNameField() {
        return this.webDriver.findElement(By.id("firstName"));
    }

    public WebElement findLastNameField() {
        return this.webDriver.findElement(By.id("lastName"));
    }

    public WebElement findEmailField() {
        return this.webDriver.findElement(By.id("email"));
    }

    public WebElement findPasswordField() {
        return this.webDriver.findElement(By.id("password"));
    }

    public void clickSubmit() {
        WebElement submitButton = this.webDriver.findElement(By.id("registerBtn"));
        submitButton.submit();
    }

    public WebElement findSuccessfulRegistrationMessage() {
        return this.webDriver.findElement(By.xpath("//div[contains(text(),'You have successfully registered')]"));
    }

    public WebElement findEmailErrorMessage() {
        return this.webDriver.findElement(By.id("email-error"));
    }

    public String getBaseUrl() {
        return this.baseUrl;
    }

    public void clickLoginHere() {
        WebElement loginHereLink = this.webDriver.findElement(By.xpath("//a[contains(text(),'Login Here')]"));
        loginHereLink.click();
    }

    public void goFullScreen() {
        this.webDriver.manage().window().maximize();
    }
}
