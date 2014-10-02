package movie.web.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import movie.business.domain.Member;
import movie.business.domain.Review;
import movie.business.exception.DataNotFoundException;
import movie.business.service.ReviewService;
import movie.business.service.ReviewServiceImpl;
import movie.util.MovieUtil;

/**
 * Servlet implementation class ReviewController
 */
public class ReviewController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getPathInfo();
		try {
			if (path.equals("/list")) {
				selectReviewList(request, response);
			} else if (path.equals("/write")) {
				writeReview(request, response);
			} else if (path.equals("/update")) {
				updateReview(request, response);
			} else if (path.equals("/remove")) {
				removeReview(request, response);
			}
		} catch (Exception ex) {
			throw new ServletException(ex);
		}
	}

	/** ���� �� ������ ��ȸ
	 */
	private void selectReviewList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String searchType = request.getParameter("searchType");
		String searchText = request.getParameter("searchText");
		String pageNumber = request.getParameter("pageNumber");
		
		int currentPageNumber = 1;
		if ((pageNumber != null) && (pageNumber.length() != 0)) {
			currentPageNumber = Integer.parseInt(pageNumber);
		}

		Map<String, Object> searchInfo = new HashMap<String, Object>();
		searchInfo.put("searchType", searchType);
		searchInfo.put("searchText", searchText);

		ReviewService service = new ReviewServiceImpl();
		
		int totalBoardCount = service.getReviewCount(searchInfo);
		int totalPageCount = MovieUtil.getTotalPageCount(totalBoardCount);
		int startPageNumber = MovieUtil.getStartPageNumber(currentPageNumber);
		int endPageNumber = MovieUtil.getEndPageNumber(currentPageNumber, totalBoardCount);
		int startRow = MovieUtil.getStartRow(currentPageNumber);
		int endRow = MovieUtil.getEndRow(currentPageNumber);
		
		searchInfo.put("startRow", startRow);
		searchInfo.put("endRow", endRow);
		
		Review[] reviewList = service.getReviewList(searchInfo);

		request.setAttribute("reviewList", reviewList);
		request.setAttribute("startPageNumber", startPageNumber);
		request.setAttribute("endPageNumber", endPageNumber);
		request.setAttribute("currentPageNumber", currentPageNumber);
		request.setAttribute("totalPageCount", totalPageCount);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/review/review.jsp");
		dispatcher.forward(request, response);
	}

	/** ���� �ۼ�
	 * �Էµ� ��ȭ ����� �������� DB�� �Է��ϰ� list ���
	 */
	private void writeReview(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		String pageNumber = request.getParameter("pageNumber");
		String movieTitle = request.getParameter("movieTitle");
		String contents = request.getParameter("contents");
		String writerName = ((Member) session.getAttribute("loginMember")).getMemberName();
		String writerID = ((Member) session.getAttribute("loginMember")).getMemberID();
		
		int currentPageNumber = 1;
		if ((pageNumber != null) && (pageNumber.length() != 0)) {
			currentPageNumber = Integer.parseInt(pageNumber);
		}

		// 2. ���� �� ��û �Ķ���� ���� ���� Review ��ü�� �����Ѵ�.
		Review review = new Review(movieTitle, writerName, writerID, contents);
		
		// 3. ReviewService ��ü�� ���� �ش� �Խñ��� ����Ѵ�.
		ReviewService service = new ReviewServiceImpl();
		service.writeReview(review);
		
		request.setAttribute("currentPageNumber", currentPageNumber);

		// 4. RequestDispatcher ��ü�� ���� ��� ����(list)�� ��û�� �����Ѵ�.
		RequestDispatcher dispatcher = request.getRequestDispatcher("list");
		dispatcher.forward(request, response);
	}
	
	/** ���� ����
	 * �ش� ������ ����� �������� form�� �߰� �ް� ������ DB�� �Է��ϰ� list ���
	 * review��ü�� �ѱ��� ���Ұ�� review.jsp�� hidden�Ӽ����� reviewNum�� �߰� �ؾ���.
	 * @throws IOException 
	 * @throws ServletException 
	 * @throws DataNotFoundException 
	 */
	private void updateReview(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DataNotFoundException {
		String reviewNum = request.getParameter("reviewNum");
		String contents = request.getParameter("contents");
		String pageNumber = request.getParameter("pageNumber");

		// (1) ���� ������ ��ȣ
		int currentPageNumber = 1;
		if ((pageNumber != null) && (pageNumber.length() != 0)) {
			currentPageNumber = Integer.parseInt(pageNumber);
		}

		Review review = new Review(Integer.parseInt(reviewNum), contents);
		
		// BoardService ��ü�� ���� �ش� ��ȣ�� �Խñ��� �˻��Ѵ�.
		ReviewService service = new ReviewServiceImpl();
		service.updateReview(review);

		// request scope �Ӽ�(board)�� �˻��� �Խñ��� �����Ѵ�.
		request.setAttribute("review", review);
		request.setAttribute("currentPageNumber", currentPageNumber);

		// RequestDispatcher ��ü�� ���� �� ������(/WEB-INF/views/board/updateForm.jsp)�� ��û�� �����Ѵ�.
		RequestDispatcher dispatcher = request.getRequestDispatcher("list");
		dispatcher.forward(request, response);
		
	}
	
	/** ���� ����
	 * ���õ� ���並 ����
	 * @throws DataNotFoundException 
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void removeReview(HttpServletRequest request, HttpServletResponse response) throws DataNotFoundException, ServletException, IOException {
		// 1. ��û �Ķ���ͷ� ���� ���� �� ��ȣ(reviewNum)�� ���Ѵ�.
		int num = Integer.parseInt(request.getParameter("reviewNum"));

		// 2. BoardService ��ü�� ���� �ش� ��ȣ�� �Խñ��� �����Ѵ�.
		ReviewService service = new ReviewServiceImpl();
		service.removeReview(num);
		
		// 3. RequestDispatcher ��ü�� ���� ��� ����(board?action=list)�� ��û�� �����Ѵ�.
		RequestDispatcher dispatcher = request.getRequestDispatcher("list");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}
