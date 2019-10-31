package ru.sanua.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ru.sanua.demo.dto.*;
import ru.sanua.demo.entity.*;
import ru.sanua.demo.exception.StudentNotFoundException;
import ru.sanua.demo.repository.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@org.springframework.stereotype.Service

public class Service {
    @Autowired
    private final GroupsRepository groupsRepository;
    private final RatingsRepository ratingsRepository;
    private final StudentsRepository studentsRepository;
    private final TeachersRepository teachersRepository;
    private final SubjectsRepository subjectsRepository;


    public Service(GroupsRepository groupsRepository, RatingsRepository ratingRepository, StudentsRepository studentsRepository, TeachersRepository teachersRepository, SubjectsRepository subjectRepository) {
        this.groupsRepository = groupsRepository;
        this.ratingsRepository = ratingRepository;
        this.studentsRepository = studentsRepository;
        this.teachersRepository = teachersRepository;
        this.subjectsRepository = subjectRepository;

    }

    public List<StudentEntity> getAll() {
        return studentsRepository.findAll();
    }

    public StudentEntity getByIdOrEmpty(Integer id) {
        Optional<StudentEntity> optionalStudentsEntity = Optional.of(studentsRepository.findById(id).orElse(new StudentEntity()));
        return optionalStudentsEntity.orElseThrow(StudentNotFoundException::new);
    }

    public GroupEntity getByIdGroupsOrEmpty(Integer id) {
        Optional<GroupEntity> optionalGroupsEntity = Optional.of(groupsRepository.findById(id).orElse(new GroupEntity()));
        return optionalGroupsEntity.orElseThrow(RuntimeException::new);
    }

    public TeacherEntity getByIdTeachersOrEmpty(Integer id) {
        Optional<TeacherEntity> optionalTeachersEntity = Optional.of(teachersRepository.findById(id).orElse(new TeacherEntity()));
        return optionalTeachersEntity.orElseThrow(RuntimeException::new);
    }

    public RatingEntity getByIdRatingOrEmpty(Integer id) {
        Optional<RatingEntity> optionalRatingEntity = Optional.of(ratingsRepository.findById(id).orElse(new RatingEntity()));
        return optionalRatingEntity.orElseThrow(RuntimeException::new);
    }

    public SubjectEntity getByIdSubjectOrEmpty(Integer id) {
        Optional<SubjectEntity> optionalSubjectEntity = Optional.of(subjectsRepository.findById(id).orElse(new SubjectEntity()));
        return optionalSubjectEntity.orElseThrow(RuntimeException::new);
    }

       public List<StudentEntity> findAllStudents() {
        return studentsRepository.findAll();

    }

    public List<SubjectEntity> findAllSubjects() {
        return subjectsRepository.findAll();
    }

    public List<RatingEntity> findAllRatings() {
        return ratingsRepository.findAll();
    }

    public List<GroupEntity> findAllGroups() {
        return groupsRepository.findAll();

    }

    public List<TeacherEntity> findAllTeacher() {
        return teachersRepository.findAll();

    }

    public void saveStudent(StudentDto student) {
        StudentEntity entity = getByIdOrEmpty(student.getId());
        entity.setName(student.getName());
        entity.setDateOfBirth(student.getDateOfBirth());
        entity.setGroupEntity(groupsRepository.findById(student.getGroupId()).get());
        entity.setId(student.getId());

        studentsRepository.save(entity);

    }

    public void saveTeacher(TeacherDto teacher) {
        TeacherEntity entity = getByIdTeachersOrEmpty(teacher.getId());
        entity.setName(teacher.getName());
        entity.setSubjectEntity(subjectsRepository.findById(teacher.getSubjectId()).get());

        teachersRepository.save(entity);
    }

    public void saveRaiting(RatingDto rating) {
        RatingEntity entity = getByIdRatingOrEmpty(rating.getId());
        entity.setValue(rating.getValue());
        entity.setSubjectEntity(subjectsRepository.findById(rating.getSubjectId()).get());
        entity.setStudentEntity(studentsRepository.findById(rating.getStudentId()).get());
        ratingsRepository.save(entity);
    }

    public void saveGroup(GroupDto group) {
        GroupEntity entity = getByIdGroupsOrEmpty(group.getId());
        entity.setNumber(group.getNumber());
        entity.setSubjectEntity(subjectsRepository.findById(group.getSubjectId()).get());

        groupsRepository.save(entity);
    }


    @Transactional
    public void removeStudentById(Integer id) {
        studentsRepository.deleteById(id);
        ratingsRepository.deleteById(id);

    }

    @Transactional
    public void removeTeacherById(Integer id) {
        teachersRepository.deleteById(id);

    }

    @Transactional
    public void removeGroupById(Integer id) {
        ratingsRepository.deleteById(id);
        studentsRepository.deleteById(id);
        groupsRepository.deleteById(id);
    }

    @Transactional
    public void removeRatingById(Integer id) {
        ratingsRepository.deleteById(id);
        studentsRepository.deleteById(id);
    }

    public List<AverageDto> getListAvarageDto(AverageDto averageDto) {
        double summ = 0;
        Integer sizeEntity;
        double avrValue = 0;
        List<StudentEntity> listOfStudents = studentsRepository.findAll();
        List<AverageDto> averageDtoList = new ArrayList();

        for (int i = 0; i < listOfStudents.size(); i++) {


            if (listOfStudents.get(i).getRatingEntities().size()== 1) {


                avrValue = listOfStudents.get(i).getRatingEntities().get(0).getValue();
                averageDto.setId(i);
                averageDto.setAverageValue(avrValue);
                averageDto.setStudentName(listOfStudents.get(i).getName());
            }
            if (listOfStudents.get(i).getRatingEntities().size() == 2) {
                summ = listOfStudents.get(i).getRatingEntities().get(0).getValue() + listOfStudents.get(i).getRatingEntities().get(1).getValue();
                avrValue = summ / listOfStudents.get(i).getRatingEntities().size();
                averageDto.setAverageValue(avrValue);
                averageDto.setStudentName(listOfStudents.get(i).getName());
            }
            averageDtoList.add(averageDto);
        }
        return averageDtoList;
    }

}
