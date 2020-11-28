package com.udacity.jwdnd.course1.cloudstorage.service;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.FileForm;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Service
public class FileService {

    private final FileMapper fileMapper;

    public FileService(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("Creating FileService bean");
    }

    public File[] getFiles(Integer userId) {
        return fileMapper.getFiles(userId);
    }

    public File getFile(Integer fileId) {
        return fileMapper.getFile(fileId);
    }

    public void addFile(FileForm fileForm) {
        try {
            String fileName = fileForm.getFile().getOriginalFilename();
            String contentType = fileForm.getFile().getContentType();
            String fileSize = String.valueOf(fileForm.getFile().getSize());
            byte[] fileData = fileForm.getFile().getBytes();

            File file = new File(null, fileName, contentType, fileSize, fileForm.getUserId(), fileData);

            fileMapper.insertFile(file);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteFile(Integer fileId) {
        fileMapper.deleteFile(fileId);
    }

}
