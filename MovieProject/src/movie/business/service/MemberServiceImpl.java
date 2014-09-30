package movie.business.service;

import java.util.Map;

import movie.business.dao.MemberDao;
import movie.business.domain.Member;
import movie.business.exception.DataDuplicatedException;
import movie.business.exception.DataNotFoundException;
import movie.dataaccess.MemberDaoImpl;

public class MemberServiceImpl implements MemberService {
	private MemberDao memberAccess;
	public MemberServiceImpl(){
		memberAccess = new MemberDaoImpl();
	}
	
	
	public Member[] getMemberList() {

		return memberAccess.selectMemberList().toArray(new Member[0]);
	}

	public int getMemberCount() {
		// TODO Auto-generated method stub
		return memberAccess.selectMemberCount();
	}

	public void writeMember(Member member) throws DataDuplicatedException {
		// TODO Auto-generated method stub
		if(memberAccess.memberIDExists(member.getMemberID())){
			throw new DataDuplicatedException("존재하는 아이디입니다.");
		}
		memberAccess.insertMember(member);
		

	}

	public void updateMember(Member member) throws DataNotFoundException {
		// TODO Auto-generated method stub
		if(!memberAccess.memberIDExists(member.getMemberID())){
			throw new DataNotFoundException("존재하지 않는  아이디입니다.");
		}
		memberAccess.updateMember(member);

	}

	public void removeMember(String memberID) throws DataNotFoundException {
		// TODO Auto-generated method stub
		if(!memberAccess.memberIDExists(memberID)){
			throw new DataNotFoundException("존재하지 않는  아이디입니다.");
		}
		memberAccess.deleteMember(memberID);
	}

	public Member getMember(String memberID) throws DataNotFoundException {
		// TODO Auto-generated method stub
		if(!memberAccess.memberIDExists(memberID)){
			throw new DataNotFoundException("존재하지 않는  아이디입니다.");
		}
		return memberAccess.selectMember(memberID);
	}


	public Member loginCheck(String memberID, String password) {
		// TODO Auto-generated method stub
		return memberAccess.checkMember(memberID, password);
	}

}
