package online.todo.application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class ApplicationsDAO {
	
	public Connection getConnection() {
		
		String connectionUrl = "jdbc:mysql://localhost:3306/online_todo_list?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		Connection connection = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(connectionUrl, "root", "Rainvow@9"); 
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}
	
	public void closeConnection(Connection connection) {
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void create (Task list) {
		String sql = "insert into task (name, description) values(?, ?)";
				
		Connection connection = getConnection();
		
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, list.getName());
			statement.setString(2, list.getDescription());
			statement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConnection(connection);
		}
	}
	
	public void update(int id, Task taskParams) {
		String sql = "update task set name= ? , description= ? where id =?";
		
		Connection connection = getConnection();
		
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, taskParams.getName());
			statement.setString(2, taskParams.getDescription());
			statement.setInt(3, id);
			statement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConnection(connection);
		}
		
	}
	
	public List<Task> selectAll() {
		List<Task> tasks = new ArrayList<Task>();
		String sql = "select * from task";
		
		Connection connection = getConnection();
		
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet results = statement.executeQuery();
			while(results.next()) {
				int id = results.getInt("id");
				String name = results.getString("name");
				String description = results.getString("description");
				Task task = new Task(id, name, description);
				tasks.add(task);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConnection(connection);
		}
		return tasks;
	}
	
	public void remove(int id) {
		String sql = "delete from task where id =?";
		
		Connection connection = getConnection();
		
		PreparedStatement statement;
		try {
			statement = connection.prepareStatement(sql);
			statement.setInt(1, id); 
			statement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConnection(connection);
		}
	}
	
	public Task editTask(int id) {
		Task task = null;
		
		String sql = "select * from task where id = ?";
		
		Connection connection = getConnection();
		
		PreparedStatement statement;
		
		try {
			statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet results = statement.executeQuery();
			if(results.next()) {
				id = results.getInt("id");
				String name = results.getString("name");
				String description = results.getString("description");
				task = new Task(id, name, description);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConnection(connection);
		}
		return task;
	}
	
	public static void main(String[] args) {
		
	}

}
