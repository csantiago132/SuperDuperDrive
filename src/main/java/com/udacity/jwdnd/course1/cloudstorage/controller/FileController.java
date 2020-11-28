package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.CredentialForm;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.FileForm;
import com.udacity.jwdnd.course1.cloudstorage.model.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.service.*;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/home")
public class FileController {

    private CredentialService credentialService;
    private EncryptionService encryptionService;
    private FileService fileService;
    private NoteService noteService;
    private UserService userService;

    public FileController(UserService userService, FileService fileService, NoteService noteService, CredentialService credentialService, EncryptionService encryptionService) {
        this.credentialService = credentialService;
        this.encryptionService = encryptionService;
        this.fileService = fileService;
        this.noteService = noteService;
        this.userService = userService;
    }

    @GetMapping
    public String getHomePage(Authentication authentication,
                              @ModelAttribute("fileForm") FileForm fileForm,
                              @ModelAttribute("noteForm") NoteForm noteForm,
                              @ModelAttribute("credentialForm") CredentialForm credentialForm,
                              Model model) {
        Integer userId = userService.getUser(authentication.getName()).getUserId();
        model.addAttribute("files", fileService.getFiles(userId));
        model.addAttribute("notes", noteService.getNotes(userId));
        model.addAttribute("credentials", credentialService.getCredentials(userId));
        model.addAttribute("encryptionService", encryptionService);

        return "home";
    }

    @PostMapping
    public String uploadFile(Authentication authentication,
                             @ModelAttribute("fileForm") FileForm fileForm,
                             Model model) {
        Integer userId = userService.getUser(authentication.getName()).getUserId();
        fileForm.setUserId(userId);
        if (fileForm.getFile().isEmpty()){
            model.addAttribute("message", "Empty File");
            model.addAttribute("result", "error");
        } else if (validateFileName(fileForm.getFile(), fileService.getFiles(userId))) {
            fileService.addFile(fileForm);
            model.addAttribute("result", "success");
        } else {
            model.addAttribute("message", "Duplicated File");
            model.addAttribute("result", "error");
        }

        return "result";
    }

    public boolean validateFileName(MultipartFile fileNew, File[] fileLst) {
        for (File file : fileLst) {
            if (file.getFileName().equals(fileNew.getOriginalFilename())) {
                return false;
            }
        }
        return true;
    }

    @GetMapping(value = "/viewFile/{fileId}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @ResponseBody
    public HttpEntity viewFile(@PathVariable Integer fileId) {
        String contentType = fileService.getFile(fileId).getContentType();
        byte[] content = fileService.getFile(fileId).getFileData();
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(contentType));
        return new HttpEntity(content, headers);
    }

    @GetMapping(value = "/deleteFile/{fileId}")
    public String deleteFile(@PathVariable Integer fileId,
                             @ModelAttribute("fileForm") FileForm fileForm,
                             Model model) {
        fileService.deleteFile(fileId);
        model.addAttribute("result", "success");

        return "result";
    }

}

