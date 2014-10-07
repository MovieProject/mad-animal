package movie.dataaccess;

import java.sql.*;
import java.util.*;

import javax.naming.*;
import javax.sql.*;

import movie.business.dao.MemberDao;
import movie.business.domain.Member;
import movie.util.MovieUtil;

public class MemberDaoImpl implements MemberDao {
	private DataSource dataSource;
	private String query;

	public MemberDaoImpl() {
		try {
			Context context = new InitialContext();
			context = ((Context) context.lookup("java:comp/env"));
			dataSource = (DataSource) context.lookup("jdbc/movieDB");
		} catch (NamingException e) {
			e.printStackTrace();
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			//
			// throw new RuntimeException("JNDI erroroccured." +
			// e.getMessage());
		}
	}

	/** Connection을 맺기 위한 Method */
	private Connection obtainConnection() throws SQLException {
		return dataSource.getConnection();
		/*
		 * return DriverManager.getConnection(
		 * "jdbc:oracle:thin:@220.67.115.225:1521:xe", "movie", "1234");
		 */
	}

	public List<Member> selectMemberList() {
		List<Member> result = new ArrayList<Member>();

		query = "SELECT * FROM MEMBER";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = obtainConnection();
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Member member = new Member(rs.getString(1), rs.getString(2),
						rs.getString(3), rs.getInt(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getInt(8));
				result.add(member);
			}
		} catch (SQLException e) {
			throw new RuntimeException("회원리스트를 조회하지 못하였습니다." + e.getMessage());
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException("DB문제 발생" + e.getMessage());

			}
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException("DB문제 발생" + e.getMessage());
			}
		}

		return result;
	}

	public int selectMemberCount() {
		int result = 0;
		query = "SELECT count(member_id) FROM member";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = obtainConnection();
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new RuntimeException("DB문제 발생" + e.getMessage());
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException("DB문제 발생" + e.getMessage());

			}
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException("DB문제 발생" + e.getMessage());
			}
		}

		return result;
	}

	public void insertMember(Member member) {
		// TODO Auto-generated method stub
		query = "INSERT INTO member(member_ID,password,member_name,age,address,mail,tel,grade) VALUES (?,?,?,?,?,?,?,"
				+ MovieUtil.GRADE_GENERAL + ")";
		Connection con = null;
		query = "INSERT INTO member(member_ID,password,member_name,age,address,mail,tel,grade) VALUES (?,?,?,?,?,?,?,"
				+ MovieUtil.GRADE_GENERAL + ")";
		PreparedStatement pstmt = null;

		try {
			con = obtainConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, member.getMemberID());
			pstmt.setString(2, member.getPassword());
			pstmt.setString(3, member.getMemberName());
			pstmt.setInt(4, member.getAge());
			pstmt.setString(5, member.getAddress());
			pstmt.setString(6, member.getEmail());
			pstmt.setString(7, member.getTel());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException("DB문제 발생" + e.getMessage());
			}
		}

	}

	public void updateMember(Member member) {
		query = "UPDATE member SET PASSWORD = ?, MEMBER_NAME = ?, AGE = ?, ADDRESS = ?, MAIL = ?, TEL = ?  WHERE MEMBER_ID = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = obtainConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, member.getPassword());
			pstmt.setString(2, member.getMemberName());
			pstmt.setInt(3, member.getAge());
			pstmt.setString(4, member.getAddress());
			pstmt.setString(5, member.getEmail());
			pstmt.setString(6, member.getTel());
			pstmt.setString(7, member.getMemberID());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("DB문제 발생" + e.getMessage());

		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException("DB문제 발생" + e.getMessage());

			}
		}

	}

	public void deleteMember(String memberID) {
		query = "DELETE FROM MEMBER WHERE MEMBER_ID =?";
		// TODO Auto-generated method stub
		Connection con = null;

		PreparedStatement pstmt = null;

		try {
			con = obtainConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, memberID);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("DB문제 발생" + e.getMessage());
		} finally {

			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException("DB문제 발생" + e.getMessage());

			}
		}

	}

	public boolean memberIDExists(String memberID) {
		boolean result = false;
		query = "SELECT MEMBER_ID,MEMBER_NAME FROM MEMBER WHERE MEMBER_ID = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = obtainConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, memberID);
			rs = pstmt.executeQuery();
			result = rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("DB문제 발생" + e.getMessage());
		} finally {

			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException("DB문제 발생" + e.getMessage());

			}
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException("DB문제 발생" + e.getMessage());

			}
		}

		return result;
	}

	public Member selectMember(String memberID) {
		Member result = null;
		query = "SELECT * FROM MEMBER WHERE member_id = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = obtainConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, memberID);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				result = new Member(rs.getString(1), rs.getString(2),
						rs.getString(3), rs.getInt(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getInt(8));
			}
		} catch (SQLException e) {
			throw new RuntimeException("회원리스트를 조회하지 못하였습니다." + e.getMessage());
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException("DB문제 발생" + e.getMessage());

			}
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException("DB문제 발생" + e.getMessage());
			}
		}

		return result;
	}

	public Member checkMember(String memberID, String password) {
		// TODO Auto-generated method stub
		Member result = new Member(memberID, password);
		query = "SELECT password, member_name,age,address,mail,tel,grade FROM member WHERE member_ID = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = obtainConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, memberID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String pw = rs.getString("PASSWORD");
				if (pw.equals(password)) {
					result.setMemberName(rs.getString("member_name"));
					result.setAddress(rs.getString("address"));
					result.setTel(rs.getString("tel"));
					result.setEmail(rs.getString("mail"));
					result.setGrade(rs.getInt("GRADE"));
					result.setAge(rs.getInt("age"));
					result.setLoginCheck(MovieUtil.VALID_MEMBER);
				} else {
					result.setLoginCheck(MovieUtil.INVALID_PASSWORD);
				}
			} else {
				result.setLoginCheck(MovieUtil.INVALID_ID);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException("DB문제 발생" + e.getMessage());

			}
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException("DB문제 발생" + e.getMessage());
			}
		}
		return result;
	}

	public void updateMemberGrade(String memberID, int grade) {
		// TODO Auto-generated method stub
		query = "UPDATE member SET GRADE = ? WHERE MEMBER_ID = ?";
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = obtainConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, grade);
			pstmt.setString(2, memberID);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
