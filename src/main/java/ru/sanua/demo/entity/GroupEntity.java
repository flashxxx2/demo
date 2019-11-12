package ru.sanua.demo.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "groups")
public class GroupEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer number;
    private Integer subjectId;
    private Integer groupId;

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

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

    public Integer getSubjectId() {
        return subjectId;
    }

    public Integer getSubjectId(Integer subjectId) {
        return this.subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

}



