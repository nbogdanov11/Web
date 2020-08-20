package com.example.demo.services;

import com.example.demo.dto.TheaterDTO;
import com.example.demo.models.Cinema;
import com.example.demo.models.Theater;
import com.example.demo.repos.CinemaRepo;
import com.example.demo.repos.TheaterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TheaterService {

    @Autowired
    private TheaterRepo theaterRepo;

    @Autowired
    private CinemaRepo cinemaRepo;

    public void createTheater(TheaterDTO request) throws Exception{
        List<Theater> allTheaters = theaterRepo.findAll();
        for(Theater t: allTheaters){
            if(!t.isDeleted()){
                if(t.getName().equals(request.getName()) && t.getCinema().getId() == request.getCinemaId()){
                    throw new Exception("Vec postoji sala sa istim nazivom u datom bioskopu.");
                }
            }
        }
        Theater theater = new Theater();
        long id = 1;
        if(!allTheaters.isEmpty()){
            id = allTheaters.size() + 1;
        }
        theater.setId(id);
        theater.setName(request.getName());
        theater.setSeats(request.getSeats());
        Cinema cinema = cinemaRepo.findOneById(request.getCinemaId());
        theater.setCinema(cinema);
        Theater saved = theaterRepo.save(theater);
        cinema.getTheaters().add(saved);
        cinemaRepo.save(cinema);
    }

    public void updateTheater(TheaterDTO request) throws Exception{
        List<Theater> allTheaters = theaterRepo.findAll();
        for(Theater t: allTheaters){
            if(!t.isDeleted() && t.getId() != request.getId()){
                if(t.getName().equals(request.getName()) && t.getCinema().getId() == request.getCinemaId()){
                    throw new Exception("Vec postoji sala sa istim nazivom u datom bioskopu.");
                }
            }
        }
        Theater theater = theaterRepo.findOneById(request.getId());
        theater.setName(request.getName());
        theater.setSeats(request.getSeats());
        theaterRepo.save(theater);
    }

    public TheaterDTO getTheater(Long id){
        Theater theater = theaterRepo.findOneById(id);
        TheaterDTO response = new TheaterDTO();
        response.setId(theater.getId());
        response.setCinemaId(theater.getCinema().getId());
        response.setCinemaName(theater.getCinema().getName());
        response.setName(theater.getName());
        response.setSeats(theater.getSeats());
        return response;
    }

    public List<TheaterDTO> getAllTheatersByCinema(Long id){
        Cinema cinema = cinemaRepo.findOneById(id);
        List<TheaterDTO> responses = new ArrayList<>();
        for(Theater t: cinema.getTheaters()){
            if(!t.isDeleted()){
                TheaterDTO response = new TheaterDTO();
                response.setName(t.getName());
                response.setSeats(t.getSeats());
                response.setId(t.getId());
                response.setCinemaName(t.getCinema().getName());
                response.setCinemaId(t.getCinema().getId());
                responses.add(response);
            }
        }
        return responses;
    }

    public void deleteTheater(Long id){
        Theater theater = theaterRepo.findOneById(id);
        theater.setDeleted(true);
        theaterRepo.save(theater);
    }
}
