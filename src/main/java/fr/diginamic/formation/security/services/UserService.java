package fr.diginamic.formation.security.services;

import fr.diginamic.formation.security.entities.User;
import fr.diginamic.formation.security.exceptions.UserNotFoundException;
import fr.diginamic.formation.security.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll(){
        List<User> users = userRepository.findAll();

        if (users.isEmpty()){
            throw new UserNotFoundException();
        }

        return users;
    }

    public User update(long id, User updatedUser){
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);

        user.setFullName(updatedUser.getFullName());
        user.setEmail(updatedUser.getEmail());
        user.setPassword(updatedUser.getPassword());
        user.setUpdatedAt(new Date());

        return userRepository.save(user);
    }

    public void delete(long id){
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);

        userRepository.delete(user);
    }
}
