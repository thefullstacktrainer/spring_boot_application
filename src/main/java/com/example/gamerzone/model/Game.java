// com.example.gamerzone.model.Game
package com.example.gamerzone.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@NamedQueries({
		@NamedQuery(name = "Game.findByPartialTitle", query = "SELECT g FROM Game g WHERE lower(g.title) LIKE lower(concat('%', :partialTitle, '%'))"),
		@NamedQuery(name = "Game.findByPartialGenre", query = "SELECT g FROM Game g WHERE lower(g.genre) LIKE lower(concat('%', :partialGenre, '%'))"),
		@NamedQuery(name = "Game.findAll", query = "SELECT g FROM Game g"),
		@NamedQuery(name = "Game.findByTitle", query = "SELECT g FROM Game g WHERE g.title = :title"),
		@NamedQuery(name = "Game.findByGenre", query = "SELECT g FROM Game g WHERE g.genre = :genre") })
public class Game {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Title is required")
	@Size(min = 1, max = 255, message = "Title must be between 1 and 255 characters")
	private String title;

	@NotBlank(message = "Genre is required")
	@Size(min = 1, max = 100, message = "Genre must be between 1 and 100 characters")
	private String genre;

	@NotNull(message = "Release year is required")
	@Min(value = 1000, message = "Release year must be at least 1000")
	@Max(value = 9999, message = "Release year cannot be more than 9999")
	private Integer releaseYear;

	// Regular expression for a valid genre
	private static final String GENRE_PATTERN = "^[a-zA-Z]+(?:-[a-zA-Z]+)*$";

	@Pattern(regexp = GENRE_PATTERN, message = "Invalid genre format. Use letters and hyphens (e.g., Action-Adventure)")
	private String customGenre;

	// Constructors
	public Game() {
	}

	public Game(Long id, String title, String genre, Integer releaseYear) {
		this.id = id;
		this.title = title;
		this.genre = genre;
		this.releaseYear = releaseYear;
	}

	// Getters and setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}

	@Override
	public String toString() {
		return "Game{" + "id=" + id + ", title='" + title + '\'' + ", genre='" + genre + '\'' + ", releaseYear="
				+ releaseYear + '}';
	}
}
