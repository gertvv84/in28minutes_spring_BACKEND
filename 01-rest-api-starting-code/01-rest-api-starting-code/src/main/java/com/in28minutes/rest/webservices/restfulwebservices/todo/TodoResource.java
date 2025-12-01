package com.in28minutes.rest.webservices.restfulwebservices.todo;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

//@RestController
public class TodoResource {

	private TodoService todoService;

	public TodoResource(TodoService todoService) {
		this.todoService = todoService;
	}

	@GetMapping("users/test")
	public String test() {
		return "test";
	}

	@GetMapping("/users/{username}/todos")
	public List<Todo> retrieveTodos(@PathVariable String username) {

		return todoService.findByUsername(username);

	}

	@GetMapping("/users/{username}/todos/{id}")
	public Todo retrieveTodo(@PathVariable String username, @PathVariable int id) {

		return todoService.findById(id);

	}

	@DeleteMapping("/users/{username}/todos/{id}")
	public ResponseEntity<Void> deleteTodo(@PathVariable String username, @PathVariable int id) {
		todoService.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/users/{username}/todos/{id}")
	public Todo updateTodo(@PathVariable String username, @PathVariable int id, @RequestBody Todo iptTodo) {
		todoService.updateTodo(iptTodo);
		return todoService.findById(id);
	}

	@PostMapping("/users/{username}/todos")
	public Todo createTodo(@PathVariable String username, @RequestBody Todo iptTodo) {
		Todo createdTodo = todoService.addTodo(username, iptTodo.getDescription(), iptTodo.getTargetDate(), false);
		return createdTodo;
	}

}
