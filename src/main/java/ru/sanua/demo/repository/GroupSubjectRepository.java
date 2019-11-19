package ru.sanua.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sanua.demo.entity.GroupSubjectEntity;
@Repository
public interface GroupSubjectRepository extends JpaRepository<GroupSubjectEntity, Integer> {
}
