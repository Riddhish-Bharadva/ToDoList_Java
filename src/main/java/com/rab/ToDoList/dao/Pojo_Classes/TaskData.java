package com.rab.ToDoList.dao.Pojo_Classes;

import java.io.Serializable;
import javax.persistence.*;
import org.springframework.stereotype.*;
import com.rab.ToDoList.dao.TableNames;

@Component
@Entity
@Table(name=TableNames.TABLE_TASK)
public class TaskData implements Serializable{
	/**
	 *  Adding default serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private String taskTitle;
	@Id
	private String toDoListTitle;
	@Id
	private String username;
	private String taskDescription;
	private boolean completeStatus;
	private String createdOn;
	private String updatedOn;

	public String getTaskTitle() {
		return taskTitle;
	}
	public String getToDoListTitle() {
		return toDoListTitle;
	}
	public String getUsername() {
		return username;
	}
	public String getTaskDescription() {
		return taskDescription;
	}
	public boolean isCompleteStatus() {
		return completeStatus;
	}
	public String getCreatedOn() {
		return createdOn;
	}
	public String getUpdatedOn() {
		return updatedOn;
	}
	public void setTaskTitle(String taskTitle) {
		this.taskTitle = taskTitle;
	}
	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setToDoListTitle(String toDoListTitle) {
		this.toDoListTitle = toDoListTitle;
	}
	public void setCompleteStatus(boolean completeStatus) {
		this.completeStatus = completeStatus;
	}
	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}
	public void setUpdatedOn(String updatedOn) {
		this.updatedOn = updatedOn;
	}
}
