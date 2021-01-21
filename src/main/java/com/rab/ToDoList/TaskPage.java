package com.rab.ToDoList;

import javax.servlet.http.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.*;
import com.rab.ToDoList.dao.Task_DAO;
import com.rab.ToDoList.dao.Pojo_Classes.*;

@Controller
public class TaskPage {
	@Autowired
	Task_DAO tDAO;
	@Autowired
	CommonLogics cl;

	@GetMapping(PageNames.taskPage)
	public ModelAndView taskPage(HttpServletRequest request, @RequestParam("toDoListTitle") String toDoListTitle) throws Exception {
		request.getSession(false).setAttribute("toDoListTitle", toDoListTitle);
		boolean sessionActive = cl.checkSession(request);
		ModelAndView mav = new ModelAndView();
		if(sessionActive) {
			String username = (String)request.getSession(false).getAttribute("username");
			List<TaskData> values = tDAO.getTasks(username, (String)request.getSession(false).getAttribute("toDoListTitle"));
			mav.addObject("TaskNames",values);
			mav.addObject("toDoListTitle",toDoListTitle);
			mav.addObject("message", request.getSession(false).getAttribute("message"));
			request.getSession(false).removeAttribute("message");
			mav.setViewName(PageNames.taskPage);
		}
		else {
			mav.setViewName("redirect:"+PageNames.indexPage);
		}
		return mav;
	}
	
	@PostMapping(PageNames.taskPage)
	public ModelAndView createTask(HttpServletRequest request, @RequestParam("taskTitle") String taskTitle, @RequestParam("taskDescription") String taskDescription) {
		ModelAndView mav = new ModelAndView();
		TaskData td = new TaskData();
		td.setUsername((String)request.getSession(false).getAttribute("username"));
		td.setToDoListTitle((String)request.getSession(false).getAttribute("toDoListTitle"));
		td.setTaskTitle(taskTitle);
		td.setTaskDescription(taskDescription);
		td.setCompleteStatus(false);
		td.setCreatedOn(cl.getDate());
		td.setUpdatedOn(cl.getDate());
		mav.setViewName("redirect:"+PageNames.taskPage+"?toDoListTitle="+(String)request.getSession(false).getAttribute("toDoListTitle"));
		if(tDAO.createTask(td)) {
			request.getSession(false).setAttribute("message","Task Created Successfully");
		}
		else {
			request.getSession(false).setAttribute("message","Task already exist");
		}
		return mav;
	}
	
	@PostMapping("/taskOperations")
	public ModelAndView operationOnTask(HttpServletRequest request, @RequestParam("deleteTask") String taskTitle) {
		ModelAndView mav = new ModelAndView();
		tDAO.deleteTask((String)request.getSession(false).getAttribute("username"), (String)request.getSession(false).getAttribute("toDoListTitle"), taskTitle);
		mav.setViewName("redirect:"+PageNames.taskPage+"?toDoListTitle="+(String)request.getSession(false).getAttribute("toDoListTitle"));
		return mav;
	}

	@PostMapping("/checkTask")
	public ModelAndView checkTask(HttpServletRequest request, @RequestParam("taskTitleTextBox") String taskTitle) {
		ModelAndView mav = new ModelAndView();
		tDAO.checkTask((String)request.getSession(false).getAttribute("username"), (String)request.getSession(false).getAttribute("toDoListTitle"), taskTitle);
		mav.setViewName("redirect:"+PageNames.taskPage+"?toDoListTitle="+(String)request.getSession(false).getAttribute("toDoListTitle"));
		return mav;
	}
}
