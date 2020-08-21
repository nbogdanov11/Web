package com.example.demo.controllers;

import com.example.demo.dto.TheaterDTO;
import com.example.demo.services.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/theater")
public class TheaterController {

    @Autowired
    private TheaterService theaterService;

    @PostMapping
    public void createTheater(@RequestBody TheaterDTO request) throws Exception{
        theaterService.createTheater(request);
    }

    @PutMapping
    public void updateTheater(@RequestBody TheaterDTO request) throws Exception{
        theaterService.updateTheater(request);
    }

    @GetMapping("/{id}/cinema")
    public List<TheaterDTO> getAllTheatersByCinema(@PathVariable Long id){
        return theaterService.getAllTheatersByCinema(id);
    }

    @GetMapping("/{id}")
    public TheaterDTO getTheater(@PathVariable Long id){
        return theaterService.getTheater(id);
    }

    @DeleteMapping("/{id}")
    public void deleteTheater(@PathVariable Long id){
        theaterService.deleteTheater(id);
    }
}
