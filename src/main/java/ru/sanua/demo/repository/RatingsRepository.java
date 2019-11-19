package ru.sanua.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sanua.demo.entity.RatingEntity;

import java.util.List;

@Repository

public interface RatingsRepository extends JpaRepository<RatingEntity, Integer> {

}
