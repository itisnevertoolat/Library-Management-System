package cc.maids.lms.controller;

import cc.maids.lms.dto.JwtAuthenticationResponse;
import cc.maids.lms.dto.SignInRequest;
import cc.maids.lms.dto.SignUpRequest;
import cc.maids.lms.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RequiredArgsConstructor
@RestController
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    @PostMapping("/user/register")
    public ResponseEntity<String> register(@RequestBody @Valid SignUpRequest request, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(bindingResult.getAllErrors().get(0).getDefaultMessage());
        String token = authenticationService.register(request);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(token);
    }

    @PostMapping("/user/login")
    public String login(@RequestBody SignInRequest request){
        return authenticationService.login(request);
    }

}
