package movie.business.service;

import java.util.*;

import movie.business.exception.*;
import movie.business.domain.Board;

public interface BoardService {
	 /**
     * Ư�� ��ȣ�� �Խñ��� �д´�. ��ȸ�� �Խñ��� ��ȸ���� �����Ǿ�� �Ѵ�.
     *
     * @param num �а��� �ϴ� �Խñ��� ��ȣ
     * @return ��ȸ�� �Խñ� ������ ��� �ִ� Board ��ü
     * @throws DataNotFoundException ��ȣ�� �ش��ϴ� �Խñ��� �������� ���� ��� �߻�
     */
	public Board readBoard(int num) throws DataNotFoundException;
 
    /**
     * Ư�� ��ȣ�� �Խñ��� ������ ã�´�.
     *
     * @param num �˻��ϰ��� �ϴ� �Խñ��� ��ȣ
     * @return �˻��� �Խñ� ������ ��� �ִ� Board ��ü
     * @throws DataNotFoundException ��ȣ�� �ش��ϴ� �Խñ��� �������� ���� ��� �߻�
     */
	public Board findBoard(int num) throws DataNotFoundException;
 
    /**
     * ���ǿ� �´� ��� �Խù� ����� ��ȸ�Ѵ�.
     * 
     * @return �˻��� ��� �Խù� ������ ��� �ִ� Board �迭
     */
	public Board[] getBoardList(Map<String,Object> searchInfo);

	 /**
	  * ���ǿ� �´� ��� �Խñ� ���� ��ȸ�Ѵ�.
	  * 
	  * @return �˻��� ��� �Խñ��� ����
	  */
	public int getBoardCount(Map<String,Object> searchInfo);
 
    /**
     * ���ο� �Խñ��� ����Ѵ�.
     *
     * @param board ����� �Խñ� ������ ��� �ִ� Board ��ü
     */
	public void writeBoard(Board board);
 
    /**
     * ���� �Խñ��� �����Ѵ�.
     *
     * @param board ������ �Խñ� ������ ��� �ִ� Board ��ü
     * @throws DataNotFoundException �ش��ϴ� �Խñ��� �������� ���� ��� �߻� 
     */
	public void updateBoard(Board board) throws DataNotFoundException;

    /**
     * Ư�� ��ȣ�� �Խñ��� �����Ѵ�.
     *
     * @param num �����ϰ��� �ϴ� �Խñ��� ��ȣ
     * @throws DataNotFoundException ��ȣ�� �ش��ϴ� �Խñ��� �������� ���� ��� �߻�
     */
	public void removeBoard(int num) throws DataNotFoundException;
 
	/**
     * ���� �Խñۿ� ����� ����Ѵ�.
     *
     * @param board ����� ��� ������ ��� �ִ� Board ��ü
     * @throws DataNotFoundException ��ȣ��  ����� �� �� �Խñ��� �������� ���� ��� �߻�
     */
	public void replyBoard(Board board) throws DataNotFoundException;

}