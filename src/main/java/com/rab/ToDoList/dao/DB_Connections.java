package com.rab.ToDoList.dao;

import java.sql.*;
import org.hibernate.*;
import org.hibernate.cfg.*;
import org.springframework.stereotype.*;
import com.rab.ToDoList.dao.Pojo_Classes.*;

@Component
public class DB_Connections {
	private static final String jdbcUrl = "jdbc:mysql://localhost:3306/todolist";
	private static final String jdbcDriver = "com.mysql.cj.jdbc.Driver";
	private static final String user = "root";
	private static final String password = "root";
	private Connection jdbcCon;
	private SessionFactory sf;

	public Connection getJDBCConnection() throws Exception {
		Class.forName(jdbcDriver);
		jdbcCon = DriverManager.getConnection(jdbcUrl, user, password);
		return jdbcCon;
	}

	public boolean closeJDBCConnection() throws Exception {
		if(jdbcCon != null) {
			jdbcCon.close();
			return true;
		}
		return false;
	}

	public Session getHibernateSession() throws Exception {
		Configuration config = new Configuration()
									.configure()
									.addAnnotatedClass(UserData.class)
									.addAnnotatedClass(ToDoListData.class)
									.addAnnotatedClass(TaskData.class);
		sf = config.buildSessionFactory();
		return sf.openSession();
	}

	public boolean closeHibernateSF() throws Exception {
		if(sf != null) {
			sf.close();
			return true;
		}
		return false;
	}
}
