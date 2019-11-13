package ru.sanua.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("teacher/{id}")
    public String getById(@PathVariable Integer id, Model model) {
        model.addAttribute("subject", groupService.getByIdSubjectOrEmpty(id));
        model.addAttribute("teacher", teacherService.getByIdTeachersOrEmpty(id));
        return "viewT";
    }

    @GetMapping("teacher/{id}/remove")
    public String remove(@PathVariable Integer id, Model model) {
        model.addAttribute("teacher", teacherService.getByIdTeachersOrEmpty(id));
        return "remove";
    }

    @PostMapping("/teacher/{id}/remove")
    public String remove(@PathVariable Integer id) {
        teacherService.removeTeacherById(id);
        return "redirect:/teachers";
    }

    @GetMapping("/teacher/{id}/edit")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("subjects", groupService.findAllSubjects());
        model.addAttribute("teacher", teacherService.getByIdTeachersOrEmpty(id));
        model.addAttribute("groups", groupService.findAllGroups());
        return "editT";
    }

    @PostMapping("/teacher/{id}/edit")
    public String save(@PathVariable int id, @ModelAttribute TeacherDto teacherDto) {
        teacherService.saveTeacher(teacherDto);

        return "redirect:/teachers";
    }


}


