<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Todo List</title>
	    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
	    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	    <script src='https://kit.fontawesome.com/a076d05399.js'></script>
	    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
	</head>
	<style>
		.pageBody{
		 	background:#757C88;
    	}
		.todolist{
			margin-top:5%;
		 	background:#ffffff;
   		 	border:2px solid black;
		 	border-radius: 10px;
		 }
	</style>
	<body class="pageBody">
		<div class="todolist">
			<div class="container">
				<hr>
				<form action="/userLogout" method="post">
					<div class="row">
						<div class="col-sm-10">
							<h5 style="padding:20px;">Create Todo List</h5>
						</div>
						<div class="col-sm-2">
							<button type="submit" class="btn btn-primary btn-block" name="userlogout" value="Logout">Logout</button>
						</div>
					</div>
				</form>
				<hr>
				<form action="/createToDoList" method="post">
					<div class="row">
						<div class="col-sm-1"></div>
						<div class="col-sm-6">
							<input type="text" id="createtodolist" name="createtodolist" class="form-control" placeholder="Todo list name" required autofocus>
						</div>
						<div class="col-sm-2">
							<button type="submit" class="btn btn-primary btn-block" name="createtodo" value="Create">Create</button>
						</div>
						<div class="col-sm-2">
							<button type="reset" class="btn btn-primary btn-block" name="reset" value="reset">Reset</button>
						</div>
						<div class="col-sm-1"></div>
					</div>
					<br>
					<div class="row">
						<div class="col-sm-2"></div>
						<div class="col-sm-8">
							<% if(request.getAttribute("message")!=null && request.getAttribute("message").equals("Todo List Created Successfully")) { %>
								<div style="background:green;color:white;padding:5px;border-radius:10px;">
									Todo list is successfully created.
								</div>
							<% } else if(request.getAttribute("message")!=null && request.getAttribute("message").equals("Todo List already exist.")) { %>
								<div style="background:red;color:white;padding:5px;border-radius:10px;">
									Todo list with same name already exist.
								</div>
							<% } %>
						</div>
						<div class="col-sm-2"></div>
					</div>
				</form>
				<hr>
				<form action="/deleteToDo" method="post">
					<div class="row">
						<div class="col-sm-12">
							<h5 style="padding:20px;">Available Todo List</h5>
						</div>
					</div>
					<marquee>Click on todo list to view tasks in each of them.</marquee>
					<hr>
					<c:forEach var="currItem" items="${ToDoListNames}">
						<div class="row">
							<div class="col-sm-5"><center><a href="/taskPage?toDoListTitle=${currItem.toDoListTitle}">${currItem.toDoListTitle}</a></center></div>
							<div class="col-sm-5"><center>Created on ${currItem.createdOn}</center></div>
							<div class="col-sm-2"><button type="submit" class="btn btn-primary btn-block" name="deletetodo" value="${currItem.toDoListTitle}">Delete</button></div>
						</div>
						<br>
					</c:forEach>
				</form>
				<hr>
			</div>
		</div>
	</body>
</html>
