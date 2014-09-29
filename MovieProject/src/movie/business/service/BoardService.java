package movie.business.service;

import java.util.*;

import movie.business.exception.*;
import movie.business.domain.Board;

public interface BoardService {

	/**
     * 특정 번호의 게시글을 읽는다. 조회된 게시글의 조회수는 증가되어야 한다.
     *
     * @param boardNum 읽고자 하는 게시글의 번호
     * @return 조회된 게시글 정보를 담고 있는 Board 객체
     * @throws DataNotFoundException 번호에 해당하는 게시글이 존재하지 않을 경우 발생
     */
	public Board readBoard(int boardNum) throws DataNotFoundException;
 
    /**
     * 특정 번호의 게시글의 내용을 찾는다.
     *
     * @param boardNum 검색하고자 하는 게시글의 번호
     * @return 검색된 게시글 정보를 담고 있는 Board 객체
     * @throws DataNotFoundException 번호에 해당하는 게시글이 존재하지 않을 경우 발생
     */
	public Board findBoard(int boardNum) throws DataNotFoundException;
 
    /**
     * 조건에 맞는 모든 게시물 목록을 조회한다.
     * 
     * @return 검색된 모든 게시물 정보를 담고 있는 Board 배열
     */
	public Board[] getBoardList(Map<String,Object> searchInfo);

	 /**
	  * 조건에 맞는 모든 게시글 수를 조회한다.
	  * 
	  * @return 검색된 모든 게시글의 개수
	  */
	public int getBoardCount(Map<String,Object> searchInfo);
 
    /**
     * 새로운 게시글을 등록한다.
     *
     * @param board 등록할 게시글 정보를 담고 있는 Board 객체
     */
	public void writeBoard(Board board);
 
    /**
     * 기존 게시글을 수정한다.
     *
     * @param board 수정할 게시글 정보를 담고 있는 Board 객체
     * @throws DataNotFoundException 해당하는 게시글이 존재하지 않을 경우 발생 
     */
	public void updateBoard(Board board) throws DataNotFoundException;

    /**
     * 특정 번호의 게시글을 삭제한다.
     *
     * @param boardNum 삭제하고자 하는 게시글의 번호
     * @throws DataNotFoundException 번호에 해당하는 게시글이 존재하지 않을 경우 발생
     */
	public void removeBoard(int boardNum) throws DataNotFoundException;
 
	/**
     * 기존 게시글에 답글을 등록한다.
     *
     * @param board 등록할 답글 정보를 담고 있는 Board 객체
     * @throws DataNotFoundException 번호에  답글을 달 원 게시글이 존재하지 않을 경우 발생
     */
	public void replyBoard(Board board) throws DataNotFoundException;

}
