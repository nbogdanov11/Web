package com.example.demo.controllers;

import com.example.demo.dto.ProjectionDTO;
import com.example.demo.dto.ProjectionSearchDTO;
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
    public void createProjection(@RequestBody ProjectionDTO request) throws Exception{
        projectionService.createProjection(request);
    }

    @DeleteMapping("/{id}")
    public void deleteProjection(@PathVariable Long id){
        projectionService.deleteProjection(id);
    }

    @GetMapping("/{id}")
    public ProjectionDTO getProjection(@PathVariable Long id){
        return projectionService.getProjection(id);
    }

    @GetMapping
    public List<ProjectionDTO> getAllProjections(){
        return projectionService.getAllProjections();
    }

    @GetMapping("/{id}/cinema")
    public List<ProjectionDTO> getAllProjectionsByCinema(@PathVariable Long id){
        return projectionService.getAllProjectionsByCinema(id);
    }

    @GetMapping("/{id}/film")
    public List<ProjectionDTO> getAllProjectionsByFilm(@PathVariable Long id){
        return projectionService.getAllProjectionsByFilm(id);
    }

    @GetMapping("/search")
    public List<ProjectionDTO> getAllProjectionsBySearch(ProjectionSearchDTO request) throws Exception{
        return projectionService.getAllProjectionsBySearch(request);
    }

    @GetMapping("/future/paid/{id}/viewer")
    public List<ProjectionDTO> getAllPaidProjectionsByViewerWhichAreInTheFuture(@PathVariable Long id){
        return projectionService.getAllPaidProjectionsByViewerWhichAreInFuture(id);
    }

    @GetMapping("/past/paid/{id}/viewer")
    public List<ProjectionDTO> getAllPaidProjectionsByViewerWhichAreInThePast(@PathVariable Long id){
        return projectionService.getAllPaidProjectionsByViewerWhichAreInThePast(id);
    }

    @GetMapping("/reserved/{id}/viewer")
    public List<ProjectionDTO> getAllReservedProjectionsByViewer(@PathVariable Long id){
        return projectionService.getAllReservedProjectionsByViewer(id);
    }
}
