package com.dictionaryapp.model.dto;

import com.dictionaryapp.model.entity.Language;

import java.util.List;

public class LanguageDTO {
    private List<Language> languages;

    public List<Language> getWords() {
        return languages;
    }

    public LanguageDTO setWords(List<Language> languages) {
        this.languages = languages;
        return this;
    }
}
