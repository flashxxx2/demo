package ru.sanua.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sanua.demo.entity.UserEntity;
import ru.sanua.demo.repository.UserRepository;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository repository;

    @GetMapping("/{id}")
    public UserEntity getUser(@PathVariable Integer id) {
        return repository.findById(id).orElse(null);
    }
}
