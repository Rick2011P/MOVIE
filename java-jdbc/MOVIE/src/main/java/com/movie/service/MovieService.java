package com.movie.service;

import com.movie.model.Movie;
import com.movie.repository.MovieRepository;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/** Capa Service: aplica validaciones y reglas de negocio antes de persistir. */
public class MovieService {
    private final MovieRepository repository;
    public MovieService(MovieRepository repository) { this.repository = repository; }

    public Movie create(Movie movie) throws SQLException { validate(movie); return repository.create(movie); }
    public List<Movie> list() throws SQLException { return repository.findAll(); }
    public Optional<Movie> find(int id) throws SQLException { requirePositiveId(id); return repository.findById(id); }
    public boolean update(Movie movie) throws SQLException { requirePositiveId(movie.getId()); validate(movie); return repository.update(movie); }
    public boolean delete(int id) throws SQLException { requirePositiveId(id); return repository.delete(id); }

    private void validate(Movie movie) {
        if (movie.getTitle()==null || movie.getTitle().isBlank() || movie.getTitle().length()>150) throw new IllegalArgumentException("El título es obligatorio y máximo 150 caracteres.");
        if (movie.getDuration()<=0) throw new IllegalArgumentException("La duración debe ser mayor que cero.");
        if (movie.getReleaseDate()==null || movie.getReleaseDate().isAfter(LocalDate.now().plusYears(5))) throw new IllegalArgumentException("Fecha de estreno inválida.");
        if (movie.getClassification()==null || movie.getClassification().isBlank()) throw new IllegalArgumentException("La clasificación es obligatoria.");
        if (movie.getGenreId()<=0 || movie.getDirectorId()<=0) throw new IllegalArgumentException("Género y director son obligatorios.");
    }
    private void requirePositiveId(Integer id) { if(id==null || id<=0) throw new IllegalArgumentException("ID inválido."); }
}
