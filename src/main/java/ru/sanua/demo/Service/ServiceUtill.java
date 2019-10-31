package ru.sanua.demo.Service;


import ru.sanua.demo.entity.RatingEntity;
import ru.sanua.demo.repository.RatingsRepository;

import java.util.HashMap;
import java.util.List;


public class ServiceUtill {
    private final RatingsRepository ratingsRepository;

    public ServiceUtill(RatingsRepository ratingsRepository) {
        this.ratingsRepository = ratingsRepository;
    }

}
