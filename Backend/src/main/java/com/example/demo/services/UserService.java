package com.example.demo.services;

import com.example.demo.UserRole;
import com.example.demo.dto.LoginDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.models.Cinema;
import com.example.demo.models.User;
import com.example.demo.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public void createViewer(UserDTO request) throws Exception{
        List<User> allUsers = userRepo.findAll();
        for(User u: allUsers){
//            if(u.isActivated()){
                if(u.getUsername().equals(request.getUsername())){
                    throw new Exception("U sistemu vec postoji korisnik sa tim korisnickim imenom.");
                }
//            }
        }
        LocalDate now = LocalDate.now();
        if(request.getBirthday().isAfter(now) || request.getBirthday() == null){
            throw new Exception("Niste selektovali validan datum.");
        }
        User user = new User();
        user.setActivated(true);
        long id = 1;
        if(!allUsers.isEmpty()){
            id = allUsers.size() + 1;
        }
        user.setId(id);
        user.setBirthday(request.getBirthday());
        user.setName(request.getName());
        user.setPassword(request.getPassword());
        user.setPhone(request.getPhone());
        user.setSurname(request.getSurname());
        user.setUsername(request.getUsername());
        user.setRole(UserRole.VIEWER);
        userRepo.save(user);
    }

    public void updateUser(UserDTO request) throws Exception{
//        List<User> allUsers = userRepo.findAll();
//        for(User u: allUsers){
//            if(u.isActivated()){
//            if(u.getUsername().equals(request.getUsername())){
//                throw new Exception("U sistemu vec postoji korisnik sa tim korisnickim imenom.");
//            }
//            }
//        }
        User user = userRepo.findOneById(request.getId());
        user.setName(request.getName());
        user.setPhone(request.getPhone());
        user.setSurname(request.getSurname());
        userRepo.save(user);
    }

    public void createManager(UserDTO request) throws Exception{
        List<User> allUsers = userRepo.findAll();
        for(User u: allUsers){
//            if(u.isActivated()){
                if(u.getUsername().equals(request.getUsername())){
                    throw new Exception("U sistemu vec postoji korisnik sa tim korisnickim imenom.");
                }
//            }
        }
        User user = new User();
        user.setActivated(false);
        long id = 1;
        if(!allUsers.isEmpty()){
            id = allUsers.size() + 1;
        }
        user.setId(id);
        user.setBirthday(request.getBirthday());
        user.setName(request.getName());
        user.setPassword(request.getPassword());
        user.setPhone(request.getPhone());
        user.setSurname(request.getSurname());
        user.setUsername(request.getUsername());
        user.setRole(UserRole.MANAGER);
        userRepo.save(user);
    }

    public UserDTO getUser(Long id){
        User u = userRepo.findOneById(id);
        UserDTO response = new UserDTO();
        response.setBirthday(u.getBirthday());
        response.setId(u.getId());
        response.setName(u.getName());
        response.setUsername(u.getSurname());
        response.setPhone(u.getPhone());
        response.setSurname(u.getSurname());
        return response;
    }

    public List<UserDTO> getUsers(){
        List<User> allUsers = userRepo.findAll();
        List<User> activeUsers = new ArrayList<>();
        for(User u: allUsers){
            if(u.isActivated()){
                activeUsers.add(u);
            }
        }
        List<UserDTO> responses = new ArrayList<>();
        for(User u: activeUsers){
            UserDTO response = new UserDTO();
            response.setBirthday(u.getBirthday());
            response.setId(u.getId());
            response.setName(u.getName());
            response.setUsername(u.getSurname());
            response.setPhone(u.getPhone());
            response.setSurname(u.getSurname());
            responses.add(response);
        }
        return responses;
    }

    public void blockUser(Long id) throws Exception{
        User u = userRepo.findOneById(id);
        if(u.getRole().equals(UserRole.MANAGER)){
            for(Cinema cinema: u.getCinemas()){
                if(cinema.getManagers().size() == 1){
                    throw new Exception("Menadzer ne moze da se obrise jer je jedini menadzer za bioskop " + cinema.getName() + ".");
                }
            }
        }
        u.setActivated(false);
        userRepo.save(u);
    }

    public UserDTO login(LoginDTO request) throws Exception{
        User u = userRepo.findOneByUsernameAndPasswordAndActivated(request.getUsername(), request.getPassword(), true);
        if(u == null){
            throw new Exception("Niste uneli validne podatke.");
        }
        UserDTO response = new UserDTO();
        response.setBirthday(u.getBirthday());
        response.setId(u.getId());
        response.setName(u.getName());
        response.setUsername(u.getSurname());
        response.setPhone(u.getPhone());
        response.setSurname(u.getSurname());
        response.setRole(u.getRole());
        return response;
    }

    public List<UserDTO> getViewers(){
        List<User> allUsers = userRepo.findAll();
        List<User> activeUsers = new ArrayList<>();
        for(User u: allUsers){
            if(u.isActivated() && u.getRole().equals(UserRole.VIEWER)){
                activeUsers.add(u);
            }
        }
        List<UserDTO> responses = new ArrayList<>();
        for(User u: activeUsers){
            UserDTO response = new UserDTO();
            response.setBirthday(u.getBirthday());
            response.setId(u.getId());
            response.setName(u.getName());
            response.setUsername(u.getUsername());
            response.setPhone(u.getPhone());
            response.setSurname(u.getSurname());
            responses.add(response);
        }
        return responses;
    }

    public List<UserDTO> getManagers(){
        List<User> allUsers = userRepo.findAll();
        List<User> activeUsers = new ArrayList<>();
        for(User u: allUsers){
            if(u.isActivated() && u.getRole().equals(UserRole.MANAGER)){
                activeUsers.add(u);
            }
        }
        List<UserDTO> responses = new ArrayList<>();
        for(User u: activeUsers){
            UserDTO response = new UserDTO();
            response.setBirthday(u.getBirthday());
            response.setId(u.getId());
            response.setName(u.getName());
            response.setUsername(u.getSurname());
            response.setPhone(u.getPhone());
            response.setSurname(u.getSurname());
            responses.add(response);
        }
        return responses;
    }

    public List<UserDTO> getInactiveManagers(){
        List<User> allUsers = userRepo.findAll();
        List<User> activeUsers = new ArrayList<>();
        for(User u: allUsers){
            if(!u.isActivated() && u.getRole().equals(UserRole.MANAGER)){
                activeUsers.add(u);
            }
        }
        List<UserDTO> responses = new ArrayList<>();
        for(User u: activeUsers){
            UserDTO response = new UserDTO();
            response.setBirthday(u.getBirthday());
            response.setId(u.getId());
            response.setName(u.getName());
            response.setUsername(u.getSurname());
            response.setPhone(u.getPhone());
            response.setSurname(u.getSurname());
            responses.add(response);
        }
        return responses;
    }

    public void activateManager(Long id){
        User user = userRepo.findOneById(id);
        user.setActivated(true);
        userRepo.save(user);
    }
}
