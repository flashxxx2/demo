package ru.sanua.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sanua.demo.entity.GroupEntity;

@Repository
public interface GroupsRepository extends JpaRepository<GroupEntity, Integer> {
   }
