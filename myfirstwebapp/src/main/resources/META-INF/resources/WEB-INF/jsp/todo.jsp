<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>
<div class="container">
	<h1>Enter details</h1>
	<form:form method="POST" modelAttribute="todo">
		<fieldset class="mb-3">
			<form:label path="description">Description</form:label>
			<form:input type="text" path="description" required="true" />
			<form:errors path="description" cssClass="text-warning" />
		</fieldset>
		<fieldset class="mb-3">
			<form:label path="targetDate">Target date</form:label>
			<form:input type="text" path="targetDate" required="true" />
			<form:errors path="targetDate" cssClass="text-warning" />
		</fieldset>
		<!-- hidden -->
		<form:input type="hidden" path="id" />
		<form:input type="hidden" path="done" />

		<input type="submit" class="btn btn-success" value="Submit" />
	</form:form>
</div>

<%@ include file="common/footer.jspf"%>

<script type="text/javascript">
	$('#targetDate').datepicker({
		format : 'dd/mm/yyyy'
	});
</script>

