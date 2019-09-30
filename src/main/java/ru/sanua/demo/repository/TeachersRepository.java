package ru.sanua.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sanua.demo.entity.TeachersEntity;

import java.util.List;

@Repository
public interface TeachersRepository extends JpaRepository<TeachersEntity,Integer> {
    List<TeachersEntity> findAll();

         }
