package cc.maids.lms.service.Impl;

import cc.maids.lms.dto.SignInRequest;
import cc.maids.lms.dto.SignUpRequest;
import cc.maids.lms.exceptions.ResourcesNotFoundException;
import cc.maids.lms.repository.UserRepository;
import cc.maids.lms.service.AuthenticationService;
import cc.maids.lms.service.JwtService;
import cc.maids.lms.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import cc.maids.lms.entities.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public String register(SignUpRequest request) {
        var user = User
                .builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        if(userRepository.findByEmail(request.getEmail()).isPresent())
            throw new ResourcesNotFoundException("This email is already found");
        user = userService.save(user);
        var jwt = jwtService.generateToken(user);
        return jwt;
    }


    public String login(SignInRequest request) {
        var jwt = "";
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new BadCredentialsException("Invalid email or password."));
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        jwt = jwtService.generateToken(user);
        return jwt;
    }
}
