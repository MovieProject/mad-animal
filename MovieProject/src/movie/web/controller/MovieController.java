package movie.web.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import movie.business.domain.Movie;
import movie.business.exception.DataDuplicatedException;
import movie.business.exception.DataNotFoundException;
import movie.business.service.MovieService;
import movie.business.service.MovieServiceImpl;
import movie.util.MovieUtil;

/**
 * Servlet implementation class MovieController
 */
public class MovieController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private File uploadDir;

	public void init() throws ServletException {
		System.out.println("�����");
		uploadDir = new File(getServletContext().getRealPath(
				"/" + getInitParameter("uploadDir")));
		System.out.println(getServletContext().getRealPath(
				"/" + getInitParameter("uploadDir")));
		if (!uploadDir.exists()) {
			uploadDir.mkdir();
		}
	}

	private void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String action = request.getPathInfo();
		try {
			if (action.equals("/writeMovie")) {
				writeMovie(request, response);
			} else if (action.equals("/movieRead")) {
				readMovie(request, response);
			} else if (action.equals("/movielist")) {
				movielist(request, response);
			} else if (action.equals("/writeMovieForm")) {
				writeMovieForm(request, response);
			} else if (action.equals("/removeMovie")) {
				removeMovie(request, response);
			} else if (action.equals("/updateMovieForm")) {
				updateMovieForm(request, response);
			} else if (action.equals("/updateMovie")) {
				updateMovie(request, response);
			} else if(action.equals("/moviepreview")){
				previewMovie(request,response);
			} else if(action.equals("/removeMovieList")){
				removeMovieList(request,response);
			}

		} catch (DataDuplicatedException e) {
			// TODO Auto-generated catch block
			throw new ServletException(e.getMessage());
		} catch (DataNotFoundException e) {
			throw new ServletException(e.getMessage());

		}

	}

	private void removeMovieList(HttpServletRequest request,
			HttpServletResponse response) throws NumberFormatException, DataNotFoundException, ServletException, IOException {
		// TODO Auto-generated method stub
		String[] items = request.getParameterValues("items");
		MovieService service =  new MovieServiceImpl();
		for(String item:items){
			service.removeMovie(Integer.parseInt(item.toString()));
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("/movie/movielist?type=1");
		dispatcher.forward(request, response);
		
	}

	private void previewMovie(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException, DataNotFoundException {
		// TODO Auto-generated method stub
		MovieService service = new MovieServiceImpl();

		// searchType, searchText ��û �Ķ���� ���� ���Ѵ�.
		String searchText = request.getParameter("searchText");
		String searchType = request.getParameter("searchType");
		int type = Integer.parseInt(request.getParameter("type"));
		// �˻� �ɼ��� ��� �ִ� Map ��ü�� �����Ͽ� searchType, searchText ���� �����Ѵ�.
		Map<String, Object> searchInfo = new HashMap<String, Object>();

		String pageNumber = request.getParameter("pageNumber");

		searchInfo.put("searchType", searchType);
		searchInfo.put("searchText", searchText);
		searchInfo.put("type", type);
		// (1) ���� ������ ��ȣ
		int currentPageNumber = 1;
		if ((pageNumber != null) && (pageNumber.length() != 0)) {
			currentPageNumber = Integer.parseInt(pageNumber);
		}

		// (4) ��ü ���ñ� ����
		int totalBoardCount = service.getMovieCount(searchInfo);

		// (5) ��ü ������ ����
		int totalPageCount = MovieUtil.getTotalPageCount(totalBoardCount);

		// (6) ������ ���� �ٿ� ǥ�õ� ���� ������ ��ȣ
		int startPageNumber = MovieUtil.getStartPageNumber(currentPageNumber);

		// (7) ������ ���ùٿ� ǥ�õ� �� ������ ��ȣ
		int endPageNumber = MovieUtil.getEndPageNumber(currentPageNumber,
				totalBoardCount);

		// (8) ���� �������� �Խñ� ��Ͽ��� ó�� ������ �Խñ��� �� ��ȣ
		int startRow = MovieUtil.getStartRow(currentPageNumber);

		// (9) ���� �������� �Խñ� ��Ͽ��� �������� ������ �Խñ��� �� ��ȣ
		int endRow = MovieUtil.getEndRow(currentPageNumber);

		// �˻��ɼ� Map(searchInfo)�� startRow�� endRow ���� �����Ѵ�.
		searchInfo.put("startRow", startRow);
		searchInfo.put("endRow", endRow);
		System.out.println(startRow);
		System.out.println(endRow);
		System.out.println(searchInfo);
		Movie[] movielist = service.getMovieList(searchInfo);
		for (Movie movie : movielist) {
			System.out.println(movie);
		}
		request.setAttribute("movielist", movielist);
		request.setAttribute("currentPageNumber", currentPageNumber);
		request.setAttribute("startPageNumber", startPageNumber);
		request.setAttribute("endPageNumber", endPageNumber);
		request.setAttribute("totalPageCount", totalPageCount);
		RequestDispatcher dispatcher = null;
		if (type == 1) {
			dispatcher = request
					.getRequestDispatcher("/WEB-INF/views/movie/member_Recommend_pre.jsp");
		} else if (type == 2) {
			dispatcher = request
					.getRequestDispatcher("/WEB-INF/views/movie/newMovieIntro_pre.jsp");

		} else if (type == 3) {
			Movie movie = service.findMovie(movielist[0].getMovieNum());
			request.setAttribute("movie", movie);
			dispatcher = request
					.getRequestDispatcher("/WEB-INF/views/movie/week_Recommend_pre.jsp");

		}
		dispatcher.forward(request, response);
		
	}

	private void updateMovieForm(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			DataNotFoundException {
		// TODO Auto-generated method stub
		String movieNum = request.getParameter("num");
		String pageNumber = request.getParameter("pageNumber");
		int currentPageNumber = 1;
		if (pageNumber != null && pageNumber.length() != 0) {
			currentPageNumber = Integer.parseInt(pageNumber);
		}
		MovieService service = new MovieServiceImpl();
		Movie movie = service.findMovie(Integer.parseInt(movieNum));
		request.setAttribute("movie", movie);
		request.setAttribute("currentPageNumber", currentPageNumber);
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("/WEB-INF/views/movie/updateMovieForm.jsp");
		dispatcher.forward(request, response);
	}

	private void updateMovie(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			DataNotFoundException {
		int movieNum = 0;//Integer.parseInt(request.getParameter("num"));
		String movieName = null;// request.getParameter("title");
		String genre =  null;//request.getParameter("genre");
		String releaseDate =  null;//request.getParameter("releaseDate");
		String director = null;// request.getParameter("director");
		String synopsis =  null;//request.getParameter("contents");
		String photoDir = null;
		String memberID =  null;//request.getParameter("memberID");
		int type =  0;//Integer.parseInt(request.getParameter("type"));
		String pageNumber =null;//request.getParameter("pageNumber");
		
		// ��ũ ����� FileItem factory ����
				DiskFileItemFactory factory = new DiskFileItemFactory();
				// repository ���� (a secure temp location is used)
				ServletContext servletContext = this.getServletConfig()
						.getServletContext();
				File repository = (File) getServletContext().getAttribute(
						"javax.servlet.context.tempdir");
				// File repository = new File(uploadDir, "temp");
				// if (!repository.exists()) { repository.mkdir(); }

				// factory ���� ����
				factory.setSizeThreshold(1024 * 100);
				// �޸𸮿� ������ �ִ� size (100K������ �޸𸮿� ����)
				factory.setRepository(repository);
				// ���� �ӽ� ����� (100K �̻��̸� repository�� ����)

				// ���� ���ε� �ڵ鷯 ����
				ServletFileUpload upload = new ServletFileUpload(factory);
				// �� request size ���� ����
				upload.setSizeMax(1024 * 1024 * 20); // �ִ� size (20M���� ����)

				// ��û �Ľ�
				try {
					List<FileItem> items = upload.parseRequest(request);

					// ���ε�� items ó��
					Iterator<FileItem> iter = items.iterator();
					while (iter.hasNext()) {
						FileItem item = iter.next();

						// �Ϲ� �� �ʵ� ó�� (<input type="file">�� �ƴ� ���)
						if (item.isFormField()) {
							String name = item.getFieldName(); // �ʵ� �̸�
							String value = item.getString("UTF-8"); // �ʵ� ��

							System.out.println(name + " : " + value);

							if (name.equals("title")) {
								movieName = value;
							} else if (name.equals("genre")) {
								genre = value;
							} else if (name.equals("director")) {
								director = value;
							} else if (name.equals("releaseDate")) {
								releaseDate = value;
							} else if (name.equals("contents")) {
								synopsis = value;
							} else if (name.equals("memberID")) {
								memberID = value;
							} else if (name.equals("type")) {
								type = Integer.parseInt(value);
							}else if(name.equals("num")){
								movieNum = Integer.parseInt(value);
							}else if(name.equals("original")){
								photoDir = value;
							}else if(name.equals("pageNumber")){
								pageNumber = value;
							}

							// ���� ���ε� ó�� (<input type="file">�� ���)
						} else {
							String fieldName = item.getFieldName(); // �ʵ� �̸�
							String fileName = item.getName(); // ��ΰ� ���Ե� ���ϸ�

							System.out.println(fieldName);
							System.out.println(fileName);

							int index = fileName.lastIndexOf("\\"); // ���͸� ������ ��ġ�� ����
							if (index == -1) {
								index = fileName.lastIndexOf("/");
							}
								photoDir = fileName.substring(index + 1); // ���ϸ� ����			
								System.out.println(photoDir);
								// ���� ���ε� ó��
								File uploadedFile = new File(uploadDir, photoDir);
								item.write(uploadedFile); // �������� ����
							
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
		
//		photoDir = request.getParameter("file");
//		if (photoDir == null) {
//			photoDir = request.getParameter("original");
//		}

		
		int currentPageNumber = 1;
		if (pageNumber != null && pageNumber.length() != 0) {
			currentPageNumber = Integer.parseInt(pageNumber);
		}		
		
		Movie movie = new Movie(movieNum, movieName, genre, releaseDate,
				director, synopsis, photoDir, memberID, type);
		MovieService service = new MovieServiceImpl();
		service.updateMovie(movie);
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("movieRead?num=" + movieNum);
		dispatcher.forward(request, response);
		// TODO Auto-generated method stub

	}

	private void removeMovie(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			DataNotFoundException {
		// TODO Auto-generated method stub
		int movieNum = Integer.parseInt(request.getParameter("num"));
		int type = Integer.parseInt(request.getParameter("type"));
		MovieService service = new MovieServiceImpl();
		service.removeMovie(movieNum);
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("/movie/movielist?type=" + type);
		dispatcher.forward(request, response);
	}

	private void writeMovieForm(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("type", request.getParameter("type"));
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("/WEB-INF/views/movie/writeMovieForm.jsp");
		dispatcher.forward(request, response);
		// TODO Auto-generated method stub

	}

	private void movielist(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		MovieService service = new MovieServiceImpl();

		// searchType, searchText ��û �Ķ���� ���� ���Ѵ�.
		String searchText = request.getParameter("searchText");
		String searchType = request.getParameter("searchType");
		int type = Integer.parseInt(request.getParameter("type"));
		// �˻� �ɼ��� ��� �ִ� Map ��ü�� �����Ͽ� searchType, searchText ���� �����Ѵ�.
		Map<String, Object> searchInfo = new HashMap<String, Object>();

		String pageNumber = request.getParameter("pageNumber");

		searchInfo.put("searchType", searchType);
		searchInfo.put("searchText", searchText);
		searchInfo.put("type", type);
		// (1) ���� ������ ��ȣ
		int currentPageNumber = 1;
		if ((pageNumber != null) && (pageNumber.length() != 0)) {
			currentPageNumber = Integer.parseInt(pageNumber);
		}

		// (4) ��ü ���ñ� ����
		int totalBoardCount = service.getMovieCount(searchInfo);

		// (5) ��ü ������ ����
		int totalPageCount = MovieUtil.getTotalPageCount(totalBoardCount);

		// (6) ������ ���� �ٿ� ǥ�õ� ���� ������ ��ȣ
		int startPageNumber = MovieUtil.getStartPageNumber(currentPageNumber);

		// (7) ������ ���ùٿ� ǥ�õ� �� ������ ��ȣ
		int endPageNumber = MovieUtil.getEndPageNumber(currentPageNumber,
				totalBoardCount);

		// (8) ���� �������� �Խñ� ��Ͽ��� ó�� ������ �Խñ��� �� ��ȣ
		int startRow = MovieUtil.getStartRow(currentPageNumber);

		// (9) ���� �������� �Խñ� ��Ͽ��� �������� ������ �Խñ��� �� ��ȣ
		int endRow = MovieUtil.getEndRow(currentPageNumber);

		// �˻��ɼ� Map(searchInfo)�� startRow�� endRow ���� �����Ѵ�.
		searchInfo.put("startRow", startRow);
		searchInfo.put("endRow", endRow);
		System.out.println(startRow);
		System.out.println(endRow);
		System.out.println(searchInfo);
		Movie[] movielist = service.getMovieList(searchInfo);
		for (Movie movie : movielist) {
			System.out.println(movie);
		}
		request.setAttribute("movielist", movielist);
		request.setAttribute("currentPageNumber", currentPageNumber);
		request.setAttribute("startPageNumber", startPageNumber);
		request.setAttribute("endPageNumber", endPageNumber);
		request.setAttribute("totalPageCount", totalPageCount);
		RequestDispatcher dispatcher = null;
		if (type == 1) {
			dispatcher = request
					.getRequestDispatcher("/WEB-INF/views/movie/member_Recommend.jsp");
		} else if (type == 2) {
			dispatcher = request
					.getRequestDispatcher("/WEB-INF/views/movie/week_Recommend.jsp");

		} else if (type == 3) {
			dispatcher = request
					.getRequestDispatcher("/WEB-INF/views/movie/newMovieIntro.jsp");

		}
		dispatcher.forward(request, response);

	}

	private void readMovie(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			DataNotFoundException {
		String movieNum = request.getParameter("num");
		MovieService service = new MovieServiceImpl();
		Movie movie = service.findMovie(Integer.parseInt(movieNum));
		System.out.println(movie);
		request.setAttribute("movie", movie);
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("/WEB-INF/views/movie/readMovie.jsp");
		dispatcher.forward(request, response);

		// TODO Auto-generated method stub

	}

	private void writeMovie(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			DataDuplicatedException {
		// TODO Auto-generated method stub
		String movieName = null; // request.getParameter("title");
		String genre = null;// request.getParameter("genre");
		String director = null;// request.getParameter("director");
		String releaseDate = null;// request.getParameter("releaseDate");
		String synopsis = null;// request.getParameter("synopsis");
		String photoDir = null; // request.getParameter("file");
		String memberID = null;// request.getParameter("memberID");
		int type = 0;// Integer.parseInt(request.getParameter("type"));

		// ��ũ ����� FileItem factory ����
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// repository ���� (a secure temp location is used)
		ServletContext servletContext = this.getServletConfig()
				.getServletContext();
		File repository = (File) getServletContext().getAttribute(
				"javax.servlet.context.tempdir");
		// File repository = new File(uploadDir, "temp");
		// if (!repository.exists()) { repository.mkdir(); }

		// factory ���� ����
		factory.setSizeThreshold(1024 * 100);
		// �޸𸮿� ������ �ִ� size (100K������ �޸𸮿� ����)
		factory.setRepository(repository);
		// ���� �ӽ� ����� (100K �̻��̸� repository�� ����)

		// ���� ���ε� �ڵ鷯 ����
		ServletFileUpload upload = new ServletFileUpload(factory);
		// �� request size ���� ����
		upload.setSizeMax(1024 * 1024 * 20); // �ִ� size (20M���� ����)

		// ��û �Ľ�
		try {
			List<FileItem> items = upload.parseRequest(request);

			// ���ε�� items ó��
			Iterator<FileItem> iter = items.iterator();
			while (iter.hasNext()) {
				FileItem item = iter.next();

				// �Ϲ� �� �ʵ� ó�� (<input type="file">�� �ƴ� ���)
				if (item.isFormField()) {
					String name = item.getFieldName(); // �ʵ� �̸�
					String value = item.getString("UTF-8"); // �ʵ� ��

					System.out.println(name + " : " + value);

					if (name.equals("title")) {
						movieName = value;
					} else if (name.equals("genre")) {
						genre = value;
					} else if (name.equals("director")) {
						director = value;
					} else if (name.equals("releaseDate")) {
						releaseDate = value;
					} else if (name.equals("synopsis")) {
						synopsis = value;
					} else if (name.equals("memberID")) {
						memberID = value;
					} else if (name.equals("type")) {
						type = Integer.parseInt(value);
					}

					// ���� ���ε� ó�� (<input type="file">�� ���)
				} else {
					String fieldName = item.getFieldName(); // �ʵ� �̸�
					String fileName = item.getName(); // ��ΰ� ���Ե� ���ϸ�

					System.out.println(fieldName);
					System.out.println(fileName);

					int index = fileName.lastIndexOf("\\"); // ���͸� ������ ��ġ�� ����
					if (index == -1) {
						index = fileName.lastIndexOf("/");
					}
					photoDir = fileName.substring(index + 1); // ���ϸ� ����
					System.out.println(photoDir);
					// ���� ���ε� ó��
					File uploadedFile = new File(uploadDir, photoDir);
					item.write(uploadedFile); // �������� ����
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		Movie movie = new Movie(movieName, genre, director, releaseDate,
				synopsis, photoDir, memberID, type);
		System.out.println(movie);

		// File uploadDir = new File(getServletContext().getRealPath("/" +
		// getInitParameter("uploadDir")));
		// // ���� ���ε� ó��
		// File uploadedFile = new File(photoDir);
		// System.out.println(uploadDir);
		// System.out.println(uploadedFile);
		// if (!uploadDir.exists()) { uploadDir.mkdir(); }
		// MovieUtil.createThumbnail(uploadDir, uploadedFile, 160, 160);

		MovieService service = new MovieServiceImpl();
		service.writeMovie(movie);

		RequestDispatcher dispatcher = request
				.getRequestDispatcher("/movie/movielist?type=" + type);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

}