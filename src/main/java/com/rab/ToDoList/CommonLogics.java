package com.rab.ToDoList;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.servlet.http.*;
import org.springframework.stereotype.*;

@Component
public class CommonLogics {
	public boolean checkSession(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if(session != null) {
			String username = (String)session.getAttribute("username");
			return username != null ? true:false;
		}
		else {
			return false;
		}
	}

	public String getDate() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss");
		LocalDateTime datetime = LocalDateTime.now();
		return dtf.format(datetime);
	}
}
