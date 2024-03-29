package ru.sanua.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sanua.demo.entity.StudentEntity;

import java.util.List;

@Repository
public interface StudentsRepository extends JpaRepository<StudentEntity, Integer> {
    List<StudentEntity> findAll();
}
