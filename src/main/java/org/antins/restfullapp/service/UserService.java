package org.antins.restfullapp.service;

import org.antins.restfullapp.model.User;
import org.antins.restfullapp.repository.UserRepository;
import org.antins.restfullapp.response.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

public interface UserService {
    ResponseEntity<Object> save(User user);
    ResponseEntity<Object> showAll();
    ResponseEntity<Object> findById(Long id);
    ResponseEntity<Object>  updateUser(long id);
}
