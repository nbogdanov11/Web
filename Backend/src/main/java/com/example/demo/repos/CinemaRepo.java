package com.example.demo.repos;

import com.example.demo.models.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CinemaRepo extends JpaRepository<Cinema, Long> {

    Cinema findOneById(Long id);
}
