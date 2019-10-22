package ru.sanua.demo.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "student")
public class StudentsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String dateOfBirth;
    private Integer number;
    private Integer value;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "groups_id")
    private GroupsEntity groupsEntity;

    @ManyToMany
    @JoinTable(name = "student_subject",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id"))
    private List<SubjectEntity> subjectEntities;

    public List<SubjectEntity> getSubjectEntities() {
        return subjectEntities;
    }

    public void setSubjectEntities(List<SubjectEntity> subjectEntities) {
        this.subjectEntities = subjectEntities;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }


    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public GroupsEntity getGroupsEntity() {
        return groupsEntity;
    }

    public void setGroupsEntity(GroupsEntity groupsEntity) {
        this.groupsEntity = groupsEntity;
    }
}
