package com.dictionaryapp.web;

import com.dictionaryapp.model.dto.LoginDTO;
import com.dictionaryapp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("loginModel")
    public LoginDTO initLogin() {
        return new LoginDTO();
    }

    @GetMapping("/login")
    public String login() {
        return "/login";
    }

    @PostMapping("/login")
    public String login(@Valid LoginDTO loginModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (this.userService.isLoggedIn()) {
            return "redirect:/home";
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("loginModel", loginModel);
            redirectAttributes.addAttribute("org.springframework.validation.BindingResult.loginModel", bindingResult);

            return "redirect:/login";
        }
        this.userService.login(loginModel);
        return "redirect:/home";
    }

}
