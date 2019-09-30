package ru.sanua.demo.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "groups")
public class GroupsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer number;
    private String subject;


    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "groupsEntity")
    private List<StudentsEntity> studentsEntities;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }



    public List<StudentsEntity> getStudentsEntities() {
        return studentsEntities;
    }

    public void setStudentsEntities(List<StudentsEntity> studentsEntities) {
        this.studentsEntities = studentsEntities;
    }



}



