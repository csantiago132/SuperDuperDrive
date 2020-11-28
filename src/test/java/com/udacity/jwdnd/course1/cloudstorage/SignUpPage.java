package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Map;

public class SignUpPage {
    private Map<String, String> data;
    private WebDriver driver;
    private int timeout = 15;

    @FindBy(css = "a[href='/login']")
    private WebElement backToLogin;

    @FindBy(id = "inputFirstName")
    private WebElement firstName;

    @FindBy(id = "inputLastName")
    private WebElement lastName;

    private final String pageLoadedText = "";

    private final String pageUrl = "/signup";

    @FindBy(id = "inputPassword")
    private WebElement password;

    @FindBy(id = "submit-button")
    private WebElement signUp;

    @FindBy(id = "inputUsername")
    private WebElement username;

    public SignUpPage() {
    }

    public SignUpPage(WebDriver driver) {
        this();
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public SignUpPage(WebDriver driver, Map<String, String> data) {
        this(driver);
        this.data = data;
    }

    public SignUpPage(WebDriver driver, Map<String, String> data, int timeout) {
        this(driver, data);
        this.timeout = timeout;
    }

    public SignUpPage clickBackToLoginLink() {
        backToLogin.click();
        return this;
    }

    public SignUpPage clickSignUpButton() {
        signUp.click();
        return this;
    }

    public SignUpPage fill() {
        setFirstNameTextField();
        setLastNameTextField();
        setUsernameTextField();
        setPasswordPasswordField();
        return this;
    }

    public SignUpPage fillAndSubmit() {
        fill();
        return submit();
    }
    public SignUpPage setFirstNameTextField() {
        return setFirstNameTextField(data.get("FIRST_NAME"));
    }


    public SignUpPage setFirstNameTextField(String firstNameValue) {
        firstName.sendKeys(firstNameValue);
        return this;
    }
    public SignUpPage setLastNameTextField() {
        return setLastNameTextField(data.get("LAST_NAME"));
    }

    public SignUpPage setLastNameTextField(String lastNameValue) {
        lastName.sendKeys(lastNameValue);
        return this;
    }

    public SignUpPage setPasswordPasswordField() {
        return setPasswordPasswordField(data.get("PASSWORD"));
    }

    public SignUpPage setPasswordPasswordField(String passwordValue) {
        password.sendKeys(passwordValue);
        return this;
    }

    public SignUpPage setUsernameTextField() {
        return setUsernameTextField(data.get("USERNAME"));
    }

    public SignUpPage setUsernameTextField(String usernameValue) {
        username.sendKeys(usernameValue);
        return this;
    }

    public SignUpPage submit() {
        clickSignUpButton();
        return this;
    }

    public SignUpPage verifyPageLoaded() {
        (new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getPageSource().contains(pageLoadedText);
            }
        });
        return this;
    }

    public SignUpPage verifyPageUrl() {
        (new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getCurrentUrl().contains(pageUrl);
            }
        });
        return this;
    }
}

