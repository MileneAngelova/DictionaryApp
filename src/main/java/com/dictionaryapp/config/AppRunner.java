package com.dictionaryapp.config;

import com.dictionaryapp.service.LanguageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements CommandLineRunner {
    private LanguageService languageService;

    public AppRunner(LanguageService languageService) {
        this.languageService = languageService;
    }


    @Override
    public void run(String... args) throws Exception {
        this.languageService.seedLanguages();
    }
}