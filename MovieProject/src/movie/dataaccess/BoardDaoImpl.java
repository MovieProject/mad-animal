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
		return dataSource.getConnection();
	}

	public List<Board> selectBoardList(Map<String, Object> searchInfo) {
		// searchInfo Map으로 부터 검색 조건을 구한다.
		String searchType = (String) searchInfo.get("searchType");
		String searchText = (String) searchInfo.get("searchText");

		// searchInfo Map으로 부터 현재 페이지에 보여질 게시글의 행 번호(startRow, endRow) 값을 구한다.
		int startRow = (Integer) searchInfo.get("startRow");
		int endRow = (Integer) searchInfo.get("endRow");

		// searchType 값에 따라 사용될 조건절(WHERE)을 생성
		String whereSQL = "";

		if (searchType != null) {
			if (searchType.equals("all")) {
				whereSQL = "WHERE title LIKE ? OR writer LIKE ? OR contents LIKE ?";

			} else if ((searchType.equals("title"))
					|| (searchType.equals("writer"))
					|| (searchType.equals("contents"))) {
				whereSQL = "WHERE " + searchType + " LIKE ?";
			}
		}
		// LIKE 절에 포함될 수 있도록 searchText 값 앞 뒤에 % 기호를 붙인다.
		if (searchText != null) {
			searchText = "%" + searchText.trim() + "%";
		}

		query = "select * from (select rownum r, boardNum, title, writerName, reg_date, read_count,reply_step from(select * from board "
				+ whereSQL
				+ " order by master_num DESC, reply_order ASC)) where r between ? and ?";

		System.out.println("BoardDaoImpl SelectBoardList() query : " + query);

		// query = "Select num,writer,title,read_count,reg_date from board "
		// + whereSQL + " ORDER BY num DESC";

		List<Board> result = new ArrayList<Board>();
		Board board = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = obtainConnection();
			pstmt = con.prepareStatement(query);

			// searchType 값에 따라 PreparedStatement의 파라미터 값을 설정한다.
			if ((searchType == null) || (searchType.length() == 0)) {
				pstmt.setInt(1, startRow);
				pstmt.setInt(2, endRow);

			} else if (searchType.equals("all")) {
				pstmt.setString(1, searchText);
				pstmt.setString(2, searchText);
				pstmt.setString(3, searchText);
				pstmt.setInt(4, startRow);
				pstmt.setInt(5, endRow);

			} else if ((searchType.equals("title"))
					|| (searchType.equals("writer"))
					|| (searchType.equals("contents"))) {
				pstmt.setString(1, searchText);
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, endRow);
			}

			rs = pstmt.executeQuery();
			while (rs.next()) {
				String title = rs.getString("title");

				if (title.length() > 28) {
					title = new StringBuilder(title.substring(0, 28)).append(
							"...").toString();
				}

				board = new Board(rs.getInt("boardNum"), title,
						rs.getString("writerName"), rs.getInt("read_count"),
						rs.getString("reg_date"), rs.getInt("reply_step"));

				result.add(board);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (SQLException ex) {
				ex.printStackTrace(System.err);
			}
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
		return result;

	}

	public int selectBoardCount(Map<String, Object> searchInfo) {
		// searchInfo로 부터 검색 조건을 구한다.
		String searchType = (String) searchInfo.get("searchType");
		String searchText = (String) searchInfo.get("searchText");

		// searchType 값에 따라 사용될 조건절(WHERE)을 생성
		String whereSQL = "";

		if ((searchType != null)) {
			if (searchType.equals("all")) {
				whereSQL = "WHERE title LIKE ? OR writer LIKE ? OR contents LIKE ?";

			} else if ((searchType.equals("title"))
					|| (searchType.equals("writer"))
					|| (searchType.equals("contents"))) {
				whereSQL = "WHERE " + searchType + " LIKE ?";
			}
		}

		// LIKE 절에 포함될 수 있도록 searchText 값 앞 뒤에 % 기호를 붙인다.
		if (searchText != null) {
			searchText = "%" + searchText.trim() + "%";
		}

		int result = 0;
		query = "select count(boardNum) from board " + whereSQL;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = obtainConnection();
			pstmt = con.prepareStatement(query);

			// searchType 값에 따라 PreparedStatement의 파라미터 값을 설정한다.
			if (searchType != null) {
				if (searchType.equals("all")) {
					pstmt.setString(1, searchText);
					pstmt.setString(2, searchText);
					pstmt.setString(3, searchText);

				} else if ((searchType.equals("title"))
						|| (searchType.equals("writer"))
						|| (searchType.equals("contents"))) {
					pstmt.setString(1, searchText);
				}
			}

			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (SQLException ex) {
				ex.printStackTrace(System.err);
			}
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

		return result;

	}

	public Board selectBoard(int boardNum) {
		query = "SELECT boardNum, title, writerName, contents, read_count, reg_date, mod_date, master_num, reply_order, reply_step FROM board WHERE boardNum=?";

		System.out.println("BoardDaoImpl selectBoard() query : " + query);

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Board result = null;

		try {
			con = obtainConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, boardNum);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = new Board(rs.getInt("boardNum"),
						rs.getString("title"), rs.getString("writerName"),
						rs.getString("contents"), rs.getInt("read_count"),
						rs.getString("reg_date"), rs.getString("mod_date"),
						rs.getInt("master_num"), rs.getInt("reply_order"),
						rs.getInt("reply_step"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (SQLException ex) {
				ex.printStackTrace(System.err);
			}
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

		return result;

	}

	public void addReadCount(int boardNum) {
		query = "UPDATE board SET read_count=read_count+1 WHERE boardNum=?";
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = obtainConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, boardNum);
			pstmt.executeUpdate();
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

	public boolean boardNumExists(int boardNum) {
		query = "SELECT boardNum FROM board WHERE boardNum=?";
		boolean result = false;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = obtainConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, boardNum);
			rs = pstmt.executeQuery();
			result = rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (SQLException ex) {
				ex.printStackTrace(System.err);
			}
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

		return result;

	}

	public void insertBoard(Board board) {
		query = "INSERT INTO board (board_num, board_title, writerName,  contents, writerID, read_count, reg_date,mod_date, master_num) "
				+ "VALUES (board_num_seq.NEXTVAL, ?, ?, ?, ?, ?, 0, SYSDATE, SYSDATE, board_num_seq.CURRVAL)";
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = obtainConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getWriterName());
			pstmt.setString(3, board.getContents());
			pstmt.executeUpdate();

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

	public void updateBoard(Board board) {
		query = "UPDATE board SET title=?, writerName=?, contents=?, writerID=?, mod_date=SYSDATE WHERE board_num=?";
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = obtainConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, board.getWriterName());
			pstmt.setString(2, board.getTitle());
			pstmt.setString(3, board.getContents());
			pstmt.setString(4, board.getWriterID());
			pstmt.setInt(5, board.getBoardNum());
			pstmt.executeUpdate();
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

	public void deleteBoard(int boardNum) {
		query = "DELETE FROM board WHERE boardNum = ?";
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = obtainConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, boardNum);
			pstmt.executeUpdate();
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

	public void insertReplyBoard(Board board) {
		query = "UPDATE board SET reply_order = reply_order + 1 WHERE master_num = ? AND reply_order > ?";

		System.out.println("BoardDaoImpl insertReplyBoard() query : " + query);

		String query2 = "INSERT INTO board (num, title, writer, contents, writerID, read_count, reg_date, mod_date, master_num, reply_order, reply_step) "
				+ "VALUES (board_num_seq.NEXTVAL, ?, ?, ?, ?, 0, SYSDATE, SYSDATE, ?, ?, ?)";
		System.out
				.println("BoardDaoImpl insertReplyBoard() query2 : " + query2);

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = obtainConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, board.getMasterNum());
			pstmt.setInt(2, board.getReplyOrder());
			pstmt.executeUpdate();
			pstmt.close();

			pstmt = con.prepareStatement(query2);
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getWriterName());
			pstmt.setString(3, board.getContents());
			pstmt.setString(4, board.getWriterID());
			pstmt.setInt(5, board.getMasterNum());
			pstmt.setInt(6, board.getReplyOrder() + 1);
			pstmt.setInt(7, board.getReplyStep() + 1);
			pstmt.executeUpdate();

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
