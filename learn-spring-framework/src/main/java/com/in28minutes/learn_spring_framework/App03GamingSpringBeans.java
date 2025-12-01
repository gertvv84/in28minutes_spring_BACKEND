package com.in28minutes.learn_spring_framework;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.in28minutes.learn_spring_framework.game.GameRunner;
import com.in28minutes.learn_spring_framework.game.IGamingConsole;

public class App03GamingSpringBeans {
	
	public static void main(String[] args) {
		try(var context = new AnnotationConfigApplicationContext(GamingConfiguration.class)) {
			
			//context.getBean(IGamingConsole.class).up();
			
			context.getBean(GameRunner.class).run();
		}
		
		
	}
	

		
	

}
