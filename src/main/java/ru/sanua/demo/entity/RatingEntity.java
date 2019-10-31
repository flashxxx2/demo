package ru.sanua.demo.entity;


import javax.persistence.*;

@Entity
@Table(name = "rating")

public class RatingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Double value;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "students_id")
    private StudentEntity studentEntity;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "subject_id")
    private SubjectEntity subjectEntity;


    public SubjectEntity getSubjectEntity() {
        return subjectEntity;
    }

    public void setSubjectEntity(SubjectEntity subjectEntity) {
        this.subjectEntity = subjectEntity;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public void setStudentEntity(StudentEntity studentEntity) {
        this.studentEntity = studentEntity;
    }

    public StudentEntity getStudentEntity() {
        return studentEntity;
    }


    public Integer getId() {
        return id;
    }

    public Double getValue() {
        return value;

    }
}
