package com.example.demo.services;

import com.example.demo.dto.FilmDTO;
import com.example.demo.models.Film;
import com.example.demo.repos.FilmRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FilmService {

    @Autowired
    private FilmRepo filmRepo;

    public void createFilm(FilmDTO request) throws Exception{
        List<Film> allFilms = filmRepo.findAll();
        for(Film f: allFilms){
            if(request.getName().equals(f.getName())){
                throw new Exception("Film sa istim nazivom vec postoji.");
            }
        }
        Film film = new Film();
        long id = 1;
        if(!allFilms.isEmpty()){
            id = allFilms.size() + 1;
        }
        film.setId(id);
        film.setName(request.getName());
        film.setDescription(request.getDescription());
        film.setDuration(request.getDuration());
        film.setGenre(request.getGenre().toUpperCase());
        filmRepo.save(film);
    }

    public void updateFilm(FilmDTO request) throws Exception{
        List<Film> allFilms = filmRepo.findAll();
        for(Film f: allFilms){
            if(!f.isDeleted() && f.getId() != request.getId() && f.getName().equals(request.getName())){
                throw new Exception("Film sa istim nazivom vec postoji.");
            }
        }
        Film film = filmRepo.findOneById(request.getId());
        film.setName(request.getName());
        film.setGenre(request.getGenre());
        film.setDescription(request.getDescription());
        film.setDuration(request.getDuration());
        filmRepo.save(film);
    }

    public FilmDTO getFilm(Long id){
        Film film = filmRepo.findOneById(id);
        FilmDTO response = new FilmDTO();
        response.setDescription(film.getDescription());
        response.setDuration(film.getDuration());
        response.setGenre(film.getGenre());
        response.setName(film.getName());
        response.setId(film.getId());
        return response;
    }

    public List<FilmDTO> getAllFilms(){
        List<Film> allFilms = filmRepo.findAll();
        List<FilmDTO> responses = new ArrayList<>();
        for(Film f: allFilms){
            if(!f.isDeleted()){
                FilmDTO response = new FilmDTO();
                response.setId(f.getId());
                response.setName(f.getName());
                response.setDuration(f.getDuration());
                response.setDescription(f.getDescription());
                response.setGenre(f.getGenre());
                responses.add(response);
            }
        }
        return responses;
    }

    public void deleteFilm(Long id){
        Film film = filmRepo.findOneById(id);
        film.setDeleted(true);
        filmRepo.save(film);
    }
}
