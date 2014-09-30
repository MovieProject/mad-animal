package movie.business.service;

import movie.business.domain.Member;
import movie.business.exception.*;

public interface MemberService {
	
	/**
     * 조건에 맞는 모든 회원 목록을 조회한다.
     * 
     * @return 검색된 모든 회원 정보를 담고 있는 Member 배열
     * @throws DataNotFoundException 해당하는 게시글이 존재하지 않을 경우 발생 
     */
	public Member[] getMemberList();
	
	 /**
	  * 조건에 맞는 모든 회원 수를 조회한다.
	  * 
	  * @return 검색된 모든 회원의 개수
	  */
	public int getMemberCount();
	
	 /**
     * 새로운 회원을 등록한다.
     *
     * @param member 등록할 회원 정보를 담고 있는 Member 객체
     * @throws DataDuplicatedException 해당하는 회원의 memberID가 중복이 될 경우 발생  
     */
	public void writeMember(Member member) throws DataDuplicatedException;
 
    /**
     * 기존 회원을 수정한다.
     *
     * @param member 수정할 회원 정보를 담고 있는 Member 객체
     * @throws DataNotFoundException 해당하는 게시글이 존재하지 않을 경우 발생 
     */
	public void updateMember(Member member) throws DataNotFoundException;
	/**
	 * 기존 회원을 수정한다.
	 *
	 * @param member 수정할 회원 정보를 담고 있는 Member 객체
	 * @throws DataNotFoundException 해당하는 게시글이 존재하지 않을 경우 발생 
	 */
	public void updateMemberGrade(String memberID,int grade) throws DataNotFoundException;

    /**
     * 특정 memberID의 회원을 삭제한다.
     *
     * @param memberID 삭제하고자 하는 회원의 ID
     * @throws DataNotFoundException memberID에 해당하는 회원이 존재하지 않을 경우 발생
     */
	public void removeMember(String memberID) throws DataNotFoundException;

    /**
     * 특정 memberID의 회원을 선택한다.
     *
     * @param memberID 찾고자 하는 회원의 ID
     * @throws DataNotFoundException memberID에 해당하는 회원이 존재하지 않을 경우 발생
     */
	public Member getMember(String memberID) throws DataNotFoundException;
    /**
     * 특정 memberID이 로그인 가능한 회원인지를 검사.
     *
     * @param memberID 찾고자 하는 회원의 ID
     * @param password 찾고자 하는 회원의 password
     * @throws DataNotFoundException memberID에 해당하는 회원이 존재하지 않을 경우 발생
     */
	public Member loginCheck(String memberID,String password);

}
