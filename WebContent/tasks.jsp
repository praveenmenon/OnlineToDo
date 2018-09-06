<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="online.todo.application.*, java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</head>
<body>
	<h1> Todo Application</h1>
<%
	ApplicationsDAO dao = new ApplicationsDAO();
	String action = request.getParameter("action");
	String name = request.getParameter("name");
	String description = request.getParameter("description");
	String id = request.getParameter("id");
	Task task = new Task();
	
	if("create".equals(action)){
		task = new Task(name, description);
		dao.create(task);	
	} else if("remove".equals(action)){
		int intId = Integer.parseInt(id);
		dao.remove(intId);
	} else if("edit".equals(action)){
		int intId = Integer.parseInt(id);
		task = dao.editTask(intId);
	} else if("update".equals(action)){
		int intId = Integer.parseInt(id);
		task = new Task(name, description);
		dao.update(intId, task);
	}
	List<Task> tasks = dao.selectAll();
%>
<form action="tasks.jsp">
<input type="hidden" name="id" value="<%= task.getId() %>"/>
<table class= "table">
	<tr>
		<td> <input name="name" class="form-control" value= "<%= task.getName() %>"/></td>
		<td> <input name="description" class="form-control" value= "<%= task.getDescription() %>"/></td> 
		<td> <button class="btn btn-primary" name="action" value="create">Add</button></td>
		<td> <button class="btn btn-warning" name="action" value="update">Update</button>
	</tr>
	<%	for(Task t:tasks){
	%> 
	<tr>
		<td><%= t.getName() %></td>
		<td><%= t.getDescription() %></td>
		<td>
		<a class="btn btn-danger" href="tasks.jsp?action=remove&id=<%= t.getId()%>">
			Delete
		</a>
		<a class="btn btn-warning" href="tasks.jsp?action=edit&id=<%= t.getId()%>">
			Edit
		</a>
		</td>
	</tr>
	<%
		}
	%>
</table>
</form>
</body>
</html>