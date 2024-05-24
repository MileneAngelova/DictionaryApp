package com.dictionaryapp.session;

import com.dictionaryapp.model.entity.Word;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.HashSet;
import java.util.Set;

@Component
@SessionScope
public class CurrentUser {
    private Long id;

    private String username;

    private boolean isLoggedIn;

    private Set<Word> words;

    public CurrentUser() {
        this.words = new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public CurrentUser setId(Long id) {
        this.id = id;
        return this;
    }

    public CurrentUser setUsername(String username) {
        this.username = username;
        return this;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public CurrentUser setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
        return this;
    }

    public void logout() {
        this.id = null;
        this.username = null;
        this.isLoggedIn = false;
    }

    public Set<Word> getWords() {
        return words;
    }

    public CurrentUser setWords(Set<Word> words) {
        this.words = words;
        return this;
    }

}
