package movie.business.dao;

import java.util.*;

import movie.business.domain.Review;

public interface ReviewDao {
	
	/**
	 * 조건에 맞는 모든review 목록을 조회한다.
	 * 
	 * @return 검색된 review 목록을 담고 있는 List 객체
	 */
	public List<Review> selectReviewList(Map<String, Object> searchInfo);
	
	/**
	 * 조건에 맞는 모든 review 개수를 조회한다.
	 * 
	 * @return 검색된 모든 review의 개수
	 */
	public int selectReviewCount();
	
	/**
	 * 인수로 주어진 review 객체의 정보로 새로운 review를 등록한다.
	 * 
	 * @param review 등록할 영화 정보를 담고 있는 Review 객체
	 */
	public void insertReview(Review review);

	/**
	 * 인수로 주어진 Review 객체의 정보로 기존 review를 수정한다.
	 * 
	 * @param review 수정할 review 정보를 담고 있는 Review 객체
	 */
	public void updateReview(Review review);
	
	/**
	 * 인수로 주어진 번호에 해당하는 review를 삭제한다.
	 * 
	 * @param reviewNum 삭제하려는 review의 번호
	 */
	public void deleteReview(int reviewNum);

}
