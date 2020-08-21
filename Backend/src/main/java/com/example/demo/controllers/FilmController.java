package com.example.demo.controllers;

import com.example.demo.dto.FilmDTO;
import com.example.demo.services.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/film")
public class FilmController {

    @Autowired
    private FilmService filmService;

    @PostMapping
    public void createFilm(@RequestBody FilmDTO request) throws Exception{
        filmService.createFilm(request);
    }

    @PutMapping
    public void updateFilm(@RequestBody FilmDTO request) throws Exception{
        filmService.updateFilm(request);
    }

    @GetMapping
    public List<FilmDTO> getAllFilms(){
        return filmService.getAllFilms();
    }

    @GetMapping("/{id}")
    public FilmDTO getFilm(@PathVariable Long id){
        return filmService.getFilm(id);
    }

    @DeleteMapping("/{id}")
    public void deleteFilm(@PathVariable Long id){
        filmService.deleteFilm(id);
    }
}
