package ru.sanua.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.sanua.demo.Service.Service;
import ru.sanua.demo.dto.StudentDto;
import ru.sanua.demo.entity.StudentEntity;

import java.util.List;

@Controller
@RequestMapping("/")

public class StudentsController {
    private final Service service;

    @Autowired
    public StudentsController(Service service) {
        this.service = service;
    }

    @GetMapping
    public String getAll() {
        return "index";
    }

    @GetMapping("student/{id}")
    public String getById(@PathVariable Integer id, Model model) {

        model.addAttribute("student", service.getByIdOrEmpty(id));
        return "view";
    }


    @GetMapping("/students")
    public String findAll(Model model) {
        model.addAttribute("students", service.findAllStudents());
        return "AllStudents";
    }

    @GetMapping("/student/{id}/edit")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("student", service.getByIdOrEmpty(id));
        model.addAttribute("groups", service.findAllGroups());
        return "edit";
    }


    @PostMapping("/student/{id}/edit")
    public String save(@PathVariable int id, @ModelAttribute StudentDto studentDto) {
        service.saveStudent(studentDto);
        return "redirect:/students";
    }

    @GetMapping("student/{id}/remove")
    public String remove(@PathVariable Integer id, Model model) {
        model.addAttribute("student", service.getByIdOrEmpty(id));
//        List<StudentEntity> studentEntityList = service.findAllStudents();
//        for (StudentEntity studentEntity : studentEntityList) {
//            if (studentEntity.getId() == id) {
//                model.addAttribute("student", service.getByIdOrEmpty(id));
//            }
//        }

        return "remove";
    }

    @PostMapping("/student/{id}/remove")
    public String remove(@PathVariable Integer id) {
        service.removeStudentById(id);
        return "redirect:/students";
    }


}
