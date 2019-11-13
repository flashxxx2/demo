package ru.sanua.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.sanua.demo.service.GroupService;
import ru.sanua.demo.dto.StudentDto;
import ru.sanua.demo.service.StudentService;

@Controller
@RequestMapping("/")

public class StudentsController {
    private final StudentService studentService;
    private final GroupService groupService;

    public StudentsController(StudentService studentService, GroupService groupService) {
        this.studentService = studentService;
        this.groupService = groupService;
    }


    @GetMapping
    public String getAll() {
        return "index";
    }

    @GetMapping("student/{id}")
    public String getById(@PathVariable Integer id, Model model) {
        model.addAttribute("student", studentService.getByIdOrEmpty(id));
        return "view";
    }


    @GetMapping("/students")
    public String findAll(Model model) {
        model.addAttribute("students", studentService.findAllStudents());
        return "AllStudents";
    }

    @GetMapping("/student/{id}/edit")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("student", studentService.getByIdOrEmpty(id));
        model.addAttribute("groups", groupService.findAllGroups());
        return "edit";
    }


    @PostMapping("/student/{id}/edit")
    public String save(@PathVariable int id, @ModelAttribute StudentDto studentDto) {
        studentService.saveStudent(studentDto);
        return "redirect:/students";
    }

    @GetMapping("student/{id}/remove")
    public String remove(@PathVariable Integer id, Model model) {
        model.addAttribute("student", studentService.getByIdOrEmpty(id));
        return "remove";
    }

    @PostMapping("/student/{id}/remove")
    public String remove(@PathVariable Integer id) {
        studentService.removeStudentById(id);
        return "redirect:/students";
    }


}
