<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>
<div class="container">
	<h1>Your To Do's</h1>
	<table class="table">
		<thead>
			<th>User</th>
			<th>Description</th>
			<th>Target Date</th>
			<th>Is Done?</th>
			<th></th>
			<th></th>
		</thead>
		<tbody>
			<c:forEach items="${todos}" var="todo">
				<tr>
					<td>${todo.username}</td>
					<td>${todo.description}</td>
					<td><tags:localDate date="${todo.targetDate}" /></td>
					<td>${todo.done}</td>
					<td><a href="delete-todo?id=${todo.id}"
						class="btn btn-warning">Delete</a></td>
					<td><a href="update-todo?id=${todo.id}"
						class="btn btn-primary">Update</a></td>
				</tr>

			</c:forEach>
		</tbody>

	</table>
	<a href="add-todo" class="btn btn-success">Add To Do</a>
</div>

<%@ include file="common/footer.jspf"%>