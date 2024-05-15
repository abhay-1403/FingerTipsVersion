package com.example.springbootb.selenium.PageObjects;

import lombok.Getter;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.awt.peer.TextComponentPeer;

public class ManagePostPage {
    private WebDriver webDriver;
    @Getter
    private String baseUrl = "http://127.0.0.1:5000";

    public ManagePostPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }
    public void goFullScreen() {
        this.webDriver.manage().window().maximize();
    }

    public void adminLogin() {
        LoginPage loginPage = new LoginPage(this.webDriver);
        loginPage.goToLoginPage();
        this.goFullScreen();
        loginPage.findEmailField().sendKeys("admin@test.com");
        loginPage.findPasswordField().sendKeys("adminpass");
        loginPage.clickSubmit();
    }
    public void userLogin() {
        LoginPage loginPage = new LoginPage(this.webDriver);
        loginPage.goToLoginPage();
        this.goFullScreen();
        loginPage.findEmailField().sendKeys("user@test.com");
        loginPage.findPasswordField().sendKeys("userpass");
        loginPage.clickSubmit();
    }

    public void clickLogout() {
        this.webDriver.findElement(By.xpath("//a[contains(text(),'Logout')]")).click();
    }

    public String getCurrentUrl() {
        return this.webDriver.getCurrentUrl();
    }

    public void clickNewPost() {
        this.webDriver.findElement(By.xpath("//a[contains(text(),'New Post')]")).click();
    }

    public WebElement findTitleField() {
        return this.webDriver.findElement(By.id("title"));
    }

    public WebElement findShorDescField() {
        return this.webDriver.findElement(By.id("shortDescription"));
    }

    public void clickSubmit() {
        WebElement submitButton = this.webDriver.findElement(By.xpath("//button[contains(text(),'Submit')]"));
        submitButton.submit();
    }

    public WebElement findContentField() {
        return this.webDriver.findElement(By.id("content-field"));
    }

    public WebElement findSearchField() {
        return this.webDriver.findElement(By.id("post-search"));
    }

    public WebElement findPostTitle(String title) {
        WebElement postTable = this.webDriver.findElement(By.id("post-table"));
        WebElement postTableBody = postTable.findElement(By.tagName("tbody"));
        for(WebElement row : postTableBody.findElements(By.tagName("tr"))) {
            if(row.findElement(By.xpath("//td[2]")).getText().equals(title)) {
                return row;
            }
        }
        return null;
    }

    public void viewPost(WebElement post) {
        post.findElement(By.xpath("//a[contains(text(),'View')]")).click();
    }

    public String fetchPostContent() {
        return this.webDriver.findElement(By.id("post-content")).getText();
    }

    public void editPost(WebElement post) {
        post.findElement(By.xpath("//a[contains(text(),'Edit')]")).click();
    }

    public void hitSearch() {
        this.webDriver.findElement(By.xpath("//button[contains(text(),'Search')]")).click();
    }

    public void deletePost(WebElement post) {
        post.findElement(By.xpath("//a[contains(text(),'Delete')]")).click();
    }
}
