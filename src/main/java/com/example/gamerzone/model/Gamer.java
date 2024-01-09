// com.example.gamerzone.model.Gamer
package com.example.gamerzone.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.cache.annotation.Cacheable;

@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Gamer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Username is required")
	private String username;

	@Email(message = "Invalid email format")
	private String email;

	@Min(value = 0, message = "Age must be at least 0")
	private int age;

	// Constructors
	public Gamer() {
	}

	public Gamer(Long id, String username, String email, int age) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.age = age;
	}

	// Getters and setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Gamer{" + "id=" + id + ", username='" + username + '\'' + ", email='" + email + '\'' + ", age=" + age
				+ '}';
	}
}
