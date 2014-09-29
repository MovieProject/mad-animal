package movie.business.service;

import java.util.Map;

import movie.business.domain.Review;
import movie.business.exception.DataNotFoundException;

public interface ReviewService {
	
	/**
     * 조건에 맞는 모든 리뷰 목록을 조회한다.
     * 
     * @return 검색된 모든 리뷰 정보를 담고 있는 Review 배열
     */
	public Review[] getReviewList(Map<String,Object> searchInfo);

	 /**
	  * 조건에 맞는 모든 리뷰 수를 조회한다.
	  * 
	  * @return 검색된 모든 리뷰의 개수
	  */
	public int getReviewCount(Map<String,Object> searchInfo);
 
    /**
     * 새로운 리뷰를 등록한다.
     *
     * @param Review 등록할 리뷰 정보를 담고 있는 Review 객체
     */
	public void writeReview(Review review);
 
    /**
     * 기존 리뷰를 수정한다.
     *
     * @param Review 수정할 리뷰 정보를 담고 있는 Review 객체
     * @throws DataNotFoundException 해당하는 리뷰가 존재하지 않을 경우 발생 
     */
	public void updateReview(Review review) throws DataNotFoundException;

    /**
     * 특정 번호의 리뷰를 삭제한다.
     *
     * @param num 삭제하고자 하는 리뷰의 번호
     * @throws DataNotFoundException 번호에 해당하는 리뷰가 존재하지 않을 경우 발생
     */
	public void removeReview(int num) throws DataNotFoundException;
}
