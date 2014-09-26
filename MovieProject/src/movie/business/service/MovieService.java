package movie.business.service;

import java.util.Map;

import movie.business.domain.Movie;
import movie.business.exception.DataDuplicatedException;
import movie.business.exception.DataNotFoundException;

public interface MovieService {
	
	/**
     * Ư�� ��ȣ�� ��ȭ�� �д´�. ��ȸ�� ��ȭ�� ��ȸ���� �����Ǿ�� �Ѵ�.
     *
     * @param num �а��� �ϴ� ��ȭ�� ��ȣ
     * @return ��ȸ�� ��ȭ ������ ��� �ִ� Movie ��ü
     * @throws DataNotFoundException ��ȣ�� �ش��ϴ� ��ȭ�� �������� ���� ��� �߻�
     */
	public Movie readMovie(int num) throws DataNotFoundException;
 
    /**
     * Ư�� ��ȣ�� ��ȭ�� ������ ã�´�.
     *
     * @param num �˻��ϰ��� �ϴ� ��ȭ�� ��ȣ
     * @return �˻��� ��ȭ ������ ��� �ִ� Movie ��ü
     * @throws DataNotFoundException ��ȣ�� �ش��ϴ� ��ȭ�� �������� ���� ��� �߻�
     */
	public Movie findMovie(int num) throws DataNotFoundException;
 
    /**
     * ���ǿ� �´� ��� ��ȭ ����� ��ȸ�Ѵ�.
     * 
     * @return �˻��� ��� ��ȭ ������ ��� �ִ� Movie �迭
     */
	public Movie[] getMovieList(Map<String,Object> searchInfo);

	 /**
	  * ���ǿ� �´� ��� ��ȭ ���� ��ȸ�Ѵ�.
	  * 
	  * @return �˻��� ��� ��ȭ�� ����
	  */
	public int getMovieCount(Map<String,Object> searchInfo);
 
    /**
     * ���ο� ��ȭ�� ����Ѵ�.
     *
     * @param Movie ����� ��ȭ ������ ��� �ִ� Movie ��ü
     * @throws DataDuplicatedException �ش��ϴ� ��ȭ�� ��ȣ�� �ߺ��� �� ��� �߻�  
     */
	public void writeMovie(Movie movie) throws DataDuplicatedException;
 
    /**
     * ���� ��ȭ�� �����Ѵ�.
     *
     * @param Movie ������ ��ȭ ������ ��� �ִ� Movie ��ü
     * @throws DataNotFoundException �ش��ϴ� ��ȭ�� �������� ���� ��� �߻� 
     */
	public void updateMovie(Movie movie) throws DataNotFoundException;

    /**
     * Ư�� ��ȣ�� ��ȭ�� �����Ѵ�.
     *
     * @param num �����ϰ��� �ϴ� ��ȭ�� ��ȣ
     * @throws DataNotFoundException ��ȣ�� �ش��ϴ� ��ȭ�� �������� ���� ��� �߻�
     */
	public void removeMovie(int num) throws DataNotFoundException;
}
