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

    @GetMapping("rating/{studentId}")
    public String getRatingById(@PathVariable Integer studentId, Model model) {

        List<AverageDto> averageDtos = ratingService.getAvarageDtoList();
        for (AverageDto averageDto : averageDtos) {
            if (averageDto.getStudentId().equals(studentId)){
                model.addAttribute("average", averageDto);

                return "viewR";
            }

        }
        return "exceptionRating";
    }
    @GetMapping("/rating/{ratingId}/edit")
    public String edit(@PathVariable Integer ratingId, Model model) {

        model.addAttribute("students", studentService.findAllStudents());
        model.addAttribute("subjects", groupService.findAllSubjects());
        model.addAttribute("rating", ratingService.getRatingByRatingId(ratingId));
        return "editR";
    }


    @PostMapping("/rating/{id}/edit")
    public String save(@PathVariable int id, @ModelAttribute RatingDto ratingDto) {
        ratingService.saveRating(ratingDto);

        return "redirect:/ratings";
    }


    @GetMapping("rating/{ratingId}/remove")
    public String remove(@PathVariable Integer ratingId, Model model) {
        model.addAttribute("rating", ratingService.getRatingByRatingId(ratingId));
         return "remove";
    }

    @PostMapping("/rating/{ratingId}/remove")
    public String remove(@PathVariable Integer ratingId) {
        ratingService.removeRatingByRatingId(ratingId);
        return "redirect:/ratings";
    }

    @GetMapping("/ratings/botans")
    public String getBotans(Model model) {
        model.addAttribute("botans", ratingService.getBotansList());
        return "botans";
    }

      @GetMapping("/ratings/loosers")
    public String getLoosers(Model model) {
        model.addAttribute("loosers", ratingService.getLoosersList());
        return "loosers";
    }

}
