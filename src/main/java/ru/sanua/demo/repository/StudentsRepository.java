package ru.sanua.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sanua.demo.entity.StudentsEntity;

import java.util.List;

@Repository
public interface StudentsRepository extends JpaRepository<StudentsEntity, Integer> {
    List<StudentsEntity> findAll();
       }
