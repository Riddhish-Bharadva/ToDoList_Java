package com.rab.ToDoList.dao;

import java.sql.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

@Component
public class TableOperations {
	@Autowired
	private User_DAO uDAO;
	@Autowired
	DB_Connections dbCon;

	public boolean createTables() {
		try {
			Statement st = dbCon.getJDBCConnection().createStatement();
			String query = "create table IF NOT EXISTS "+TableNames.TABLE_USER+"(username varchar(255) PRIMARY KEY, password varchar(255));";
			st.execute(query);
			query = "create table IF NOT EXISTS "
					+ TableNames.TABLE_TODOLIST
					+ "(toDoListTitle varchar(255), username varchar(255), createdOn varchar(255), CONSTRAINT toDoTableKey PRIMARY KEY (toDoListTitle, username));";
			st.execute(query);
			uDAO.addUser("test","pwd123");
			query = "create table IF NOT EXISTS "+TableNames.TABLE_TASK+"(taskTitle varchar(255), toDoListTitle varchar(255), username varchar(255), taskDescription varchar(255), completeStatus tinyint(1), createdOn varchar(255), updatedOn varchar(255), CONSTRAINT taskTableKeyName PRIMARY KEY (taskTitle, toDoListTitle, username));";
			st.execute(query);
			st.close();
			dbCon.closeJDBCConnection();
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
