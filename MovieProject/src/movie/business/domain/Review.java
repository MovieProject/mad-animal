package movie.business.domain;

public class Review {
	private int reviewNum;
	private int movieNum;
	private String movieTitle;
	private String writerName;
	private String writerID;
	private String contents;
	private String regDate;

	public Review() { }

	// ���� ��ȸ��
	public Review(int reviewNum, String movieTitle, String writerName,
			String contents, String regDate) {
		super();
		this.reviewNum = reviewNum;
		this.movieTitle = movieTitle;
		this.writerName = writerName;
		this.contents = contents;
		this.regDate = regDate;
	}

	public Review(int reviewNum, int movieNum, String movieTitle,
			String writerName, String writerID, String contents, String regDate) {
		super();
		this.reviewNum = reviewNum;
		this.movieNum = movieNum;
		this.movieTitle = movieTitle;
		this.writerName = writerName;
		this.writerID = writerID;
		this.contents = contents;
		this.regDate = regDate;
	}

	// ���� �����
	public Review(String movieTitle, String writerName, String writerID, String contents) {
		this.movieTitle = movieTitle;
		this.writerName = writerName;
		this.writerID = writerID;
		this.contents = contents;
	}

	public int getReviewNum() {
		return reviewNum;
	}

	public int getMovieNum() {
		return movieNum;
	}

	public String getMovieTitle() {
		return movieTitle;
	}

	public String getWriterName() {
		return writerName;
	}

	public String getWriterID() {
		return writerID;
	}

	public String getContents() {
		return contents;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setReviewNum(int reviewNum) {
		this.reviewNum = reviewNum;
	}

	public void setMovieNum(int movieNum) {
		this.movieNum = movieNum;
	}

	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}

	public void setWriterName(String writerName) {
		this.writerName = writerName;
	}

	public void setWriterID(String writerID) {
		this.writerID = writerID;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return "Review [reviewNum=" + reviewNum + ", movieNum=" + movieNum + ", movieTitle=" + movieTitle + ", writerName=" + writerName
				+ ", writerID=" + writerID + ", contents=" + contents + ", regDate=" + regDate + "]";
	}

}
