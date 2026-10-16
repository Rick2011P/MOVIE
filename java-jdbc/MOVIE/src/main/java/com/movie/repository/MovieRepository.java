package com.movie.repository;

import com.movie.config.DatabaseConnection;
import com.movie.model.Movie;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/** Capa Repository: concentra exclusivamente el acceso JDBC a la tabla pelicula. */
public class MovieRepository {
    public Movie create(Movie movie) throws SQLException {
        String sql = "INSERT INTO pelicula(titulo,descripcion,duracion,fecha_estreno,clasificacion,id_genero,id_director) VALUES (?,?,?,?,?,?,?)";
        try (Connection cn = DatabaseConnection.getConnection(); PreparedStatement ps = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            setParameters(ps, movie); ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) { if (rs.next()) movie.setId(rs.getInt(1)); }
            return movie;
        }
    }

    public List<Movie> findAll() throws SQLException {
        String sql = "SELECT * FROM pelicula ORDER BY id_pelicula"; List<Movie> movies = new ArrayList<>();
        try (Connection cn = DatabaseConnection.getConnection(); PreparedStatement ps = cn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) movies.add(map(rs));
        }
        return movies;
    }

    public Optional<Movie> findById(int id) throws SQLException {
        String sql = "SELECT * FROM pelicula WHERE id_pelicula=?";
        try (Connection cn = DatabaseConnection.getConnection(); PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1,id); try (ResultSet rs=ps.executeQuery()) { return rs.next()?Optional.of(map(rs)):Optional.empty(); }
        }
    }

    public boolean update(Movie movie) throws SQLException {
        String sql = "UPDATE pelicula SET titulo=?,descripcion=?,duracion=?,fecha_estreno=?,clasificacion=?,id_genero=?,id_director=? WHERE id_pelicula=?";
        try (Connection cn=DatabaseConnection.getConnection(); PreparedStatement ps=cn.prepareStatement(sql)) {
            setParameters(ps,movie); ps.setInt(8,movie.getId()); return ps.executeUpdate()==1;
        }
    }

    public boolean delete(int id) throws SQLException {
        String sql="DELETE FROM pelicula WHERE id_pelicula=?";
        try(Connection cn=DatabaseConnection.getConnection(); PreparedStatement ps=cn.prepareStatement(sql)) { ps.setInt(1,id); return ps.executeUpdate()==1; }
    }

    private void setParameters(PreparedStatement ps, Movie m) throws SQLException {
        ps.setString(1,m.getTitle()); ps.setString(2,m.getDescription()); ps.setInt(3,m.getDuration());
        ps.setDate(4,Date.valueOf(m.getReleaseDate())); ps.setString(5,m.getClassification()); ps.setInt(6,m.getGenreId()); ps.setInt(7,m.getDirectorId());
    }
    private Movie map(ResultSet rs) throws SQLException {
        return new Movie(rs.getInt("id_pelicula"),rs.getString("titulo"),rs.getString("descripcion"),rs.getInt("duracion"),
            rs.getDate("fecha_estreno").toLocalDate(),rs.getString("clasificacion"),rs.getInt("id_genero"),rs.getInt("id_director"));
    }
}
