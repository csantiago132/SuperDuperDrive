package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CredentialTests extends CloudStorageApplicationTests {

    public static final String FACEBOOK_URL = "https://www.facebook.com/";
    public static final String FACEBOOK_USERNAME = "mzuckerberg";
    public static final String FACEBOOK_PASSWORD = "123";

    public static final String TESLA_URL = "https://www.tesla.com/";
    public static final String TESLA_USERNAME = "emusk";
    public static final String TESLA_PASSWORD = "456";

    /**
     * Write a test that creates a set of credentials, verifies that they are displayed, and verifies that the displayed password is encrypted.
     */
    @Test
    public void testCreate() {
        HomePage homePage = signUpAndLogin();

        createCredential(homePage, FACEBOOK_URL, FACEBOOK_USERNAME, FACEBOOK_PASSWORD);
        verifyCredential(homePage, FACEBOOK_URL, FACEBOOK_USERNAME, FACEBOOK_PASSWORD);
        homePage.clickDeleteLink2();

        ResultPage resultPage = new ResultPage(driver);
        resultPage.clickHereLink();

        homePage.clickLogoutButton();
    }

    @Test
    public void testEdit() {
        HomePage homePage = signUpAndLogin();

        createCredential(homePage, FACEBOOK_URL, FACEBOOK_USERNAME, FACEBOOK_PASSWORD);
        verifyCredential(homePage, FACEBOOK_URL, FACEBOOK_USERNAME, FACEBOOK_PASSWORD);

        Credential originalCredential = homePage.getCredential();
        String originalPassword = originalCredential.getPassword();

        homePage.clickEditCredentialButton();

        setCredentialFields(homePage, TESLA_URL, TESLA_USERNAME, TESLA_PASSWORD);

        homePage.clickSaveChanges2Button();

        ResultPage resultPage = new ResultPage(driver);
        resultPage.clickHereLink();

        homePage.clickCredentialsLink();
        Credential modifiedCredential = homePage.getCredential();

        Assertions.assertEquals(TESLA_URL, modifiedCredential.getUrl());
        Assertions.assertEquals(TESLA_USERNAME, modifiedCredential.getUsername());

        String modifiedPassword = modifiedCredential.getPassword();

        Assertions.assertNotEquals(TESLA_PASSWORD, modifiedPassword);
        Assertions.assertNotEquals(originalPassword, modifiedPassword);

        homePage.clickDeleteLink2();
        resultPage.clickHereLink();

        homePage.clickLogoutButton();
    }

    @Test
    public void testDelete() {
        HomePage homePage = signUpAndLogin();
        createCredential(homePage, FACEBOOK_URL, FACEBOOK_USERNAME, FACEBOOK_PASSWORD);
        createCredential(homePage, TESLA_URL, TESLA_USERNAME, TESLA_PASSWORD);
        Assertions.assertFalse(homePage.validateCredentials(driver));

        homePage.clickDeleteLink2();
        ResultPage resultPage = new ResultPage(driver);
        resultPage.clickHereLink();

        homePage.clickCredentialsLink();
        homePage.clickDeleteLink2();
        resultPage.clickHereLink();

        homePage.clickCredentialsLink();

        Assertions.assertTrue(homePage.validateCredentials(driver));

        homePage.clickLogoutButton();
    }

    private void createCredential(HomePage homePage, String url, String username, String password) {
        homePage.clickCredentialsLink();
        homePage.clickAddANewCredentialButton();

        setCredentialFields(homePage, url, username, password);
        homePage.clickSaveChanges2Button();

        ResultPage resultPage = new ResultPage(driver);
        resultPage.clickHereLink();
    }

    private void verifyCredential(HomePage homePage, String url, String username, String password) {
        homePage.clickCredentialsLink();

        Credential credential = homePage.getCredential();

        Assertions.assertEquals(url, credential.getUrl());
        Assertions.assertEquals(username, credential.getUsername());
        Assertions.assertNotEquals(password, credential.getPassword());
    }

    private void setCredentialFields(HomePage homePage, String url, String username, String password) {
        homePage.setUrlTextField(url);
        homePage.setUsernameTextField(username);
        homePage.setPasswordTextField(password);
    }

}
