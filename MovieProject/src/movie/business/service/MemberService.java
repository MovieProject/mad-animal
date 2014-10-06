package movie.business.service;

import movie.business.domain.Member;
import movie.business.exception.*;

public interface MemberService {
	
	/**
     * ���ǿ� �´� ��� ȸ�� ����� ��ȸ�Ѵ�.
     * 
     * @return �˻��� ��� ȸ�� ������ ��� �ִ� Member �迭
     * @throws DataNotFoundException �ش��ϴ� �Խñ��� �������� ���� ��� �߻� 
     */
	public Member[] getMemberList();
	
	 /**
	  * ���ǿ� �´� ��� ȸ�� ���� ��ȸ�Ѵ�.
	  * 
	  * @return �˻��� ��� ȸ���� ����
	  */
	public int getMemberCount();
	
	 /**
     * ���ο� ȸ���� ����Ѵ�.
     *
     * @param member ����� ȸ�� ������ ��� �ִ� Member ��ü
     * @throws DataDuplicatedException �ش��ϴ� ȸ���� memberID�� �ߺ��� �� ��� �߻�  
     */
	public void writeMember(Member member) throws DataDuplicatedException;
 
    /**
     * ���� ȸ���� �����Ѵ�.
     *
     * @param member ������ ȸ�� ������ ��� �ִ� Member ��ü
     * @throws DataNotFoundException �ش��ϴ� �Խñ��� �������� ���� ��� �߻� 
     */
	public void updateMember(Member member) throws DataNotFoundException;
	/**
	 * ���� ȸ���� �����Ѵ�.
	 *
	 * @param member ������ ȸ�� ������ ��� �ִ� Member ��ü
	 * @throws DataNotFoundException �ش��ϴ� �Խñ��� �������� ���� ��� �߻� 
	 */
	public void updateMemberGrade(String memberID,int grade) throws DataNotFoundException;

    /**
     * Ư�� memberID�� ȸ���� �����Ѵ�.
     *
     * @param memberID �����ϰ��� �ϴ� ȸ���� ID
     * @throws DataNotFoundException memberID�� �ش��ϴ� ȸ���� �������� ���� ��� �߻�
     */
	public void removeMember(String memberID) throws DataNotFoundException;

    /**
     * Ư�� memberID�� ȸ���� �����Ѵ�.
     *
     * @param memberID ã���� �ϴ� ȸ���� ID
     * @throws DataNotFoundException memberID�� �ش��ϴ� ȸ���� �������� ���� ��� �߻�
     */
	public Member getMember(String memberID) throws DataNotFoundException;
    /**
     * Ư�� memberID�� �α��� ������ ȸ�������� �˻�.
     *
     * @param memberID ã���� �ϴ� ȸ���� ID
     * @param password ã���� �ϴ� ȸ���� password
     * @throws DataNotFoundException memberID�� �ش��ϴ� ȸ���� �������� ���� ��� �߻�
     */
	public Member loginCheck(String memberID,String password);

}
