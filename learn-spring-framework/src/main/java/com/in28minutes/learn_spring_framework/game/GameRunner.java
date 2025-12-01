package com.in28minutes.learn_spring_framework.game;

public class GameRunner {
	IGamingConsole game;

	public GameRunner(IGamingConsole game) {
		super();
		this.game = game;
	}

	public void run() {
		System.out.println("Running game: " + game);
		
		game.up();
		game.down();
		game.left();
		game.right();
		
	}
	
	

}
