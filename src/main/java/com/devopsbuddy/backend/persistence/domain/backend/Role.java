package com.devopsbuddy.backend.persistence.domain.backend;

import com.devopsbuddy.enums.RoleEnum;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by octavio on 12/24/16.
 */

@Entity
public class Role implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    private int id;
    private String name;


    public Role() {
    }

    public Role(RoleEnum role){
        this.id = role.getId();
        this.name = role.getRoleName();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role role = (Role) o;

        return id == role.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
