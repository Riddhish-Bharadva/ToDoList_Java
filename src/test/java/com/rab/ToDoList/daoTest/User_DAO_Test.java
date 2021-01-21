package com.rab.ToDoList.daoTest;

import static org.junit.Assert.assertEquals;
import java.sql.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.*;
import org.junit.runner.*;
import org.mockito.*;
import org.mockito.junit.*;
import com.rab.ToDoList.dao.*;
import com.rab.ToDoList.dao.Pojo_Classes.UserData;

@RunWith(MockitoJUnitRunner.class)
public class User_DAO_Test {
	@Mock
	DB_Connections dbCon;
	@Mock
	Connection con;
	@Mock
	Statement st;
	@Mock
	ResultSet rs;
	@Mock
	PreparedStatement ps;
	@Mock
	Session session;

	@InjectMocks User_DAO uDAO;

	@Before
	public void setUp() throws Exception {
		Mockito.when(dbCon.getHibernateSession()).thenReturn(session);
		Mockito.when(dbCon.closeHibernateSF()).thenReturn(true);
		UserData ud = Mockito.mock(UserData.class);
		ud.setUsername(TableOperationsTest.username);
		ud.setPassword(TableOperationsTest.password);
		Transaction tx = Mockito.mock(Transaction.class);
		Mockito.when(session.getTransaction()).thenReturn(tx);
		Mockito.when(session.get(UserData.class,TableOperationsTest.username)).thenReturn(ud);
		Mockito.when(ud.getPassword()).thenReturn(TableOperationsTest.password);
	}

	@Test
	public void addUserTest() throws Exception {
		Mockito.when(dbCon.getJDBCConnection()).thenReturn(con);
		Mockito.when(con.createStatement()).thenReturn(st);
		Mockito.when(st.executeQuery(Mockito.anyString())).thenReturn(rs);
		Mockito.when(dbCon.getJDBCConnection().prepareStatement(Mockito.anyString())).thenReturn(ps);
		Mockito.when(dbCon.closeJDBCConnection()).thenReturn(true);
		assertEquals(true, uDAO.addUser(TableOperationsTest.username, TableOperationsTest.password));
	}

	@Test
	public void checkUserAllCorrectTest() throws Exception {
		assertEquals("Successful Login", uDAO.checkUser(TableOperationsTest.username, TableOperationsTest.password));
	}

	@Test
	public void checkUserNullUsernameTest() throws Exception {
		assertEquals("User Not Found", uDAO.checkUser(null, TableOperationsTest.password));
	}

	@Test
	public void checkUserNullPasswordTest() throws Exception {
		assertEquals("Password Missmatch", uDAO.checkUser(TableOperationsTest.username, null));
	}
}
