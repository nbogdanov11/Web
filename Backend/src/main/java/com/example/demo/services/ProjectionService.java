package com.example.demo.services;

import com.example.demo.dto.ProjectionDto;
import com.example.demo.models.Film;
import com.example.demo.models.Projection;
import com.example.demo.models.Theater;
import com.example.demo.repos.FilmRepo;
import com.example.demo.repos.ProjectionRepo;
import com.example.demo.repos.TheaterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectionService {

    @Autowired
    private ProjectionRepo projectionRepo;

    @Autowired
    private FilmRepo filmRepo;

    @Autowired
    private TheaterRepo theaterRepo;

    public void createProjection(ProjectionDto request) throws Exception{
        List<Projection> allProjections = projectionRepo.findAll();
        Film film = filmRepo.findOneById(request.getFilmId());
        for(Projection p: allProjections){
            if(!p.isDeleted()){
                if(p.getTheater().getId() == request.getTheaterId()){
                    if(p.getTime().plusMinutes(p.getFilm().getDuration()).isAfter(request.getTime()) && p.getTime().isBefore(request.getTime())){
                        throw new Exception("Vec postoji projekcija u datom terminu.");
                    }else if(request.getTime().plusMinutes(film.getDuration()).isAfter(p.getTime()) && request.getTime().isBefore(p.getTime())){
                        throw new Exception("Vec postoji projekcija u datom terminu.");
                    }
                }
            }
        }
        Projection projection = new Projection();
        long id = 1;
        if(!allProjections.isEmpty()){
            id = allProjections.size() + 1;
        }
        projection.setId(id);
        projection.setTime(request.getTime());
        projection.setPrice(request.getPrice());
        Theater theater = theaterRepo.findOneById(request.getTheaterId());
        projection.setFreeSeats(theater.getSeats());
        projection.setTheater(theater);
        projection.setFilm(film);
        Projection saved = projectionRepo.save(projection);
        theater.getProjections().add(saved);
        theaterRepo.save(theater);
        film.getProjections().add(saved);
        filmRepo.save(film);
    }

    public void deleteProjection(Long id){
        Projection projection = projectionRepo.findOneById(id);
        projection.setDeleted(true);
        projectionRepo.save(projection);
    }

    public List<ProjectionDto> getAllProjections(){
        List<Projection> allProjections = projectionRepo.findAll();
        List<ProjectionDto> responses = new ArrayList<>();
        for(Projection p: allProjections){
            if(!p.isDeleted()){
                ProjectionDto response = new ProjectionDto();
                response.setId(p.getId());
                response.setPrice(p.getPrice());
                response.setTime(p.getTime());
                response.setFreeSeats(p.getFreeSeats());
                response.setTheaterId(p.getTheater().getId());
                response.setTheaterName(p.getTheater().getName());
                response.setCinemaName(p.getTheater().getCinema().getName());
                response.setFilmId(p.getFilm().getId());
                response.setFilmName(p.getFilm().getName());
                response.setFilmDuration(p.getFilm().getDuration());
                response.setFilmGenre(p.getFilm().getGenre());
                responses.add(response);
            }
        }
        return responses;
    }

    public List<ProjectionDto> getAllProjectionsByCinema(Long id){
        List<Projection> allProjections = projectionRepo.findAll();
        List<ProjectionDto> responses = new ArrayList<>();
        for(Projection p: allProjections){
            if(!p.isDeleted() && p.getTheater().getCinema().getId() == id){
                ProjectionDto response = new ProjectionDto();
                response.setId(p.getId());
                response.setPrice(p.getPrice());
                response.setTime(p.getTime());
                response.setFreeSeats(p.getFreeSeats());
                response.setTheaterId(p.getTheater().getId());
                response.setTheaterName(p.getTheater().getName());
                response.setCinemaName(p.getTheater().getCinema().getName());
                response.setFilmId(p.getFilm().getId());
                response.setFilmName(p.getFilm().getName());
                response.setFilmDuration(p.getFilm().getDuration());
                response.setFilmGenre(p.getFilm().getGenre());
                responses.add(response);
            }
        }
        return responses;
    }

    public List<ProjectionDto> getAllProjectionsByFilm(Long id){
        List<Projection> allProjections = projectionRepo.findAll();
        List<ProjectionDto> responses = new ArrayList<>();
        for(Projection p: allProjections){
            if(!p.isDeleted() && p.getFilm().getId() == id){
                ProjectionDto response = new ProjectionDto();
                response.setId(p.getId());
                response.setPrice(p.getPrice());
                response.setTime(p.getTime());
                response.setFreeSeats(p.getFreeSeats());
                response.setTheaterId(p.getTheater().getId());
                response.setTheaterName(p.getTheater().getName());
                response.setCinemaName(p.getTheater().getCinema().getName());
                response.setFilmId(p.getFilm().getId());
                response.setFilmName(p.getFilm().getName());
                response.setFilmDuration(p.getFilm().getDuration());
                response.setFilmGenre(p.getFilm().getGenre());
                responses.add(response);
            }
        }
        return responses;
    }

    public ProjectionDto getProjection(Long id){
        Projection p = projectionRepo.findOneById(id);
        ProjectionDto response = new ProjectionDto();
        response.setId(p.getId());
        response.setPrice(p.getPrice());
        response.setTime(p.getTime());
        response.setFreeSeats(p.getFreeSeats());
        response.setTheaterId(p.getTheater().getId());
        response.setTheaterName(p.getTheater().getName());
        response.setCinemaName(p.getTheater().getCinema().getName());
        response.setFilmId(p.getFilm().getId());
        response.setFilmName(p.getFilm().getName());
        response.setFilmDuration(p.getFilm().getDuration());
        response.setFilmGenre(p.getFilm().getGenre());
        return response;
    }
}
