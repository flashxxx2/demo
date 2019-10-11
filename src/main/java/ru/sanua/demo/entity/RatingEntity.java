package ru.sanua.demo.entity;


import javax.persistence.*;

@Entity
@Table(name= "rating")

public class RatingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer value;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "students_id")
    private StudentsEntity studentsEntity;


    public void setId(Integer id) {
        this.id = id;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public void setStudentsEntity(StudentsEntity studentsEntity) {
        this.studentsEntity = studentsEntity;
    }

    public void setTeachersEntity(TeachersEntity teachersEntity) {
        this.teachersEntity = teachersEntity;
    }



    public String getTeacherName() {
        return teachersEntity.getName();
    }
    public String getObject(){
        return teachersEntity.getObject();
    }



    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "teacher_id")
    private TeachersEntity teachersEntity;


    public Integer getId() {
        return id;
    }

        public Integer getValue() {
        return value;
    }
 }
