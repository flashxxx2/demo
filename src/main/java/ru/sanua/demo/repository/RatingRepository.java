package ru.sanua.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sanua.demo.entity.RatingEntity;
import ru.sanua.demo.entity.StudentsEntity;

import java.util.List;

@Repository

public interface RatingRepository extends JpaRepository<RatingEntity, Integer> {

  //  List<StudentsEntity> findAllByStudentsEntityOrderByValueAsc();

    List<Integer>findRatingEntitiesByValueOrderByValueDesc();
}
