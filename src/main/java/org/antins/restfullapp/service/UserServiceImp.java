package org.antins.restfullapp.service;

import lombok.AllArgsConstructor;
import org.antins.restfullapp.exception.UserEmailException;
import org.antins.restfullapp.exception.UserNotFoundException;
import org.antins.restfullapp.model.User;
import org.antins.restfullapp.repository.UserRepository;
import org.antins.restfullapp.response.ResponseHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
@Service
@AllArgsConstructor
public class UserServiceImp implements UserService {
    UserRepository userRepository;
    @Override
    public ResponseEntity<Object> save(User user) {
        if (user.getEmail().equals(userRepository.findByEmail(user.getEmail()).get().getEmail())) {
            throw new UserEmailException("User with email " +
                    user.getEmail() + " already exist use 'i forget my password' form");
        }
        return ResponseHandler.responseBuilder("user with email " + user.getEmail() + " was created",
                HttpStatus.OK, userRepository.save(user).getId());
    }

    @Override
    public ResponseEntity<Object> showAll() {
        return ResponseHandler.responseBuilder("there is all users", HttpStatus.OK, userRepository.findAll());
    }

    @Override
    public ResponseEntity<Object> findById(Long id) {
        if (!userRepository.findById(id).isPresent()) {
            throw new UserNotFoundException("Sorry, User with id = " + id + " doesn't exist");
        }
        return ResponseHandler.responseBuilder(
                "user with id " + id + " was find", HttpStatus.OK, userRepository.findById(id).get());
    }
    @Override
    public ResponseEntity<Object> updateUser(long id) {
        if (!userRepository.findById(id).isPresent()) {
            throw new UserNotFoundException("Sorry, User with id = " + id + " doesn't exist");
        }
        User user = userRepository.findById(id).get();
        Map<String, String> upResponce = new HashMap<>();
        upResponce.put("id", user.getId().toString());
        upResponce.put("previous status", user.getStatus());
        if (user.getStatus().equalsIgnoreCase("offline")) {
            user.setStatus("online");
        } else {
            user.setStatus("offline");
        }
        upResponce.put("current status", user.getStatus());
        userRepository.save(user);
        return ResponseHandler.responseBuilder(
                "user with id " + id + " was updated", HttpStatus.OK, upResponce);
    }

}
