package ru.sanua.demo.Service;

import ru.sanua.demo.repository.RatingRepository;

import java.util.List;

public class ServiceUtill {
    private final   RatingRepository ratingRepository;

    public ServiceUtill(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    public List<Integer> getAllBotans(){

       List<Integer> values = ratingRepository.findRatingEntitiesByValueOrderByValueDesc();

        return values;
    }
}
