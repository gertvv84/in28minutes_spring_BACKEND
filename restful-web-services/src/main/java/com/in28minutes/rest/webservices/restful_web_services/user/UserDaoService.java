package com.in28minutes.rest.webservices.restful_web_services.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {

	private static List<User> users = new ArrayList<User>();
	private static int userCount = 0;

	static {
		users.add(new User(++userCount, "Adam", LocalDate.now().minusYears(30)));
		users.add(new User(++userCount, "Eve", LocalDate.now().minusYears(18)));
		users.add(new User(++userCount, "Gert", LocalDate.now().minusYears(22)));
		users.add(new User(++userCount, "Kobe", LocalDate.now().minusYears(44)));
	}

	public List<User> findAll() {
		return users;
	}

	public User save(User user) {
		user.setId(++userCount);
		users.add(user);
		return user;
	}

	public User findOne(int id) {
		return users.stream().filter(u -> u.getId().equals(id)).findFirst().orElse(null);
	}

	public void deleteById(int id) {
		users.removeIf(u -> u.getId().equals(id));
	}

}
