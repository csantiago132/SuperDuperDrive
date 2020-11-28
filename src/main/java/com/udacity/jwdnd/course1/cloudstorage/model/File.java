package com.udacity.jwdnd.course1.cloudstorage.model;

public class File {
    private byte[] fileData;
    private Integer fileId;
    private Integer userId;
    private String contentType;
    private String fileName;
    private String fileSize;

    public File(Integer fileId, String fileName, String contentType, String fileSize, Integer userId, byte[] fileData) {
        this.contentType = contentType;
        this.fileData = fileData;
        this.fileId = fileId;
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.userId = userId;
    }

    public byte[] getFileData() {
        return fileData;
    }

    public Integer getFileId() {
        return fileId;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getContentType() {
        return contentType;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

}


