package com.rab.ToDoList.dao;

import java.time.*;
import java.time.format.*;
import org.springframework.stereotype.*;

@Component
public class CommonLogic {
	protected String getDate() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss");
		LocalDateTime datetime = LocalDateTime.now();
		return dtf.format(datetime);
	}
}
