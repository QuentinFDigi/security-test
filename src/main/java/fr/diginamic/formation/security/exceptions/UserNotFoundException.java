package fr.diginamic.formation.security.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super("User not found",new Throwable("User not found"));
    }
}
