package com.social_media.dev.controller;


import com.social_media.dev.dto.UserDetailsResponse;
import com.social_media.dev.dto.UserRegistrationDto;
import com.social_media.dev.dto.auth.LoginRequest;
import com.social_media.dev.dto.auth.LoginResponse;
import com.social_media.dev.service.AuthService;
import com.social_media.dev.service.UserService;
import com.social_media.dev.util.jwt.JwtTokenUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@Tag(name = "Helper - Auth")
@RestController
@RequestMapping("auth")
@AllArgsConstructor
public class AuthController extends ApiController {

    private AuthService authService;

    private UserService userService;

    private JwtTokenUtil jwtTokenUtil;


    @Operation(
            summary = "Login",
            parameters = @Parameter(name = "Accept-Language", in = ParameterIn.HEADER, example = "en")
    )
    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest request) {
        var token = authService.login(request.getUsername(), request.getPassword());

        var user = userService.get(jwtTokenUtil.getUserId(token));

        var response = new LoginResponse();

        response.setUser(modelMapper.map(user, UserDetailsResponse.class));
        response.setToken(token);

        return response;
    }


    @Operation(
            summary = "Logout",
            parameters = @Parameter(name = "Accept-Language", in = ParameterIn.HEADER, example = "en")
    )
    @PostMapping("/logout")
    public void logout() {
        // ..
    }

    @Operation(
            summary = "Sign Up",
            parameters = @Parameter(name = "Accept-Language", in = ParameterIn.HEADER, example = "en")
    )
    @PostMapping("/signup")
    public ResponseEntity<LoginResponse> registerUser(@RequestBody UserRegistrationDto registrationDto) {
        var user = userService.registerUser(registrationDto);
        var token = jwtTokenUtil.generateAccessToken(user);
        var response = new LoginResponse();

        response.setUser(modelMapper.map(user, UserDetailsResponse.class));
        response.setToken(token);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
