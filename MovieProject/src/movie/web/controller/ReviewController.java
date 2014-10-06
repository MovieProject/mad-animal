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

	/** 리뷰 한 페이지 조회
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

	/** 리뷰 작성
	 * 입력된 영화 제목과 한줄평을 DB에 입력하고 list 출력
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

		// 2. 구해 온 요청 파라미터 값을 지닌 Review 객체를 생성한다.
		Review review = new Review(movieTitle, writerName, writerID, contents);
		
		// 3. ReviewService 객체를 통해 해당 게시글을 등록한다.
		ReviewService service = new ReviewServiceImpl();
		service.writeReview(review);
		
		request.setAttribute("currentPageNumber", currentPageNumber);

		// 4. RequestDispatcher 객체를 통해 목록 보기(list)로 요청을 전달한다.
		RequestDispatcher dispatcher = request.getRequestDispatcher("list");
		dispatcher.forward(request, response);
	}
	
	/** 리뷰 수정
	 * 해당 리뷰의 제목과 한줄평을 form에 추가 받고 수정후 DB에 입력하고 list 출력
	 * review객체를 넘기지 못할경우 review.jsp에 hidden속성으로 reviewNum값 추가 해야함.
	 * @throws IOException 
	 * @throws ServletException 
	 * @throws DataNotFoundException 
	 */
	private void updateReview(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DataNotFoundException {
		String reviewNum = request.getParameter("reviewNum");
		String contents = request.getParameter("contents");
		String pageNumber = request.getParameter("pageNumber");

		// (1) 현재 페이지 번호
		int currentPageNumber = 1;
		if ((pageNumber != null) && (pageNumber.length() != 0)) {
			currentPageNumber = Integer.parseInt(pageNumber);
		}

		Review review = new Review(Integer.parseInt(reviewNum), contents);
		
		// BoardService 객체를 통해 해당 번호의 게시글을 검색한다.
		ReviewService service = new ReviewServiceImpl();
		service.updateReview(review);

		// request scope 속성(board)에 검색한 게시글을 저장한다.
		request.setAttribute("review", review);
		request.setAttribute("currentPageNumber", currentPageNumber);

		// RequestDispatcher 객체를 통해 뷰 페이지(/WEB-INF/views/board/updateForm.jsp)로 요청을 전달한다.
		RequestDispatcher dispatcher = request.getRequestDispatcher("list");
		dispatcher.forward(request, response);
		
	}
	
	/** 리뷰 삭제
	 * 선택된 리뷰를 삭제
	 * @throws DataNotFoundException 
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void removeReview(HttpServletRequest request, HttpServletResponse response) throws DataNotFoundException, ServletException, IOException {
		// 1. 요청 파라미터로 부터 리뷰 글 번호(reviewNum)를 구한다.
		int num = Integer.parseInt(request.getParameter("reviewNum"));

		// 2. BoardService 객체를 통해 해당 번호의 게시글을 삭제한다.
		ReviewService service = new ReviewServiceImpl();
		service.removeReview(num);
		
		// 3. RequestDispatcher 객체를 통해 목록 보기(board?action=list)로 요청을 전달한다.
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
