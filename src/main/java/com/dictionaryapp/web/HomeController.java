package com.dictionaryapp.web;

import com.dictionaryapp.model.dto.AddWordDTO;
import com.dictionaryapp.model.dto.LanguageDTO;
import com.dictionaryapp.service.UserService;
import com.dictionaryapp.service.WordService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {
    private final UserService userService;

    private final WordService wordService;

    public HomeController(UserService userService, WordService wordService) {
        this.userService = userService;
        this.wordService = wordService;
    }

    @ModelAttribute("wordModel")
    public AddWordDTO dictionary() {
        return new AddWordDTO();
    }


    @GetMapping("/")
    public String index() {
        if (this.userService.isLoggedIn()) {
            return "redirect:/home";
        }
        return "index";
    }

    @GetMapping("/home")
    public String home(Model model) {
        if (!this.userService.isLoggedIn()) {
            return "redirect:/";
        }

        int countAllWords = this.wordService.getAllUserWords();

        int germanWordsCounter = this.wordService.germanWordsCount();
        int spanishWordsCounter = this.wordService.spanishWordsCount();
        int frenchWordsCounter = this.wordService.frenchWordsCount();
        int italianWordsCounter = this.wordService.italianWordsCount();

        model.addAttribute("countAllWords", countAllWords);
        model.addAttribute("germanWordsCounter", germanWordsCounter);
        model.addAttribute("spanishWordsCounter", spanishWordsCounter);
        model.addAttribute("frenchWordsCounter", frenchWordsCounter);
        model.addAttribute("italianWordsCounter", italianWordsCounter);

        return "/home";
    }

    @GetMapping("/logout")
    public String logout() {
        this.userService.logout();
        return "redirect:/";
    }
}
