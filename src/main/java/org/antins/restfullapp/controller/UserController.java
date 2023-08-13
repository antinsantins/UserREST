package org.antins.restfullapp.controller;

import lombok.AllArgsConstructor;
import org.antins.restfullapp.model.User;
import org.antins.restfullapp.service.UserService;
import org.antins.restfullapp.service.UserServiceImp;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    UserService userService;
    @GetMapping("/{id}")
    public ResponseEntity<Object> getUser(@PathVariable String id) {
        return userService.findById(Long.parseLong(id));
    }
    @PostMapping
    public ResponseEntity<Object> addUser(@RequestBody User user) {
        return userService.save(user);
    }

    @PutMapping("/{id} ")
    public ResponseEntity<Object>  updateUserStatus(@PathVariable String id) {
        return userService.updateUser(Long.parseLong(id));
    }
    @GetMapping("/all")
    public ResponseEntity<Object> showAllUsers() {
        return userService.showAll();
    }
}
