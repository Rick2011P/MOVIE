package com.movie.model;

import java.time.LocalDate;

/** Entidad que representa una película del catálogo MOVIE. */
public class Movie {
    private Integer id;
    private String title;
    private String description;
    private int duration;
    private LocalDate releaseDate;
    private String classification;
    private int genreId;
    private int directorId;

    public Movie() { }

    public Movie(Integer id, String title, String description, int duration, LocalDate releaseDate,
                 String classification, int genreId, int directorId) {
        this.id = id; this.title = title; this.description = description; this.duration = duration;
        this.releaseDate = releaseDate; this.classification = classification;
        this.genreId = genreId; this.directorId = directorId;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public int getDuration() { return duration; }
    public void setDuration(int duration) { this.duration = duration; }
    public LocalDate getReleaseDate() { return releaseDate; }
    public void setReleaseDate(LocalDate releaseDate) { this.releaseDate = releaseDate; }
    public String getClassification() { return classification; }
    public void setClassification(String classification) { this.classification = classification; }
    public int getGenreId() { return genreId; }
    public void setGenreId(int genreId) { this.genreId = genreId; }
    public int getDirectorId() { return directorId; }
    public void setDirectorId(int directorId) { this.directorId = directorId; }

    @Override public String toString() {
        return id + " | " + title + " | " + releaseDate + " | " + duration + " min | " + classification;
    }
}
