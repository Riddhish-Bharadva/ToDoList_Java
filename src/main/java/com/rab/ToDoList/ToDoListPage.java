package com.rab.ToDoList;

import javax.servlet.http.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.*;
import com.rab.ToDoList.dao.ToDoList_DAO;
import com.rab.ToDoList.dao.Pojo_Classes.*;

@Controller
public class ToDoListPage {
	@Autowired
	ToDoList_DAO tdlDAO;
	@Autowired
	CommonLogics cl;

	@PostMapping("/createToDoList")
	public ModelAndView createToDoList(HttpServletRequest request, @RequestParam("createtodolist") String todolistTitle) {
		boolean sessionActive = cl.checkSession(request);
		ModelAndView mav = new ModelAndView();
		if(sessionActive) {
			String username = (String)request.getSession(false).getAttribute("username");
			if(tdlDAO.createToDoList(todolistTitle, username)) {
				request.getSession(false).setAttribute("message","Todo List Created Successfully");
			}
			else {
				request.getSession(false).setAttribute("message","Todo List already exist.");
			}
			mav.setViewName("redirect:"+PageNames.toDoListPage);
		}
		else {
			mav.setViewName("redirect:"+PageNames.indexPage);
		}
		return mav;
	}

	@GetMapping(PageNames.toDoListPage)
	public ModelAndView toDoListPage(HttpServletRequest request) throws Exception {
		boolean sessionActive = cl.checkSession(request);
		ModelAndView mav = new ModelAndView();
		if(sessionActive) {
			String username = (String)request.getSession(false).getAttribute("username");
			List<ToDoListData> values = tdlDAO.getToDoLists(username);
			mav.addObject("ToDoListNames",values);
			mav.addObject("message",request.getSession(false).getAttribute("message"));
			request.getSession(false).removeAttribute("message");
			request.getSession(false).removeAttribute("toDoListName");
			mav.setViewName(PageNames.toDoListPage);
		}
		else {
			mav.setViewName("redirect:"+PageNames.indexPage);
		}
		return mav;
	}

	@PostMapping("/userLogout")
	public ModelAndView userLogout(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession(false);
		session.removeAttribute("username");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:"+PageNames.indexPage);
		session.invalidate();
		return mav;
	}

	@PostMapping("/deleteToDo")
	public ModelAndView deleteToDo(HttpServletRequest request, @RequestParam("deletetodo") String todolistTitle) {
		boolean sessionActive = cl.checkSession(request);
		ModelAndView mav = new ModelAndView();
		if(sessionActive) {
			String username = (String)request.getSession(false).getAttribute("username");
			tdlDAO.deleteToDoList(todolistTitle, username);
			mav.setViewName("redirect:"+PageNames.toDoListPage);
		}
		else {
			mav.setViewName("redirect:"+PageNames.indexPage);
		}
		return mav;
	}
}
