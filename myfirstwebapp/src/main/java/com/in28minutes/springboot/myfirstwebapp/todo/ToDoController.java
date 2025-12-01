package com.in28minutes.springboot.myfirstwebapp.todo;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.validation.Valid;

//@Controller BACK-UP - REPLACED BY JPA CONTROLLER
@SessionAttributes("name")
public class ToDoController {

	private TodoService todoService;

	public ToDoController(TodoService todoService) {
		super();
		this.todoService = todoService;
	}

	@RequestMapping("list-todos")
	public String listAllTodos(ModelMap model) {
		String username = getLoggedInUserName(model);
		List<Todo> todos = todoService.findByUsername(username);
		model.put("todos", todos);
		return "listToDos";
	}

	@RequestMapping(value = "add-todo", method = RequestMethod.GET)
	public String showNewToDoPage(ModelMap model) {
		String username = getLoggedInUserName(model);
		Todo newTodo = new Todo(0, username, "", LocalDate.now().plusYears(1), false);
		model.put("todo", newTodo);
		return "todo";
	}

	@RequestMapping(value = "add-todo", method = RequestMethod.POST)
	public String addNewToDo(ModelMap model, @Valid Todo newTodo, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "todo";
		}
		String username = getLoggedInUserName(model);
		todoService.addToDo(username, newTodo.getDescription(), newTodo.getTargetDate(), false);
		return "redirect:list-todos";
	}

	@RequestMapping(value = "delete-todo")
	public String deleteToDo(@RequestParam int id) {
		todoService.deleteById(id);
		return "redirect:list-todos";
	}

	@RequestMapping(value = "update-todo")
	public String showUpdateToDoPage(@RequestParam int id, ModelMap model) {
		Optional<Todo> toDoToBeUpdated = todoService.findById(id);

		if (toDoToBeUpdated.isPresent()) {
			model.put("todo", toDoToBeUpdated.get());
			return "todo";
		}
		// Error??
		return "";
	}

	@RequestMapping(value = "update-todo", method = RequestMethod.POST)
	public String updateToDo(ModelMap model, @Valid Todo updatedTodo, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "todo";
		}
		updatedTodo.setUsername(getLoggedInUserName(model));
		todoService.updateTodo(updatedTodo);
		return "redirect:list-todos";
	}

	private String getLoggedInUserName(ModelMap model) {
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}

}
