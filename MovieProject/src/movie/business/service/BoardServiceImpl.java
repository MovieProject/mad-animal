package movie.business.service;

import java.util.Map;

import movie.business.dao.BoardDao;
import movie.business.domain.Board;
import movie.business.exception.DataNotFoundException;
import movie.dataaccess.BoardDaoImpl;

public class BoardServiceImpl implements BoardService {

	private BoardDao boardDataAccess;

	public BoardServiceImpl() {
		super();
		this.boardDataAccess = new BoardDaoImpl();
	}

	public Board readBoard(int boardNum) throws DataNotFoundException {
		if (!boardDataAccess.boardNumExists(boardNum)) {
			new DataNotFoundException("Data is not Exists");
		}
		boardDataAccess.addReadCount(boardNum);
		Board result = boardDataAccess.selectBoard(boardNum);
		return result;
	}

	public Board findBoard(int boardNum) throws DataNotFoundException {
		if (!boardDataAccess.boardNumExists(boardNum)) {
			new DataNotFoundException("Data is not Exists");
		}
		Board result = boardDataAccess.selectBoard(boardNum);
		return result;
	}

	public Board[] getBoardList(Map<String, Object> searchInfo) {
		return boardDataAccess.selectBoardList(searchInfo)
				.toArray(new Board[0]);
	}

	public int getBoardCount(Map<String, Object> searchInfo) {
		return boardDataAccess.selectBoardCount(searchInfo);
	}

	public void writeBoard(Board board) {
		boardDataAccess.insertBoard(board);
	}

	public void updateBoard(Board board) throws DataNotFoundException {
		if (!boardDataAccess.boardNumExists(board.getBoardNum())) {
			new DataNotFoundException("Data is not Exists");
		}
		boardDataAccess.updateBoard(board);
	}

	public void removeBoard(int boardNum) throws DataNotFoundException {
		if (!boardDataAccess.boardNumExists(boardNum)) {
			new DataNotFoundException("Data is not Exists");
		}
		boardDataAccess.deleteBoard(boardNum);
	}

	public void replyBoard(Board board) throws DataNotFoundException {
		if (!boardDataAccess.boardNumExists(board.getBoardNum())) {
			new DataNotFoundException("Data is not Exists");
		}
		boardDataAccess.insertReplyBoard(board);
	}

}
