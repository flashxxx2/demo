package ru.sanua.demo.entity;

import javax.persistence.*;

@Entity
@Table(name = "users1")
public class UserEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "group_id")
    private GroupEntity groupEntity;

}
