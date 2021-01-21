package com.rab.ToDoList;

import static org.junit.Assert.assertEquals;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import com.rab.ToDoList.dao.Task_DAO;
import com.rab.ToDoList.dao.Pojo_Classes.TaskData;

@RunWith(MockitoJUnitRunner.class)
public class TaskPageTest {
	@Mock
	Task_DAO tDAO;

	@Mock
	CommonLogics cl;

	@Mock
	TaskData td;

	@Mock
	HttpServletRequest request;

	@Mock
	HttpSession session;

	@InjectMocks TaskPage tp;

	public static final String toDoListTitle = "toDoListTitle";
	public static final String taskTitle = "taskTitle";
	public static final String taskDescription = "taskDescription";

	@Before
	public void setUp() {
		request = Mockito.mock(HttpServletRequest.class);
		session = Mockito.mock(HttpSession.class);
		td = Mockito.mock(TaskData.class);
		Mockito.when(request.getSession(false)).thenReturn(session);
		Mockito.when(session.getAttribute("username")).thenReturn("username");
	}

	@Test
	public void taskPageNotNullSessionTest() throws Exception {
		Mockito.when(cl.checkSession(request)).thenReturn(true);
		assertEquals(PageNames.taskPage,tp.taskPage(request,"toDoListTitle").getViewName());
	}

	@Test
	public void taskPageNullSessionTest() throws Exception {
		Mockito.when(cl.checkSession(request)).thenReturn(false);
		assertEquals("redirect:"+PageNames.indexPage, tp.taskPage(request,"toDoListTitle").getViewName());
	}
}
