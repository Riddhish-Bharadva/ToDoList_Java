package com.rab.ToDoList.daoTest;

import static org.junit.Assert.assertEquals;
import java.util.*;
import org.hibernate.*;
import org.hibernate.query.Query;
import org.junit.*;
import org.junit.runner.*;
import org.mockito.*;
import org.mockito.junit.*;
import com.rab.ToDoList.dao.*;
import com.rab.ToDoList.dao.Pojo_Classes.*;

@RunWith(MockitoJUnitRunner.class)
public class Task_DAO_Test {
	@Mock
	DB_Connections dbCon;
	@Mock
	CommonLogic cl;
	@Mock
	Session session;
	@Mock
	TaskData td;

	@InjectMocks Task_DAO tDAO;

	@Before
	public void setUp() throws Exception {
		Mockito.when(dbCon.getHibernateSession()).thenReturn(session);
		Mockito.when(dbCon.closeHibernateSF()).thenReturn(true);
		Transaction tx = Mockito.mock(Transaction.class);
		Mockito.when(session.getTransaction()).thenReturn(tx);
	}

	@Test
	public void createTaskTest() {
		List<TaskData> td = new ArrayList<TaskData>();
		Query q = Mockito.mock(Query.class);
		Mockito.when(session.createQuery(Mockito.anyString())).thenReturn(q);
		assertEquals(true, tDAO.createTask(this.td));
	}

	@Test
	public void getTaskTest() {
		List<TaskData> td = new ArrayList<TaskData>();
		Query q = Mockito.mock(Query.class);
		Mockito.when(session.createQuery(Mockito.anyString())).thenReturn(q);
		assertEquals(td, tDAO.getTasks(TableOperationsTest.username, "toDoListTitle"));
	}

	@Test
	public void deleteTaskTest() {
		Query q = Mockito.mock(Query.class);
		Mockito.when(session.createQuery(Mockito.anyString())).thenReturn(q);
		assertEquals(true, tDAO.deleteTask(TableOperationsTest.username, "toDoListTitle", "taskTitle"));
	}
}
