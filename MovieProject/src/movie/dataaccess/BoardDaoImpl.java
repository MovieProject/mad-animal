package movie.dataaccess;

import java.sql.*;
import java.util.*;

import javax.naming.*;
import javax.sql.DataSource;

import movie.business.dao.BoardDao;
import movie.business.domain.Board;

public class BoardDaoImpl implements BoardDao {
	private String query;
	private DataSource dataSource;
	
	public BoardDaoImpl() {
		  try {
		   Context context = new InitialContext();
		   dataSource = (DataSource) context
		     .lookup("java:comp/env/jdbc/dukeshopDB");
		  } catch (NamingException ne) {
		   System.err.println("Driver Class Not found");
		   ne.printStackTrace(System.err);
		   throw new RuntimeException("JNDI error occured." + ne.getMessage());
		  }
		 }
	
	private Connection obtainConnection() throws SQLException {
		  // return DriverManager.getConnection(dbUrl, dbUser, dbPassword);
		  return dataSource.getConnection();
		 }

	public List<Board> selectBoardList(Map<String, Object> searchInfo) {
		return null;
	}

	public int selectBoardCount(Map<String, Object> searchInfo) {
		return 0;
	}

	public Board selectBoard(int boardNum) {
		return null;
	}

	public void addReadCount(int boardNum) {
		
	}

	public boolean boardNumExists(int boardNum) {
		return false;
	}

	public void insertBoard(Board board) {
		
	}

	public void updateBoard(Board board) {
		
	}

	public void deleteBoard(int boardNum) {
		
	}

	public void insertReplyBoard(Board board) {
		query = "UPDATE board SET reply_order = reply_order + 1 WHERE master_num = ? AND reply_order > ?";

		  System.out.println("BoardDaoImpl insertReplyBoard() query : " + query);

		  String query2 = "INSERT INTO board (num, writer, title, contents, ip, read_count, reg_date,mod_date, master_num, reply_order, reply_step) "
		      + "VALUES (board_num_seq.NEXTVAL, ?, ?, ?, ?, 0, SYSDATE, SYSDATE, ?, ?, ?)";
		  System.out
		    .println("BoardDaoImpl insertReplyBoard() query2 : " + query2);

		  Connection con = null;
		  PreparedStatement pstmt = null;

		  try {
		   con = obtainConnection();
		 
		   
		  

		  } catch (SQLException e) {
		   e.printStackTrace();
		  } finally {

		   try {
		    if (pstmt != null)
		     pstmt.close();
		   } catch (SQLException ex) {
		    ex.printStackTrace(System.err);
		   }
		   try {
		    if (con != null)
		     con.close();
		   } catch (SQLException e) {
		    e.printStackTrace(System.err);
		   }
		  }

	}
	
}
