package ru.sanua.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.sanua.demo.Service.Service;
import ru.sanua.demo.dto.TeacherDto;

@Controller
@RequestMapping("/")
public class TeachersController {

    @Autowired
    public TeachersController(Service service) {
        this.service = service;
    }

    private final Service service;

    @GetMapping("/teachers")
    public String findAll(Model model) {
        model.addAttribute("teachers", service.findAllTeacher());
        return "AllTeacher";
    }

    @GetMapping("teacher/{id}")
    public String getById(@PathVariable Integer id, Model model) {
        model.addAttribute("subject", service.getByIdSubjectOrEmpty(id));
        model.addAttribute("teacher",service.getByIdTeachersOrEmpty(id));
        return "viewT";
    }

    @GetMapping("teacher/{id}/remove")
    public String remove(@PathVariable Integer id, Model model) {
        model.addAttribute("teacher", service.getByIdTeachersOrEmpty(id));
        return "remove";
    }

    @PostMapping("/teacher/{id}/remove")
    public String remove(@PathVariable Integer id) {
        service.removeTeacherById(id);
        return "redirect:/teachers";
    }

    @GetMapping("/teacher/{id}/edit")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("subjects", service.findAllSubjects());
        model.addAttribute("teacher", service.getByIdTeachersOrEmpty(id));
        model.addAttribute("groups", service.findAllGroups());
        return "editT";
    }

    @PostMapping("/teacher/{id}/edit")
    public String save(@PathVariable int id, @ModelAttribute TeacherDto teacherDto) {
        service.saveTeacher(teacherDto);

        return "redirect:/teachers";
    }


}


