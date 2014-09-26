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
	private Connection theCon;

	public MemberDaoImpl() {
		try {
			Context context = new InitialContext();
			context = ((Context) context.lookup("java:comp/env"));
			dataSource = (DataSource) context.lookup("jdbc/movieDB");

			obtainConnection();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			throw new RuntimeException("JNDI erroroccured." + e.getMessage());
		} catch (SQLException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
			throw new RuntimeException("Connection fail" + e.getMessage());

		}

	}

	/** Connection을 맺기 위한 Method */
	private void obtainConnection() throws SQLException {
		theCon = dataSource.getConnection();
	}

	public List<Member> selectMemberList() {
		// TODO Auto-generated method stub
		List<Member> result = new ArrayList<Member>();

		query = "SELECT * FROM MEMBER";
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = theCon.prepareStatement(query);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Member member = new Member(rs.getString(1), rs.getString(2),
						rs.getString(3), rs.getInt(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getInt(8));
				result.add(member);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("회원리스트를 조회하지 못하였습니다." + e.getMessage());
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new RuntimeException("DB문제 발생" + e.getMessage());

			}
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new RuntimeException("DB문제 발생" + e.getMessage());
			}
		}

		return result;
	}

	public int selectMemberCount() {
		// TODO Auto-generated method stub
		int result = 0;
		query = "SELECT count(member_id) FROM member";
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		try {
			pstmt = theCon.prepareStatement(query);
			rs = pstmt.executeQuery();
			if(rs.next()){
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("DB문제 발생" + e.getMessage());
		}finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new RuntimeException("DB문제 발생" + e.getMessage());

			}
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new RuntimeException("DB문제 발생" + e.getMessage());
			}
		}
		
		return result;
	}

	public void insertMember(Member member) {
		// TODO Auto-generated method stub
		query = "INSERT INTO member(member_ID,password,member_name,age,address,mail,tel,grade) VALUES (?,?,?,?,?,?,?,"+MovieUtil.GRADE_GENERAL +")";
		PreparedStatement pstmt = null;
		try {
			pstmt = theCon.prepareStatement(query);
			pstmt.setString(1,member.getMemberID());
			pstmt.setString(2,member.getPassword());
			pstmt.setString(3,member.getMemberName());
			pstmt.setInt(4,member.getAge());
			pstmt.setString(5,member.getAddress());
			pstmt.setString(6,member.getEmail());
			pstmt.setString(7,member.getTel());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
	
			try {
				if(pstmt != null){
					pstmt.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			throw new RuntimeException("DB문제 발생" + e.getMessage());
			}
		}
		

	}

	public void updateMember(Member member) {
		// TODO Auto-generated method stub
		
		query = "UPDATE member SET PASSWORD = ?, MEMBER_NAME = ?, AGE = ?, ADDRESS = ?, MAIL = ?, TEL = ? ,GRADE = ? WHERE MEMBER_ID = ?";
		PreparedStatement pstmt = null;
		try {
			pstmt = theCon.prepareStatement(query);
			pstmt.setString(1, member.getPassword());
			pstmt.setString(2, member.getMemberName());
			pstmt.setInt(3, member.getAge());
			pstmt.setString(4, member.getAddress());
			pstmt.setString(5, member.getEmail());
			pstmt.setString(6, member.getTel());
			pstmt.setInt(7, member.getGrade());
			pstmt.setString(8, member.getMemberID());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("DB문제 발생" + e.getMessage());

		}finally{
			try {
				if(pstmt != null){
					pstmt.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new RuntimeException("DB문제 발생" + e.getMessage());

			}
		}
		

	}

	public void deleteMember(String memberID) {
		query = "DELETE FROM MEMBER WHERE MEMBER_ID =?";
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		
		try {
			pstmt = theCon.prepareStatement(query);
			pstmt.setString(1, memberID);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("DB문제 발생" + e.getMessage());
		}finally{
			try {
				if(pstmt != null){
					pstmt.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new RuntimeException("DB문제 발생" + e.getMessage());

			}
		}

	}

	public boolean memberIDExists(String memberID) {
		// TODO Auto-generated method stub
		boolean result = false;
		query = "SELECT MEMBER_ID,MEMBER_NAME FROM MEMBER WHERE MEMBER_ID = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = theCon.prepareStatement(query);
			pstmt.setString(1, memberID);
			rs = pstmt.executeQuery();
			result = rs.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("DB문제 발생" + e.getMessage());
		}finally{
			try {
				if(pstmt != null){
					pstmt.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new RuntimeException("DB문제 발생" + e.getMessage());

			}
			try {
				if(rs != null){
					rs.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new RuntimeException("DB문제 발생" + e.getMessage());
				
			}
		}
		
		return result;
	}

	public Member selectMember(String memberID) {
		// TODO Auto-generated method stub
		Member result = null;
		query = "SELECT * FROM MEMBER WHERE member_id = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = theCon.prepareStatement(query);
			pstmt.setString(1, memberID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				 result = new Member(rs.getString(1), rs.getString(2),
						rs.getString(3), rs.getInt(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getInt(8));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("회원리스트를 조회하지 못하였습니다." + e.getMessage());
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new RuntimeException("DB문제 발생" + e.getMessage());

			}
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new RuntimeException("DB문제 발생" + e.getMessage());
			}
		}

		return result;
	}

}
