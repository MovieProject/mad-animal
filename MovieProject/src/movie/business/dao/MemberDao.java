package movie.business.dao;

import java.util.*;

import movie.business.domain.Member;

public interface MemberDao {

	/**
	 * ���ǿ� �´� ��� ȸ�� ����� ��ȸ�Ѵ�.
	 * 
	 * @return �˻��� ȸ�� ����� ��� �ִ� List ��ü
	 */
	public List<Member> selectMemberList();
	
	/**
	 * ���ǿ� �´� ��� ȸ�� ���� ��ȸ�Ѵ�.
	 * 
	 * @return �˻��� ��� ȸ���� ��
	 */
	public int selectMemberCount();
	
	/**
	 * �μ��� �־��� Member ��ü�� ������ ���ο� ȸ���� ����Ѵ�.
	 * 
	 * @param member ����� ȸ�� ������ ��� �ִ� Member ��ü
	 */
	public void insertMember(Member member);
	
	/**
	 * �μ��� �־��� Member ��ü�� ������ ���� ȸ���� �����Ѵ�.
	 * 
	 * @param member ������ ȸ�� ������ ��� �ִ� Member ��ü
	 */
	public void updateMember(Member member);
	
	/**
	 * �μ��� �־��� ��ȣ�� �ش��ϴ� ȸ���� �����Ѵ�.
	 * 
	 * @param memberID �����Ϸ��� ȸ���� ID
	 */
	public void deleteMember(String memberID);
	
	/**
	 * �μ��� �־��� ID�� �ش��ϴ� ȸ���� �ִ��� Ȯ���Ѵ�.
	 * 
	 * @param memberID ���翩�θ� Ȯ���Ϸ��� ȸ���� ID
	 * @return �ش��ϴ� ȸ���� �����ϸ� true, �������� ������ false
	 */
	public boolean memberIDExists(String memberID);


	/**
	 * �μ��� �־��� ID�� �ش��ϴ� ȸ���� �����Ѵ�.
	 * 
	 * @param memberID ���翩�θ� Ȯ���Ϸ��� ȸ���� ID
	 * @return ȸ������
	 */
	public Member selectMember(String memberID);
	/**
	 * ��ȿ�� ȸ�������� Ȯ���Ѵ�.
	 * 
	 * @param memberID ���翩�θ� Ȯ���Ϸ��� ȸ���� ID
	 * @param password ���翩�θ� Ȯ���Ϸ��� ȸ���� password
	 * @return ȸ������
	 */
	public Member checkMember(String memberID,String password);
}
