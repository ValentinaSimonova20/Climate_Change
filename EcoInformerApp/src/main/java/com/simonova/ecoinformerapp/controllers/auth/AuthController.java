package com.simonova.ecoinformerapp.controllers.auth;

import com.simonova.ecoinformerapp.models.ERole;
import com.simonova.ecoinformerapp.models.Role;
import com.simonova.ecoinformerapp.models.User;
import com.simonova.ecoinformerapp.models.payload.request.LoginRequest;
import com.simonova.ecoinformerapp.models.payload.request.SignupRequest;
import com.simonova.ecoinformerapp.models.payload.response.JwtResponse;
import com.simonova.ecoinformerapp.models.payload.response.MessageResponse;
import com.simonova.ecoinformerapp.repository.RoleRepository;
import com.simonova.ecoinformerapp.repository.UserRepository;

import com.simonova.ecoinformerapp.security.services.UserDetailsImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

}
