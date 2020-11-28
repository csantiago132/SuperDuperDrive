package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NoteTests extends CloudStorageApplicationTests {

    @Test
    public void testCreate() {
        String title = "Testing Title";
        String description = "Testing Description";

        HomePage homePage = signUpAndLogin();
        createNote(homePage, title, description);

        homePage = new HomePage(driver);
        homePage.clickNotesLink();
        Note note = homePage.getNote();

        Assertions.assertEquals(title, note.getNoteTitle());
        Assertions.assertEquals(description, note.getNoteDescription());

        deleteNote(homePage);

        homePage = new HomePage(driver);
        homePage.clickLogoutButton();
    }

    @Test
    public void testEdit() {
        String originalTitle = "Original Title";
        String originalDescription = "Original Description";

        HomePage homePage = signUpAndLogin();
        createNote(homePage, originalTitle, originalDescription);

        homePage = new HomePage(driver);
        homePage.clickNotesLink();
        homePage.clickEditButton();

        String modifiedNoteTitle = "Modified Title";
        String modifiedNoteDescription = "Modified Description";
        homePage.setTitleTextField(modifiedNoteTitle);
        homePage.setDescriptionTextareaField(modifiedNoteDescription);
        homePage.clickSaveChanges1Button();

        ResultPage resultPage = new ResultPage(driver);
        resultPage.clickHereLink();

        homePage = new HomePage(driver);
        homePage.clickNotesLink();

        Note note = homePage.getNote();
        Assertions.assertEquals(modifiedNoteTitle, note.getNoteTitle());
        Assertions.assertEquals(modifiedNoteDescription, note.getNoteDescription());

        deleteNote(homePage);

        homePage = new HomePage(driver);
        homePage.clickLogoutButton();
    }

    @Test
    public void testDelete() {
        String title = "Testing Title";
        String description = "Testing Description";
        HomePage homePage = signUpAndLogin();
        createNote(homePage, title, description);

        homePage = new HomePage(driver);
        homePage.clickNotesLink();

        Assertions.assertFalse(homePage.validateNote(driver));

        deleteNote(homePage);
        Assertions.assertTrue(homePage.validateNote(driver));
    }

    private void createNote(HomePage homePage, String noteTitle, String noteDescription) {
        homePage.clickNotesLink();
        homePage.clickAddANewNoteButton();
        homePage.setTitleTextField(noteTitle);
        homePage.setDescriptionTextareaField(noteDescription);
        homePage.clickSaveChanges1Button();

        ResultPage resultPage = new ResultPage(driver);
        resultPage.clickHereLink();
    }

    private void deleteNote(HomePage homePage) {
        homePage = new HomePage(driver);
        homePage.clickDeleteLink();
        ResultPage resultPage = new ResultPage(driver);
        resultPage.clickHereLink();
    }

}
