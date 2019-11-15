package ru.sanua.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sanua.demo.dto.GroupDto;
import ru.sanua.demo.dto.GroupSubjectDto;
import ru.sanua.demo.entity.GroupEntity;
import ru.sanua.demo.entity.GroupSubjectEntity;
import ru.sanua.demo.entity.StudentEntity;
import ru.sanua.demo.entity.SubjectEntity;
import ru.sanua.demo.repository.GroupSubjectRepository;
import ru.sanua.demo.repository.GroupsRepository;
import ru.sanua.demo.repository.StudentsRepository;
import ru.sanua.demo.repository.SubjectsRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GroupService {
    private final GroupsRepository groupsRepository;
    private final GroupSubjectRepository groupSubjectRepository;
    private final SubjectsRepository subjectsRepository;
    private final StudentsRepository studentsRepository;


    public GroupService(GroupsRepository groupsRepository, GroupSubjectRepository groupSubjectRepository, SubjectsRepository subjectsRepository, StudentsRepository studentsRepository) {
        this.groupsRepository = groupsRepository;

        this.groupSubjectRepository = groupSubjectRepository;
        this.subjectsRepository = subjectsRepository;
        this.studentsRepository = studentsRepository;
    }

    public GroupEntity getGroupByIdOrEmpty(Integer teacherId) {
        Optional<GroupEntity> optionalGroupsEntity = Optional.of(groupsRepository.findById(teacherId).orElse(new GroupEntity()));
        return optionalGroupsEntity.orElseThrow(RuntimeException::new);
    }

    public SubjectEntity getSubjectBySubjectIdOrEmpty(Integer subjectId) {
        Optional<SubjectEntity> optionalSubjectEntity = Optional.of(subjectsRepository.findById(subjectId).orElse(new SubjectEntity()));
        return optionalSubjectEntity.orElseThrow(RuntimeException::new);
    }

    public GroupSubjectEntity getGroupSubjectByIdOrEmpty(Integer groupSubjectId) {
        Optional<GroupSubjectEntity> optionalGroupSubjectEntity = Optional.of(groupSubjectRepository.findById(groupSubjectId).orElse(new GroupSubjectEntity()));
        return optionalGroupSubjectEntity.orElseThrow(RuntimeException::new);
    }

    public List<GroupEntity> findAllGroups() {
        return groupsRepository.findAll();

    }

    public List<SubjectEntity> findAllSubjects() {
        return subjectsRepository.findAll();
    }

    public List<GroupSubjectEntity> findAllGroupSubject() {
        return groupSubjectRepository.findAll();
    }

    public void saveGroup(GroupDto group) {
        GroupEntity entity = getGroupByIdOrEmpty(group.getId());
        entity.setNumber(group.getNumber());
        groupsRepository.save(entity);
    }

    public void saveGroupSubject(GroupSubjectDto groupSubjectDto) {
        GroupSubjectEntity entity = getGroupSubjectByIdOrEmpty(groupSubjectDto.getId());
        entity.setGroupEntity(groupsRepository.findById(groupSubjectDto.getGroupId()).get());
        entity.setSubjectEntity(subjectsRepository.findById(groupSubjectDto.getSubjectId()).get());
        groupSubjectRepository.save(entity);
    }

    @Transactional
    public void removeGroupById(Integer groupId) {
        List<StudentEntity> allStudents = studentsRepository.findAll();
        List<GroupSubjectEntity> allGroupSubject = findAllGroupSubject();
        for (int i = 0; i < allStudents.size(); i++) {
            GroupEntity group = allStudents.get(i).getGroupEntity();

            if (!(group == (null)) && group.getId().equals(groupId)) {
                allStudents.
                        get(i).
                        setGroupEntity(null);
            }
        }
        for (int j = 0; j < allGroupSubject.size(); j++) {
            GroupEntity groupEntity = allGroupSubject.get(j).getGroupEntity();

            if (!(groupEntity == (null)) && groupEntity.getId().equals(groupId)) {
                allGroupSubject.
                        get(j).
                        setGroupEntity(null);

            }
        }
        groupsRepository.deleteById(groupId);
    }

    public List<GroupSubjectEntity> getSubjectEntityListById(Integer groupId) {
        List<GroupSubjectEntity> allGroupSubject = findAllGroupSubject();
        List<GroupSubjectEntity> groupSubjectEntities = new ArrayList<>();
        for (int i = 0; i < allGroupSubject.size(); i++) {
            GroupSubjectEntity groupSubjectEntity = allGroupSubject.get(i);
            if (!(groupSubjectEntity.getGroupEntity() == (null)) && groupSubjectEntity.getGroupEntity().getId().equals(groupId)) {

                groupSubjectEntities.add(groupSubjectEntity);

            }
        }
        return groupSubjectEntities;
    }
}
