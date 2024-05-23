package com.dictionaryapp.model.dto;

import com.dictionaryapp.model.entity.Language;

import java.util.List;

public class WordDTO {
    private List<Language> words;

    public List<Language> getWords() {
        return words;
    }

    public WordDTO setWords(List<Language> words) {
        this.words = words;
        return this;
    }
}
