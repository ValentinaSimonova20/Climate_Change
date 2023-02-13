package com.simonova.ecoinformerapp.controllers.auth;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
public class AuthController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
