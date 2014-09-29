package movie.business.dao;

import java.util.*;

import movie.business.domain.Review;

public interface ReviewDao {
	
	/**
	 * ���ǿ� �´� ���review ����� ��ȸ�Ѵ�.
	 * 
	 * @return �˻��� review ����� ��� �ִ� List ��ü
	 */
	public List<Review> selectReviewList(Map<String, Object> searchInfo);
	
	/**
	 * ���ǿ� �´� ��� review ������ ��ȸ�Ѵ�.
	 * 
	 * @return �˻��� ��� review�� ����
	 */
	public int selectReviewCount();
	
	/**
	 * �μ��� �־��� review ��ü�� ������ ���ο� review�� ����Ѵ�.
	 * 
	 * @param review ����� ��ȭ ������ ��� �ִ� Review ��ü
	 */
	public void insertReview(Review review);

	/**
	 * �μ��� �־��� Review ��ü�� ������ ���� review�� �����Ѵ�.
	 * 
	 * @param review ������ review ������ ��� �ִ� Review ��ü
	 */
	public void updateReview(Review review);
	
	/**
	 * �μ��� �־��� ��ȣ�� �ش��ϴ� review�� �����Ѵ�.
	 * 
	 * @param reviewNum �����Ϸ��� review�� ��ȣ
	 */
	public void deleteReview(int reviewNum);

}
