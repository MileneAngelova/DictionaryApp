package com.dictionaryapp.model.entity;

import com.dictionaryapp.model.dto.LanguageNameEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "languages")
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private LanguageNameEnum name;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Word> words;

    public Language() {
        this.words = new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public Language setId(Long id) {
        this.id = id;
        return this;
    }

    public LanguageNameEnum getName() {
        return name;
    }

    public Language setName(LanguageNameEnum name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Language setDescription(String description) {
        this.description = description;
        return this;
    }

    public Set<Word> getWords() {
        return words;
    }

    public Language setWords(Set<Word> words) {
        this.words = words;
        return this;
    }
}
