package fr.diginamic.formation.security.services;

import fr.diginamic.formation.security.dtos.LoginUserDto;
import fr.diginamic.formation.security.dtos.RegisterUserDto;
import fr.diginamic.formation.security.entities.User;
import fr.diginamic.formation.security.exceptions.UserNotFoundException;
import fr.diginamic.formation.security.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    public User signup(RegisterUserDto input){
        var user = new User();
        user.setEmail(input.getEmail());
        user.setFullName(input.getFullName());
        user.setPassword(passwordEncoder.encode(input.getPassword()));

        return userRepository.save(user);
    }

    public User authenticate(LoginUserDto input){
        System.out.println(input.getEmail() + " " + input.getPassword() + " dans le authenticate");
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(input.getEmail(), input.getPassword()));

        return userRepository.findByEmail(input.getEmail()).orElseThrow(UserNotFoundException::new);
    }

    public List<User> allUsers(){

        return userRepository.findAll();
    }
}
