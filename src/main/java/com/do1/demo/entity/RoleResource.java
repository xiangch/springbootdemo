package com.do1.demo.entity;

import javax.persistence.*;

@Entity
@Table(name="role_resource")
public class RoleResource {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private int roleId;
    private int resourceId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }
}
