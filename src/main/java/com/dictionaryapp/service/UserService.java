package com.dictionaryapp.service;

import com.dictionaryapp.model.dto.LoginDTO;
import com.dictionaryapp.model.dto.RegisterDTO;
import com.dictionaryapp.model.entity.User;
import com.dictionaryapp.repo.UserRepository;
import com.dictionaryapp.session.CurrentUser;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

 import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final CurrentUser currentUser;
    private final ModelMapper modelMapper;
    private final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, CurrentUser currentUser, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.currentUser = currentUser;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean userExist(String username) {
        Optional<User> byUsername = this.userRepository.findByUsername(username);

        return byUsername.isPresent();
    }

    public boolean userFound(String username, String password) {
        Optional<User> byUsername = this.userRepository.findByUsernameAndPassword(username, password);

        return byUsername.isPresent();
    }

    public void register(RegisterDTO registerDTO) {
        if (userExist(registerDTO.getEmail())) {
            throw new RuntimeException("User with email " + registerDTO.getEmail() + " already exists!");
        }

        User newUser = this.modelMapper.map(registerDTO, User.class);

        newUser.setPassword(this.passwordEncoder.encode(registerDTO.getPassword()));
        this.userRepository.save(newUser);
    }

    public void login(LoginDTO loginDTO) {
        Optional<User> optUser = this.userRepository.findByUsername(loginDTO.getUsername());

        if (optUser.isEmpty()) {
            LOGGER.error("Username " + optUser.get().getUsername() + "is not found!");
        }

        if (optUser.get().getPassword().equals(loginDTO.getPassword())) {
            currentUser.setUsername(loginDTO.getUsername());
            currentUser.setLoggedIn(true);
        } else {
            LOGGER.error("Wrong Username or Password!");
        }
    }

    public boolean isLoggedIn() {
        return currentUser.isLoggedIn();
    }

    public void logout() {
        this.currentUser.logout();
    }
}
