package ru.sanua.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ru.sanua.demo.dto.GroupDto;
import ru.sanua.demo.dto.RatingDto;
import ru.sanua.demo.dto.StudentDto;
import ru.sanua.demo.dto.TeacherDto;
import ru.sanua.demo.entity.*;
import ru.sanua.demo.exception.StudentNotFoundException;
import ru.sanua.demo.repository.*;

import java.util.List;
import java.util.Optional;


@org.springframework.stereotype.Service

public class Service {
    @Autowired
    private final GroupsRepository groupsRepository;
    private final RatingRepository ratingRepository;
    private final StudentsRepository studentsRepository;
    private final TeachersRepository teachersRepository;
    private final SubjectRepository subjectRepository;

    public Service(GroupsRepository groupsRepository, RatingRepository ratingRepository, StudentsRepository studentsRepository, TeachersRepository teachersRepository, SubjectRepository subjectRepository) {
        this.groupsRepository = groupsRepository;
        this.ratingRepository = ratingRepository;
        this.studentsRepository = studentsRepository;
        this.teachersRepository = teachersRepository;
        this.subjectRepository = subjectRepository;
    }

    public List<StudentsEntity> getAll() {
        return studentsRepository.findAll();
    }

    public StudentsEntity getByIdOrEmpty(Integer id) {
        Optional<StudentsEntity> optionalStudentsEntity = Optional.of(studentsRepository.findById(id).orElse(new StudentsEntity()));
        return optionalStudentsEntity.orElseThrow(StudentNotFoundException::new);
    }

    public GroupsEntity getByIdGroupsOrEmpty(Integer id) {
        Optional<GroupsEntity> optionalGroupsEntity = Optional.of(groupsRepository.findById(id).orElse(new GroupsEntity()));
        return optionalGroupsEntity.orElseThrow(RuntimeException::new);
    }

    public TeachersEntity getByIdTeachersOrEmpty(Integer id) {
        Optional<TeachersEntity> optionalTeachersEntity = Optional.of(teachersRepository.findById(id).orElse(new TeachersEntity()));
        return optionalTeachersEntity.orElseThrow(RuntimeException::new);
    }

    public RatingEntity getByIdRatingOrEmpty(Integer id) {
        Optional<RatingEntity> optionalRatingEntity = Optional.of(ratingRepository.findById(id).orElse(new RatingEntity()));
        return optionalRatingEntity.orElseThrow(RuntimeException::new);
    }

    public SubjectEntity getByIdSubjectOrEmpty(Integer id) {
        Optional<SubjectEntity> optionalSubjectEntity = Optional.of(subjectRepository.findById(id).orElse(new SubjectEntity()));
        return optionalSubjectEntity.orElseThrow(RuntimeException::new);
    }


    public List<StudentsEntity> findAllStudents() {
        return studentsRepository.findAll();

    }

    public List<SubjectEntity> findAllSubjects() {
        return subjectRepository.findAll();
    }

    public List<RatingEntity> findAllRatings() {
        return ratingRepository.findAll();
    }

    public List<GroupsEntity> findAllGroups() {
        return groupsRepository.findAll();

    }

    public List<TeachersEntity> findAllTeacher() {
        return teachersRepository.findAll();

    }

    public void saveStudent(StudentDto student) {
        StudentsEntity entity = getByIdOrEmpty(student.getId());
        entity.setName(student.getName());
        entity.setDateOfBirth(student.getDateOfBirth());
        entity.setGroupsEntity(groupsRepository.findById(student.getGroupId()).get());
        //entity.setNumber(groupsRepository.findById(student.getNumber()));
        entity.setId(student.getId());

        studentsRepository.save(entity);
        //из др энтити нужно номер группы
    }

    public void saveTeacher(TeacherDto teacher) {
        TeachersEntity entity = getByIdTeachersOrEmpty(teacher.getId());
        entity.setName(teacher.getName());
        entity.setSubjectEntity(subjectRepository.findById(teacher.getSubjectId()).get());

        teachersRepository.save(entity);
    }

    public void saveRaiting(RatingDto rating) {
        RatingEntity entity = getByIdRatingOrEmpty(rating.getId());
        entity.setValue(rating.getValue());
        entity.setSubjectEntity(subjectRepository.findById(rating.getSubjectId()).get());
        entity.setStudentsEntity(studentsRepository.findById(rating.getStudentsId()).get());
        ratingRepository.save(entity);
    }

    public void saveGroup(GroupDto group) {
        GroupsEntity entity = getByIdGroupsOrEmpty(group.getId());
        entity.setNumber(group.getNumber());
        entity.setSubjectEntity(subjectRepository.findById(group.getSubjectId()).get());
        // entity.setTeachersEntity(teachersRepository.findById(group.getTeacherId()).get());

        groupsRepository.save(entity);
    }
//    public List<StudentsEntity> getGoodStudents(){
//        return ratingRepository.findAllByStudentsEntityOrderByValueAsc();
//    }

    @Transactional
    public void removeStudentById(Integer id) {
        studentsRepository.deleteById(id);
        ratingRepository.deleteById(id);
        //  teachersRepository.deleteById(id);
        // groupsRepository.deleteById(id);

    }

    @Transactional
    public void removeTeacherById(Integer id) {
        teachersRepository.deleteById(id);

    }

    @Transactional
    public void removeGroupById(Integer id) {
        ratingRepository.deleteById(id);
        studentsRepository.deleteById(id);
        groupsRepository.deleteById(id);



    }

    @Transactional
    public void removeRatingById(Integer id) {
        ratingRepository.deleteById(id);
        studentsRepository.deleteById(id);
    }
}
