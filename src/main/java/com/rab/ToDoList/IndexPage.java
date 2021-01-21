package com.rab.ToDoList;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;
import com.rab.ToDoList.dao.User_DAO;
import javax.servlet.http.*;

@Controller
public class IndexPage {
	@Autowired
	User_DAO uDAO;

	@GetMapping("/")
	public String indexPage() {
		return "index";
	}

	@PostMapping("/")
	public ModelAndView signIn(HttpServletRequest request, @RequestParam("username") String username, @RequestParam("password") String password) throws Exception {
		String message = uDAO.checkUser(username, password);
		ModelAndView mAV = new ModelAndView();
		if(message.equals("Successful Login")) {
			HttpSession session = request.getSession(true);
			if(session != null) {
				session.setMaxInactiveInterval(300);
				session.setAttribute("username", username);
				mAV.setViewName("redirect:toDoListPage");
			}
			else
				mAV.setViewName("index");
		}
		else {
			mAV.addObject("message",message);
			mAV.setViewName("index");
		}
		return mAV;
	}
}
