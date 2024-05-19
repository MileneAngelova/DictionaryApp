package com.dictionaryapp.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WordController {
    @GetMapping("/word/add")
    public String addWord() {
        return "/word-add";
    }
}
