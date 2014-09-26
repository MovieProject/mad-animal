package movie.business.service;

import java.util.Map;

import movie.business.domain.Movie;
import movie.business.exception.DataDuplicatedException;
import movie.business.exception.DataNotFoundException;

public interface MovieService {
	
	/**
     * 특정 번호의 영화을 읽는다. 조회된 영화의 조회수는 증가되어야 한다.
     *
     * @param num 읽고자 하는 영화의 번호
     * @return 조회된 영화 정보를 담고 있는 Movie 객체
     * @throws DataNotFoundException 번호에 해당하는 영화가 존재하지 않을 경우 발생
     */
	public Movie readMovie(int num) throws DataNotFoundException;
 
    /**
     * 특정 번호의 영화의 내용을 찾는다.
     *
     * @param num 검색하고자 하는 영화의 번호
     * @return 검색된 영화 정보를 담고 있는 Movie 객체
     * @throws DataNotFoundException 번호에 해당하는 영화가 존재하지 않을 경우 발생
     */
	public Movie findMovie(int num) throws DataNotFoundException;
 
    /**
     * 조건에 맞는 모든 영화 목록을 조회한다.
     * 
     * @return 검색된 모든 영화 정보를 담고 있는 Movie 배열
     */
	public Movie[] getMovieList(Map<String,Object> searchInfo);

	 /**
	  * 조건에 맞는 모든 영화 수를 조회한다.
	  * 
	  * @return 검색된 모든 영화의 개수
	  */
	public int getMovieCount(Map<String,Object> searchInfo);
 
    /**
     * 새로운 영화를 등록한다.
     *
     * @param Movie 등록할 영화 정보를 담고 있는 Movie 객체
     * @throws DataDuplicatedException 해당하는 영화의 번호가 중복이 될 경우 발생  
     */
	public void writeMovie(Movie movie) throws DataDuplicatedException;
 
    /**
     * 기존 영화를 수정한다.
     *
     * @param Movie 수정할 영화 정보를 담고 있는 Movie 객체
     * @throws DataNotFoundException 해당하는 영화가 존재하지 않을 경우 발생 
     */
	public void updateMovie(Movie movie) throws DataNotFoundException;

    /**
     * 특정 번호의 영화을 삭제한다.
     *
     * @param num 삭제하고자 하는 영화의 번호
     * @throws DataNotFoundException 번호에 해당하는 영화가 존재하지 않을 경우 발생
     */
	public void removeMovie(int num) throws DataNotFoundException;
}
