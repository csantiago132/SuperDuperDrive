package com.udacity.jwdnd.course1.cloudstorage.service;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.CredentialForm;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class CredentialService {
    private final CredentialMapper credentialMapper;

    public CredentialService(CredentialMapper credentialMapper) {
        this.credentialMapper = credentialMapper;
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("Creating CredentialService bean");
    }

    public Credential[] getCredentials(Integer userId) {
        return credentialMapper.getCredentials(userId);
    }

    public void addCredential(CredentialForm credentialForm, String encryptedPassword) {
        Credential credential = new Credential(null, credentialForm.getUrl(), credentialForm.getUsername(),
                credentialForm.getKey(), encryptedPassword, credentialForm.getUserId());
        credentialMapper.insertCredential(credential);
    }

    public void updateCredential(Integer credentialId, String username, String url, String key, String encryptedPassword) {
        credentialMapper.updateCredential(credentialId, username, url, key, encryptedPassword);
    }

    public void deleteCredential(Integer noteId) {
        credentialMapper.deleteCredential(noteId);
    }
}
