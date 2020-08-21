package com.example.demo.repos;

import com.example.demo.models.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RateRepo extends JpaRepository<Rate, Long> {

    Rate findOneById(Long id);

    List<Rate> findAllByFilm_Id(Long id);

    List<Rate> findAllByViewer_Id(Long id);

    Rate findOneByViewer_IdAndFilm_Id(Long id1, Long id2);
}
