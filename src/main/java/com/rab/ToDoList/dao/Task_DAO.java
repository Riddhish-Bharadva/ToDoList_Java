package com.rab.ToDoList.dao;

import java.util.*;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import com.rab.ToDoList.dao.Pojo_Classes.*;

@Component
public class Task_DAO {
	@Autowired
	private DB_Connections dbCon;
	@Autowired
	CommonLogic cl;

	public boolean createTask(TaskData td) {
		try {
			Session session = dbCon.getHibernateSession();
			session.beginTransaction();
			List<TaskData> values = (List<TaskData>)session.createQuery("from TaskData where username='"+td.getUsername()+"' and toDoListTitle='"+td.getToDoListTitle()+"' and taskTitle='"+td.getTaskTitle()+"'").list();
			if(values.isEmpty()) {
				session.save(td);
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

	public List<TaskData> getTasks(String username, String toDoListTitle) {
		try {
			Session session = dbCon.getHibernateSession();
			session.beginTransaction();
			List<TaskData> tasksData = (List<TaskData>)session
					.createQuery("from TaskData where username='"+username+"' and toDoListTitle='"+toDoListTitle+"'")
					.list();
			session.getTransaction().commit();
			dbCon.closeHibernateSF();
			return tasksData;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<TaskData>();
	}
	
	public boolean deleteTask(String username, String toDoListTitle, String taskTitle) {
		try {
			Session session = dbCon.getHibernateSession();
			session.beginTransaction();
			TaskData value = (TaskData)session.createQuery("from TaskData where taskTitle='"+taskTitle+"' and toDoListTitle='"+toDoListTitle+"' and username='"+username+"'").getSingleResult();
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

	public boolean checkTask(String username, String toDoListTitle, String taskTitle) {
		try {
			Session session = dbCon.getHibernateSession();
			session.beginTransaction();
			TaskData value = (TaskData)session.createQuery("from TaskData where taskTitle='"+taskTitle+"' and toDoListTitle='"+toDoListTitle+"' and username='"+username+"'").getSingleResult();
			if(value.isCompleteStatus()) {
				value.setCompleteStatus(false);
			}
			else {
				value.setCompleteStatus(true);
			}
			value.setUpdatedOn(cl.getDate());
			session.save(value);
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
