package ru.sanua.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sanua.demo.entity.GroupEntity;
import ru.sanua.demo.repository.GroupRepository;

@RestController
@RequestMapping("/api/groups")
@RequiredArgsConstructor
public class GroupController {

    private final GroupRepository repository;

    @GetMapping("/{id}")
    public GroupEntity getGroup(@PathVariable Integer id) {
        return repository.findById(id).orElse(null);
    }
}
