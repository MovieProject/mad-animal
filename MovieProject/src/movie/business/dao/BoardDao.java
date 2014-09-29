package movie.business.dao;

import java.util.*;

import movie.business.domain.Board;

public interface BoardDao {
/**수정이 안됨요
 * */
	/**
	 * 조건에 맞는 모든 게시물 목록을 조회한다.
	 * 
	 * @return 검색된 게시물 목록을 담고 있는 List 객체
	 */
	public List<Board> selectBoardList(Map<String, Object> searchInfo);

	/**
	 * 조건에 맞는 모든 게시글 개수를 조회한다.
	 * 
	 * @return 검색된 모든 게시글의 개수
	 */
	public int selectBoardCount(Map<String, Object> searchInfo);

	/**
	 * 지정된 번호에 해당하는 게시글을 검색한다.
	 * 
	 * @param boardNum 검색하고자 하는 게시글의 번호
	 * @return
	 */
	public Board selectBoard(int boardNum);

	/**
	 * 지정된 번호에 해당하는 게시글의 조회수를 증가시킨다.
	 * 
	 * @param boardNum 조회수를 증가시킬 게시글의 번호
	 */
	public void addReadCount(int boardNum);

	/**
	 * 인수로 주어진 번호에 해당하는 게시글이 있는지 확인한다.
	 * 
	 * @param boardNum 존재여부를 확인하려는 게시글의 번호
	 * @return 해당하는 게시글이 존재하면 true, 존재하지 않으면 false
	 */
	public boolean boardNumExists(int boardNum);

	/**
	 * 인수로 주어진 Board 객체의 정보로 새로운 게시글을 등록한다.
	 * 
	 * @param board 등록할 게시글 정보를 담고 있는 Board 객체
	 */
	public void insertBoard(Board board);

	/**
	 * 인수로 주어진 Board 객체의 정보로 기존 게시글을 수정한다.
	 * 
	 * @param board 수정할 게시글 정보를 담고 있는 Board 객체
	 */
	public void updateBoard(Board board);

	/**
	 * 인수로 주어진 번호에 해당하는 게시글을 삭제한다.
	 * 
	 * @param boardNum 삭제하려는 게시글의 번호
	 */
	public void deleteBoard(int boardNum);

	/**
	 * 인수로 주어진 Board 객체의 정보로 새로운 답글을 등록한다.
	 * 
	 * @param board 등록할 답글 정보를 담고 있는 Board 객체
	 */
	public void insertReplyBoard(Board board);

}
