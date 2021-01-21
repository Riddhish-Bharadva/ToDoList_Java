package com.rab.ToDoList;

import javax.servlet.http.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CommonLogicsTest {
	@Mock
	HttpServletRequest request;
	
	@InjectMocks private CommonLogics commonLogics;

	@Test
	public void checkSessionNotNullTest() throws Exception {
		request = Mockito.mock(HttpServletRequest.class);
		HttpSession session = Mockito.mock(HttpSession.class);
		Mockito.when(request.getSession(false)).thenReturn(session);
		Mockito.when((String)session.getAttribute("username")).thenReturn("username");
		Assert.assertEquals(true, commonLogics.checkSession(request));
	}

	@Test
	public void checkSessionNullTest() throws Exception {
		request = Mockito.mock(HttpServletRequest.class);
		Mockito.when(request.getSession(false)).thenReturn(null);
		Assert.assertEquals(false, commonLogics.checkSession(request));
	}
}
