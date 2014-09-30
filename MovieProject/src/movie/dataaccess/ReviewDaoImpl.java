package movie.dataaccess;

import java.sql.*;
import java.util.*;

import javax.naming.*;
import javax.sql.DataSource;

import oracle.net.aso.p;
import movie.business.dao.ReviewDao;
import movie.business.domain.Review;

public class ReviewDaoImpl implements ReviewDao {

	private String query;
	private DataSource dataSource;

	public ReviewDaoImpl() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context
					.lookup("java:comp/env/jdbc/dukeshopDB");
		} catch (NamingException ne) {
			System.err.println("Driver Class Not found");
			ne.printStackTrace(System.err);
			throw new RuntimeException("JNDI error occured." + ne.getMessage());
		}
	}

	private Connection obtainConnection() throws SQLException {
		return dataSource.getConnection();
	}

	public List<Review> selectReviewList(Map<String, Object> searchInfo) {
		// searchInfo Map으로 부터 현재 페이지에 보여질 게시글의 행 번호(startRow, endRow) 값을 구한다.
		int startRow = (Integer) searchInfo.get("startRow");
		int endRow = (Integer) searchInfo.get("endRow");

		query = "select * from (select rownum r, review_num, movie_name, member_name, review_contents, review_date from(select * from Review "
				+ "order by review_num DESC)) where r between ? and ?";

		System.out.println("ReviewDaoImpl SelectReviewList() query : " + query);

		List<Review> result = new ArrayList<Review>();
		Review review = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = obtainConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				String contents = rs.getString("review_contents");

				if (contents.length() > 40) {
					contents = new StringBuilder(contents.substring(0, 40))
							.append("...").toString();
				}

				review = new Review(rs.getInt("review_num"), "movie_name",
						"member_name", "review_contents", "review_date");
				result.add(review);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (SQLException ex) {
				ex.printStackTrace(System.err);
			}
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException ex) {
				ex.printStackTrace(System.err);
			}
			try {
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace(System.err);
			}
		}
		return result;
	}

	public int selectReviewCount() {
		query = "SELECT count(review_num) FROM review";
		int result = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = obtainConnection();
			pstmt = con.prepareStatement(query);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (SQLException ex) {
				ex.printStackTrace(System.err);
			}
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException ex) {
				ex.printStackTrace(System.err);
			}
			try {
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace(System.err);
			}
		}

		return result;
	}

	public void insertReview(Review review) {
		query = "INSERT INTO review(review_num, movie_name, movie_num, member_name, member_id, review_contents, review_date)"
				+ " VALUES(review_sequence.NEXTVAL, ?, ?, ?, ?, ?, SYSDATE)";

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = obtainConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, review.getMoviewTitle());
			pstmt.setInt(2, review.getMovieNum());
			pstmt.setString(3, review.getWriterName());
			pstmt.setString(4, review.getWriterID());
			pstmt.setString(5, review.getContents());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException ex) {
				ex.printStackTrace(System.err);
			}
			try {
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace(System.err);
			}
		}

	}

	public void updateReview(Review review) {
		query = "UPDATE review SET review_contents = ? WHERE review_num = ?";

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = obtainConnection();
			pstmt = con.prepareStatement(query);

			pstmt.setString(1, review.getContents());
			pstmt.setInt(2, review.getReviewNum());

			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException ex) {
				ex.printStackTrace(System.err);
			}
			try {
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace(System.err);
			}
		}
	}

	public void deleteReview(int reviewNum) {
		query = "DELETE FROM review WHERE review_num = ?";

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = obtainConnection();
			pstmt = con.prepareStatement(query);

			pstmt.setInt(1, reviewNum);

			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException ex) {
				ex.printStackTrace(System.err);
			}
			try {
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace(System.err);
			}
		}

	}

	public boolean reviewNumExists(int reviewNum) {
		query = "SELECT review_num FROM rivew WHERE review_num=?";
		boolean result = false;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = obtainConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, reviewNum);
			rs = pstmt.executeQuery();
			result = rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (SQLException ex) {
				ex.printStackTrace(System.err);
			}
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException ex) {
				ex.printStackTrace(System.err);
			}
			try {
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace(System.err);
			}
		}

		return result;
	}

}
