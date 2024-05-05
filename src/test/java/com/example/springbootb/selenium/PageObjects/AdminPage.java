package com.example.springbootb.selenium.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AdminPage {
    private WebDriver webDriver;
    private String baseUrl = "http://127.0.0.1:5000";

    public String getBaseUrl() {
        return baseUrl;
    }
    public AdminPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }
    public void goToAdminPage() {
        this.webDriver.get(this.baseUrl+"/login");
    }

    public void goFullScreen() {
        this.webDriver.manage().window().maximize();
    }

    public WebElement findEmailField() {
        return this.webDriver.findElement(By.id("username"));
    }

    public WebElement findPasswordField() {
        return this.webDriver.findElement(By.id("password"));
    }

    public void clickSubmit() {
        this.webDriver.findElement(By.id("submit-btn")).submit();
    }

    public String getCurrentUrl() {
        return this.webDriver.getCurrentUrl();
    }

    public void clickSignUp() {
        this.webDriver.findElement(By.xpath("//a[contains(text(),'Register/SignUp Here')]")).click();
    }
}
