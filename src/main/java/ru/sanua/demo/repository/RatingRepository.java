package ru.sanua.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sanua.demo.entity.RatingEntity;

@Repository

public interface RatingRepository extends JpaRepository<RatingEntity, Integer> {
}
