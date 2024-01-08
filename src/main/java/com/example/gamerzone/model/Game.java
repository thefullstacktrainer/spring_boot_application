// com.example.gamerzone.model.Game
package com.example.gamerzone.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.cache.annotation.Cacheable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@NamedQueries({
		@NamedQuery(name = "Game.findByPartialTitle", query = "SELECT g FROM Game g WHERE lower(g.title) LIKE lower(concat('%', :partialTitle, '%'))"),
		@NamedQuery(name = "Game.findByPartialGenre", query = "SELECT g FROM Game g WHERE lower(g.genre) LIKE lower(concat('%', :partialGenre, '%'))"),
		@NamedQuery(name = "Game.findAll", query = "SELECT g FROM Game g"),
		@NamedQuery(name = "Game.findByTitle", query = "SELECT g FROM Game g WHERE g.title = :title"),
		@NamedQuery(name = "Game.findByGenre", query = "SELECT g FROM Game g WHERE g.genre = :genre") })
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

}
