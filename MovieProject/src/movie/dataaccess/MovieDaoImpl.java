package movie.dataaccess;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.*;
import javax.sql.DataSource;

import movie.business.dao.MovieDao;
import movie.business.domain.Movie;

public class MovieDaoImpl implements MovieDao {
	private DataSource dataSource;
	private String query;

	public MovieDaoImpl() {
		try {
			// Class.forName("oracle.jdbc.OracleDriver");

			Context context = new InitialContext();
			context = ((Context) context.lookup("java:comp/env"));
			dataSource = (DataSource) context.lookup("jdbc/movieDB");

		} catch (NamingException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			//
			throw new RuntimeException("JNDI erroroccured." + e.getMessage());
		}

	}

	private Connection obtainConnection() throws SQLException {
		return dataSource.getConnection();
	}

	public List<Movie> selectMovieList(Map<String, Object> searchInfo) {
		String searchType = (String) searchInfo.get("searchType");
		String searchText = (String) searchInfo.get("searchText");

		System.out.println(searchType);
		System.out.println(searchText);
		int type = (Integer) searchInfo.get("type");
		int startRow = (Integer) searchInfo.get("startRow");
		int endRow = (Integer) searchInfo.get("endRow");

		String whereOutQuery = "";
		String whereInQuery = "WHERE type = " + type;
		if (searchType != null) {
			if (searchType.equals("all")) {
				whereOutQuery = "WHERE movie_name like ? OR director like ?";
			} else if (searchType.equals("movie_name")
					|| searchType.equals("director")) {
				whereOutQuery = "WHERE " + searchType + " LIKE ? ";

			}
		}

		if (searchType != null) {
			searchText = "%" + searchText.trim() + "%";
		}

		List<Movie> result = new ArrayList<Movie>();
		Movie movie = null;
		query = "SELECT * FROM ("
				+ " SELECT rownum r, movie_num, movie_name, director, release_date FROM ("
				+ " SELECT * FROM ( " + "SELECT * FROM movie " + whereInQuery
				+ " ) " + whereOutQuery + " ORDER BY movie_num DESC)) "
				+ "WHERE r BETWEEN ? and ? ";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = obtainConnection();
			pstmt = con.prepareStatement(query);

			if ((searchType == null) || (searchType.length() == 0)) {
				pstmt.setInt(1, startRow);
				pstmt.setInt(2, endRow);
			} else if (searchType.equals("all")) {
				pstmt.setString(1, searchText);
				pstmt.setString(2, searchText);
				pstmt.setInt(3, startRow);
				pstmt.setInt(4, endRow);
			} else if (searchType.equals("movie_name")
					|| searchType.equals("director")) {
				pstmt.setString(1, searchText);
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, endRow);
			}

			rs = pstmt.executeQuery();

			while (rs.next()) {
				String date = rs.getString("release_Date").substring(0, 10);
				movie = new Movie(rs.getInt(2), rs.getString(3),
						rs.getString(4), date, type);
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

		String searchType = (String) searchInfo.get("searchType");
		String searchText = (String) searchInfo.get("searchText");

		int type = (Integer) searchInfo.get("type");
		String whereOutQuery = "";
		String whereInQuery = "WHERE type = " + type;
		if (searchType != null) {
			if (searchType.equals("all")) {
				whereOutQuery = "WHERE movie_name like ? OR director like ? ";
			} else if (searchType.equals("movie_name")
					|| searchType.equals("director")) {
				whereOutQuery = "WHERE " + searchType + " LIKE ?";

			}
		}
		if (searchType != null) {
			searchText = "%" + searchText.trim() + "%";
		}

		query = "SELECT count(movie_num) FROM (SELECT * FROM Movie "
				+ whereInQuery + ")" + whereOutQuery;
		int result = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = obtainConnection();
			pstmt = con.prepareStatement(query);
			if ((searchType != null)) {
				if (searchType.equals("all")) {
					pstmt.setString(1, searchText);
					pstmt.setString(2, searchText);
				} else if (searchType.equals("movie_name")
						|| searchType.equals("director")) {
					pstmt.setString(1, searchText);
				}
			}

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
						rs.getString(6), rs.getString(7), rs.getString(8),
						rs.getInt(9));
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
		query = "INSERT INTO movie(movie_num, movie_name, genre, release_date, director, syonpsis,"
				+ "photodir, member_id, type) VALUES (MOVIE_SEQUENCE.NEXTVAL, ?, ?, ?, ?, ?, ?, ? ,?)";
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
			pstmt.setString(7, movie.getMemberID());
			pstmt.setInt(8, movie.getType());
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
		query = "UPDATE movie SET movie_name = ?, genre = ? ,director = ? ,syonpsis = ?, photodir = ? WHERE movie_num = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = obtainConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, movie.getMovieName());
			pstmt.setString(2, movie.getGenre());
			pstmt.setString(3, movie.getdirector());
			pstmt.setString(4, movie.getSynopsis());
			pstmt.setString(5, movie.getPhotoDir());
			pstmt.setInt(6, movie.getMovieNum());
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
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = obtainConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, movieNum);
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
