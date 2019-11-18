package ru.sanua.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.sanua.demo.repository.SubjectsRepository;
import ru.sanua.demo.service.GroupService;
import ru.sanua.demo.dto.GroupDto;
import ru.sanua.demo.dto.GroupSubjectDto;
import ru.sanua.demo.repository.GroupsRepository;


@Controller
@RequestMapping("/")

public class GroupsController {

    private final GroupsRepository groupsRepository;
    private final GroupService groupService;

    public GroupsController(GroupsRepository groupsRepository, GroupService groupService, SubjectsRepository subjectsRepository) {
        this.groupsRepository = groupsRepository;
        this.groupService = groupService;
        this.subjectsRepository = subjectsRepository;
    }

    private final SubjectsRepository subjectsRepository;


    @GetMapping("/groups")
    public String findAll(Model model) {
        model.addAttribute("groups", groupService.findAllGroups());
        return "AllGroups";
    }

    @GetMapping("groups/{groupId}")
    public String getById(@PathVariable Integer groupId, Model model) {
        model.addAttribute("group", groupService.getGroupByIdOrEmpty(groupId));
        model.addAttribute("subjects", groupService.getSubjectEntityListByGroupId(groupId));

        return "viewG";
    }

    @GetMapping("group/{groupId}/remove")
    public String remove(@PathVariable Integer groupId, Model model) {
        model.addAttribute("group", groupService.getGroupByIdOrEmpty(groupId));
        return "remove";
    }

    @PostMapping("/group/{groupId}/remove")
    public String remove(@PathVariable Integer groupId) {
        groupService.removeGroupById(groupId);
        return "redirect:/groups";
    }

    @GetMapping("/group/{groupId}/edit")
    public String edit(@PathVariable Integer groupId, Model model) {
        model.addAttribute("subjects", groupService.findAllSubjects());
        model.addAttribute("group", groupService.getGroupByIdOrEmpty(groupId));
        return "editG";
    }

    @GetMapping("/group/{id}/addSubject")
    public String addSubject(@PathVariable Integer id, Model model) {
        model.addAttribute("subjects", groupService.findAllSubjects());
        model.addAttribute("groups", groupService.findAllGroups());

        return "addSubject";
    }

    @PostMapping("/group/{id}/addSubject")
    public String saveSubject(@PathVariable Integer id, @ModelAttribute GroupSubjectDto groupSubjectDto) {
        groupService.saveGroupSubject(groupSubjectDto);
        return "redirect:/groups";
    }

    @PostMapping("/group/{id}/edit")
    public String save(@PathVariable int id, @ModelAttribute GroupDto groupDto) {
        groupService.saveGroup(groupDto);

        return "redirect:/groups";
    }

}
