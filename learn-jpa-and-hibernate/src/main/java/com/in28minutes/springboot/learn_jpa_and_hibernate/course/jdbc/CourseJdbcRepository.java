package com.in28minutes.springboot.learn_jpa_and_hibernate.course.jdbc;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.in28minutes.springboot.learn_jpa_and_hibernate.course.Course;

@Repository
public class CourseJdbcRepository {

	private static final String INSERT_QUERY = """
				insert into course (id,name,author) values (?,?,?);
			""";
	private static final String DELETE_QUERY = """
				delete from course where id = ?;
			""";

	private static final String SELECT_QUERY = """
				select * from course where id = ?;
			""";

	private static final String SELECT_FROM_AUTHOR_QUERY = """
				select * from course where author = ?;
			""";
	private JdbcTemplate springJdbcTemplate;

	public CourseJdbcRepository(JdbcTemplate springJdbcTemplate) {
		super();
		this.springJdbcTemplate = springJdbcTemplate;
	}

	public void insert(Course course) {
		springJdbcTemplate.update(INSERT_QUERY, course.getId(), course.getName(), course.getAuthor());
	}

	public void deleteById(long courseId) {
		springJdbcTemplate.update(DELETE_QUERY, courseId);
	}

	public Course getCourseById(long courseId) {
		return springJdbcTemplate.queryForObject(SELECT_QUERY, new BeanPropertyRowMapper<>(Course.class), courseId);
	}

}
