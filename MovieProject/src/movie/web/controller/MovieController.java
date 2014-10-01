package movie.web.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
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
import org.apache.commons.fileupload.FileUploadException;
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

	public void init() throws ServletException{
		System.out.println("실행됨");
		uploadDir = new File(getServletContext().getRealPath("/" + getInitParameter("uploadDir")));
		System.out.println(getServletContext().getRealPath("/" + getInitParameter("uploadDir")));
		if (!uploadDir.exists()) { uploadDir.mkdir(); }
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String action = request.getParameter("action");
		try{
			if(action.equals("writeMovie")){
				writeMovie(request,response);
			}else if(action.equals("movieRead")){
				readMovie(request,response);
			}else if(action.equals("movielist")){
				movielist(request,response);
			}else if(action.equals("remove")){
				removeMovie(request,response);
				
			}
						
		}catch (DataDuplicatedException e) {
			// TODO Auto-generated catch block
			throw new ServletException(e.getMessage());
		} catch (DataNotFoundException e) {
			throw new ServletException(e.getMessage());

		}

	}

	private void removeMovie(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,DataNotFoundException {
		// TODO Auto-generated method stub
		int num =Integer.parseInt(request.getParameter("num"));
		MovieService service = new MovieServiceImpl();
		service.removeMovie(num);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/movie?action=movielist");
		dispatcher.forward(request, response);
		
	}

	private void movielist(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		MovieService service = new MovieServiceImpl();
		Map<String, Object> searchInfo = new HashMap<String, Object>();
		Movie[] movielist = service.getMovieList(searchInfo );
		request.setAttribute("movielist",movielist);
		RequestDispatcher dispatcher = request.getRequestDispatcher("member_Recommend.jsp");
		dispatcher.forward(request, response);
		
		
	}

	private void readMovie(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,DataNotFoundException{
		String movieNum = request.getParameter("num");
		MovieService service = new MovieServiceImpl();
		Movie movie = service.findMovie(Integer.parseInt(movieNum));
		request.setAttribute("movie", movie);
		RequestDispatcher dispatcher = request.getRequestDispatcher("readMovie.jsp");
		dispatcher.forward(request, response);
		
		
		
		// TODO Auto-generated method stub
		
	}

	private void writeMovie(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,DataDuplicatedException{
		// TODO Auto-generated method stub
		String movieName = request.getParameter("title");
		String genre = request.getParameter("genre");
		String director = request.getParameter("director");
		String releaseDate =request.getParameter("releaseDate");
		String synopsis =request.getParameter("synopsis");
		String photoDir = request.getParameter("file");
		int memberGrade = Integer.parseInt(request.getParameter("memberGrade"));
//	    if(name.equals("title")){
//	    	movieName = value;
//	    }else if(name.equals("genre")){
//	    	genre = value;
//	    }else if(name.equals("director")){
//	    	director = value;
//		}else if(name.equals("releaseDate")){
//			releaseDate = value;
//		}else if(name.equals("synopsis")){
//			synopsis = value;
//		}
		
//		// 디스크 기반의 FileItem factory 생성
//				DiskFileItemFactory factory = new DiskFileItemFactory();
//				// repository 생성 (a secure temp location is used)
//				ServletContext servletContext = this.getServletConfig().getServletContext();
//				File repository = (File) getServletContext().getAttribute("javax.servlet.context.tempdir");
//				//File repository = new File(uploadDir, "temp");  
//				//if (!repository.exists()) { repository.mkdir(); }
//				
//				// factory 제약 설정
//				factory.setSizeThreshold(1024 * 100); // 메모리에 저장할 최대 size (100K까지는 메모리에 저장)
//				factory.setRepository(repository);	// 파일 임시 저장소 (100K 이상이면 repository에 저장)	
//				
//				// 파일 업로드 핸들러 생성
//				ServletFileUpload upload = new ServletFileUpload(factory);
//				// 총 request size 제약 설정
//				upload.setSizeMax(1024 * 1024 * 20); // 최대 size (20M까지 가능)
//				
//				// 요청 파싱
//				try {
//					List<FileItem> items = upload.parseRequest(request);
//					
//					// 업로드된 items 처리
//					Iterator<FileItem> iter = items.iterator();
//					while (iter.hasNext()) {    
//						FileItem item = iter.next();
//						
//						// 일반 폼 필드 처리 (<input type="file">이 아닌 경우)
//						if (item.isFormField()) {
//						    String name = item.getFieldName(); // 필드 이름
//						    String value = item.getString(); // 필드 값
//						    
//						    System.out.println(name +" : " + value);
//						    
//						    if(name.equals("title")){
//						    	movieName = value;
//						    }else if(name.equals("genre")){
//						    	genre = value;
//						    }else if(name.equals("director")){
//						    	director = value;
//							}else if(name.equals("releaseDate")){
//								releaseDate = value;
//							}else if(name.equals("synopsis")){
//								synopsis = value;
//							}
//						    
//						 // 파일 업로드 처리 (<input type="file">인 경우)
//						} else {
//							String fieldName = item.getFieldName(); // 필드 이름
//							String fileName = item.getName(); // 경로가 포함된 파일명
//							
//							System.out.println(fieldName);
//							System.out.println(fileName);
//
//							int index = fileName.lastIndexOf("\\"); // 디렉터리 구분자 위치를 통해
//							if (index == -1) {
//								index = fileName.lastIndexOf("/");
//							}
//							photoDir = fileName.substring(index + 1); // 파일명만 추출
//							System.out.println(photoDir);
//							// 파일 업로드 처리
//							File uploadedFile = new File(uploadDir, photoDir);
//							item.write(uploadedFile); // 실질적인 저장			
//							}
//					}
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
		Movie movie = new Movie(movieName, genre, director, releaseDate, synopsis, photoDir, memberGrade);
		System.out.println(movie);
		
		
		
//	      	File uploadDir = new File(getServletContext().getRealPath("/" + getInitParameter("uploadDir")));
//	         // 파일 업로드 처리
//	         File uploadedFile = new File(photoDir);  
//	         System.out.println(uploadDir);
//	         System.out.println(uploadedFile);
//	         if (!uploadDir.exists()) { uploadDir.mkdir(); }
//	         MovieUtil.createThumbnail(uploadDir, uploadedFile, 160, 160);   
		
		MovieService service = new MovieServiceImpl();
		service.writeMovie(movie);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/movie?action=movielist");
		dispatcher.forward(request, response);
	}
		/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request,response);
	}

}
