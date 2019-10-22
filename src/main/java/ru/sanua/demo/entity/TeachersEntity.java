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

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setObject(String object) {
        this.object = object;
    }


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
