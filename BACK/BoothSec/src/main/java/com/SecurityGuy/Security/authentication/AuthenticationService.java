package com.SecurityGuy.Security.authentication;

import com.SecurityGuy.Security.config.JwtService;
import com.SecurityGuy.Security.entity.User;
import com.SecurityGuy.Security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

// Specify it is a service
@Service
public class AuthenticationService {

    // Inject AuthenticationManger to use it to login and other authentication methods
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    // Password encoder to save in database
    private final PasswordEncoder passwordEncoder;

    public AuthenticationService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public AuthenticationResponse register(User request){
        var user = new User();
        user.setName(request.getName());
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setPhoto(request.getPhoto());
        user.setLocation(request.getLocation());
        user.setActive(request.getActive());
        user.setRole(request.getRole());
        userRepository.save(user);
        String registerJwt = jwtService.generateToken(user, generateExtraClaims(user));
        return  new AuthenticationResponse(registerJwt);
    }

    // Authentication method
    public AuthenticationResponse login(AuthenticationRequest authenticationRequest){
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                authenticationRequest.getUsername(), authenticationRequest.getPassword()
        );
        // Takes auth token and check password and username
        authenticationManager.authenticate(authToken);
        // Once no issue, get user
        User user = userRepository.findByUsername(authenticationRequest.getUsername()).get();
        // Generate claims
        String accessJwt = jwtService.generateToken(user, generateExtraClaims(user));
        return new AuthenticationResponse(accessJwt);
    }

    // Generate extra claims to can put more stuff in it
    // Extra is name and role
    private Map<String, Object> generateExtraClaims(User user) {
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("userId", user.getId());
        extraClaims.put("name", user.getName());
        extraClaims.put("role", user.getRole().name());
        return extraClaims;
    }
}