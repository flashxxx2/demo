package ru.sanua.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.sanua.demo.entity.GroupEntity;

import java.util.List;

@Repository
public interface GroupsRepository extends JpaRepository<GroupEntity, Integer> {
    @Override
    @Query(value = "SELECT * FROM groups",nativeQuery = true)
    List<GroupEntity> findAll();
}
