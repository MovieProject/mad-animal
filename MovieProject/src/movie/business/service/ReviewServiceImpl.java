package movie.business.service;

import java.util.Map;

import movie.business.dao.ReviewDao;
import movie.business.domain.Review;
import movie.business.exception.DataNotFoundException;
import movie.dataaccess.ReviewDaoImpl;

public class ReviewServiceImpl implements ReviewService {

	private ReviewDao reviewDataAccess;

	public ReviewServiceImpl() {
		super();
		reviewDataAccess = new ReviewDaoImpl();
	}

	public Review[] getReviewList(Map<String, Object> searchInfo) {
		return reviewDataAccess.selectReviewList(searchInfo).toArray(
				new Review[0]);
	}

	public int getReviewCount(Map<String, Object> searchInfo) {
		return reviewDataAccess.selectReviewCount();
	}

	public void writeReview(Review review) {
		reviewDataAccess.insertReview(review);
	}

	public void updateReview(Review review) throws DataNotFoundException {
		reviewDataAccess.updateReview(review);
	}

	public void removeReview(int reviewNum) throws DataNotFoundException {
		reviewDataAccess.deleteReview(reviewNum);
	}

}
