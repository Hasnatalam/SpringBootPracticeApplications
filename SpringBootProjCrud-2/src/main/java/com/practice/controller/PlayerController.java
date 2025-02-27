package com.practice.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.practice.entity.Player;
import com.practice.service.PlayerService;

@RestController
@RequestMapping("/player/api")

public class PlayerController {
	@Autowired
	private PlayerService service;
	
	@GetMapping("/healthCheck")
	public String healthCheck() {
		return service.healthCheck();	
	}
	@PostMapping("/getAllPlayer")
	public List<Player> getAllPlayer(){
		return service.getAllPlayer();
	}
	@GetMapping("/getPlayer")
	public Optional<Player> getPlayer(@RequestParam Integer id){
		return service.getPlayer(id);
	}
	@PostMapping("/addPlayer")
	public Player addEmployee(@RequestBody Player p) {
		return service.addPlayer(p);
	}
	@PostMapping("/update")
	public Player update(@RequestBody Player p) {
		return service.update(p);
	}
	@GetMapping("/deletePlayer")
	public void deletePlayer(@RequestParam Integer id) {
		service.deletePlayer(id);
	}
	

}
