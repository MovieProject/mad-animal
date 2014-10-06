package movie.dataaccess;

import java.sql.*;
import java.util.*;

import javax.naming.*;
import javax.sql.DataSource;

import movie.business.dao.ReviewDao;
import movie.business.domain.Review;

public class ReviewDaoImpl implements ReviewDao {

	private String query;
	private DataSource dataSource;

	public ReviewDaoImpl() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context
					.lookup("java:comp/env/jdbc/movieDB");
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
		String searchType = (String) searchInfo.get("searchType");
		String searchText = (String) searchInfo.get("searchText");
		
		int startRow = (Integer) searchInfo.get("startRow");
		int endRow = (Integer) searchInfo.get("endRow");

		String whereSQL = "";

		if (searchType == null || searchType.length() == 0) {
			whereSQL = "";
		} else if (searchType.equals("all")) {
			whereSQL = " WHERE movie_name LIKE ? OR member_name LIKE ? OR review_contents LIKE ?";
		} else if (searchType.equals("title")) {
			whereSQL = " WHERE movie_name LIKE ?";
		} else if (searchType.equals("writer")) {
			whereSQL = " WHERE member_name LIKE ?";
		} else if (searchType.equals("contents")) {
			whereSQL = " WHERE review_contents LIKE ?";
		}
		
		if (searchText != null) {
			searchText = "%" + searchText.trim() + "%";
		}
		
		query = "SELECT * FROM (SELECT ROWNUM r, review_num, movie_name, member_name, review_contents, review_date FROM (SELECT * FROM Review"
				+ whereSQL + " ORDER BY review_num DESC)) WHERE r between ? and ?";

		System.out.println("ReviewDaoImpl SelectReviewList() query : " + query);
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<Review> result = new ArrayList<Review>();
		Review review = null;

		try {
			con = obtainConnection();
			pstmt = con.prepareStatement(query);

			if (searchType == null || searchType.length() == 0) {
            	pstmt.setInt(1, startRow);
            	pstmt.setInt(2, endRow);

            } else if (searchType.equals("all")) {
            	pstmt.setString(1, searchText);
            	pstmt.setString(2, searchText);
            	pstmt.setString(3, searchText);
            	pstmt.setInt(4, startRow);
            	pstmt.setInt(5, endRow);

        	} else if (searchType.equals("title") || searchType.equals("writer") || searchType.equals("contents")) {
        		pstmt.setString(1, searchText);
            	pstmt.setInt(2, startRow);
            	pstmt.setInt(3, endRow);        		
            }
			
			rs = pstmt.executeQuery();

			while (rs.next()) {

				String contents = rs.getString("review_contents");

//				if (contents.length() > 40) {
//					contents = new StringBuilder(contents.substring(0, 40))
//							.append("...").toString();
//				}
				
				String date = (rs.getString("review_date")).substring(0, 10);

				review = new Review(rs.getInt("review_num"),
												rs.getString("movie_name"),
												rs.getString("member_name"),
												contents,
												date);
				result.add(review);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try { if (rs != null) rs.close(); } catch (SQLException ex) { ex.printStackTrace(System.err); }
			try { if (pstmt != null) pstmt.close(); } catch (SQLException ex) { ex.printStackTrace(System.err); }
			try { if (con != null) con.close(); } catch (SQLException e) { e.printStackTrace(System.err); }
		}
		return result;
	}

	public int selectReviewCount(Map<String, Object> searchInfo) {
		String searchType = (String) searchInfo.get("searchType");
		String searchText = (String) searchInfo.get("searchText");
		
		String whereSQL = "";
		
		if (searchType == null || searchType.length() == 0) {
			whereSQL = "";
		} else if (searchType.equals("all")) {
			whereSQL = " WHERE movie_name LIKE ? OR member_name LIKE ? OR review_contents LIKE ?";
		} else if (searchType.equals("title")) {
			whereSQL = " WHERE movie_name LIKE ?";
		} else if (searchType.equals("writer")) {
			whereSQL = " WHERE member_name LIKE ?";
		} else if (searchType.equals("contents")) {
			whereSQL = " WHERE review_contents LIKE ?";
		}
		
		if (searchText != null) {
			searchText = "%" + searchText.trim() + "%";
		}
		
		query = "SELECT count(review_num) FROM review" + whereSQL;
		System.out.println("ReviewDaoImpl selectReviewCount() query : " + query);
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int count = 0;

		try {
			con = obtainConnection();
			pstmt = con.prepareStatement(query);

			if (searchType == null || searchType.length() == 0) {

            } else if (searchType.equals("all")) {
            	pstmt.setString(1, searchText);
            	pstmt.setString(2, searchText);
            	pstmt.setString(3, searchText);
            	
        	} else if (searchType.equals("title") || searchType.equals("writer") || searchType.equals("contents")) {
        		pstmt.setString(1, searchText);        		
            }
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try { if (rs != null) rs.close(); } catch (SQLException ex) { ex.printStackTrace(System.err); }
			try { if (pstmt != null) pstmt.close(); } catch (SQLException ex) { ex.printStackTrace(System.err); }
			try { if (con != null) con.close(); } catch (SQLException e) { e.printStackTrace(System.err); }
		}

		return count;
	}

	public void insertReview(Review review) {
		query = "INSERT INTO review(review_num, movie_name, member_name, member_id, review_contents, review_date)"
				+ " VALUES(review_sequence.NEXTVAL, ?, ?, ?, ?, SYSDATE)";
		System.out.println("ReviewDaoImpl insertReview() query : " + query);

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = obtainConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, review.getMovieTitle());
			pstmt.setString(2, review.getWriterName());
			pstmt.setString(3, review.getWriterID());
			pstmt.setString(4, review.getContents());
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
		System.out.println("ReviewDaoImpl updateReview() query : " + query);

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
		System.out.println("ReviewDaoImpl deleteReview() query : " + query);

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
		query = "SELECT review_num FROM review WHERE review_num=?";
		System.out.println("ReviewDaoImpl reviewNumExists() query : " + query);
		
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
