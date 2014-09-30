package movie.business.service;

import java.util.*;

import movie.business.domain.Member;
import movie.business.exception.*;

public interface MemberService {
	
	/**
     * ���ǿ� �´� ��� ȸ�� ����� ��ȸ�Ѵ�.
     * 
     * @return �˻��� ��� ȸ�� ������ ��� �ִ� Member �迭
     * @throws DataNotFoundException �ش��ϴ� �Խñ��� �������� ���� ��� �߻� 
     */
	public Member[] getMemberList(Map<String,Object> searchInfo);
	
	 /**
	  * ���ǿ� �´� ��� ȸ�� ���� ��ȸ�Ѵ�.
	  * 
	  * @return �˻��� ��� ȸ���� ����
	  */
	public int getMemberCount(Map<String,Object> searchInfo);
	
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

}
