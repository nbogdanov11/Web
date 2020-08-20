package com.example.demo.repos;

import com.example.demo.models.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheaterRepo extends JpaRepository<Theater, Long> {

    Theater findOneById(Long id);
}
