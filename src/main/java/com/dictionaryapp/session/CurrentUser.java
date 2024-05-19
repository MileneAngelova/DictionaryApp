package com.dictionaryapp.session;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class CurrentUser {
    private Long id;
    private String username;

    private boolean isLoggedIn;

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
        this.isLoggedIn = false;
        this.id = null;
        this.username = null;
    }
}
