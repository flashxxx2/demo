package ru.sanua.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ru.sanua.demo.dto.*;
import ru.sanua.demo.entity.*;
import ru.sanua.demo.exception.StudentNotFoundException;
import ru.sanua.demo.repository.*;

import java.text.DecimalFormat;
import java.util.*;


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
        return optionalStudentsEntity.orElseThrow(RuntimeException::new);
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
        ratingsRepository.save(entity);

        entity.setStudentEntity(studentsRepository.findById(rating.getStudentId()).get());
        // entity.setTeachersEntity(teachersRepository.findById(rating.getTeacherId()).get());
        ratingsRepository.save(entity);
    }

    public void saveGroup(GroupDto group) {
        GroupEntity entity = getByIdGroupsOrEmpty(group.getId());
        entity.setNumber(group.getNumber());
        entity.setSubjectEntity(subjectsRepository.findById(group.getSubjectId()).get());


        groupsRepository.save(entity);
    }

    public void saveGroupSubject(GroupDto group) {
        GroupEntity entity = getByIdGroupsOrEmpty(group.getId());
        entity.setSubjectEntity(subjectsRepository.findById(group.getSubjectId()).get());
        entity.setId(group.getGroupId());
        entity.setNumber(getByIdGroupsOrEmpty(group.getGroupId()).getNumber());
        groupsRepository.save(entity);
    }

    @Transactional
    public void removeStudentById(Integer id) {

        List<RatingEntity> allRatings = findAllRatings();
        for (int i = 0; i < allRatings.size(); i++) {
            StudentEntity student = allRatings.get(i).getStudentEntity();

            if (!(student == null) && student.getId().equals(id)) {
                allRatings.get(i).setStudentEntity(null);
            }

        }

        studentsRepository.deleteById(id);
    }


    @Transactional
    public void removeTeacherById(Integer id) {
        teachersRepository.deleteById(id);

    }

    @Transactional
    public void removeGroupById(Integer id) {
        List<StudentEntity> allStudents = findAllStudents();
        for (int i = 0; i < allStudents.size(); i++) {
            GroupEntity group = allStudents.get(i).getGroupEntity();

            if (!(group == (null)) && group.getId().equals(id)) {
                allStudents.
                        get(i).
                        setGroupEntity(null);
            }


        }

        groupsRepository.deleteById(id);
    }


    @Transactional
    public void removeRatingById(Integer id) {
        ratingsRepository.deleteById(id);
    }

    public List<AverageDto> getListAvarageDto() {
        double summ;
        double avrValue;
        List<StudentEntity> listOfStudents = studentsRepository.findAll();
        List<AverageDto> averageDtoList = new ArrayList();
        for (int i = 0; i < listOfStudents.size(); i++) {
            summ = 0;
            AverageDto averageDto = new AverageDto();
            for (int j = 0; j < listOfStudents.get(i).getRatingEntities().size(); j++) {
                summ = summ + listOfStudents.get(i).getRatingEntities().get(j).getValue();
                avrValue = summ / listOfStudents.get(i).getRatingEntities().size();
                avrValue = Math.round(avrValue * 100);
                avrValue = avrValue / 100;
                averageDto.setAvrValue(avrValue);
                averageDto.setId(getByIdRatingOrEmpty(j).getId());
                averageDto.setStudentId(listOfStudents.get(i).getId());
                averageDto.setStudentName(listOfStudents.get(i).getName());
            }
            if (!(averageDto.getAvrValue()==null)) {
                averageDtoList.add(averageDto);
            }
        }
        return averageDtoList;
    }


    public List<AverageDto> getListBotans() {
        List<AverageDto> listBotans = getListAvarageDto();
        listBotans.sort(new Comparator<AverageDto>() {
            @Override
            public int compare(AverageDto o1, AverageDto o2) {
                if (o1.getAvrValue() == o2.getAvrValue()) return 0;
                else if (o1.getAvrValue() > o2.getAvrValue()) return -1;
                else return 1;
            }


        });
        return listBotans.subList(0, 3);
    }

    public List<AverageDto> getListLoosers() {
        List<AverageDto> listLoosers = getListAvarageDto();
        listLoosers.sort(new Comparator<AverageDto>() {
            @Override
            public int compare(AverageDto o1, AverageDto o2) {
                if (o1.getAvrValue() == o2.getAvrValue()) return 0;
                else if (o1.getAvrValue() > o2.getAvrValue()) return 1;
                else return -1;
            }


        });
        return listLoosers.subList(0, 3);
    }
}

