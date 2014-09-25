package movie.dataaccess;

import java.util.List;
import java.util.Map;

import movie.business.domain.Member;
import movie.business.service.MemberDao;

public class MemberDaoImpl implements MemberDao {

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

}
