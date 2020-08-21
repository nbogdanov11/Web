package com.example.demo.repos;

import com.example.demo.models.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmRepo extends JpaRepository<Film, Long> {

    Film findOneById(Long id);
}
