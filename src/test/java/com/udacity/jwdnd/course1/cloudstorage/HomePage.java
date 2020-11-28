package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Map;

public class HomePage {
    private Map<String, String> data;
    private int timeout = 15;
    private WebDriverWait wait;
    private JavascriptExecutor js;

    @FindBy(id = "buttonAddCredential")
    private WebElement addANewCredential;

    @FindBy(id = "btnAddNewNote")
    private WebElement addANewNote;

    @FindBy(css = "#noteModal div.modal-dialog div.modal-content div:nth-of-type(3) button:nth-of-type(1)")
    private WebElement close1;

    @FindBy(css = "#credentialModal div.modal-dialog div.modal-content div:nth-of-type(3) button:nth-of-type(1)")
    private WebElement close2;

    @FindBy(id = "nav-credentials-tab")
    private WebElement credentials;

    @FindBy(id = "anchorDeleteNote")
    private WebElement delete;

    @FindBy(id = "anchorDeleteCredential")
    private WebElement delete2;

    @FindBy(id = "note-description")
    private WebElement description;

    @FindBy(id = "tableNoteTitle")
    private WebElement tableNoteTitle;

    @FindBy(id = "tableNoteDescription")
    private WebElement tableNoteDescription;

    @FindBy(id = "buttonEditNote")
    private WebElement edit;

    @FindBy(id = "buttonEditCredential")
    private WebElement buttonEditCredential;

    @FindBy(id = "nav-files-tab")
    private WebElement files;

    @FindBy(id = "btnLogout")
    private WebElement logout;

    @FindBy(id = "nav-notes-tab")
    private WebElement notes;

    private final String pageLoadedText = "";

    private final String pageUrl = "/home";

    @FindBy(id = "credential-password")
    private WebElement password;

    @FindBy(id = "buttonSaveNote")
    private WebElement saveChanges1;

    @FindBy(id = "buttonSaveCredential")
    private WebElement saveChanges2;

    @FindBy(id = "note-title")
    private WebElement title;

    @FindBy(css = "button.btn.btn-dark")
    private WebElement upload;

    @FindBy(id = "fileUpload")
    private WebElement uploadANewFile;

    @FindBy(id = "credential-url")
    private WebElement url;

    @FindBy(id = "credential-username")
    private WebElement username;

    @FindBy(id = "tblCredentialUrl")
    private WebElement tblCredentialUrl;

    @FindBy(id = "tblCredentialUsername")
    private WebElement tblCredentialUsername;

    @FindBy(id = "tblCredentialPassword")
    private WebElement tblCredentialPassword;

    public HomePage() {
    }

    public HomePage(WebDriver driver) {
        this();
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, timeout);
        js = (JavascriptExecutor) driver;
    }

    public HomePage(WebDriver driver, Map<String, String> data) {
        this(driver);
        this.data = data;
    }

    public HomePage(WebDriver driver, Map<String, String> data, int timeout) {
        this(driver, data);
        this.timeout = timeout;
    }

    public HomePage clickAddANewCredentialButton() {
        js.executeScript("arguments[0].click();", addANewCredential);
        return this;
    }

    public HomePage clickAddANewNoteButton() {
        js.executeScript("arguments[0].click();", addANewNote);
        return this;
    }

    public HomePage clickEditCredentialButton() {
        js.executeScript("arguments[0].click();", buttonEditCredential);
        return this;
    }

    public HomePage clickClose1Button() {
        close1.click();
        return this;
    }

    public HomePage clickClose2Button() {
        close2.click();
        return this;
    }

    public HomePage clickCredentialsLink() {
        js.executeScript("arguments[0].click();", credentials);
        return this;
    }

    public HomePage clickDeleteLink() {
        js.executeScript("arguments[0].click();", delete);
        return this;
    }

    public HomePage clickDeleteLink2() {
        js.executeScript("arguments[0].click();", delete2);
        return this;
    }

    public HomePage clickEditButton() {
        js.executeScript("arguments[0].click();", edit);
        return this;
    }

    public HomePage clickFilesLink() {
        files.click();
        return this;
    }

    public HomePage clickLogoutButton() {
        js.executeScript("arguments[0].click();", logout);
        return this;
    }

    public HomePage clickNotesLink() {
        js.executeScript("arguments[0].click();", notes);
        return this;
    }
    public HomePage clickSaveChanges1Button() {
        js.executeScript("arguments[0].click();", saveChanges1);
        return this;
    }

    public HomePage clickSaveChanges2Button() {
        js.executeScript("arguments[0].click();", saveChanges2);
        return this;
    }

    public HomePage clickUploadButton() {
        upload.click();
        return this;
    }

    public HomePage fill() {
        setTitleTextField();
        setDescriptionTextareaField();
        setUrlTextField();
        setUsernameTextField();
        setPasswordTextField();
        return this;
    }

    public HomePage fillAndSubmit() {
        fill();
        return submit();
    }
    public HomePage setDescriptionTextareaField() {
        return setDescriptionTextareaField(data.get("DESCRIPTION"));
    }

    public HomePage setDescriptionTextareaField(String descriptionValue) {
        wait.until(ExpectedConditions.elementToBeClickable(description)).clear();
        wait.until(ExpectedConditions.elementToBeClickable(description)).sendKeys(descriptionValue);
        return this;
    }

    public HomePage setPasswordTextField() {
        return setPasswordTextField(data.get("PASSWORD"));
    }

    public HomePage setPasswordTextField(String passwordValue) {
        wait.until(ExpectedConditions.elementToBeClickable(password)).clear();
        wait.until(ExpectedConditions.elementToBeClickable(password)).sendKeys(passwordValue);
        return this;
    }

    public HomePage setTitleTextField() {
        return setTitleTextField(data.get("TITLE"));
    }

    public HomePage setTitleTextField(String titleValue) {
        wait.until(ExpectedConditions.elementToBeClickable(title)).clear();
        wait.until(ExpectedConditions.elementToBeClickable(title)).sendKeys(titleValue);
        return this;
    }

    public HomePage setUploadANewFileFileField() {
        return this;
    }

    public HomePage setUrlTextField() {
        return setUrlTextField(data.get("URL"));
    }

    public HomePage setUrlTextField(String urlValue) {
        wait.until(ExpectedConditions.elementToBeClickable(url)).clear();
        wait.until(ExpectedConditions.elementToBeClickable(url)).sendKeys(urlValue);
        return this;
    }

    public HomePage setUsernameTextField() {
        return setUsernameTextField(data.get("USERNAME"));
    }

    public HomePage setUsernameTextField(String usernameValue) {
        wait.until(ExpectedConditions.elementToBeClickable(username)).clear();
        wait.until(ExpectedConditions.elementToBeClickable(username)).sendKeys(usernameValue);
        return this;
    }

    public HomePage submit() {
        clickLogoutButton();
        return this;
    }

    public HomePage verifyPageLoaded(WebDriver driver) {
        (new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getPageSource().contains(pageLoadedText);
            }
        });
        return this;
    }

    public HomePage verifyPageUrl(WebDriver driver) {
        (new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getCurrentUrl().contains(pageUrl);
            }
        });
        return this;
    }

    public Note getNote() {
        String title = wait.until(ExpectedConditions.elementToBeClickable(tableNoteTitle)).getText();
        String description = wait.until(ExpectedConditions.elementToBeClickable(tableNoteDescription)).getText();

        return new Note(null, title, description, null);
    }

    public boolean validateElement(WebDriver driver, By id) {
        try {
            driver.findElement(id);
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
        return true;
    }

    public boolean validateNote(WebDriver driver) {
        return !validateElement(driver,By.id("tableNoteTitle")) && !validateElement(driver,By.id("tableNoteDescription"));
    }

    public Credential getCredential() {
        String url = wait.until(ExpectedConditions.elementToBeClickable(tblCredentialUrl)).getText();
        String username = wait.until(ExpectedConditions.elementToBeClickable(tblCredentialUsername)).getText();
        String password = wait.until(ExpectedConditions.elementToBeClickable(tblCredentialPassword)).getText();

        return new Credential(null, url, username, null, password,null);
    }

    public boolean validateCredentials(WebDriver driver) {
        return !validateElement(driver, By.id("tblCredentialUrl")) && !validateElement(driver, By.id("tblCredentialUsername")) && !validateElement(driver, By.id("tblCredentialPassword"));
    }
}

