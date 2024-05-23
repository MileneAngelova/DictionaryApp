package com.dictionaryapp.web;

import com.dictionaryapp.model.dto.WordDTO;
import com.dictionaryapp.service.UserService;
import com.dictionaryapp.service.WordService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class HomeController {
    private final UserService userService;

    private final WordService wordService;

    public HomeController(UserService userService, WordService wordService) {
        this.userService = userService;
        this.wordService = wordService;
    }

    @ModelAttribute("wordModel")
    public WordDTO dictionary() {
        return new WordDTO();
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

        Long countAllWords = this.wordService.getAllWords();
        Long germanWordsCount = this.wordService.germanWordsCount();
        Long spanishWordsCount = this.wordService.spanishWordsCount();
        Long frenchWordsCount = this.wordService.frenchWordsCount();
        Long italianWordsCount = this.wordService.italianWordsCount();

        model.addAttribute("countAllWords", countAllWords);
        model.addAttribute("germanWordsCount", germanWordsCount);
        model.addAttribute("spanishWordsCount", spanishWordsCount);
        model.addAttribute("frenchWordsCount", frenchWordsCount);
        model.addAttribute("italianWordsCount", italianWordsCount);



        return "/home";
    }

    @GetMapping("/logout")
    public String logout() {
        this.userService.logout();
        return "redirect:/";
    }
}
