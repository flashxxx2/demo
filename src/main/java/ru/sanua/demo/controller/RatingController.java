package ru.sanua.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import ru.sanua.demo.dto.AverageDto;
import ru.sanua.demo.dto.RatingDto;
import ru.sanua.demo.service.GroupService;
import ru.sanua.demo.service.RatingService;
import ru.sanua.demo.service.StudentService;

import java.util.List;


@Controller
@RequestMapping("/")

public class RatingController {
    private final RatingService ratingService;
    private final StudentService studentService;
    private final GroupService groupService;

    public RatingController(RatingService ratingService, StudentService studentService, GroupService groupService) {
        this.ratingService = ratingService;
        this.studentService = studentService;
        this.groupService = groupService;
    }


    @GetMapping("/ratings")
    public String getRating(Model model) {
        model.addAttribute("ratings", studentService.findAllStudents());
        return "AllRatings";

    }

    @GetMapping("rating/{userId}")
    public String getRatingById(@PathVariable Integer userId, Model model) {

        List<AverageDto> averageDtos = ratingService.getListAvarageDto();
        for (AverageDto averageDto : averageDtos) {
            if (averageDto.getStudentId().equals(userId)){
                model.addAttribute("average", averageDto);

                return "viewR";
            }

        }
        return "exceptionRating";
    }
    @GetMapping("/rating/{id}/edit")
    public String edit(@PathVariable Integer id, Model model) {

        model.addAttribute("students", studentService.findAllStudents());
        model.addAttribute("subjects", groupService.findAllSubjects());
        model.addAttribute("rating", ratingService.getByIdRatingOrEmpty(id));
        return "editR";
    }


    @PostMapping("/rating/{id}/edit")
    public String save(@PathVariable int id, @ModelAttribute RatingDto ratingDto) {
        ratingService.saveRaiting(ratingDto);

        return "redirect:/ratings";
    }


    @GetMapping("rating/{id}/remove")
    public String remove(@PathVariable Integer id, Model model) {
        model.addAttribute("rating", ratingService.getByIdRatingOrEmpty(id));
         return "remove";
    }

    @PostMapping("/rating/{id}/remove")
    public String remove(@PathVariable Integer id) {
        ratingService.removeRatingById(id);
        return "redirect:/ratings";
    }

    @GetMapping("/ratings/botans")
    public String getBotans(Model model) {
        model.addAttribute("botans", ratingService.getListBotans());
        return "botans";
    }

      @GetMapping("/ratings/loosers")
    public String getLoosers(Model model) {
        model.addAttribute("loosers", ratingService.getListLoosers());
        return "loosers";
    }

}
