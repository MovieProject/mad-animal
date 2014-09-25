package movie.business.service;

import java.util.*;

import movie.business.domain.Movie;

public interface MovieDao {
	
	/**
	 * ���ǿ� �´� ��� ��ȭ ����� ��ȸ�Ѵ�.
	 * 
	 * @return �˻��� ��ȭ ����� ��� �ִ� List ��ü
	 */
	public List<Movie> selectMovieList(Map<String, Object> searchInfo);
	
	/**
	 * ���ǿ� �´� ��� ��ȭ ������ ��ȸ�Ѵ�.
	 * 
	 * @return �˻��� ��� ��ȭ�� ����
	 */
	public int selectMovieCount(Map<String, Object> searchInfo);

	/**
	 * ������ ��ȣ�� �ش��ϴ� ��ȭ�� �˻��Ѵ�.
	 * 
	 * @param movieNum �˻��ϰ��� �ϴ� ��ȭ�� ��ȣ
	 * @return �˻��� �ϳ��� Movie 
	 */
	public Movie selectMovie(int movieNum);

	/**
	 * �μ��� �־��� movie ��ü�� ������ ���ο� ��ȭ�� ����Ѵ�.
	 * 
	 * @param movie ����� ��ȭ ������ ��� �ִ� Movie ��ü
	 */
	public void insertMovie(Movie movie);

	/**
	 * �μ��� �־��� Movie ��ü�� ������ ���� ��ȭ�� �����Ѵ�.
	 * 
	 * @param movie ������ ��ȭ ������ ��� �ִ� Movie ��ü
	 */
	public void updateMovie(Movie movie);
	
	/**
	 * �μ��� �־��� ��ȣ�� �ش��ϴ� ��ȭ�� �����Ѵ�.
	 * 
	 * @param movieNum �����Ϸ��� ��ȭ�� ��ȣ
	 */
	public void deleteMovie(int movieNum);

	/**
	 * �μ��� �־��� ��ȣ�� �ش��ϴ� ��ȭ�� �ִ��� Ȯ���Ѵ�.
	 * 
	 * @param movieNum ���翩�θ� Ȯ���Ϸ��� ��ȭ�� ��ȣ
	 * @return �ش��ϴ� ��ȭ�� �����ϸ� true, �������� ������ false
	 */
	public boolean movieNumExists(int movieNum);
}
