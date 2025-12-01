package com.in28minutes.springboot.myfirstwebapp.todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

@Service
public class TodoService {
	private static List<Todo> todos = new ArrayList<>();
	private static int toDoCount = 0;

	static {
		todos.add(new Todo(++toDoCount, "GVV", "Learn AWS", LocalDate.now().plusYears(1), false));
		todos.add(new Todo(++toDoCount, "GVV", "Learn Angular", LocalDate.now().plusYears(2), false));
		todos.add(new Todo(++toDoCount, "GVV", "Learn React", LocalDate.now().plusYears(3), false));
	}

	public List<Todo> findByUsername(String username) {
		return todos.stream().filter(matchUserPredicate(username)).toList();
	}

	public void addToDo(String username, String description, LocalDate targetDate, boolean done) {
		Todo todo = new Todo(++toDoCount, username, description, targetDate, done);
		todos.add(todo);
	}

	public void deleteById(int id) {
		todos.removeIf(matchIdPredicate(id));
	}

	public Optional<Todo> findById(int id) {
		return todos.stream().filter(matchIdPredicate(id)).findFirst();
	}

	public void updateTodo(Todo updatedToDo) {
		this.deleteById(updatedToDo.getId());

		todos.add(updatedToDo);

	}

	/**
	 * Predicate to find ToDo in list by ID.
	 * 
	 * @param id
	 * @return
	 */
	private Predicate<? super Todo> matchIdPredicate(int id) {
		return todo -> todo.getId() == id;
	}

	/**
	 * Predicate to find ToDo By Username
	 * 
	 * @param user
	 * @return
	 */
	private Predicate<? super Todo> matchUserPredicate(String user) {
		return todo -> todo.getUsername().equals(user);
	}

}
