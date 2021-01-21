package com.rab.ToDoList.dao.Pojo_Classes;

import javax.persistence.*;
import org.springframework.stereotype.*;
import com.rab.ToDoList.dao.TableNames;

@Component
@Entity
@Table(name=TableNames.TABLE_USER)
public class UserData {
	@Id
	private String username;
	private String password;

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
