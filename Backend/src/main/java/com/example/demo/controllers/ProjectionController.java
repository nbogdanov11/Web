package com.example.demo.controllers;

import com.example.demo.dto.ProjectionDto;
import com.example.demo.services.ProjectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projection")
public class ProjectionController {

    @Autowired
    private ProjectionService projectionService;

    @PostMapping
    public void createProjection(@RequestBody ProjectionDto request) throws Exception{
        projectionService.createProjection(request);
    }

    @DeleteMapping("/{id}")
    public void deleteProjection(@PathVariable Long id){
        projectionService.deleteProjection(id);
    }

    @GetMapping("/{id}")
    public ProjectionDto getProjection(@PathVariable Long id){
        return projectionService.getProjection(id);
    }

    @GetMapping
    public List<ProjectionDto> getAllProjections(){
        return projectionService.getAllProjections();
    }

    @GetMapping("/{id}/cinema")
    public List<ProjectionDto> getAllProjectionsByCinema(@PathVariable Long id){
        return projectionService.getAllProjectionsByCinema(id);
    }

    @GetMapping("/{id}/film")
    public List<ProjectionDto> getAllProjectionsByFilm(@PathVariable Long id){
        return projectionService.getAllProjectionsByFilm(id);
    }
}
