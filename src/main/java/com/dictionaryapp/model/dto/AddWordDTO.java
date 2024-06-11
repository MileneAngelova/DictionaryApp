package com.dictionaryapp.model.dto;

import com.dictionaryapp.model.entity.LanguageNameEnum;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class AddWordDTO {
    private Long id;
    @NotBlank(message = "This field can not be empty.")
    @Size(min = 2, max = 40, message = "The term length must be between 2 and 40 characters!")
    private String term;

    @NotBlank(message = "This field can not be empty.")
    @Size(min = 2, max = 80, message = "The translation length must be between 2 and 80 characters!")
    private String translation;

    @NotBlank(message = "This field can not be empty.")
    @Size(min = 2, max = 200, message = "The example length must be between 2 and 200 characters!")
    private String example;

    @NotNull(message = "You must select a date!")
    @PastOrPresent(message = "The date can not be in the future!")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate inputDate;

    @NotNull(message = "You must select a language!")
    private LanguageNameEnum language;

    public String getTerm() {
        return term;
    }

    public AddWordDTO setTerm(String term) {
        this.term = term;
        return this;
    }

    public String getTranslation() {
        return translation;
    }

    public AddWordDTO setTranslation(String translation) {
        this.translation = translation;
        return this;
    }

    public String getExample() {
        return example;
    }

    public AddWordDTO setExample(String example) {
        this.example = example;
        return this;
    }

    public LocalDate getInputDate() {
        return inputDate;
    }

    public AddWordDTO setInputDate(LocalDate inputDate) {
        this.inputDate = inputDate;
        return this;
    }

    public LanguageNameEnum getLanguage() {
        return language;
    }

    public AddWordDTO setLanguage(LanguageNameEnum language) {
        this.language = language;
        return this;
    }

    public Long getId() {
        return id;
    }

    public AddWordDTO setId(Long id) {
        this.id = id;
        return this;
    }
}
