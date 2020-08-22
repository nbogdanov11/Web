package com.example.demo.controllers;

import com.example.demo.dto.LoginDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/new-viewer")
    public void createViewer(@RequestBody UserDTO request) throws Exception{
        userService.createViewer(request);
    }

    @PostMapping("/new-manager")
    public void createManager(@RequestBody UserDTO request) throws Exception{
        userService.createManager(request);
    }

    @GetMapping
    public List<UserDTO> getUsers(){
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    public UserDTO getUser(@PathVariable Long id){
        return userService.getUser(id);
    }

    @PostMapping("/login")
    public UserDTO login(@RequestBody LoginDTO request) throws Exception{
        return userService.login(request);
    }

    @GetMapping("/managers")
    public List<UserDTO> getManagers(){
        return userService.getManagers();
    }

    @GetMapping("/inactive-managers")
    public List<UserDTO> getInactiveManagers(){
        return userService.getInactiveManagers();
    }

    @PutMapping("/activate/{id}/manager")
    public void activateManager(@PathVariable Long id, @RequestBody UserDTO emptyBody){
        userService.activateManager(id);
    }

    @GetMapping("/viewers")
    public List<UserDTO> getViewers(){
        return userService.getViewers();
    }

    @DeleteMapping("/{id}")
    public void blockUser(@PathVariable Long id) throws Exception{
        userService.blockUser(id);
    }
}
