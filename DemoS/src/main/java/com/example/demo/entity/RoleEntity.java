package com.example.demo.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity
@Embeddable
@Table(name = "role")
public class RoleEntity implements Serializable {
	 private static final long serialVersionUID = 1L;

	    @Id
	    @Column(name = "id", nullable = false)
	    private int id;

	    @Column(name = "name", nullable = false)
	    private String name;

	    @ManyToMany(mappedBy = "roles")
	    private Set<UserEntity> users;

	    public RoleEntity() {
	    }

	    public RoleEntity(String name) {
	        this.name = name;
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

	    public Set<UserEntity> getUsers() {
	        return users;
	    }

	    public void setUsers(Set<UserEntity> users) {
	        this.users = users;
	    }
}
