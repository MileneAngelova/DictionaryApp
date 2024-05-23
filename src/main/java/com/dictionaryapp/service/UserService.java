package com.dictionaryapp.service;

import com.dictionaryapp.model.dto.LoginDTO;
import com.dictionaryapp.model.dto.RegisterDTO;
import com.dictionaryapp.model.entity.User;
import com.dictionaryapp.repo.UserRepository;
import com.dictionaryapp.session.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final CurrentUser currentUser;
    private final ModelMapper modelMapper;


    public UserService(UserRepository userRepository, CurrentUser currentUser, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.currentUser = currentUser;
        this.modelMapper = modelMapper;
    }

    public boolean userExist(RegisterDTO registerDTO) {
        Optional<User> byEmail = this.userRepository.findByEmail(registerDTO.getEmail());

        return byEmail.isPresent() && byEmail.get().getPassword().equals(registerDTO.getPassword());
    }

    public void register(RegisterDTO registerDTO) {

        if (userExist(registerDTO)) {
            throw new RuntimeException("User with email " + registerDTO.getEmail() + " already exists!");
        }

        User newUser = this.modelMapper.map(registerDTO, User.class);
        this.userRepository.save(newUser);
    }

    public void login(LoginDTO loginDTO) {
        Optional<User> optUser = this.userRepository.findByUsername(loginDTO.getUsername());

        if (optUser.isPresent() && optUser.get().getPassword().equals(loginDTO.getPassword())) {
            currentUser.setUsername(loginDTO.getUsername());
          currentUser.setLoggedIn(true);
        }
    }

    public boolean isLoggedIn() {
        return currentUser.isLoggedIn();
    }

    public void logout() {
        this.currentUser.logout();
    }

    public Long getUserId() {
        return this.currentUser.getId();
    }
}
