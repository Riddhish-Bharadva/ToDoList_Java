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
	    <script type="text/javascript">
			function onCheckBoxClick(StatusValue) {
				document.getElementById("taskTitleTextBox").value = StatusValue;
			}
	    </script>
	</head>
	<style>
		.pageBody{
		 	background:#757C88;
    	}
		.taskPage{
			margin-top:5%;
		 	background:#ffffff;
   		 	border:2px solid black;
		 	border-radius: 10px;
		 }
	</style>
	<body class="pageBody">
		<div class="taskPage">
			<div class="container">
				<hr>
				<form action="/userLogout" method="post">
					<div class="row">
						<div class="col-sm-2">
							<a href="/toDoListPage" class="btn btn-primary btn-block" name="GoBack" value="GoBack">Go Back</a>
						</div>
						<div class="col-sm-8">
							<h5 style="padding:20px;"><center>Tasks Page</center></h5>
						</div>
						<div class="col-sm-2">
							<button type="submit" class="btn btn-primary btn-block" name="userlogout" value="Logout">Logout</button>
						</div>
					</div>
				</form>
				<hr>
				<form action="/taskPage" method="post">
					<div class="row">
						<div class="col-sm-2"></div>
						<div class="col-sm-8">
							<% if(request.getAttribute("message")!=null && request.getAttribute("message").equals("Task Created Successfully")) { %>
								<div style="background:green;color:white;padding:5px;border-radius:10px;">
									Task is successfully created.
								</div>
							<% } else if(request.getAttribute("message")!=null && request.getAttribute("message").equals("Task already exist")) { %>
								<div style="background:red;color:white;padding:5px;border-radius:10px;">
									Task with same name already exist.
								</div>
							<% } %>
						</div>
						<div class="col-sm-2"></div>
					</div>
					<br>
					<div class="row">
						<div class="col-sm-3"></div>
						<div class="col-sm-6">
							<input type="text" id="taskTitle" name="taskTitle" class="form-control" placeholder="Task title" required autofocus>
						</div>
						<div class="col-sm-3"></div>
					</div>
					<br>
					<div class="row">
						<div class="col-sm-3"></div>
						<div class="col-sm-6">
							<textarea id="taskDescription" name="taskDescription" class="form-control" placeholder="Task description" rows="5" maxlength="150" required autofocus></textarea>
						</div>
						<div class="col-sm-3"></div>
					</div>
					<br>
					<div class="row">
						<div class="col-sm-3"></div>
						<div class="col-sm-3">
							<button type="submit" class="btn btn-primary btn-block" name="createTask" value="Create">Create</button>
						</div>
						<div class="col-sm-3">
							<button type="reset" class="btn btn-primary btn-block" name="reset" value="reset">Reset</button>
						</div>
						<div class="col-sm-3"></div>
					</div>
				</form>
				<hr>
				<div class="row">
					<div class="col-sm-12">
						<h5 style="padding:20px;">Available tasks are:</h5>
					</div>
				</div>
				<hr>
				<form action="/taskOperations" method="post">
					<c:forEach var="currentTask" items="${TaskNames}">
						<div class="row">
							<c:if test="${currentTask.completeStatus}">
								<div class="col-sm-1"><center><input type="checkbox" name="checkTask" value="${currentTask.taskTitle}" onclick="onCheckBoxClick(this.value);" data-toggle="modal" data-target="#ChangeTaskStatusModal" checked></center></div>
							</c:if>
							<c:if test="${not currentTask.completeStatus}">
								<div class="col-sm-1"><center><input type="checkbox" name="checkTask" value="${currentTask.taskTitle}" onclick="onCheckBoxClick(this.value);" data-toggle="modal" data-target="#ChangeTaskStatusModal"></center></div>
							</c:if>
							<div class="col-sm-3"><center>${currentTask.taskTitle}</center></div>
							<div class="col-sm-3"><center>Created: ${currentTask.createdOn}</center></div>
							<div class="col-sm-3"><center>Updated: ${currentTask.updatedOn}</center></div>
							<div class="col-sm-2"><button type="submit" class="btn btn-primary btn-block" name="deleteTask" value="${currentTask.taskTitle}">Delete</button></div>
						</div>
						<br>
						<div class="row">
							<div class="col-sm-2"></div>
							<div class="col-sm-8">Description: ${currentTask.taskDescription}</div>
							<div class="col-sm-2"></div>
						</div>
						<hr>
					</c:forEach>
				</form>
			</div>
		</div>

		<div id="ChangeTaskStatusModal" style="margin-top:10%;" class="modal fade" role="dialog">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h6 class="modal-title">Are you sure you want to change task completion status of below task?</h6>
					</div>
					<div class="modal-body">
						<form action="/checkTask" method="post">
							<div class="row">
								<div class="col-sm-12"><br></div>
							</div>
							<div class="row">
								<div class="col-sm-3"><br></div>
		                        <div class="col-sm-6">
									<input type="text" class="form-control" id="taskTitleTextBox" name="taskTitleTextBox" value="" onkeydown="return false;">
		                        </div>
		                        <div class="col-sm-3"><br></div>
							</div>
							<div class="row">
								<div class="col-sm-12"><br></div>
							</div>
							<div class="row">
								<div class="col-sm-3"></div>
		                        <div class="col-sm-3">
		                          <input type="submit" class="btn btn-primary btn-block" name="Proceed" id="Proceed" value="Proceed">
		                        </div>
		                        <div class="col-sm-3">
		                        	<a href="/taskPage?toDoListTitle=${toDoListTitle}" class="btn btn-primary btn-block">Cancel</a>
		                        </div>
		                        <div class="col-sm-3"></div>
							</div>
						</form>
	                </div>
				</div>
			</div>
		</div>

    </body>
</html>
