package com.example.demo.controllers;

import com.example.demo.dto.CinemaDTO;
import com.example.demo.services.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cinema")
public class CinemaController {

    @Autowired
    private CinemaService cinemaService;

    @PostMapping
    public void createCinema(@RequestBody CinemaDTO request) throws Exception{
        cinemaService.createCinema(request);
    }

    @PutMapping
    public void updateCinema(@RequestBody CinemaDTO request) throws Exception{
        cinemaService.updateCinema(request);
    }

    @GetMapping
    public List<CinemaDTO> getAllCinemas(){
        return cinemaService.getAllCinemas();
    }

    @GetMapping("/{id}")
    public CinemaDTO getCinema(@PathVariable Long id){
        return cinemaService.getCinema(id);
    }

    @DeleteMapping("/{id}")
    public void deleteCinema(@PathVariable Long id){
        cinemaService.deleteCinema(id);
    }

    @GetMapping("/{id}/manager")
    public List<CinemaDTO> getAllCinemasByManager(@PathVariable Long id){
        return cinemaService.getAllCinemasByManager(id);
    }
}
