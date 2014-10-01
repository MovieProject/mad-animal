package movie.business.dao;

import java.util.*;

import movie.business.domain.Board;

public interface BoardDao {
/**������ �ȵʿ�
 * */
	/**
	 * ���ǿ� �´� ��� �Խù� ����� ��ȸ�Ѵ�.
	 * 
	 * @return �˻��� �Խù� ����� ��� �ִ� List ��ü
	 */
	public List<Board> selectBoardList(Map<String, Object> searchInfo);

	/**
	 * ���ǿ� �´� ��� �Խñ� ������ ��ȸ�Ѵ�.
	 * 
	 * @return �˻��� ��� �Խñ��� ����
	 */
	public int selectBoardCount(Map<String, Object> searchInfo);

	/**
	 * ������ ��ȣ�� �ش��ϴ� �Խñ��� �˻��Ѵ�.
	 * 
	 * @param boardNum �˻��ϰ��� �ϴ� �Խñ��� ��ȣ
	 * @return
	 */
	public Board selectBoard(int boardNum);

	/**
	 * ������ ��ȣ�� �ش��ϴ� �Խñ��� ��ȸ���� ������Ų��.
	 * 
	 * @param boardNum ��ȸ���� ������ų �Խñ��� ��ȣ
	 */
	public void addReadCount(int boardNum);

	/**
	 * �μ��� �־��� ��ȣ�� �ش��ϴ� �Խñ��� �ִ��� Ȯ���Ѵ�.
	 * 
	 * @param boardNum ���翩�θ� Ȯ���Ϸ��� �Խñ��� ��ȣ
	 * @return �ش��ϴ� �Խñ��� �����ϸ� true, �������� ������ false
	 */
	public boolean boardNumExists(int boardNum);

	/**
	 * �μ��� �־��� Board ��ü�� ������ ���ο� �Խñ��� ����Ѵ�.
	 * 
	 * @param board ����� �Խñ� ������ ��� �ִ� Board ��ü
	 */
	public void insertBoard(Board board);

	/**
	 * �μ��� �־��� Board ��ü�� ������ ���� �Խñ��� �����Ѵ�.
	 * 
	 * @param board ������ �Խñ� ������ ��� �ִ� Board ��ü
	 */
	public void updateBoard(Board board);

	/**
	 * �μ��� �־��� ��ȣ�� �ش��ϴ� �Խñ��� �����Ѵ�.
	 * 
	 * @param boardNum �����Ϸ��� �Խñ��� ��ȣ
	 */
	public void deleteBoard(int boardNum);

	/**
	 * �μ��� �־��� Board ��ü�� ������ ���ο� ����� ����Ѵ�.
	 * 
	 * @param board ����� ��� ������ ��� �ִ� Board ��ü
	 */
	public void insertReplyBoard(Board board);

}
