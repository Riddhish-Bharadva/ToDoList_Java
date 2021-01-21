package com.rab.ToDoList.dao.Pojo_Classes;

import java.io.Serializable;
import javax.persistence.*;
import org.springframework.stereotype.*;
import com.rab.ToDoList.dao.TableNames;

@Component
@Entity
@Table(name=TableNames.TABLE_TODOLIST)
public class ToDoListData implements Serializable{
	/**
	 *  Adding default serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private String toDoListTitle;
	@Id
	private String username;
	private String createdOn;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getToDoListTitle() {
		return toDoListTitle;
	}
	public void setToDoListTitle(String toDoListTitle) {
		this.toDoListTitle = toDoListTitle;
	}
	public String getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}
}
