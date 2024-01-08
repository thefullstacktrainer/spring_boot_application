// com.example.gamerzone.model.Game
package com.example.gamerzone.model;

import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.cache.annotation.Cacheable;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
	private String title;

	@NotBlank(message = "Genre is required")
	private String genre;

	@NotNull(message = "Release year is required")
	@Min(value = 1000, message = "Invalid release year")
	private int releaseYear;

	// Constructors
	public Game() {
	}

	public Game(Long id, String title, String genre, int releaseYear) {
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
