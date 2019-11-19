package ru.sanua.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.sanua.demo.service.GroupService;
import ru.sanua.demo.dto.TeacherDto;
import ru.sanua.demo.service.TeacherService;

@Controller
@RequestMapping("/")
public class TeachersController {


    private final GroupService groupService;
    private final TeacherService teacherService;

    public TeachersController(GroupService groupService, TeacherService teacherService) {
        this.groupService = groupService;
        this.teacherService = teacherService;
    }

    @GetMapping("/teachers")
    public String findAll(Model model) {
        model.addAttribute("teachers", teacherService.findAllTeacher());
        return "AllTeacher";
    }

    @GetMapping("teacher/{teacherId}")
    public String getById(@PathVariable Integer teacherId, Model model) {
        model.addAttribute("teacher", teacherService.getTeacherByIdOrEmpty(teacherId));
        return "viewT";
    }

    @GetMapping("teacher/{teacherId}/remove")
    public String remove(@PathVariable Integer teacherId, Model model) {
        model.addAttribute("teacher", teacherService.getTeacherByIdOrEmpty(teacherId));
        return "remove";
    }

    @PostMapping("/teacher/{teacherId}/remove")
    public String remove(@PathVariable Integer teacherId) {
        teacherService.removeTeacherById(teacherId);
        return "redirect:/teachers";
    }

    @GetMapping("/teacher/{teacherId}/edit")
    public String edit(@PathVariable Integer teacherId, Model model) {
        model.addAttribute("subjects", groupService.findAllSubjects());
        model.addAttribute("teacher", teacherService.getTeacherByIdOrEmpty(teacherId));
        model.addAttribute("groups", groupService.findAllGroups());
        return "editT";
    }

    @PostMapping("/teacher/{id}/edit")
    public String save(@PathVariable int id, @ModelAttribute TeacherDto teacherDto) {
        teacherService.saveTeacher(teacherDto);

        return "redirect:/teachers";
    }


}


