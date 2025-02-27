package com.practice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice.dao.PlayerRepository;
import com.practice.entity.Player;

@Service
public class PlayerService {
	@Autowired
	private PlayerRepository playerRepo;
	
	public String healthCheck() {
		return "Hello";
	}
	
	public List<Player> getAllPlayer(){
		return playerRepo.findAll();
	}
	public Optional<Player> getPlayer(Integer id){
		return playerRepo.findById(id);
	}
	public Player addPlayer(Player p) {
		return playerRepo.save(p);
	}
	public Player update(Player p) {
		Optional<Player> byId = playerRepo.findById(p.getId());
		if(byId.isPresent()) {
			Player player = byId.get();
			player.setName(p.getName());
			player.setTeamName(p.getTeamName());
			return playerRepo.save(player);
		}
		else throw new RuntimeException("Player Not Found");
	}
	public void deletePlayer(Integer id) {
		playerRepo.deleteById(id);
	}
}
