package com.in28minutes.springboot.learn_jpa_and_hibernate.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.in28minutes.springboot.learn_jpa_and_hibernate.course.springdatajpa.CourseSpringDataJpaRepository;

@Component
public class CourseCommandLineRunner implements CommandLineRunner {

//	@Autowired
//	CourseJdbcRepository repos;

//	@Autowired
//	CourseJpaRepository repos;

	@Autowired
	CourseSpringDataJpaRepository repos;

	@Override
	public void run(String... args) throws Exception {
//		repos.insert(new Course(1, "learn Aws", "in28minutes"));
//		repos.insert(new Course(2, "learn Java", "in28minutes"));
//		repos.insert(new Course(3, "learn Azure", "in28minutes"));
//		repos.insert(new Course(4, "learn Angular", "in28minutes"));
//		repos.insert(new Course(5, "learn React", "in28minutes"));
//
//		repos.deleteById(4);
//
//		System.out.println(repos.getCourseById(2));
//		System.out.println(repos.getCourseById(3));

		repos.save(new Course(1, "learn Aws", "in28minutes"));
		repos.save(new Course(2, "learn Java", "GVV"));
		repos.save(new Course(3, "learn Azure", "in28minutes"));
		repos.save(new Course(4, "learn Angular", "in28minutes"));
		repos.save(new Course(5, "learn React", "GVV"));

		// repos.deleteById(4l);

		System.out.println(repos.findById(2l));
		System.out.println(repos.findById(3l));

		repos.findAll().stream().forEach(t -> System.out.println(t.getAuthor()));

		System.out.println(repos.findByAuthor("in28minutes"));

		System.out.println(repos.findByName("learn Aws"));

	}

}
