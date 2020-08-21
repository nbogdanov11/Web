package com.example.demo.controllers;

import com.example.demo.dto.ProjectionDTO;
import com.example.demo.dto.RateDTO;
import com.example.demo.services.ProjectionService;
import com.example.demo.services.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rate")
public class RateController {

    @Autowired
    private RateService rateService;

    @Autowired
    private ProjectionService projectionService;

    @PostMapping
    public void rateFilm(@RequestBody RateDTO request) throws Exception{
        rateService.rateRepo(request);
    }

    @GetMapping("/rated-films/{id}/viewer")
    public List<RateDTO> getAllRatedFilmsByViewer(@PathVariable Long id){
        return rateService.getAllRatedFilmsByViewer(id);
    }

    @GetMapping("/rating/{id}/film")
    public RateDTO getFilmsRating(@PathVariable Long id){
        return rateService.getFilmsRating(id);
    }

    @GetMapping("/{id}/viewer")
    public List<ProjectionDTO> getAllFilmsWhichCanBeRatedByViewer(@PathVariable Long id){
        return projectionService.getAllFilmsWhichCanBeRatedByViewer(id);
    }
}
