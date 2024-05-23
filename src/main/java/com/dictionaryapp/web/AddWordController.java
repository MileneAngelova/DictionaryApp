package com.dictionaryapp.web;

import com.dictionaryapp.model.dto.AddWordDTO;
import com.dictionaryapp.model.entity.LanguageNameEnum;
import com.dictionaryapp.service.LanguageService;
import com.dictionaryapp.service.UserService;
import com.dictionaryapp.service.WordService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class AddWordController {
    private final UserService userService;

    private final WordService wordService;
    private final LanguageService languageService;

    public AddWordController(UserService userService, WordService wordService, LanguageService languageService) {
        this.userService = userService;
        this.wordService = wordService;
        this.languageService = languageService;
    }

    @ModelAttribute("addWordModel")
    public AddWordDTO initWord() {
        return new AddWordDTO();
    }

    @GetMapping("/word/add")
    @Transactional
    public String addWord(Model model) {
        if (!this.userService.isLoggedIn()) {
            return "redirect:/";
        }

        List<LanguageNameEnum> languages = this.languageService.getAllLanguages();
        model.addAttribute("languages", languages);

        return "/word-add";
    }

    @PostMapping("/word/add")
    public String addWord(@Valid AddWordDTO addWordModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (!this.userService.isLoggedIn()) {
            return "redirect:/";
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addWordModel", addWordModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addWordModel", bindingResult);

            return "redirect:/word/add";
        }
        this.wordService.addWord(addWordModel);
        return "redirect:/home";
    }
}
