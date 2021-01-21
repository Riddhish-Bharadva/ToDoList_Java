package com.rab.ToDoList.daoTest;

import static org.junit.Assert.assertEquals;
import java.sql.*;
import org.junit.*;
import org.junit.runner.*;
import org.mockito.*;
import org.mockito.junit.*;
import com.rab.ToDoList.dao.*;

@RunWith(MockitoJUnitRunner.class)
public class TableOperationsTest {
	@Mock
	private User_DAO uDAO;
	@Mock
	DB_Connections dbCon;
	@Mock
	Connection con;
	@Mock
	Statement st;

	@InjectMocks TableOperations to;

	public static final String username = "test";
	public static final String password = "pwd123";

	@Test
	public void createTablesTest() throws Exception {
		Mockito.when(dbCon.getJDBCConnection()).thenReturn(con);
		Mockito.when(con.createStatement()).thenReturn(st);
		Mockito.when(dbCon.closeJDBCConnection()).thenReturn(true);
		assertEquals(true, to.createTables());
	}
}
