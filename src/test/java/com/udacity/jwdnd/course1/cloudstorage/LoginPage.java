package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Map;

public class LoginPage {
    private Map<String, String> data;
    private int timeout = 15;
    private JavascriptExecutor js;

    @FindBy(id = "signup-link")
    private WebElement clickHereToSignUp;

    @FindBy(id = "submit-button")
    private WebElement login;

    private final String pageLoadedText = "Click here to sign up";

    private final String pageUrl = "/login";

    @FindBy(id = "inputPassword")
    private WebElement password;

    @FindBy(id = "inputUsername")
    private WebElement username;

    public LoginPage() {
    }

    public LoginPage(WebDriver driver) {
        this();
        PageFactory.initElements(driver, this);
        js = (JavascriptExecutor) driver;
    }

    public LoginPage(WebDriver driver, Map<String, String> data) {
        this(driver);
        this.data = data;
    }

    public LoginPage(WebDriver driver, Map<String, String> data, int timeout) {
        this(driver, data);
        this.timeout = timeout;
    }

    public LoginPage clickClickHereToSignUpLink() {
        js.executeScript("arguments[0].click();", clickHereToSignUp);
        return this;
    }

    public LoginPage clickLoginButton() {
        js.executeScript("arguments[0].click();", login);
        return this;
    }

    public LoginPage fill() {
        setUsernameTextField();
        setPasswordPasswordField();
        return this;
    }

    public LoginPage fillAndSubmit() {
        fill();
        return submit();
    }

    public LoginPage setPasswordPasswordField() {
        return setPasswordPasswordField(data.get("PASSWORD"));
    }

    public LoginPage setPasswordPasswordField(String passwordValue) {
        password.sendKeys(passwordValue);
        return this;
    }

    public LoginPage setUsernameTextField() {
        return setUsernameTextField(data.get("USERNAME"));
    }

    public LoginPage setUsernameTextField(String usernameValue) {
        username.sendKeys(usernameValue);
        return this;
    }
    public LoginPage submit() {
        clickLoginButton();
        return this;
    }

    public LoginPage verifyPageLoaded(WebDriver driver) {
        (new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getPageSource().contains(pageLoadedText);
            }
        });
        return this;
    }

    public LoginPage verifyPageUrl(WebDriver driver) {
        (new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getCurrentUrl().contains(pageUrl);
            }
        });
        return this;
    }
}
