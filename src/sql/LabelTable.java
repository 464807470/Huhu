package sql;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Format;

import com.mysql.jdbc.Connection;

public class LabelTable {
	private Connection conn;
	public LabelTable(String uri,String sqlUser,String Password) throws SQLException, ClassNotFoundException {
		//连接数据库
		Class.forName("com.mysql.jdbc.Driver");
	    conn = (Connection) DriverManager.getConnection(uri, sqlUser, Password);
	}
	
	/**
	 * @param id
	 * @param label
	 * @return 是否插入成功
	 * @throws SQLException
	 */
	public boolean setLabel(String label) throws SQLException{
		
		//使用插入语句插入sql数据库
		String insertSQL ="insert into label_table(label) values(?)";
		PreparedStatement sqlStatement  = conn.prepareStatement(insertSQL);
		sqlStatement.setString(1,label);
		int status = sqlStatement.executeUpdate();
		
		if(status!=0 )
			return true;
		else
			return false;
	}
	
	/**
	 * 
	 * @param id
	 * @return 从数据库得到的结果集
	 * @throws SQLException
	 */
	public ResultSet getLabel(int id) throws SQLException{
		ResultSet rs;
		String selectSQL=String.format("select * from label_table where id=%d", id);
		PreparedStatement sqlStatement = conn.prepareStatement(selectSQL);
		rs = sqlStatement.executeQuery();
		
		return rs;
	}
	
}
