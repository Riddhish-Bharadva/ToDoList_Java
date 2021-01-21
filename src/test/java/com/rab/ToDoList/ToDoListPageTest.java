package com.rab.ToDoList;

import com.rab.ToDoList.dao.ToDoList_DAO;
import javax.servlet.http.*;
import org.junit.*;
import org.junit.runner.*;
import org.mockito.*;
import org.mockito.junit.*;

@RunWith(MockitoJUnitRunner.class)
public class ToDoListPageTest {
	@Mock
	private ToDoList_DAO todoDAO;

	@Mock
	HttpServletRequest request;

	@Mock
	HttpSession session;

	@Mock
	CommonLogics cl;

	@InjectMocks private ToDoListPage toDoListPage;
	
	@Test
	public void createToDoNotNullSessionTest() {
		Mockito.when(request.getSession(false)).thenReturn(session);
		Mockito.when(cl.checkSession(request)).thenReturn(true);
		Mockito.when(session.getAttribute("username")).thenReturn(IndexPageTest.USERNAME);
		Mockito.when(todoDAO.createToDoList(TaskPageTest.toDoListTitle, IndexPageTest.USERNAME)).thenReturn(true);
		Assert.assertEquals("redirect:"+PageNames.toDoListPage, toDoListPage.createToDoList(request, TaskPageTest.toDoListTitle).getViewName());
	}

	@Test
	public void createToDoNullSessionTest() {
		Mockito.when(cl.checkSession(request)).thenReturn(false);
		Assert.assertEquals("redirect:"+PageNames.indexPage, toDoListPage.createToDoList(request, TaskPageTest.toDoListTitle).getViewName());
	}

	@Test
	public void userLogoutTest() throws Exception {
		Mockito.when(request.getSession(false)).thenReturn(session);
		Assert.assertEquals("redirect:"+PageNames.indexPage, toDoListPage.userLogout(request).getViewName());
	}

	@Test
	public void deleteToDoNotNullSessionTest() {
		Mockito.when(request.getSession(false)).thenReturn(session);
		Mockito.when(cl.checkSession(request)).thenReturn(true);
		Mockito.when(session.getAttribute("username")).thenReturn(IndexPageTest.USERNAME);
		Assert.assertEquals("redirect:"+PageNames.toDoListPage, toDoListPage.deleteToDo(request, TaskPageTest.toDoListTitle).getViewName());
	}

	@Test
	public void deleteToDoNullSessionTest() {
		Mockito.when(cl.checkSession(request)).thenReturn(false);
		Assert.assertEquals("redirect:"+PageNames.indexPage, toDoListPage.deleteToDo(request, TaskPageTest.toDoListTitle).getViewName());
	}
}
