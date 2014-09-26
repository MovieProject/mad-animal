package movie.business.dao;

import java.util.*;

import movie.business.domain.Member;

public interface MemberDao {

	/**
	 * 조건에 맞는 모든 회원 목록을 조회한다.
	 * 
	 * @return 검색된 회원 목록을 담고 있는 List 객체
	 */
	public List<Member> selectMemberList(Map<String, Object> searchInfo);
	
	/**
	 * 조건에 맞는 모든 회원 수를 조회한다.
	 * 
	 * @return 검색된 모든 회원의 수
	 */
	public int selectMemberCount(Map<String, Object> searchInfo);
	
	/**
	 * 인수로 주어진 Member 객체의 정보로 새로운 회원을 등록한다.
	 * 
	 * @param member 등록할 회원 정보를 담고 있는 Member 객체
	 */
	public void insertMember(Member member);
	
	/**
	 * 인수로 주어진 Member 객체의 정보로 기존 회원을 수정한다.
	 * 
	 * @param member 수정할 회원 정보를 담고 있는 Member 객체
	 */
	public void updateMember(Member member);
	
	/**
	 * 인수로 주어진 번호에 해당하는 회원을 삭제한다.
	 * 
	 * @param memberID 삭제하려는 회원의 ID
	 */
	public void deleteMember(String memberID);
	
	/**
	 * 인수로 주어진 ID에 해당하는 회원이 있는지 확인한다.
	 * 
	 * @param memberID 존재여부를 확인하려는 회원의 ID
	 * @return 해당하는 회원이 존재하면 true, 존재하지 않으면 false
	 */
	public boolean memberIDExists(String memberID);

}
