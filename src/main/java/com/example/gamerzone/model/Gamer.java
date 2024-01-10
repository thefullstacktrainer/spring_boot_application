// com.example.gamerzone.model.Gamer
package com.example.gamerzone.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

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
	@Size(min = 1, max = 50, message = "Username must be between 1 and 50 characters")
	private String username;

	@Email(message = "Invalid email format")
	private String email;

	@NotNull(message = "Age is required")
	@Min(value = 0, message = "Age must be at least 0")
	@Max(value = 150, message = "Age cannot be more than 150")
	private Integer age;

	// Regular expression for a valid username (alphanumeric with underscores)
	private static final String USERNAME_PATTERN = "^[a-zA-Z0-9_]+$";

	@Pattern(regexp = USERNAME_PATTERN, message = "Invalid username format. Use alphanumeric characters and underscores only.")
	private String customUsername;

	// Constructors
	public Gamer() {
	}

	public Gamer(Long id, String username, String email, Integer age) {
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
