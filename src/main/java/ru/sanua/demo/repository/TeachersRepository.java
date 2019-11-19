package ru.sanua.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sanua.demo.entity.TeacherEntity;

import java.util.List;

@Repository
public interface TeachersRepository extends JpaRepository<TeacherEntity,Integer> {
    List<TeacherEntity> findAll();

         }
