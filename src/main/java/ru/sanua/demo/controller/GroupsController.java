package ru.sanua.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.sanua.demo.Service.Service;
import ru.sanua.demo.dto.GroupDto;
import ru.sanua.demo.repository.GroupsRepository;

@Controller
@RequestMapping("/")

public class GroupsController {

    private final GroupsRepository repository;
    private final Service service;

    @Autowired
    public GroupsController(GroupsRepository repository, Service service) {
        this.repository = repository;
        this.service = service;
    }
    @GetMapping("/groups")
    public String findAll(Model model){
        model.addAttribute("groups",service.findAllGroups());
        return "AllGroups";
    }
    @GetMapping("groups/{id}")
    public String getById(@PathVariable Integer id, Model model) {
        model.addAttribute("group", service.getByIdGroupsOrEmpty(id));
        return "viewG";
    }
    @GetMapping("group/{id}/remove")
    public String remove(@PathVariable Integer id, Model model) {
        model.addAttribute("group", service.getByIdGroupsOrEmpty(id));
        return "remove";
    }

    @PostMapping("/group/{id}/remove")
    public String remove(@PathVariable Integer id) {
        service.removeGroupById(id);
        return "redirect:/groups";
    }
    @GetMapping("/group/{id}/edit")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("subjects", service.findAllSubjects());
        model.addAttribute("group",service.getByIdGroupsOrEmpty(id));
        return "editG";
    }


    @PostMapping("/group/{id}/edit")
    public String save(@PathVariable int id, @ModelAttribute GroupDto groupDto) {
        service.saveGroup(groupDto);

        return "redirect:/groups";
    }
}
