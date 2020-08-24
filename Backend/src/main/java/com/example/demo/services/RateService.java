package com.example.demo.services;

import com.example.demo.dto.RateDTO;
import com.example.demo.models.Film;
import com.example.demo.models.Rate;
import com.example.demo.models.User;
import com.example.demo.repos.FilmRepo;
import com.example.demo.repos.RateRepo;
import com.example.demo.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RateService {

    @Autowired
    private RateRepo rateRepo;

    @Autowired
    private FilmRepo filmRepo;

    @Autowired
    private UserRepo userRepo;

    public void rateFilm(RateDTO request) throws Exception{
        if(rateRepo.findOneByViewer_IdAndFilm_Id(request.getViewerId(), request.getFilmId()) != null){
            throw new Exception("Vec ste ocenili ovaj film.");
        }
        Rate rate = new Rate();
        long id = 1;
        if(!rateRepo.findAll().isEmpty()){
            id = rateRepo.findAll().size() + 1;
        }
        rate.setId(id);
        rate.setRate(request.getRate());
        Film film = filmRepo.findOneById(request.getFilmId());
        User viewer = userRepo.findOneById(request.getViewerId());
        rate.setFilm(film);
        rate.setViewer(viewer);
        Rate saved = rateRepo.save(rate);
        film.getRates().add(saved);
        filmRepo.save(film);
        viewer.getRates().add(saved);
        userRepo.save(viewer);
    }

    public List<RateDTO> getAllRatedFilmsByViewer(Long id){
        List<Rate> rates = rateRepo.findAllByViewer_Id(id);
        List<RateDTO> responses = new ArrayList<>();
        for(Rate r: rates){
            RateDTO response = new RateDTO();
            response.setFilmName(r.getFilm().getName());
            response.setFilmDescription(r.getFilm().getDescription());
            response.setFilmDuration(r.getFilm().getDuration());
            response.setFilmId(r.getFilm().getId());
            response.setFilmGenre(r.getFilm().getGenre());
            response.setRate(r.getRate());
            response.setId(r.getId());
            responses.add(response);
        }
        return responses;
    }

    public RateDTO getFilmsRating(Long id){
        float rating = 0;
        List<Rate> rates = rateRepo.findAllByFilm_Id(id);
        for(Rate r: rates){
            rating += r.getRate();
        }
        if(!rates.isEmpty()){
            rating /= rates.size();
        }
        RateDTO response = new RateDTO();
        response.setRating(rating);
        return response;
    }
}
