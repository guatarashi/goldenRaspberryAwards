package br.com.atarashi.goldenRaspberryAwards.repository;

import br.com.atarashi.goldenRaspberryAwards.repository.model.Film;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO film (`year`, title, studios, producers, winner) " +
                   "VALUES (:#{#film.year},:#{#film.title},:#{#film.studios},:#{#film.producers},:#{#film.winner})", nativeQuery = true)
    void insert(@Param("film") Film film);

    @Modifying
    @Transactional
    @Query(value = "delete from film where id = :id", nativeQuery = true)
    void delete(@Param("id") int id);
    @Query(value = "SELECT * FROM film f order by `year`", nativeQuery = true)
    @Override
    List<Film> findAll();

    @Query(value = "SELECT * FROM film f where id = :id order by `year`", nativeQuery = true)
    Film findId(@Param("id") int id);
    @Query(value = "SELECT * FROM film f where winner = 'yes'", nativeQuery = true)
    List<Film> findByWinner();
}
