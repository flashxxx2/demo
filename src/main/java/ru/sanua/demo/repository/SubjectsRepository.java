package ru.sanua.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sanua.demo.entity.SubjectEntity;

import java.util.List;

@Repository
public interface SubjectsRepository extends JpaRepository<SubjectEntity, Integer> {
    List<SubjectEntity> findAll();
}
