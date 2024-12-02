package by.cars.delivery.controller;

import by.cars.delivery.errors.CredentialsAlreadyTakenException;
import by.cars.delivery.model.UserEntity;
import by.cars.delivery.repository.UserRepository;
import by.cars.delivery.service.AuthService;
import by.cars.delivery.util.PersonDetailsService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping("/auth")
@Slf4j
public class AuthController {

    private final AuthService authService;
    private final PersonDetailsService personDetailsService;
    private final UserRepository userRepository;

    @GetMapping("/registration/page")
    public String registrationPage(@ModelAttribute("user")UserEntity user) {

        return "auth/registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("user") @Valid UserEntity user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "auth/registration";
        }
        try {
            authService.registration(user);
        } catch (CredentialsAlreadyTakenException e) {
            bindingResult.rejectValue("email", "error.user", e.getMessage());
            bindingResult.rejectValue("phone", "error.user", e.getMessage());
            return "/auth/registration";
        }

        return "redirect:/auth/login";
    }

    @RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST})
    public String loginPage( Model model,UserEntity user, CsrfToken csrfToken) {
        model.addAttribute("_csrf", csrfToken);
        return "auth/login";
    }
}