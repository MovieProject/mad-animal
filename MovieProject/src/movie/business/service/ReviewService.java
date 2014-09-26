package movie.business.service;

import java.util.Map;

import movie.business.domain.Review;
import movie.business.exception.DataNotFoundException;

public interface ReviewService {
	
	/**
     * ���ǿ� �´� ��� ���� ����� ��ȸ�Ѵ�.
     * 
     * @return �˻��� ��� ���� ������ ��� �ִ� Review �迭
     */
	public Review[] getReviewList(Map<String,Object> searchInfo);

	 /**
	  * ���ǿ� �´� ��� ���� ���� ��ȸ�Ѵ�.
	  * 
	  * @return �˻��� ��� ������ ����
	  */
	public int getReviewCount(Map<String,Object> searchInfo);
 
    /**
     * ���ο� ���並 ����Ѵ�.
     *
     * @param Review ����� ���� ������ ��� �ִ� Review ��ü
     */
	public void writeReview(Review review);
 
    /**
     * ���� ���並 �����Ѵ�.
     *
     * @param Review ������ ���� ������ ��� �ִ� Review ��ü
     * @throws DataNotFoundException �ش��ϴ� ���䰡 �������� ���� ��� �߻� 
     */
	public void updateReview(Review review) throws DataNotFoundException;

    /**
     * Ư�� ��ȣ�� ���並 �����Ѵ�.
     *
     * @param num �����ϰ��� �ϴ� ������ ��ȣ
     * @throws DataNotFoundException ��ȣ�� �ش��ϴ� ���䰡 �������� ���� ��� �߻�
     */
	public void removeReview(int num) throws DataNotFoundException;
}
