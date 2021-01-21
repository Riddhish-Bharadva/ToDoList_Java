package com.rab.ToDoList.dao;

import java.util.*;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import com.rab.ToDoList.dao.Pojo_Classes.*;

@Component
public class ToDoList_DAO {
	@Autowired
	DB_Connections dbCon;
	@Autowired
	CommonLogic cl;
	@Autowired
	Task_DAO tDAO;
	
	public boolean createToDoList(String toDoListTitle, String username) {
		try {
			Session session = dbCon.getHibernateSession();
			session.beginTransaction();
			List<ToDoListData> values = (List<ToDoListData>)session.createQuery("from ToDoListData where toDoListTitle='"+toDoListTitle+"' and username='"+username+"'").list();
			if(values.size() == 0) {
				ToDoListData value = new ToDoListData();
				value.setToDoListTitle(toDoListTitle);
				value.setUsername(username);
				value.setCreatedOn(cl.getDate());
				session.save(value);
				session.getTransaction().commit();
				dbCon.closeHibernateSF();
				return true;
			}
			else {
				dbCon.closeHibernateSF();
				return false;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public List<ToDoListData> getToDoLists(String username) {
		try {
			Session session = dbCon.getHibernateSession();
			session.beginTransaction();
			List<ToDoListData> todolistsdata = (List<ToDoListData>)session.createQuery("from ToDoListData where username='"+username+"'").list();
			session.getTransaction().commit();
			dbCon.closeHibernateSF();
			return todolistsdata;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<ToDoListData>();
	}

	public boolean deleteToDoList(String toDoListTitle, String username) {
		List<TaskData> taskData = tDAO.getTasks(username, toDoListTitle);
		for(TaskData each: taskData) {
			tDAO.deleteTask(username, toDoListTitle, each.getTaskTitle());
		}
		try {
			Session session = dbCon.getHibernateSession();
			session.beginTransaction();
			ToDoListData value = (ToDoListData) session.createQuery("from ToDoListData where toDoListTitle='"+toDoListTitle+"' and username='"+username+"'").getSingleResult();
			session.delete(value);
			session.getTransaction().commit();
			dbCon.closeHibernateSF();
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
