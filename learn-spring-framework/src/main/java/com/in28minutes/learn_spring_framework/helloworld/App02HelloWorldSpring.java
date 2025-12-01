package com.in28minutes.learn_spring_framework.helloworld;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App02HelloWorldSpring {

	public static void main(String[] args) {
		// 1. Launch a Spring context

		try (var context = new AnnotationConfigApplicationContext(HelloWorldConfiguration.class)) {
			// if somethings goes wrong within this try
			// context is automatically closed.

			// 2. Configure the things that we want spring framework to manage -
			// @Configuration
			// HelloWorldCOnfiguration - @Configuration
			// name method - @Bean

			// 3. Retrieving beans managed by Spring
			System.out.println(context.getBean("name"));
			System.out.println(context.getBean("age"));
			System.out.println(context.getBean("person"));
			System.out.println(context.getBean("address2"));

			System.out.println(context.getBean("person2MethodCall"));

			System.out.println(context.getBean("person3Parameters"));

			System.out.println(context.getBean(Address.class));
			System.out.println(context.getBean(Person.class));

			System.out.println(context.getBean(Address.class));

			// Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
			// //method reference
		}
	}

}
