package movie.util;

public class MovieUtil {
	/**유효한 회원임을 나타내는 상수*/
	public static final int VALID_MEMBER = 1; 
	
	/**memberID가 존재하지 않는 회원임을 나타내는 상수*/
	public static final int INVALID_ID = 0; 
	/**PASSWORD가 일치하지 않는 회원임을 나타내는 상수*/
	public static final int INVALID_PASSWORD = -1; 
	
	public static final int GRADE_ADMIN = 2;
	public static final int GRADE_WRITER = 1;
	public static final int GRADE_GENERAL = 0;

	public final static int PAGE_LIST_SIZE = 10;
	public final static int PAGE_GROUP_SIZE = 3;

	public static int getTotalPageCount(int totalBoardCount) {
		int totalPageCount = totalBoardCount / PAGE_LIST_SIZE;
		if (totalBoardCount % PAGE_LIST_SIZE != 0) {
			totalPageCount++;
		}

		return totalPageCount;
	}

	public static int getStartPageNumber(int pageNumber) {
		return (((pageNumber - 1) / PAGE_GROUP_SIZE) * PAGE_GROUP_SIZE) + 1;
	}

	public static int getEndPageNumber(int pageNumber, int totalBoardCount) {
		int totalPageCount = getTotalPageCount(totalBoardCount);
		int startPageNumber = getStartPageNumber(pageNumber);

		int endPageNumber = (startPageNumber + PAGE_GROUP_SIZE) - 1;
		if (endPageNumber > totalPageCount) {
			endPageNumber = totalPageCount;
		}

		return endPageNumber;

	}

	public static int getStartRow(int pageNumber) {
		return (pageNumber - 1) * PAGE_LIST_SIZE + 1;
	}

	public static int getEndRow(int pageNumber) {
		return pageNumber * PAGE_LIST_SIZE;
	}
}
