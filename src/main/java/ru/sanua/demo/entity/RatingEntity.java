package ru.sanua.demo.entity;


import javax.persistence.*;

@Entity
@Table(name = "rating")

public class RatingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer value;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "students_id")
    private StudentsEntity studentsEntity;



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

    public void setValue(Integer value) {
        this.value = value;
    }

    public void setStudentsEntity(StudentsEntity studentsEntity) {
        this.studentsEntity = studentsEntity;
    }

    public StudentsEntity getStudentsEntity() {
        return studentsEntity;
    }



    public Integer getId() {
        return id;
    }

    public Integer getValue() {
        return value;
    }
}
