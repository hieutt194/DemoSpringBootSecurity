package com.example.demo.entity;

	import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.data.annotation.Id;

	@Entity
	@Embeddable
	@Table(name = "user")
	public class UserEntity implements Serializable {

	    private static final long serialVersionUID = 1L;

	    @javax.persistence.Id
	    @Column(name = "id", nullable = false)
	    private int id;

	    @Column(name = "email", nullable = false, unique = true)
	    private String email;

	    @Column(name = "password", nullable = false)
	    private String password;

	    @ManyToMany(fetch = FetchType.EAGER)
	    @JoinTable(
	            name = "user_role",
	            joinColumns = @JoinColumn(name = "user_id"),
	            inverseJoinColumns = @JoinColumn(name = "role_id")
	    )
	    private Set<RoleEntity> roles;

	    public int getId() {
	        return id;
	    }

	    public void setId(int id) {
	        this.id = id;
	    }

	    public String getEmail() {
	        return email;
	    }

	    public void setEmail(String email) {
	        this.email = email;
	    }

	    public String getPassword() {
	        return password;
	    }

	    public void setPassword(String password) {
	        this.password = password;
	    }

	    public Set<RoleEntity> getRoles() {
	        return roles;
	    }

	    public void setRoles(Set<RoleEntity> roles) {
	        this.roles = roles;
	    }

}
