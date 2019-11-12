package ru.sanua.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.sanua.demo.Service.Service;
import ru.sanua.demo.dto.AverageDto;
import ru.sanua.demo.dto.RatingDto;

import java.util.List;


@Controller
@RequestMapping("/")

public class RatingController {
    private final Service service;


    @Autowired
    public RatingController(Service service) {
        this.service = service;
    }

    @GetMapping("/ratings")
    public String getRating(Model model) {
        model.addAttribute("ratings", service.findAllStudents());
        return "AllRatings";
    }

    @GetMapping("rating/{userId}")
    public String getRatingById(@PathVariable Integer userId, Model model) {

        List<AverageDto> averageDtos = service.getListAvarageDto();
        for (AverageDto averageDto : averageDtos) {
            if (averageDto.getStudentId().equals(userId)){
                model.addAttribute("average", averageDto);

            }
            else return "exceptionRating";
        }
        return "viewR";
    }


    @GetMapping("/rating/{id}/edit")
    public String edit(@PathVariable Integer id, Model model) {

        model.addAttribute("students", service.findAllStudents());
        model.addAttribute("subjects", service.findAllSubjects());
        model.addAttribute("rating", service.getByIdRatingOrEmpty(id));
        return "editR";
    }


    @PostMapping("/rating/{id}/edit")
    public String save(@PathVariable int id, @ModelAttribute RatingDto ratingDto) {
        service.saveRaiting(ratingDto);

        return "redirect:/ratings";
    }


    @GetMapping("rating/{id}/remove")
    public String remove(@PathVariable Integer id, Model model) {
        model.addAttribute("rating", service.getByIdRatingOrEmpty(id));
       // model.addAttribute("student", service.getByIdOrEmpty(id));
        return "remove";
    }

    @PostMapping("/rating/{id}/remove")
    public String remove(@PathVariable Integer id) {
        service.removeRatingById(id);
        return "redirect:/ratings";
    }

    @GetMapping("/ratings/botans")
    public String getBotans(Model model) {
        model.addAttribute("botans", service.getListBotans());
        return "botans";
    }

      @GetMapping("/ratings/loosers")
    public String getLoosers(Model model) {
        model.addAttribute("loosers", service.getListLoosers());
        return "loosers";
    }

}
