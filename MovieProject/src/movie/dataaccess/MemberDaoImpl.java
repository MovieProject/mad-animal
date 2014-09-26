package movie.dataaccess;

import java.sql.*;
import java.util.*;

import javax.naming.*;
import javax.sql.*;

import movie.business.domain.Member;
import movie.business.service.MemberDao;

public class MemberDaoImpl implements MemberDao {
	private DataSource dataSource;
	public MemberDaoImpl(){
		try {
			Context context = new InitialContext();
			context = ((Context) context.lookup("java:comp/env"));
			dataSource = (DataSource) context.lookup("jdbc/movieDB");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			throw new RuntimeException("JNDI erroroccured." + e.getMessage());
		}
		 
	}
	/**Connection¿ª ∏Œ±‚ ¿ß«— Method*/
	private Connection obtainConnection() throws SQLException{
		return dataSource.getConnection();
	}

	public List<Member> selectMemberList(Map<String, Object> searchInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	public int selectMemberCount(Map<String, Object> searchInfo) {
		// TODO Auto-generated method stub
		return 0;
	}

	public void insertMember(Member member) {
		// TODO Auto-generated method stub

	}

	public void updateMember(String memberID) {
		// TODO Auto-generated method stub

	}

	public void deleteMember(String memberID) {
		// TODO Auto-generated method stub

	}

	public boolean memberIDExists(String memberID) {
		// TODO Auto-generated method stub
		return false;
	}
	public Member selectMember(String memberID) {
		// TODO Auto-generated method stub
		return null;
	}

}
