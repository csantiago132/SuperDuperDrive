package com.udacity.jwdnd.course1.cloudstorage.model;

public class Note {
    private Integer noteId;
    private String noteTitle;
    private String noteDescription;
    private Integer userId;

    public Note(Integer noteId, String noteTitle, String noteDescription, Integer userId) {
        this.noteDescription = noteDescription;
        this.noteId = noteId;
        this.noteTitle = noteTitle;
        this.userId = userId;
    }

    public Integer getNoteId() {
        return noteId;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getNoteDescription() {
        return noteDescription;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteId(Integer noteId) {
        this.noteId = noteId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}


