package movie.unitTest;

import static org.junit.Assert.*;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MoveAction;

import movie.business.dao.MovieDao;
import movie.business.domain.Movie;
import movie.dataaccess.MovieDaoImpl;

import org.junit.Ignore;
import org.junit.Test;

public class MovieDaoUnitTest {
	MovieDao movieAccess = new MovieDaoImpl();
	Movie movie = new Movie(8,"test","test","20140929","test","test","test");
	

	@Test
	@Ignore
	public final void testMovieDaoImpl() {
		fail("Not yet implemented");
	}

	@Test
	@Ignore
	public final void testSelectMovieList() {
		fail("Not yet implemented");
	}

	@Test
	@Ignore
	public final void testSelectMovieCount() {
		fail("Not yet implemented");
	}

	@Test
	public final void testInsertMovie() {
		movieAccess.insertMovie(movie);
		assertTrue(movieAccess.movieNumExists(10));
	}
	@Test
	public final void testMovieNumExists() {
		assertTrue(movieAccess.movieNumExists(10));
	}

	@Test
	public final void testSelectMovie() {
		Movie newMovie = movieAccess.selectMovie(8);
		assertTrue(newMovie.getMovieName().equals("test"));
	}


	@Test
	public final void testUpdateMovie() {
		movie.setMovieName("testtest");
		movieAccess.updateMovie(movie);
		Movie newMovie = movieAccess.selectMovie(8);
		assertTrue(newMovie.getMovieName().equals("testtest"));
	}

	@Test
	public final void testDeleteMovie() {
		movieAccess.deleteMovie(7);
		assertFalse(movieAccess.movieNumExists(7));
	}


}
