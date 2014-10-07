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
		System.out.println("실행됨");
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

		// searchType, searchText 요청 파라미터 값을 구한다.
		String searchText = request.getParameter("searchText");
		String searchType = request.getParameter("searchType");
		int type = Integer.parseInt(request.getParameter("type"));
		// 검색 옵션을 담고 있는 Map 객체를 생성하여 searchType, searchText 값을 저장한다.
		Map<String, Object> searchInfo = new HashMap<String, Object>();

		String pageNumber = request.getParameter("pageNumber");

		searchInfo.put("searchType", searchType);
		searchInfo.put("searchText", searchText);
		searchInfo.put("type", type);
		// (1) 현재 페이지 번호
		int currentPageNumber = 1;
		if ((pageNumber != null) && (pageNumber.length() != 0)) {
			currentPageNumber = Integer.parseInt(pageNumber);
		}

		// (4) 전체 개시글 개수
		int totalBoardCount = service.getMovieCount(searchInfo);

		// (5) 전체 페이지 개수
		int totalPageCount = MovieUtil.getTotalPageCount(totalBoardCount);

		// (6) 페이지 선택 바에 표시될 시작 페이지 번호
		int startPageNumber = MovieUtil.getStartPageNumber(currentPageNumber);

		// (7) 페이지 선택바에 표시될 끝 페이지 번호
		int endPageNumber = MovieUtil.getEndPageNumber(currentPageNumber,
				totalBoardCount);

		// (8) 현재 페이지의 게시글 목록에서 처음 보여질 게시글의 행 번호
		int startRow = MovieUtil.getStartRow(currentPageNumber);

		// (9) 현재 페이지의 게시글 목록에서 마지막에 보여질 게시글의 행 번호
		int endRow = MovieUtil.getEndRow(currentPageNumber);

		// 검색옵션 Map(searchInfo)에 startRow와 endRow 값을 저장한다.
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
		
		// 디스크 기반의 FileItem factory 생성
				DiskFileItemFactory factory = new DiskFileItemFactory();
				// repository 생성 (a secure temp location is used)
				ServletContext servletContext = this.getServletConfig()
						.getServletContext();
				File repository = (File) getServletContext().getAttribute(
						"javax.servlet.context.tempdir");
				// File repository = new File(uploadDir, "temp");
				// if (!repository.exists()) { repository.mkdir(); }

				// factory 제약 설정
				factory.setSizeThreshold(1024 * 100);
				// 메모리에 저장할 최대 size (100K까지는 메모리에 저장)
				factory.setRepository(repository);
				// 파일 임시 저장소 (100K 이상이면 repository에 저장)

				// 파일 업로드 핸들러 생성
				ServletFileUpload upload = new ServletFileUpload(factory);
				// 총 request size 제약 설정
				upload.setSizeMax(1024 * 1024 * 20); // 최대 size (20M까지 가능)

				// 요청 파싱
				try {
					List<FileItem> items = upload.parseRequest(request);

					// 업로드된 items 처리
					Iterator<FileItem> iter = items.iterator();
					while (iter.hasNext()) {
						FileItem item = iter.next();

						// 일반 폼 필드 처리 (<input type="file">이 아닌 경우)
						if (item.isFormField()) {
							String name = item.getFieldName(); // 필드 이름
							String value = item.getString("UTF-8"); // 필드 값

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

							// 파일 업로드 처리 (<input type="file">인 경우)
						} else {
							String fieldName = item.getFieldName(); // 필드 이름
							String fileName = item.getName(); // 경로가 포함된 파일명

							System.out.println(fieldName);
							System.out.println(fileName);

							int index = fileName.lastIndexOf("\\"); // 디렉터리 구분자 위치를 통해
							if (index == -1) {
								index = fileName.lastIndexOf("/");
							}
								photoDir = fileName.substring(index + 1); // 파일명만 추출			
								System.out.println(photoDir);
								// 파일 업로드 처리
								File uploadedFile = new File(uploadDir, photoDir);
								item.write(uploadedFile); // 실질적인 저장
							
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

		// searchType, searchText 요청 파라미터 값을 구한다.
		String searchText = request.getParameter("searchText");
		String searchType = request.getParameter("searchType");
		int type = Integer.parseInt(request.getParameter("type"));
		// 검색 옵션을 담고 있는 Map 객체를 생성하여 searchType, searchText 값을 저장한다.
		Map<String, Object> searchInfo = new HashMap<String, Object>();

		String pageNumber = request.getParameter("pageNumber");

		searchInfo.put("searchType", searchType);
		searchInfo.put("searchText", searchText);
		searchInfo.put("type", type);
		// (1) 현재 페이지 번호
		int currentPageNumber = 1;
		if ((pageNumber != null) && (pageNumber.length() != 0)) {
			currentPageNumber = Integer.parseInt(pageNumber);
		}

		// (4) 전체 개시글 개수
		int totalBoardCount = service.getMovieCount(searchInfo);

		// (5) 전체 페이지 개수
		int totalPageCount = MovieUtil.getTotalPageCount(totalBoardCount);

		// (6) 페이지 선택 바에 표시될 시작 페이지 번호
		int startPageNumber = MovieUtil.getStartPageNumber(currentPageNumber);

		// (7) 페이지 선택바에 표시될 끝 페이지 번호
		int endPageNumber = MovieUtil.getEndPageNumber(currentPageNumber,
				totalBoardCount);

		// (8) 현재 페이지의 게시글 목록에서 처음 보여질 게시글의 행 번호
		int startRow = MovieUtil.getStartRow(currentPageNumber);

		// (9) 현재 페이지의 게시글 목록에서 마지막에 보여질 게시글의 행 번호
		int endRow = MovieUtil.getEndRow(currentPageNumber);

		// 검색옵션 Map(searchInfo)에 startRow와 endRow 값을 저장한다.
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

		// 디스크 기반의 FileItem factory 생성
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// repository 생성 (a secure temp location is used)
		ServletContext servletContext = this.getServletConfig()
				.getServletContext();
		File repository = (File) getServletContext().getAttribute(
				"javax.servlet.context.tempdir");
		// File repository = new File(uploadDir, "temp");
		// if (!repository.exists()) { repository.mkdir(); }

		// factory 제약 설정
		factory.setSizeThreshold(1024 * 100);
		// 메모리에 저장할 최대 size (100K까지는 메모리에 저장)
		factory.setRepository(repository);
		// 파일 임시 저장소 (100K 이상이면 repository에 저장)

		// 파일 업로드 핸들러 생성
		ServletFileUpload upload = new ServletFileUpload(factory);
		// 총 request size 제약 설정
		upload.setSizeMax(1024 * 1024 * 20); // 최대 size (20M까지 가능)

		// 요청 파싱
		try {
			List<FileItem> items = upload.parseRequest(request);

			// 업로드된 items 처리
			Iterator<FileItem> iter = items.iterator();
			while (iter.hasNext()) {
				FileItem item = iter.next();

				// 일반 폼 필드 처리 (<input type="file">이 아닌 경우)
				if (item.isFormField()) {
					String name = item.getFieldName(); // 필드 이름
					String value = item.getString("UTF-8"); // 필드 값

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

					// 파일 업로드 처리 (<input type="file">인 경우)
				} else {
					String fieldName = item.getFieldName(); // 필드 이름
					String fileName = item.getName(); // 경로가 포함된 파일명

					System.out.println(fieldName);
					System.out.println(fileName);

					int index = fileName.lastIndexOf("\\"); // 디렉터리 구분자 위치를 통해
					if (index == -1) {
						index = fileName.lastIndexOf("/");
					}
					photoDir = fileName.substring(index + 1); // 파일명만 추출
					System.out.println(photoDir);
					// 파일 업로드 처리
					File uploadedFile = new File(uploadDir, photoDir);
					item.write(uploadedFile); // 실질적인 저장
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
		// // 파일 업로드 처리
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