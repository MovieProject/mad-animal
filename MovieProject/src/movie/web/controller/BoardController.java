package movie.web.controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import movie.business.domain.Board;
import movie.business.exception.DataNotFoundException;
import movie.business.service.BoardService;
import movie.business.service.BoardServiceImpl;
import movie.util.MovieUtil;

/**
 * Servlet implementation class BoardController
 */
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		try {
			// action ��û�Ķ���� ���� Ȯ���Ѵ�.
			String action = request.getPathInfo();

			// action ���� ���� ������ �޼ҵ带 �����Ͽ� ȣ���Ѵ�.
			if (action.equals("/list")) {
				selectBoardList(request, response);
			} else if (action.equals("/read")) {
				readBoard(request, response);
			} else if (action.equals("/writeForm")) {
				writeBoardForm(request, response);
			} else if (action.equals("/write")) {
				writeBoard(request, response);
			} else if (action.equals("/updateForm")) {
				updateBoardForm(request, response);
			} else if (action.equals("/update")) {
				updateBoard(request, response);
			} else if (action.equals("/remove")) {
				removeBoard(request, response);
			} else if (action.equals("/reply")) {
				replyBoard(request, response);
			} else if (action.equals("/replyForm")) {
				replyBoardForm(request, response);
			} else {
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
			}
		} catch (Exception ex) {
			throw new ServletException(ex);
		}
	}

	private void selectBoardList(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// searchType, searchText ��û �Ķ���� ���� ���Ѵ�.
		String searchText = request.getParameter("searchText");
		String searchType = request.getParameter("searchType");

		// �˻� �ɼ��� ��� �ִ� Map ��ü�� �����Ͽ� searchType, searchText ���� �����Ѵ�.
		Map<String, Object> searchInfo = new HashMap<String, Object>();

		searchInfo.put("searchType", searchType);
		searchInfo.put("searchText", searchText);

		// pageNumber ��û �Ķ���� ���� ���Ѵ�.
		String pageNumber = request.getParameter("pageNumber");

		// (1) ���� ������ ��ȣ
		int currentPageNumber = 1;
		if ((pageNumber != null) && (pageNumber.length() != 0)) {
			currentPageNumber = Integer.parseInt(pageNumber);
		}

		// BoardService ��ü�κ��� ��� �Խñ� ����Ʈ�� ���ؿ´�.
		BoardService service = new BoardServiceImpl();

		// (4) ��ü ���ñ� ����
		int totalBoardCount = service.getBoardCount(searchInfo);

		// (5) ��ü ������ ����
		int totalPageCount = MovieUtil.getTotalPageCount(totalBoardCount);
		// int totalPageCount = (int) Math.ceil(totalBoardCount
		// / (float) PAGE_LIST_SIZE);

		// (6) ������ ���� �ٿ� ǥ�õ� ���� ������ ��ȣ
		int startPageNumber = MovieUtil.getStartPageNumber(currentPageNumber);
		// int startPageNumber = (((currentPageNumber - 1) / PAGE_GROUP_SIZE) *
		// PAGE_GROUP_SIZE) + 1;

		// (7) ������ ���ùٿ� ǥ�õ� �� ������ ��ȣ
		int endPageNumber = MovieUtil.getEndPageNumber(currentPageNumber,
				totalBoardCount);
		// int endPageNumber = (startPageNumber + PAGE_GROUP_SIZE) - 1;
		//
		// if (endPageNumber > totalPageCount) {
		// endPageNumber = totalPageCount;
		// }

		// (8) ���� �������� �Խñ� ��Ͽ��� ó�� ������ �Խñ��� �� ��ȣ
		int startRow = MovieUtil.getStartRow(currentPageNumber);
		// int startRow = (currentPageNumber - 1) * PAGE_LIST_SIZE + 1;

		// (9) ���� �������� �Խñ� ��Ͽ��� �������� ������ �Խñ��� �� ��ȣ
		int endRow = MovieUtil.getEndRow(currentPageNumber);
		// int endRow = currentPageNumber * PAGE_LIST_SIZE;

		// �˻��ɼ� Map(searchInfo)�� startRow�� endRow ���� �����Ѵ�.
		searchInfo.put("startRow", startRow);
		searchInfo.put("endRow", endRow);

		Board[] boardList = service.getBoardList(searchInfo);

		// request scope �Ӽ�(boardList)�� �Խñ� ����Ʈ�� �����Ѵ�.
		request.setAttribute("boardList", boardList);

		request.setAttribute("currentPageNumber", currentPageNumber);
		request.setAttribute("startPageNumber", startPageNumber);
		request.setAttribute("endPageNumber", endPageNumber);
		request.setAttribute("totalPageCount", totalPageCount);

		// RequestDispatcher ��ü�� ���� �� ������(list.jsp)�� ��û�� �����Ѵ�.
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("/WEB-INF/views/board/list.jsp");
		dispatcher.forward(request, response);

	}

	/*
	 * ���õ� �Խñ��� �о�ͼ� �����ִ� ��û�� ó���Ѵ�.
	 */
	private void readBoard(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			DataNotFoundException {
		// 1.1. ��û �Ķ����(num)�� ���� �� ��ȣ�� ���Ѵ�.
		String num = request.getParameter("boardNum");

		// pageNumber ��û �Ķ���� ���� ���Ѵ�.
		String pageNumber = request.getParameter("pageNumber");

		// (1) ���� ������ ��ȣ
		int currentPageNumber = 1;
		if ((pageNumber != null) && (pageNumber.length() != 0)) {
			currentPageNumber = Integer.parseInt(pageNumber);
		}

		// 2. BoardService ��ü�κ��� �ش� �� ��ȣ�� �Խñ��� ���ؿ´�.
		BoardService service = new BoardServiceImpl();
		Board board = service.readBoard(Integer.parseInt(num));

		// 3. request scope �Ӽ�(board)�� �Խñ��� �����Ѵ�.
		request.setAttribute("board", board);
		request.setAttribute("currentPageNumber", currentPageNumber);
		// request.setAttribute("searchText", searchText);

		// 4. RequestDispatcher ��ü�� ���� �� ������(read.jsp)�� ��û�� �����Ѵ�.
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("/WEB-INF/views/board/read.jsp");
		dispatcher.forward(request, response);

	}

	/*
	 * �Խñ� ����� ���� ���� �����Ѵ�.
	 */
	private void writeBoardForm(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			DataNotFoundException {

		// RequestDispatcher ��ü�� ���� �� ������(writeForm.jsp)�� ��û�� �����Ѵ�.
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("/WEB-INF/views/board/writeForm.jsp");
		dispatcher.forward(request, response);
	}

	/*
	 * �Խñ��� ����ϴ� ��û�� ó���Ѵ�.
	 */
	private void writeBoard(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			DataNotFoundException {
		// 1. ��û �Ķ���ͷ� ���� �ۼ���(writer), ����(title), ����(contents)�� ���Ѵ�.
		String writerName = request.getParameter("writerName");
		String title = request.getParameter("title");
		String contents = request.getParameter("contents");
		String writerID = request.getParameter("writerID");

		// 2. ���� �� ��û �Ķ���� ���� ip ���� ���� Board ��ü�� �����Ѵ�.
		Board board = new Board(title, writerName, contents, writerID);
		// 3. BoardService ��ü�� ���� �ش� �Խñ��� ����Ѵ�.
		BoardService service = new BoardServiceImpl();
		service.writeBoard(board);

		// 4. RequestDispatcher ��ü�� ���� ��� ����(board?action=list)�� ��û�� �����Ѵ�.
		RequestDispatcher dispatcher = request.getRequestDispatcher("list");
		dispatcher.forward(request, response);
	}

	/*
	 * �Խñ� ������ ���� ������ ������ ä���� ���� �����Ѵ�.
	 */
	private void updateBoardForm(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			DataNotFoundException {
		// ��û �Ķ���ͷ� ���� �� ��ȣ(num)�� ���Ѵ�.
		String num = request.getParameter("boardNum");
		// String searchText = request.getParameter("searchText");
		// String searchType = request.getParameter("searchType");

		// pageNumber ��û �Ķ���� ���� ���Ѵ�.
		String pageNumber = request.getParameter("pageNumber");

		// (1) ���� ������ ��ȣ
		int currentPageNumber = 1;
		if ((pageNumber != null) && (pageNumber.length() != 0)) {
			currentPageNumber = Integer.parseInt(pageNumber);
		}

		// BoardService ��ü�� ���� �ش� ��ȣ�� �Խñ��� �˻��Ѵ�.
		BoardService boardService = new BoardServiceImpl();
		Board board = boardService.findBoard(Integer.parseInt(num));

		// request scope �Ӽ�(board)�� �˻��� �Խñ��� �����Ѵ�.
		request.setAttribute("board", board);
		request.setAttribute("currentPageNumber", currentPageNumber);
		// request.setAttribute("searchText", searchText);

		// RequestDispatcher ��ü�� ���� �� ������(updateForm.jsp)�� ��û�� �����Ѵ�.
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("/WEB-INF/views/board/updateForm.jsp");
		dispatcher.forward(request, response);
	}

	/*
	 * �Խñ��� �����ϴ� ��û�� ó���Ѵ�.
	 */
	private void updateBoard(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			DataNotFoundException {
		// 1. ��û �Ķ���ͷ� ���� �� ��ȣ(num), �ۼ���(writer), ����(title), ����(contents)�� ���Ѵ�.
		String boardNum = request.getParameter("boardNum");
		String writerName = request.getParameter("writerName");
		String title = request.getParameter("title");
		String contents = request.getParameter("contents");
		String writerID = request.getParameter("writerID");

		// 2. ���� �� ��û �Ķ���� ���� ip ���� ���� Board ��ü�� �����Ѵ�.
		Board board = new Board(Integer.parseInt(boardNum), title, writerName,
				contents, writerID);
		System.out.println("board" + board);

		// 3. BoardService ��ü�� ���� �ش� �Խñ��� �����Ѵ�.
		BoardService service = new BoardServiceImpl();
		service.updateBoard(board);

		// 4. request scope �Ӽ�(board)�� �Խñ��� �����Ѵ�.
		request.setAttribute("board", board);
		// request.setAttribute("searchType", searchType);
		// request.setAttribute("searchText", searchText);

		// 5. RequestDispatcher ��ü�� ���� �Խù� ����(board?action=read)�� ��û�� �����Ѵ�.
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("read?boardNum=" + boardNum);
		dispatcher.forward(request, response);
	}

	/*
	 * �Խñ��� �����ϴ� ��û�� ó���Ѵ�.
	 */
	private void removeBoard(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			DataNotFoundException {
		// 1. ��û �Ķ���ͷ� ���� �� ��ȣ(boardNum)�� ���Ѵ�.
		String num = request.getParameter("boardNum");
		// 2. BoardService ��ü�� ���� �ش� ��ȣ�� �Խñ��� �����Ѵ�.
		BoardService service = new BoardServiceImpl();
		service.removeBoard(Integer.parseInt(num));
		// 3. RequestDispatcher ��ü�� ���� ��� ����(board?action=list)�� ��û�� �����Ѵ�.
		RequestDispatcher dispatcher = request.getRequestDispatcher("list");
		dispatcher.forward(request, response);
	}

	/*
	 * �亯�� �ۼ��� ���� ������ ������ ä���� ���� �����Ѵ�.
	 */
	private void replyBoardForm(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			NumberFormatException, DataNotFoundException {
		// ��û �Ķ���ͷ� ���� �� ��ȣ(num)�� ���Ѵ�.
		String num = request.getParameter("boardNum");
		// String searchText = request.getParameter("searchText");
		// String searchType = request.getParameter("searchType");

		// pageNumber ��û �Ķ���� ���� ���Ѵ�.
		String pageNumber = request.getParameter("pageNumber");

		// (1) ���� ������ ��ȣ
		int currentPageNumber = 1;
		if ((pageNumber != null) && (pageNumber.length() != 0)) {
			currentPageNumber = Integer.parseInt(pageNumber);
		}

		// BoardService ��ü�� ���� �ش� ��ȣ�� �Խñ��� �˻��Ѵ�.
		BoardService boardService = new BoardServiceImpl();
		Board board = boardService.findBoard(Integer.parseInt(num));

		// request scope �Ӽ�(board)�� �˻��� �Խñ��� �����Ѵ�.
		request.setAttribute("board", board);
		request.setAttribute("currentPageNumber", currentPageNumber);

		// RequestDispatcher ��ü�� ���� �� ������(replyForm.jsp)�� ��û�� �����Ѵ�.
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("/WEB-INF/views/board/replyForm.jsp");
		dispatcher.forward(request, response);
	}

	/*
	 * �亯���� ����ϴ� ��û�� ó��
	 */
	private void replyBoard(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			DataNotFoundException {
		// ��û �Ķ���ͷ� ���� �� ��ȣ(num), �ۼ���(writer), ����(title), ����(contents), ����
		// �۹�ȣ(masterNum)�� ���Ѵ�.
		String boardNum = request.getParameter("boardNum");
		String writerName = request.getParameter("writerName");
		String title = request.getParameter("title");
		String contents = request.getParameter("contents");
		String masterNum = request.getParameter("masterNum");
		String replyOrder = request.getParameter("replyOrder");
		String replyStep = request.getParameter("replyStep");
		String writerID = request.getParameter("writerID");

		// ���� �� ��û �Ķ���� ���� ip ���� ���� Board ��ü�� �����Ѵ�.
		Board board = new Board(Integer.parseInt(boardNum), title, writerName,
				contents, writerID, Integer.parseInt(masterNum),
				Integer.parseInt(replyOrder), Integer.parseInt(replyStep));
		System.out.println("board" + board);

		// BoardService ��ü�� ���� �ش� ����� ����Ѵ�.
		BoardService service = new BoardServiceImpl();
		service.replyBoard(board);

		// RequestDispatcher ��ü�� ���� �Խù� ��� ����(list)�� ��û�� �����Ѵ�.
		RequestDispatcher dispatcher = request.getRequestDispatcher("list");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
