package movie.business.domain;

public class Board {
	private int boardNum;
	private String title;
	private String writerName;
	private String contents;
	private String writerID;
	private int readCount;
	private String regDate;
	private String modDate;
	private int masterNum;
	private int replyOrder;
	private int replyStep;

	public Board() {

	}

	public Board(int boardNum, String title, String writerName,
			String contents, String writerID, int readCount, String regDate,
			String modDate, int masterNum, int replyOrder, int replyStep) {
		super();
		this.boardNum = boardNum;
		this.title = title;
		this.writerName = writerName;
		this.contents = contents;
		this.writerID = writerID;
		this.readCount = readCount;
		this.regDate = regDate;
		this.modDate = modDate;
		this.masterNum = masterNum;
		this.replyOrder = replyOrder;
		this.replyStep = replyStep;
	}

	public int getBoardNum() {
		return boardNum;
	}

	public String getTitle() {
		return title;
	}

	public String getWriterName() {
		return writerName;
	}

	public String getContents() {
		return contents;
	}

	public String getWriterID() {
		return writerID;
	}

	public int getReadCount() {
		return readCount;
	}

	public String getRegDate() {
		return regDate;
	}

	public String getModDate() {
		return modDate;
	}

	public int getMasterNum() {
		return masterNum;
	}

	public int getReplyOrder() {
		return replyOrder;
	}

	public int getReplyStep() {
		return replyStep;
	}

	public void setBoardNum(int boardNum) {
		this.boardNum = boardNum;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setWriterName(String writerName) {
		this.writerName = writerName;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public void setWriterID(String writerID) {
		this.writerID = writerID;
	}

	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public void setModDate(String modDate) {
		this.modDate = modDate;
	}

	public void setMasterNum(int masterNum) {
		this.masterNum = masterNum;
	}

	public void setReplyOrder(int replyOrder) {
		this.replyOrder = replyOrder;
	}

	public void setReplyStep(int replyStep) {
		this.replyStep = replyStep;
	}

	@Override
	public String toString() {
		return "Board [boardNum=" + boardNum + ", title=" + title
				+ ", writerName=" + writerName + ", contents=" + contents
				+ ", writerID=" + writerID + ", readCount=" + readCount
				+ ", regDate=" + regDate + ", modDate=" + modDate
				+ ", masterNum=" + masterNum + ", replyOrder=" + replyOrder
				+ ", replyStep=" + replyStep + "]";
	}

}
