package com.example.demo.repos;

import com.example.demo.models.Projection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectionRepo extends JpaRepository<Projection, Long> {

    Projection findOneById(Long id);
}
