package com.islacristina.aplicaciongestionincidencias.services;

import com.islacristina.aplicaciongestionincidencias.exceptions.InvalidCredentialsException;
import com.islacristina.aplicaciongestionincidencias.exceptions.UserNotFoundException;
import com.islacristina.aplicaciongestionincidencias.model.User;
import com.islacristina.aplicaciongestionincidencias.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User login(String username, String password) throws UserNotFoundException, InvalidCredentialsException {
        User user = userRepository.findByName(username);
        if (user == null) {
            throw new UserNotFoundException("User not found");
        }

        if (!user.getContrasena().equals(password)) {
            throw new InvalidCredentialsException("Invalid credentials");
        }

        return user;
    }

    public User findByName(String username) {
        return userRepository.findByName(username);
    }

    public void updateUser(User user) {
        userRepository.save(user);
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}