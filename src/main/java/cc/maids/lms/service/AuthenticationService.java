package cc.maids.lms.service;

import cc.maids.lms.dto.JwtAuthenticationResponse;
import cc.maids.lms.dto.SignInRequest;
import cc.maids.lms.dto.SignUpRequest;


public interface AuthenticationService {


    public String register(SignUpRequest request);
    public String login(SignInRequest request);

}
