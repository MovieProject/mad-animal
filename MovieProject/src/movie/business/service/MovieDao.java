package movie.business.service;

import java.util.*;

import movie.business.domain.Movie;

public interface MovieDao {
	
	/**
	 * 조건에 맞는 모든 영화 목록을 조회한다.
	 * 
	 * @return 검색된 영화 목록을 담고 있는 List 객체
	 */
	public List<Movie> selectMovieList(Map<String, Object> searchInfo);
	
	/**
	 * 조건에 맞는 모든 영화 개수를 조회한다.
	 * 
	 * @return 검색된 모든 영화의 개수
	 */
	public int selectMovieCount(Map<String, Object> searchInfo);

	/**
	 * 지정된 번호에 해당하는 영화을 검색한다.
	 * 
	 * @param movieNum 검색하고자 하는 영화의 번호
	 * @return 검색된 하나의 Movie 
	 */
	public Movie selectMovie(int movieNum);

	/**
	 * 인수로 주어진 movie 객체의 정보로 새로운 영화를 등록한다.
	 * 
	 * @param movie 등록할 영화 정보를 담고 있는 Movie 객체
	 */
	public void insertMovie(Movie movie);

	/**
	 * 인수로 주어진 Movie 객체의 정보로 기존 영화를 수정한다.
	 * 
	 * @param movie 수정할 영화 정보를 담고 있는 Movie 객체
	 */
	public void updateMovie(Movie movie);
	
	/**
	 * 인수로 주어진 번호에 해당하는 영화를 삭제한다.
	 * 
	 * @param movieNum 삭제하려는 영화의 번호
	 */
	public void deleteMovie(int movieNum);

	/**
	 * 인수로 주어진 번호에 해당하는 영화가 있는지 확인한다.
	 * 
	 * @param movieNum 존재여부를 확인하려는 영화의 번호
	 * @return 해당하는 영화가 존재하면 true, 존재하지 않으면 false
	 */
	public boolean movieNumExists(int movieNum);
}
