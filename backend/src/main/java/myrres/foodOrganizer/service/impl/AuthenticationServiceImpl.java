package myrres.foodOrganizer.service.impl;

import myrres.foodOrganizer.rest.api.AuthenticationRequest;
import myrres.foodOrganizer.rest.api.AuthenticationResponse;
import myrres.foodOrganizer.rest.api.RegisterRequest;
import myrres.foodOrganizer.config.JwtService;
import lombok.RequiredArgsConstructor;
import myrres.foodOrganizer.rest.api.Role;
import myrres.foodOrganizer.jpa.entity.User;
import myrres.foodOrganizer.jpa.repository.UserRepository;
import myrres.foodOrganizer.service.AuthenticationService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        Authentication authentication= authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(), request.getPassword())
        );
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow(); //todo change to normal exception maybe ^_^
        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder().token(jwtToken).build();
    }

}
