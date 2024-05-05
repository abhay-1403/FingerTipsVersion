package com.example.springbootb.selenium.PageObjects;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FeedbackPage {
    private WebDriver webDriver;
    private String baseUrl = "http://127.0.0.1:5000";

    public FeedbackPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }
    public void goToFeedbackPage() {
        this.webDriver.get(this.baseUrl+"/feedback");
    }

    public void goFullScreen() {
        this.webDriver.manage().window().maximize();
    }

    public WebElement findNameField() {
        return this.webDriver.findElement(By.id("name"));
    }

    public WebElement findEmailField() {
        return this.webDriver.findElement(By.id("email"));
    }

    public WebElement findFeedbackField() {
        return this.webDriver.findElement(By.id("message"));
    }

    public void clickSubmit() {
        WebElement submitButton = this.webDriver.findElement(By.xpath("//button[contains(text(),'Submit Feedback')]"));
        submitButton.submit();
    }

    public boolean findSuccessfulFeedbackMessage() {
        return this.webDriver.findElement(By.xpath("//p[contains(text(),'Feedback has been received. Thank you for your feedback')]")).isDisplayed();
    }
}
