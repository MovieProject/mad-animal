package movie.business.service;

import java.util.Map;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MoveAction;

import movie.business.dao.MovieDao;
import movie.business.domain.Movie;
import movie.business.exception.DataDuplicatedException;
import movie.business.exception.DataNotFoundException;
import movie.dataaccess.MovieDaoImpl;

public class MovieServiceImpl implements MovieService {
	private MovieDao movieAccess;

	public MovieServiceImpl() {
		movieAccess = new MovieDaoImpl();
	}

	public Movie findMovie(int num) throws DataNotFoundException {
		if(!movieAccess.movieNumExists(num)){
			throw new DataNotFoundException("존재하지 않는 영화입니다.");
		}
		return movieAccess.selectMovie(num);
	}

	public Movie[] getMovieList(Map<String, Object> searchInfo) {
		// TODO Auto-generated method stub
		return movieAccess.selectMovieList(searchInfo).toArray(new Movie[0]);
	}

	public int getMovieCount(Map<String, Object> searchInfo) {
		// TODO Auto-generated method stub
		return movieAccess.selectMovieCount(searchInfo);
	}

	public void writeMovie(Movie movie) throws DataDuplicatedException {
		// TODO Auto-generated method stub
		if(movieAccess.movieNumExists(movie.getMovieNum())){
			throw new DataDuplicatedException();
		}
		movieAccess.insertMovie(movie);

	}

	public void updateMovie(Movie movie) throws DataNotFoundException {
		// TODO Auto-generated method stub
		if(!movieAccess.movieNumExists(movie.getMovieNum())){
			throw new DataNotFoundException();
		}
		movieAccess.updateMovie(movie);
	}

	public void removeMovie(int num) throws DataNotFoundException {
		// TODO Auto-generated method stub
		if(!movieAccess.movieNumExists(num)){
			throw new DataNotFoundException();
		}
		movieAccess.deleteMovie(num);

	}

}
