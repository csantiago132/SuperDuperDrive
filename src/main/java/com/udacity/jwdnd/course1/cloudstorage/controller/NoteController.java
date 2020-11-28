package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.service.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("note")
public class NoteController {

    private final NoteService noteService;
    private final UserService userService;

    public NoteController(NoteService noteService, UserService userService) {
        this.noteService = noteService;
        this.userService = userService;
    }

    @PostMapping("handleNote")
    public String handleNote(Authentication authentication,
                          @ModelAttribute("noteForm") NoteForm noteForm,
                          Model model) {
        Integer userId = userService.getUser(authentication.getName()).getUserId();
        noteForm.setUserId(userId);
        if (noteForm.getNoteId() == null) {
            noteService.addNote(noteForm);
        } else {
            noteService.updateNote(noteForm);
        }
        model.addAttribute("result", "success");

        return "result";
    }

    @GetMapping(value = "/deleteNote/{noteId}")
    public String deleteNote(@PathVariable Integer noteId,
                             Model model) {
        noteService.deleteNote(noteId);
        model.addAttribute("result", "success");

        return "result";
    }
}
