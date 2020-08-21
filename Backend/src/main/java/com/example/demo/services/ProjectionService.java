package com.example.demo.services;

import com.example.demo.TicketStatus;
import com.example.demo.dto.ProjectionDTO;
import com.example.demo.dto.ProjectionSearchDTO;
import com.example.demo.models.Film;
import com.example.demo.models.Projection;
import com.example.demo.models.Theater;
import com.example.demo.models.Ticket;
import com.example.demo.repos.FilmRepo;
import com.example.demo.repos.ProjectionRepo;
import com.example.demo.repos.TheaterRepo;
import com.example.demo.repos.TicketRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.time.LocalDateTime.now;

@Service
public class ProjectionService {

    @Autowired
    private ProjectionRepo projectionRepo;

    @Autowired
    private FilmRepo filmRepo;

    @Autowired
    private TicketRepo ticketRepo;

    @Autowired
    private TheaterRepo theaterRepo;

    public void createProjection(ProjectionDTO request) throws Exception{
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

    public List<ProjectionDTO> getAllProjections(){
        List<Projection> allProjections = projectionRepo.findAll();
        List<ProjectionDTO> responses = new ArrayList<>();
        LocalDateTime now = now();
        for(Projection p: allProjections){
            if(!p.isDeleted() && p.getTime().isAfter(now)){
                ProjectionDTO response = new ProjectionDTO();
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

    public List<ProjectionDTO> getAllProjectionsByCinema(Long id){
        List<Projection> allProjections = projectionRepo.findAll();
        List<ProjectionDTO> responses = new ArrayList<>();
        LocalDateTime now = now();
        for(Projection p: allProjections){
            if(!p.isDeleted() && p.getTheater().getCinema().getId() == id  && p.getTime().isAfter(now)){
                ProjectionDTO response = new ProjectionDTO();
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

    public List<ProjectionDTO> getAllProjectionsByFilm(Long id){
        List<Projection> allProjections = projectionRepo.findAll();
        List<ProjectionDTO> responses = new ArrayList<>();
        LocalDateTime now = now();
        for(Projection p: allProjections){
            if(!p.isDeleted() && p.getFilm().getId() == id && p.getTime().isAfter(now)){
                ProjectionDTO response = new ProjectionDTO();
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

    public ProjectionDTO getProjection(Long id){
        Projection p = projectionRepo.findOneById(id);
        ProjectionDTO response = new ProjectionDTO();
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

    public List<ProjectionDTO> getAllProjectionsBySearch(ProjectionSearchDTO request) throws Exception{
        List<Projection> allProjections = projectionRepo.findAll();
        List<Projection> searchedByFilmName = new ArrayList<>();
        List<Projection> searchedByFilmNameAndCinemaName = new ArrayList<>();
        List<Projection> searchedByFilmNameAndCinemaNameAndGenre = new ArrayList<>();
        LocalDateTime now = now();

        for(Projection p: allProjections){
            if(!p.isDeleted() && p.getFilm().getName().toLowerCase().contains(request.getFilmName().toLowerCase()) && p.getTime().isAfter(now)){
                searchedByFilmName.add(p);
            }
        }
        for(Projection p: searchedByFilmName){
            if(p.getTheater().getCinema().getName().toLowerCase().contains(request.getCinemaName().toLowerCase())){
                searchedByFilmNameAndCinemaName.add(p);
            }
        }

        for(Projection p: searchedByFilmNameAndCinemaName){
            if(p.getFilm().getGenre().contains(request.getGenre().toUpperCase())){
                searchedByFilmNameAndCinemaNameAndGenre.add(p);
            }
        }

        List<ProjectionDTO> responses = new ArrayList<>();
        for(Projection p: searchedByFilmNameAndCinemaNameAndGenre){
            ProjectionDTO response = new ProjectionDTO();
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
        return responses;
    }

    public List<ProjectionDTO> getAllReservedProjectionsByViewer(Long id){
        List<Projection> projections = projectionRepo.findAllByDeletedAndTicket_Viewer_IdAndTicket_Status(false, id, TicketStatus.RESERVED);
        LocalDateTime now = now();
        List<ProjectionDTO> responses = new ArrayList<>();
        for(Projection p: projections){
            if(p.getTime().isAfter(now)){
                ProjectionDTO response = new ProjectionDTO();
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
            }else{
                Ticket ticket = p.getTicket();
                ticket.setStatus(TicketStatus.CANCELED);
                ticketRepo.save(ticket);
            }
        }
        return responses;
    }

    public List<ProjectionDTO> getAllPaidProjectionsByViewerWhichAreInFuture(Long id){
        List<Projection> projections = projectionRepo.findAllByDeletedAndTicket_Viewer_IdAndTicket_Status(false, id, TicketStatus.RESERVED);
        LocalDateTime now = now();
        List<ProjectionDTO> responses = new ArrayList<>();
        for(Projection p: projections){
            if(p.getTime().isAfter(now)){
                ProjectionDTO response = new ProjectionDTO();
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

    public List<ProjectionDTO> getAllPaidProjectionsByViewerWhichAreInThePast(Long id){
        List<Projection> projections = projectionRepo.findAllByDeletedAndTicket_Viewer_IdAndTicket_Status(false, id, TicketStatus.RESERVED);
        LocalDateTime now = now();
        List<ProjectionDTO> responses = new ArrayList<>();
        for(Projection p: projections){
            if(p.getTime().isBefore(now)){
                ProjectionDTO response = new ProjectionDTO();
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
}
