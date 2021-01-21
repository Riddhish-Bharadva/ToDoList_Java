package com.rab.ToDoList;

import com.rab.ToDoList.dao.User_DAO;
import javax.servlet.http.*;
import org.junit.*;
import org.junit.runner.*;
import org.mockito.*;
import org.mockito.junit.*;

@RunWith(MockitoJUnitRunner.class)
public class IndexPageTest {
	@Mock
	private User_DAO userDao;

	@Mock
	HttpServletRequest request;
	
	@Mock
	HttpSession session;
	
	public static final String USERNAME = "test";
	public static final String PASSWORD = "pwd123";

	@InjectMocks private IndexPage indexPage;
	
	@Test
	public void indexPageTest() {
		String str = indexPage.indexPage();
		Assert.assertEquals(true, str.equals("index"));
	}

	@Test
	public void signInUsernameInvalidTest() throws Exception {
		Mockito.when(userDao.checkUser(USERNAME, PASSWORD)).thenReturn("User Not Found");
		Assert.assertEquals("index", indexPage.signIn(request, USERNAME, PASSWORD).getViewName());
	}

	@Test
	public void signInPasswordMissmatchTest() throws Exception {
		Mockito.when(userDao.checkUser(USERNAME, PASSWORD)).thenReturn("Password Mismatch");
		Assert.assertEquals("index", indexPage.signIn(request, USERNAME, PASSWORD).getViewName());
	}

	@Test
	public void signInNullSessionTest() throws Exception {
		request = Mockito.mock(HttpServletRequest.class);
		Mockito.when(request.getSession(true)).thenReturn(null);
		Mockito.when(userDao.checkUser(USERNAME, PASSWORD)).thenReturn("Successful Login");
		Assert.assertEquals("index", indexPage.signIn(request, USERNAME, PASSWORD).getViewName());
	}

	@Test
	public void signInNotNullSessionTest() throws Exception {
		request = Mockito.mock(HttpServletRequest.class);
		session = Mockito.mock(HttpSession.class);
		Mockito.when(request.getSession(true)).thenReturn(session);
		Mockito.when(userDao.checkUser(USERNAME, PASSWORD)).thenReturn("Successful Login");
		Assert.assertEquals("redirect:"+PageNames.toDoListPage, indexPage.signIn(request, USERNAME, PASSWORD).getViewName());
	}
}
