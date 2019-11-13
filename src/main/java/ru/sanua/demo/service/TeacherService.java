package ru.sanua.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sanua.demo.dto.TeacherDto;
import ru.sanua.demo.entity.TeacherEntity;
import ru.sanua.demo.repository.SubjectsRepository;
import ru.sanua.demo.repository.TeachersRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {
    private final TeachersRepository teachersRepository;
    private final SubjectsRepository subjectsRepository;

    public TeacherService(TeachersRepository teachersRepository, SubjectsRepository subjectsRepository) {
        this.teachersRepository = teachersRepository;
        this.subjectsRepository = subjectsRepository;
    }

    public TeacherEntity getByIdTeachersOrEmpty(Integer id) {
        Optional<TeacherEntity> optionalTeachersEntity = Optional.of(teachersRepository.findById(id).orElse(new TeacherEntity()));
        return optionalTeachersEntity.orElseThrow(RuntimeException::new);
    }

    public List<TeacherEntity> findAllTeacher() {
        return teachersRepository.findAll();

    }

    public void saveTeacher(TeacherDto teacher) {
        TeacherEntity entity = getByIdTeachersOrEmpty(teacher.getId());
        entity.setName(teacher.getName());
        entity.setSubjectEntity(subjectsRepository.findById(teacher.getSubjectId()).get());
        entity.setGroupId(teacher.getGroupId());

        teachersRepository.save(entity);
    }
    @Transactional
    public void removeTeacherById(Integer id) {
        teachersRepository.deleteById(id);

    }
}
