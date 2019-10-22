package ru.sanua.demo.dto;

import ru.sanua.demo.entity.TeachersEntity;

import javax.persistence.criteria.CriteriaBuilder;

public class RatingDto {
    public Integer id;
    public Integer studentsId;
    public Integer value;
    public Integer subjectId;

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStudentsId() {
        return studentsId;
    }

    public void setStudentsId(Integer studentsId) {
        this.studentsId = studentsId;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
