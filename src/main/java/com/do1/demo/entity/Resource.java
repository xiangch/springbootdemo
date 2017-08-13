package com.do1.demo.entity;

import javax.persistence.*;

@Entity
@Table(name="resource")
public class Resource {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String key;
    private String path;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
