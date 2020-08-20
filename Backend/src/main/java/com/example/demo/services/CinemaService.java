package com.example.demo.services;

import com.example.demo.dto.CinemaDTO;
import com.example.demo.models.Cinema;
import com.example.demo.models.User;
import com.example.demo.repos.CinemaRepo;
import com.example.demo.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CinemaService {

    @Autowired
    private CinemaRepo cinemaRepo;

    @Autowired
    private UserRepo userRepo;

    public void createCinema(CinemaDTO request) throws Exception{
        List<Cinema> cinemas = cinemaRepo.findAll();
        for(Cinema c: cinemas){
            if(!c.isDeleted()){
                if(c.getName().equals(request.getName()) || c.getEmail().equals(request.getEmail())){
                    throw new Exception("U sistemu vec postoji bioskop sa tim imenom ili emailom.");
                }
            }
        }
        Cinema cinema = new Cinema();
        long id = 1;
        if(!cinemas.isEmpty()){
            id = cinemas.size() + 1;
        }
        cinema.setId(id);
        cinema.setAddress(request.getAddress());
        cinema.setEmail(request.getEmail());
        cinema.setName(request.getName());
        cinema.setPhone(request.getPhone());
        User manager = userRepo.findOneById(request.getManagerId());
        cinema.getManagers().add(manager);
        Cinema saved = cinemaRepo.save(cinema);
        manager.getCinemas().add(saved);
        userRepo.save(manager);
    }

    public void updateCinema(CinemaDTO request) throws Exception{
        List<Cinema> cinemas = cinemaRepo.findAll();
        for(Cinema c: cinemas){
            if(!c.isDeleted() && c.getId() != request.getId()){
                if(c.getName().equals(request.getName()) || c.getEmail().equals(request.getEmail())){
                    throw new Exception("U sistemu vec postoji bioskop sa tim imenom ili emailom.");
                }
            }
        }
        Cinema cinema = cinemaRepo.findOneById(request.getId());
        cinema.setPhone(request.getPhone());
        cinema.setAddress(request.getAddress());
        cinema.setName(request.getName());
        cinema.setEmail(request.getEmail());
        cinemaRepo.save(cinema);
    }

    public void deleteCinema(Long id){
        Cinema cinema = cinemaRepo.findOneById(id);
        cinema.setDeleted(true);
        cinemaRepo.save(cinema);
    }

    public CinemaDTO getCinema(Long id){
        Cinema cinema = cinemaRepo.findOneById(id);
        CinemaDTO response = new CinemaDTO();
        response.setAddress(cinema.getAddress());
        response.setEmail(cinema.getEmail());
        response.setId(cinema.getId());
        response.setName(cinema.getName());
        response.setPhone(cinema.getPhone());
        return response;
    }

    public List<CinemaDTO> getAllCinemas(){
        List<Cinema> allCinemas = cinemaRepo.findAll();
        List<Cinema> activeCinemas = new ArrayList<>();
        for(Cinema c: allCinemas){
            if(!c.isDeleted()){
                activeCinemas.add(c);
            }
        }
        List<CinemaDTO> responses = new ArrayList<>();
        for(Cinema c: activeCinemas){
            CinemaDTO response = new CinemaDTO();
            response.setPhone(c.getPhone());
            response.setName(c.getName());
            response.setId(c.getId());
            response.setEmail(c.getEmail());
            response.setAddress(c.getAddress());
            responses.add(response);
        }
        return responses;
    }

    public List<CinemaDTO> getAllCinemasByManager(Long id){
        List<Cinema> allCinemas = cinemaRepo.findAll();
        List<Cinema> activeCinemas = new ArrayList<>();
        User manager = userRepo.findOneById(id);
        for(Cinema c: allCinemas){
            if(!c.isDeleted() && c.getManagers().contains(manager)){
                activeCinemas.add(c);
            }
        }
        List<CinemaDTO> responses = new ArrayList<>();
        for(Cinema c: activeCinemas){
            CinemaDTO response = new CinemaDTO();
            response.setPhone(c.getPhone());
            response.setName(c.getName());
            response.setId(c.getId());
            response.setEmail(c.getEmail());
            response.setAddress(c.getAddress());
            responses.add(response);
        }
        return responses;
    }
}
