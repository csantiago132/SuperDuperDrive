package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.CredentialForm;
import com.udacity.jwdnd.course1.cloudstorage.service.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.service.EncryptionService;
import com.udacity.jwdnd.course1.cloudstorage.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("credential")
public class CredentialController {

    private final CredentialService credentialService;
    private final EncryptionService encryptionService;
    private final UserService userService;

    public CredentialController(CredentialService credentialService, EncryptionService encryptionService, UserService userService) {
        this.credentialService = credentialService;
        this.encryptionService = encryptionService;
        this.userService = userService;
    }

    @PostMapping("handleCredential")
    public String handleCredential(Authentication authentication,
                                @ModelAttribute("credentialForm") CredentialForm credentialForm,
                                Model model) {
        Integer userId = userService.getUser(authentication.getName()).getUserId();
        credentialForm.setUserId(userId);

        String key = encryptionService.generateKey();
        credentialForm.setKey(key);

        String encryptedPassword = encryptionService.encryptValue(credentialForm.getPassword(), key);

        if (credentialForm.getCredentialId() == null) {
            credentialService.addCredential(credentialForm, encryptedPassword);
        } else {
            credentialService.updateCredential(credentialForm.getCredentialId(), credentialForm.getUsername(), credentialForm.getUrl(), credentialForm.getKey(), encryptedPassword);
        }
        model.addAttribute("result", "success");

        return "result";
    }

    @GetMapping(value = "/deleteCredential/{credentialId}")
    public String deleteCredential(@PathVariable Integer credentialId,
                                   Model model) {
        credentialService.deleteCredential(credentialId);
        model.addAttribute("result", "success");

        return "result";
    }
}
