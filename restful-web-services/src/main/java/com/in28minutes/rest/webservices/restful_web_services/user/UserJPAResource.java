package com.in28minutes.rest.webservices.restful_web_services.user;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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

import com.in28minutes.rest.webservices.restful_web_services.jpa.PostRepository;
import com.in28minutes.rest.webservices.restful_web_services.jpa.UserRepository;
import com.in28minutes.rest.webservices.restful_web_services.post.Post;

import jakarta.validation.Valid;

@RestController
public class UserJPAResource {

	private PostRepository postRepos;
	private UserRepository userRepos;

	public UserJPAResource(PostRepository postRepos, UserRepository repos) {
		this.postRepos = postRepos;
		this.userRepos = repos;
	}

	@GetMapping(path = "/jpa/users")
	public List<User> retrieveAllUsers() {
		return userRepos.findAll();
	}

	@GetMapping(path = "/jpa/users/{id}")
	public EntityModel<User> retrieveUser(@PathVariable int id) {
		Optional<User> foundUser = userRepos.findById(id);

		if (foundUser.isEmpty()) {
			throw new UserNotFoundException("id: " + id);
		}

		// Add link to /users to response:
		EntityModel<User> em = EntityModel.of(foundUser.get());
		// Link to specific method:
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		em.add(link.withRel("all-users"));

		return em;
	}

	@PostMapping(path = "/jpa/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		User newUser = userRepos.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest() // get current url (/users)
				.path("/{id}") // add '/{id}' to the url
				.buildAndExpand(newUser.getId()) // replace {id} by the id of new user
				.toUri(); // convert to URI

		return ResponseEntity.created(location).build();
	}

	@DeleteMapping(path = "/jpa/users/{id}")
	public void deleteUser(@PathVariable int id) {
		userRepos.deleteById(id);
	}

	@GetMapping("/jpa/users/{id}/posts")
	public List<Post> retrievePostsForUser(@PathVariable int id) {
		Optional<User> foundUser = userRepos.findById(id);

		if (foundUser.isEmpty()) {
			throw new UserNotFoundException("id: " + id);
		}

		return foundUser.get().getPosts();
	}

	@PostMapping("/jpa/users/{id}/posts")
	public ResponseEntity<Post> createPostForUser(@PathVariable int id, @Valid @RequestBody Post post) {
		Optional<User> foundUser = userRepos.findById(id);

		if (foundUser.isEmpty()) {
			throw new UserNotFoundException("id: " + id);
		}
		post.setUser(foundUser.get());

		postRepos.save(post);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest() // get current url (/users)
				.path("/{id}") // add '/{id}' to the url
				.buildAndExpand(id) // replace {id} by the id of new user
				.toUri(); // convert to URI

		return ResponseEntity.created(location).build();

	}

	@GetMapping(path = "/jpa/users/{userId}/posts/{postId}")
	public Post getPost(@PathVariable int userId, @PathVariable int postId) {
		Optional<Post> foundPost = postRepos.findById(postId);

		if (foundPost.isEmpty()) {
			throw new UserNotFoundException("id: " + userId);
		}

		// System.out.println(foundPost.get().getUser().getName());

		return foundPost.get();
	}
}
