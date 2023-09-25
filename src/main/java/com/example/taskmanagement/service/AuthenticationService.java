package com.example.taskmanagement.service;


import com.example.taskmanagement.dao.entity.UserEntity;
import com.example.taskmanagement.dao.repository.UserRepository;
import com.example.taskmanagement.enums.UserRole;
import com.example.taskmanagement.exception.NoSuchUserException;
import com.example.taskmanagement.exception.UsernameAlreadyExist;
import com.example.taskmanagement.request.AuthenticationRequest;
import com.example.taskmanagement.request.RegisterRequest;
import com.example.taskmanagement.response.AuthenticationResponse;
import com.example.taskmanagement.response.RegisterResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public RegisterResponse register(RegisterRequest request) {
        var exist = userRepo.findByUsername(request.getUsername()).isPresent();
        if (exist) {
            throw new UsernameAlreadyExist("Username already exist");
        }
        var user = UserEntity.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(UserRole.USER)
                .build();
        var userEntity = userRepo.save(user);
        return RegisterResponse.buildRegisterDto(userEntity);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var user = userRepo.findByUsername(request.getUsername())
                .orElseThrow(() -> new NoSuchUserException("No such user!"));
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
