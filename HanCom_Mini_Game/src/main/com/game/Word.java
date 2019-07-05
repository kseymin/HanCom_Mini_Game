package main.com.game;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

class Word {
	
	String DB_driverName = "oracle.jdbc.driver.OracleDriver";
	String DB_URL = "jdbc:oracle:thin:@192.168.30.75:1521:xe";
	String DB_USER = "c##ora_user";
	String DB_PW = "rla";
	ArrayList<String> words;
	
	Connection connection = null;
	Statement statement = null;
	ResultSet resultSet = null;
	
	public Word() {
		 words = new ArrayList<>();
		 try {
				Class.forName(DB_driverName);

			} catch (ClassNotFoundException e) {
				System.out.println("[로드 오류]\n" + e.getStackTrace());
			}
		 getRandomWords();
	}
	//SELECT [컬럼명] FROM [테이블명] ORDER BY RAND() LIMIT [개수]
	
	public void closeDatabase() {
		try {
			if (connection != null) {
				connection.close();
			}

			if (statement != null) {
				statement.close();
			}

			if (resultSet != null) {
				resultSet.close();
			}
		} catch (SQLException e) {
			System.out.println("[닫기 오류]\n" + e.getStackTrace());
		}
	}
	
	public void getRandomWords() {
		ResultSet rs = null;
		//String queryString="SELECT * FROM(SELECT * FROM WORDS ORDER BY DBMS_RANDOM.RANDOM) WHERE ROWNUM <= 27";
		
		try {
			String queryString ="select * from WORDS";
			connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PW);
			statement = connection.createStatement();
			rs = statement.executeQuery(queryString);
			
			while(rs.next()) {
				words.add(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeDatabase();
		}
	
	}
	
	
}
