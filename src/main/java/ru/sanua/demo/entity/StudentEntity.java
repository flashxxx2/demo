package ru.sanua.demo.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "student")
public class StudentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String dateOfBirth;
//    private Integer number;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "groups_id")
    private GroupEntity groupEntity;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "studentEntity")
    List<RatingEntity> ratingEntities;

    public List<RatingEntity> getRatingEntities() {
        return ratingEntities;
    }

    public void setRatingEntities(List<RatingEntity> ratingEntities) {
        this.ratingEntities = ratingEntities;
    }

//    public Integer getNumber() {
//        return number;
//    }

//    public void setNumber(Integer number) {
//        this.number = number;
//    }

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

    public GroupEntity getGroupEntity() {
        return groupEntity;
    }

    public void setGroupEntity(GroupEntity groupEntity) {
        this.groupEntity = groupEntity;
    }
}
