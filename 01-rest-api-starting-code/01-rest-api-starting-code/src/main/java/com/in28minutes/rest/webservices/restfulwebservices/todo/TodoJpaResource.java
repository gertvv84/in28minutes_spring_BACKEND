package com.in28minutes.rest.webservices.restfulwebservices.todo;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.in28minutes.rest.webservices.restfulwebservices.todo.repository.TodoRepository;

@RestController
public class TodoJpaResource {

	private TodoRepository todoRepos;

	public TodoJpaResource(TodoRepository todoRepos) {
		this.todoRepos = todoRepos;
	}

	@GetMapping("users/test")
	public String test() {
		return "test";
	}

	@GetMapping("/users/{username}/todos")
	public List<Todo> retrieveTodos(@PathVariable String username) {

		return todoRepos.findByUsername(username);

	}

	@GetMapping("/users/{username}/todos/{id}")
	public Todo retrieveTodo(@PathVariable String username, @PathVariable int id) {

		Optional<Todo> optTodo = todoRepos.findById(Integer.valueOf(id));

		return optTodo.isPresent() ? optTodo.get() : null;

	}

	@DeleteMapping("/users/{username}/todos/{id}")
	public ResponseEntity<Void> deleteTodo(@PathVariable String username, @PathVariable int id) {
		todoRepos.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/users/{username}/todos/{id}")
	public Todo updateTodo(@PathVariable String username, @PathVariable int id, @RequestBody Todo iptTodo) {

		return todoRepos.save(iptTodo);
	}

	@PostMapping("/users/{username}/todos")
	public Todo createTodo(@PathVariable String username, @RequestBody Todo iptTodo) {
		iptTodo.setUsername(username);
		iptTodo.setId(null); // When ID null, .save know it's a create-action

		return todoRepos.save(iptTodo);
	}

}
