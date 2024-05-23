package com.dictionaryapp.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.PastOrPresent;

import java.time.Instant;
import java.time.LocalDate;

@Entity
@Table(name = "words")
public class Word {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String term;

    @Column(nullable = false)
    private String example;

    @Column(name = "input_date", nullable = false)
    @PastOrPresent
    private Instant inputDate;


    @ManyToOne(targetEntity = Language.class)
    @JoinColumn(name = "language_id", nullable = false)
    private Language language;

    @ManyToOne(targetEntity = User.class)
    private User addedBy;

    public Word() {
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Long getId() {
        return id;
    }

    public Word setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTerm() {
        return term;
    }

    public Word setTerm(String term) {
        this.term = term;
        return this;
    }

    public String getExample() {
        return example;
    }

    public Word setExample(String example) {
        this.example = example;
        return this;
    }

    public Instant getInputDate() {
        return inputDate;
    }

    public Word setInputDate(Instant inputDate) {
        this.inputDate = inputDate;
        return this;
    }

    public User getAddedBy() {
        return addedBy;
    }

    public Word setAddedBy(User addedBy) {
        this.addedBy = addedBy;
        return this;
    }
}
