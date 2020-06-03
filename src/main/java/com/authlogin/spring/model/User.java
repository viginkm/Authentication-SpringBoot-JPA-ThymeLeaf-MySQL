package com.authlogin.spring.model;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Cascade;
import org.hibernate.cache.spi.entry.ReferenceCacheEntryImpl;

@Entity
@Table(uniqueConstraints=@UniqueConstraint(columnNames="email"))
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String firstName;
    private String lastName;
	private String email;
	private String password;
	
	@ManyToMany(fetch=FetchType.EAGER,  cascade = CascadeType.ALL)
	

	@JoinTable(
			
			name = "user_role" ,  
			
			joinColumns = @JoinColumn(
					
					name="user_id", referencedColumnName="id"),
			inverseJoinColumns = @JoinColumn(
					
					name="role_id", referencedColumnName="id"))
					

	
	private Collection<Role>  roles;
	
	
	

	public User() {
		super();
	}



	public User(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public User(String firstName, String lastName, String email, String password, Collection<Role> roles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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



	public Collection<Role> getRoles() {
		return roles;
	}



	public void setRole(Collection<Role> roles) {
		this.roles = roles;
	}



	@Override
	public String toString() {
		return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + "*********" + '\'' +
                ", roles=" + roles +
                '}';
	}
	
	
	
	
	

}
