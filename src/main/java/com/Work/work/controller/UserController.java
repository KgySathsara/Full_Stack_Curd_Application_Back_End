package com.Work.work.controller;

import com.Work.work.exception.UserNotFoundException;
import com.Work.work.model.User;
import com.Work.work.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class UserController {
    @Autowired
    private UserRepository UserRepository;

    @PostMapping("/user")
    User newUser(@RequestBody User newUser){
        return UserRepository.save(newUser);
    }

    @GetMapping("/users")
    List<User> getAllUsers(){
        return UserRepository.findAll();
    }

    @GetMapping("/user/{id}")
    User getUserById(@PathVariable Long id){
        return UserRepository.findById(id)
                .orElseThrow(()->new UserNotFoundException(id));
    }

    @PutMapping("/user/{id}")
    User updateUser(@RequestBody User newUser, @PathVariable Long id){
        return UserRepository.findById(id)
                .map(user -> {
                    user.setUsername(newUser.getUsername());
                    user.setName(newUser.getName());
                    user.setEmail(newUser.getEmail());
                    return UserRepository.save(user);
                }).orElseThrow(()->new UserNotFoundException(id));
    }

    @DeleteMapping("/user/{id}")
    String deleteUser(@PathVariable Long id){
        if(!UserRepository.existsById(id)){
            throw new UserNotFoundException(id);
        }
        UserRepository.deleteById(id);
        return "User with id" + id +"has been deleted Success";
    }
}
