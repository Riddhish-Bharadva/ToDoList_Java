package com.rab.ToDoList.daoTest;

import static org.junit.Assert.assertEquals;
import java.util.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.*;
import org.junit.*;
import org.junit.runner.*;
import org.mockito.*;
import org.mockito.junit.*;
import com.rab.ToDoList.*;
import com.rab.ToDoList.dao.*;
import com.rab.ToDoList.dao.Pojo_Classes.*;

@RunWith(MockitoJUnitRunner.class)
public class ToDoList_DAO_Test {
	@Mock
	DB_Connections dbCon;
	@Mock
	CommonLogic cl;
	@Mock
	Task_DAO tDAO;
	@Mock
	Session session;

	@InjectMocks ToDoList_DAO todoDAO;

	@Before
	public void setUp() throws Exception {
		Mockito.when(dbCon.getHibernateSession()).thenReturn(session);
		Mockito.when(dbCon.closeHibernateSF()).thenReturn(true);
		Transaction tx = Mockito.mock(Transaction.class);
		Mockito.when(session.getTransaction()).thenReturn(tx);
	}

	@Test
	public void createToDoTest() {
		List<ToDoListData> tdl = new ArrayList<ToDoListData>();
		Query q = Mockito.mock(Query.class);
		Mockito.when(session.createQuery(Mockito.anyString())).thenReturn(q);
		assertEquals(true, todoDAO.createToDoList(TaskPageTest.toDoListTitle,TableOperationsTest.username));
	}

	@Test
	public void getToDoListsTest() {
		List<ToDoListData> tdl = new ArrayList<ToDoListData>();
		Query q = Mockito.mock(Query.class);
		Mockito.when(session.createQuery(Mockito.anyString())).thenReturn(q);
		assertEquals(tdl, todoDAO.getToDoLists(TableOperationsTest.username));
	}

	@Test
	public void deleteToDoListTest() {
		Query q = Mockito.mock(Query.class);
		Mockito.when(session.createQuery(Mockito.anyString())).thenReturn(q);
		assertEquals(true, todoDAO.deleteToDoList("toDoListName", TableOperationsTest.username));
	}
}
