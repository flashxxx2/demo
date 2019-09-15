package ru.sanua.demo.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "groups1")
public class GroupEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer number;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "groupEntity")
    private List<UserEntity> userEntities;


}
