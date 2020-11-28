package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Map;

public class ResultPage {
    private Map<String, String> data;
    private int timeout = 15;
    private WebDriverWait wait;
    private JavascriptExecutor js;

    @FindBy(id = "anchorResultSuccess")
    private WebElement here;

    private final String pageLoadedText = "Your changes were successfully saved";

    private final String pageUrl = "/note/handleNote";

    public ResultPage() {
    }

    public ResultPage clickHereLink() {
        js.executeScript("arguments[0].click();", here);
        return this;
    }

    public ResultPage(WebDriver driver) {
        this();
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, timeout);
        js = (JavascriptExecutor) driver;
    }

    public ResultPage(WebDriver driver, Map<String, String> data) {
        this(driver);
        this.data = data;
    }

    public ResultPage(WebDriver driver, Map<String, String> data, int timeout) {
        this(driver, data);
        this.timeout = timeout;
    }

    public ResultPage verifyPageUrl(WebDriver driver) {
        (new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getCurrentUrl().contains(pageUrl);
            }
        });
        return this;
    }

    public ResultPage verifyPageLoaded(WebDriver driver) {
        (new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getPageSource().contains(pageLoadedText);
            }
        });
        return this;
    }

}

