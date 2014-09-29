package movie.dataaccess;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import movie.business.dao.MovieDao;
import movie.business.domain.Movie;

public class MovieDaoImpl implements MovieDao {
	private DataSource dataSource;
	private String query;

	public MovieDaoImpl() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");

			/*
			 * Context context = new InitialContext(); context = ((Context)
			 * context.lookup("java:comp/env")); dataSource = (DataSource)
			 * context.lookup("jdbc/movieDB");
			 */

			// catch (NamingException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			//
			// throw new RuntimeException("JNDI erroroccured." +
			// e.getMessage());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private Connection obtainConnection() throws SQLException {
		// return dataSource.getConnection();
		return DriverManager.getConnection(
				"jdbc:oracle:thin:@220.67.115.225:1521:xe", "movie", "1234");
	}

	public List<Movie> selectMovieList(Map<String, Object> searchInfo) {
		List<Movie> result = new ArrayList<Movie>();
		Movie movie = null;
		query = "Select * from Movie";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = obtainConnection();
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				movie = new Movie(rs.getInt(1), rs.getString(2),
						rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7));
				result.add(movie);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		// TODO Auto-generated method stub
		return result;
	}

	public int selectMovieCount(Map<String, Object> searchInfo) {
		// TODO Auto-generated method stub
		query = "SELECT count(movie_num) FROM Movie";
		int result = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = obtainConnection();
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return result;
	}

	public Movie selectMovie(int movieNum) {
		Movie result = null;
		query = "SELECT * FROM MOVIE WHERE movie_num = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = obtainConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, movieNum);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = new Movie(rs.getInt(1), rs.getString(2),
						rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		// TODO Auto-generated method stub
		return result;
	}

	public void insertMovie(Movie movie) {
		// TODO Auto-generated method stub
		query = "INSERT INTO movie(movie_num,movie_name,genre,release_date,director,syonpsis,photodir) VALUES(MOVIE_SEQUENCE.NEXTVAL,?,?,?,?,?,?)";
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = obtainConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, movie.getMovieName());
			pstmt.setString(2, movie.getGenre());
			pstmt.setString(3, movie.getReleaseDate());
			pstmt.setString(4, movie.getdirector());
			pstmt.setString(5, movie.getSynopsis());
			pstmt.setString(6, movie.getPhotoDir());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public void updateMovie(Movie movie) {
		// TODO Auto-generated method stub
		query = "UPDATE movie SET movie_name = ?, genre = ? ,release_date = ?,director = ? ,syonpsis = ?, photodir = ? WHERE movie_num = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = obtainConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, movie.getMovieName());
			pstmt.setString(2, movie.getGenre());
			pstmt.setString(3, movie.getReleaseDate());
			pstmt.setString(4, movie.getdirector());
			pstmt.setString(5, movie.getSynopsis());
			pstmt.setString(6, movie.getPhotoDir());
			pstmt.setInt(7, movie.getMovieNum());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public void deleteMovie(int movieNum) {
		// TODO Auto-generated method stub
		query = "DELETE FROM Movie WHERE movie_num = ?";
		Connection con =null;
		PreparedStatement pstmt = null;
		try {
			con = obtainConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, movieNum);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

	public boolean movieNumExists(int movieNum) {
		boolean result = false;
		query = "SELECT movie_name FROM MOVIE WHERE movie_num = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = obtainConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, movieNum);
			rs = pstmt.executeQuery();
			result = rs.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		// TODO Auto-generated method stub
		return result;
	}

}
