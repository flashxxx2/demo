package ru.sanua.demo.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "teacher")
public class TeachersEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String object;

//    @OneToMany(fetch = FetchType.EAGER)//, mappedBy = "teacherEntity")
//    @JoinColumn(name = "teacher_id")
//    private List<StudentsEntity> studentsEntity;


//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "groups_id")
//    private GroupsEntity groupsEntity;

//    public List<StudentsEntity> getStudentsEntity() {
//        return studentsEntity;
//    }

//    public GroupsEntity getGroupsEntity() {
//        return groupsEntity;
//    }


    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setObject(String object) {
        this.object = object;
    }
//
//    public void setStudentsEntity(List<StudentsEntity> studentsEntity) {
//        this.studentsEntity = studentsEntity;
//    }

//    public void setGroupsEntity(GroupsEntity groupsEntity) {
//        this.groupsEntity = groupsEntity;
//    }

//    public Integer getGroupsNamber() {
//        return groupsEntity.getNumber();
//    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getObject() {
        return object;
    }
}
