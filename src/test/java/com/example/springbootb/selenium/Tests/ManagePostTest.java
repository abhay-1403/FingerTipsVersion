package com.example.springbootb.selenium.Tests;

import com.example.springbootb.selenium.PageObjects.ManagePostPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.assertj.core.api.Assertions.assertThat;

public class ManagePostTest {
    private WebDriver webDriver;
    private ManagePostPage managePostPage;
    @BeforeAll
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }
    @BeforeEach
    public void openHomePage() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        this.webDriver = new ChromeDriver(options);
        this.managePostPage = new ManagePostPage(this.webDriver);
    }
    //admin tests
    @Test
    void testnewPostCreation() {
        this.managePostPage.adminLogin();
        this.managePostPage.clickNewPost();
        assertThat(this.managePostPage.getCurrentUrl())
                .isEqualTo(this.managePostPage.getBaseUrl()+"/admin/posts/newpost");
        this.managePostPage.findTitleField().sendKeys("New Post Title");
        this.managePostPage.findShorDescField().sendKeys("New Post Description");
        this.managePostPage.findContentField().sendKeys("New Post Content");
        this.managePostPage.clickSubmit();
        assertThat(this.managePostPage.getCurrentUrl())
                .isEqualTo(this.managePostPage.getBaseUrl()+"/admin/posts");
        this.managePostPage.findSearchField().sendKeys("New Post Title");
        this.managePostPage.hitSearch();
        WebElement post = this.managePostPage.findPostTitle("New Post Title");
        this.managePostPage.viewPost(post);
        assertThat(this.managePostPage.fetchPostContent()).isEqualTo("New Post Content");
    }

    @Test
    void testEditPost() {
        this.managePostPage.adminLogin();
        this.managePostPage.clickNewPost();
        assertThat(this.managePostPage.getCurrentUrl())
                .isEqualTo(this.managePostPage.getBaseUrl()+"/admin/posts/newpost");
        this.managePostPage.findTitleField().sendKeys("Test Post Title");
        this.managePostPage.findShorDescField().sendKeys("Test Post Description");
        this.managePostPage.findContentField().sendKeys("Test Post Content");
        this.managePostPage.clickSubmit();
        assertThat(this.managePostPage.getCurrentUrl())
                .isEqualTo(this.managePostPage.getBaseUrl()+"/admin/posts");
        this.managePostPage.findSearchField().sendKeys("Test Post Title");
        this.managePostPage.hitSearch();
        WebElement editedPost = this.managePostPage.findPostTitle("Test Post Title");
        this.managePostPage.editPost(editedPost);
        WebElement field = this.managePostPage.findTitleField();
        field.clear();
        field.sendKeys("Edited Post Title");
        field = this.managePostPage.findShorDescField();
        field.clear();
        field.sendKeys("Edited Short Description");
        field = this.managePostPage.findContentField();
        field.clear();
        field.sendKeys("Edited Post Content");
        this.managePostPage.clickSubmit();
        assertThat(this.managePostPage.getCurrentUrl())
                .isEqualTo(this.managePostPage.getBaseUrl()+"/admin/posts");
        this.managePostPage.findSearchField().sendKeys("Edited Post Title");
        this.managePostPage.hitSearch();
        WebElement post = this.managePostPage.findPostTitle("Edited Post Title");
        this.managePostPage.viewPost(post);
        assertThat(this.managePostPage.fetchPostContent()).isEqualTo("Edited Post Content");
    }

    @Test
    void testDeletePost() {
        this.managePostPage.adminLogin();
        this.managePostPage.clickNewPost();
        assertThat(this.managePostPage.getCurrentUrl())
                .isEqualTo(this.managePostPage.getBaseUrl()+"/admin/posts/newpost");
        this.managePostPage.findTitleField().sendKeys("Delete Post Title");
        this.managePostPage.findShorDescField().sendKeys("Delete Post Description");
        this.managePostPage.findContentField().sendKeys("Delete Post Content");
        this.managePostPage.clickSubmit();
        assertThat(this.managePostPage.getCurrentUrl())
                .isEqualTo(this.managePostPage.getBaseUrl()+"/admin/posts");
        this.managePostPage.findSearchField().sendKeys("Delete Post Title");
        this.managePostPage.hitSearch();
        WebElement post = this.managePostPage.findPostTitle("Delete Post Title");
        this.managePostPage.deletePost(post);
        this.managePostPage.findSearchField().sendKeys("Delete Post Title");
        this.managePostPage.hitSearch();
        assertThat(this.managePostPage.findPostTitle("Test Post Title")).isNull();
    }

    @Test
    void testComment() {

    }
    @Test
    void testLogout() {
        this.managePostPage.adminLogin();
        this.managePostPage.clickLogout();
        assertThat(this.managePostPage.getCurrentUrl()).isEqualTo(this.managePostPage.getBaseUrl()+"/login?logout");
    }

//    @AfterEach
//    public void tearDown() {
//        this.webDriver.quit();
//    }
}
