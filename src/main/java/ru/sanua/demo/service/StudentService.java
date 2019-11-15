package ru.sanua.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sanua.demo.dto.StudentDto;
import ru.sanua.demo.entity.RatingEntity;
import ru.sanua.demo.entity.StudentEntity;
import ru.sanua.demo.repository.GroupsRepository;
import ru.sanua.demo.repository.RatingsRepository;
import ru.sanua.demo.repository.StudentsRepository;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentsRepository studentsRepository;
    private final RatingsRepository ratingsRepository;
    private final GroupsRepository groupsRepository;

    public StudentService(StudentsRepository studentsRepository, RatingsRepository ratingsRepository, GroupsRepository groupsRepository) {
        this.studentsRepository = studentsRepository;
        this.ratingsRepository = ratingsRepository;
        this.groupsRepository = groupsRepository;
    }
    public StudentEntity getByIdOrEmpty(Integer id) {
        Optional<StudentEntity> optionalStudentsEntity = Optional.of(studentsRepository.findById(id).orElse(new StudentEntity()));
        return optionalStudentsEntity.orElseThrow(RuntimeException::new);
    }
    public List<StudentEntity> findAllStudents() {
        return studentsRepository.findAll();

    }
    public void saveStudent(StudentDto student) {
        StudentEntity entity = getByIdOrEmpty(student.getId());
        entity.setName(student.getName());
        entity.setDateOfBirth(student.getDateOfBirth());
        entity.setGroupEntity(groupsRepository.findById(student.getGroupId()).get());
        entity.setId(student.getId());

        studentsRepository.save(entity);

    }
    @Transactional
    public void removeStudentById(Integer id) {

        List<RatingEntity> allRatings =ratingsRepository.findAll();
        for (int i = 0; i < allRatings.size(); i++) {
            StudentEntity student = allRatings.get(i).getStudentEntity();

            if (!(student == null) && student.getId().equals(id)) {
                allRatings.get(i).setStudentEntity(null);
            }

        }

        studentsRepository.deleteById(id);
    }
}
