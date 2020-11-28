package com.udacity.jwdnd.course1.cloudstorage.model;

import org.springframework.web.multipart.MultipartFile;

public class FileForm {
    private MultipartFile file;
    private Integer userId;

    public Integer getUserId() {
        return userId;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}


