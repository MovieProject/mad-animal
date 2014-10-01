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
		System.out.println("�����");
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
		
//		// ��ũ ����� FileItem factory ����
//				DiskFileItemFactory factory = new DiskFileItemFactory();
//				// repository ���� (a secure temp location is used)
//				ServletContext servletContext = this.getServletConfig().getServletContext();
//				File repository = (File) getServletContext().getAttribute("javax.servlet.context.tempdir");
//				//File repository = new File(uploadDir, "temp");  
//				//if (!repository.exists()) { repository.mkdir(); }
//				
//				// factory ���� ����
//				factory.setSizeThreshold(1024 * 100); // �޸𸮿� ������ �ִ� size (100K������ �޸𸮿� ����)
//				factory.setRepository(repository);	// ���� �ӽ� ����� (100K �̻��̸� repository�� ����)	
//				
//				// ���� ���ε� �ڵ鷯 ����
//				ServletFileUpload upload = new ServletFileUpload(factory);
//				// �� request size ���� ����
//				upload.setSizeMax(1024 * 1024 * 20); // �ִ� size (20M���� ����)
//				
//				// ��û �Ľ�
//				try {
//					List<FileItem> items = upload.parseRequest(request);
//					
//					// ���ε�� items ó��
//					Iterator<FileItem> iter = items.iterator();
//					while (iter.hasNext()) {    
//						FileItem item = iter.next();
//						
//						// �Ϲ� �� �ʵ� ó�� (<input type="file">�� �ƴ� ���)
//						if (item.isFormField()) {
//						    String name = item.getFieldName(); // �ʵ� �̸�
//						    String value = item.getString(); // �ʵ� ��
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
//						 // ���� ���ε� ó�� (<input type="file">�� ���)
//						} else {
//							String fieldName = item.getFieldName(); // �ʵ� �̸�
//							String fileName = item.getName(); // ��ΰ� ���Ե� ���ϸ�
//							
//							System.out.println(fieldName);
//							System.out.println(fileName);
//
//							int index = fileName.lastIndexOf("\\"); // ���͸� ������ ��ġ�� ����
//							if (index == -1) {
//								index = fileName.lastIndexOf("/");
//							}
//							photoDir = fileName.substring(index + 1); // ���ϸ� ����
//							System.out.println(photoDir);
//							// ���� ���ε� ó��
//							File uploadedFile = new File(uploadDir, photoDir);
//							item.write(uploadedFile); // �������� ����			
//							}
//					}
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
		Movie movie = new Movie(movieName, genre, director, releaseDate, synopsis, photoDir, memberGrade);
		System.out.println(movie);
		
		
		
//	      	File uploadDir = new File(getServletContext().getRealPath("/" + getInitParameter("uploadDir")));
//	         // ���� ���ε� ó��
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
