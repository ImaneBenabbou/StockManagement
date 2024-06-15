package org.example.pfa.Controllers;

import jakarta.validation.Valid;

import org.example.pfa.Repository.UtilisateurRepo;
import org.example.pfa.models.Utilisateur;
import org.example.pfa.payload.request.SignInRequest;
import org.example.pfa.payload.request.SignUpRequest;
import org.example.pfa.payload.response.AuthenticationResponse;
import org.example.pfa.payload.response.MessageResponse;
import org.example.pfa.security.Jwt.JwtUtils;
import org.example.pfa.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UtilisateurRepo userRepository;


    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtUtils jwtUtils;

    /**
     * Endpoint for authenticating users.
     *
     * @param loginRequest The SignInRequest object containing user credentials
     * @return ResponseEntity with AuthenticationResponse and RefreshToken if authentication is successful
     */
    @PostMapping("/sign-in")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody SignInRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String token = jwtUtils.generateJwtToken(userDetails);

        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new AuthenticationResponse(
                token,
                userDetails.getId(),
                userDetails.getEmail(),
                roles
        ));
    }

    /**
     * Endpoint for registering new users.
     *
     * @param signUpRequest The SignUpRequest object containing user details
     * @return ResponseEntity with a MessageResponse indicating successful registration
     *         or an error message if email is already in use
     */
    @PostMapping("/sign-up")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
        }

        Utilisateur user = new Utilisateur();
        user.setPhone(signUpRequest.getPhone());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(encoder.encode(signUpRequest.getPassword()));
        user.setRole("CLIENT");

        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User Registered Successfully!"));
    }

    /**
     * Endpoint for logging out users.
     *
     * @return ResponseEntity with a MessageResponse indicating successful logout
     */
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/sign-out")
    public ResponseEntity<?> logoutUser() {
        ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(new MessageResponse("You've Been Signed Out!"));
    }


}
