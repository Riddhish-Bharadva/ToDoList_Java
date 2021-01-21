package com.rab.ToDoList.dao;

import java.sql.*;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import com.rab.ToDoList.dao.Pojo_Classes.*;

@Component
public class User_DAO {
	@Autowired
	private DB_Connections dbCon;

	public boolean addUser(String username, String password) {
		try {
			Statement st = dbCon.getJDBCConnection().createStatement();
			String query = "select * from "+TableNames.TABLE_USER+" where username='"+username+"'";
			ResultSet rs = st.executeQuery(query);
			if(!rs.next()) {
				PreparedStatement ps = dbCon.getJDBCConnection().prepareStatement("insert into "+TableNames.TABLE_USER+" values(?,?)");
				ps.setString(1,username);
				ps.setString(2,password);
				ps.executeUpdate();
				ps.close();
			}
			st.close();
			dbCon.closeJDBCConnection();
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public String checkUser(String username, String password) throws Exception {
		Session session = dbCon.getHibernateSession();
		session.beginTransaction();
		UserData userData = session.get(UserData.class, username);
		session.getTransaction().commit();
		dbCon.closeHibernateSF();
		if(userData != null && userData.getPassword().equals(password))
			return "Successful Login";
		else if(userData != null && !userData.getPassword().equals(password))
			return "Password Missmatch";
		else
			return "User Not Found";
	}
}
