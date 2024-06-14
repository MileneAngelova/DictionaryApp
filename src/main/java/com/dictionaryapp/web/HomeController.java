package com.dictionaryapp.web;

import com.dictionaryapp.model.dto.AddWordDTO;
import com.dictionaryapp.model.entity.Word;
import com.dictionaryapp.service.UserService;
import com.dictionaryapp.service.WordService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

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


        List<Word> allWords = this.wordService.getAllWords();

        List<Word> germanWords = this.wordService.germanWords();
        List<Word> spanishWords = this.wordService.spanishWords();
        List<Word> frenchWords = this.wordService.frenchWordsCount();
        List<Word> italianWords = this.wordService.italianWords();

        model.addAttribute("germanWords", germanWords);
        model.addAttribute("spanishWords", spanishWords);
        model.addAttribute("frenchWords", frenchWords);
        model.addAttribute("italianWords", italianWords);
        model.addAttribute("allWords", allWords);

        return "/home";
    }

    @GetMapping("/home/delete-word/{id}")
    public String deleteSingleWord(@PathVariable Long id) {
        if (!this.userService.isLoggedIn()) {
            return "redirect:/";
        }
        this.wordService.deleteSingleWord(id);
        return "redirect:/home";
    }

    @DeleteMapping("/home/remove-all")
    public String removeAllWords() {
        if (!this.userService.isLoggedIn()) {
            return "redirect:/";
        }
        this.wordService.deleteAllWords();
        return "redirect:/home";
    }
}
