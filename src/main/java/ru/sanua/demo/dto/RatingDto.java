package ru.sanua.demo.dto;

import ru.sanua.demo.entity.TeachersEntity;

public class RatingDto {
    public Integer id;
    public Integer studentsId;
    public String subject;
    public Integer teacherId;
    public Integer value;
    public TeachersEntity teacherName;

    public TeachersEntity getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(TeachersEntity teacherName) {
        this.teacherName = teacherName;
    }
//    public String getTeacherName() {
//        return teacherName;
//    }
//
//    public void setTeacherName(String teacherName) {
//        this.teacherName = teacherName;
//    }

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

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
