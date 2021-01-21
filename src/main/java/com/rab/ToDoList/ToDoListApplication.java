package com.rab.ToDoList;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.context.*;
import com.rab.ToDoList.dao.TableOperations;

@SpringBootApplication
public class ToDoListApplication {
	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(ToDoListApplication.class, args);
		TableOperations to = applicationContext.getBean(TableOperations.class);
		to.createTables();
	}
}
