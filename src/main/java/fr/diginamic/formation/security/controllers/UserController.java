package fr.diginamic.formation.security.controllers;

import fr.diginamic.formation.security.entities.User;
import fr.diginamic.formation.security.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/me")
    public ResponseEntity<User> authenticatedUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        User currentUser = (User) auth.getPrincipal();

        return ResponseEntity.ok(currentUser);
    }

    @GetMapping("")
    public ResponseEntity<List<User>> allUsers(){
        return ResponseEntity.ok(userService.findAll());
    }
}
