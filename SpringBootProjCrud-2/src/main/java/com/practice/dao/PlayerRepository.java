package com.practice.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practice.entity.Player;

public interface PlayerRepository extends JpaRepository<Player, Integer> {

}
