package com.in28minutes.rest.webservices.restful_web_services.user;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;

@RestController
public class UserResource {

	private UserDaoService dao;

	public UserResource(UserDaoService dao) {
		this.dao = dao;
	}

	@GetMapping(path = "/users")
	public List<User> retrieveAllUsers() {
		return dao.findAll();
	}

	@GetMapping(path = "/users/{id}")
	public EntityModel<User> retrieveUser(@PathVariable int id) {
		User foundUser = dao.findOne(id);

		if (foundUser == null) {
			throw new UserNotFoundException("id: " + id);
		}

		// Add link to /users to response:
		EntityModel<User> em = EntityModel.of(foundUser);
		// Link to specific method:
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		em.add(link.withRel("all-users"));

		return em;
	}

	@PostMapping(path = "/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		User newUser = dao.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest() // get current url (/users)
				.path("/{id}") // add '/{id}' to the url
				.buildAndExpand(newUser.getId()) // replace {id} by the id of new user
				.toUri(); // convert to URI

		return ResponseEntity.created(location).build();
	}

	@DeleteMapping(path = "/users/{id}")
	public void deleteUser(@PathVariable int id) {
		dao.deleteById(id);
	}
}
