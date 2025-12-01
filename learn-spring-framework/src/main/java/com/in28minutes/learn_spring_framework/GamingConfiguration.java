package com.in28minutes.learn_spring_framework;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.in28minutes.learn_spring_framework.game.GameRunner;
import com.in28minutes.learn_spring_framework.game.IGamingConsole;
import com.in28minutes.learn_spring_framework.game.PacmanGame;

@Configuration
public class GamingConfiguration {
	
	@Bean
	public IGamingConsole game() {
		return new PacmanGame();
	}
	
	@Bean
	public GameRunner gameRunner(IGamingConsole game) {
		return new GameRunner(game);
	}

}
