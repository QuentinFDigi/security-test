package fr.diginamic.formation.security.controllers;

import fr.diginamic.formation.security.dtos.LoginUserDto;
import fr.diginamic.formation.security.dtos.RegisterUserDto;
import fr.diginamic.formation.security.entities.User;
import fr.diginamic.formation.security.response.LoginResponse;
import fr.diginamic.formation.security.services.AuthenticationService;
import fr.diginamic.formation.security.services.JwtService;
import org.hibernate.sql.results.ResultsLogger_$logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody RegisterUserDto registerUserDto){
        User registerUser = authenticationService.signup(registerUserDto);

        return ResponseEntity.ok(registerUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto){
        System.out.println(loginUserDto.getEmail() + " " + loginUserDto.getPassword());
        User authenticatedUser = authenticationService.authenticate(loginUserDto);
        System.out.println("test avant jwt");
        String jwtToken = jwtService.generateToken(authenticatedUser);
        System.out.println(jwtToken);

        LoginResponse loginResponse = new LoginResponse().setToken(jwtToken).setExpiresIn(jwtService.getExpiration());

        return ResponseEntity.ok(loginResponse);
    }
}
