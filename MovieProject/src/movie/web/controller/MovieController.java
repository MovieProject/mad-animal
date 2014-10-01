package movie.web.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import movie.business.domain.Movie;
import movie.business.exception.DataDuplicatedException;
import movie.business.exception.DataNotFoundException;
import movie.business.service.MovieService;
import movie.business.service.MovieServiceImpl;

/**
 * Servlet implementation class MovieController
 */
public class MovieController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String action = request.getParameter("action");
		try{
			if(action.equals("writeMovie")){
				writeMovie(request,response);
			}else if(action.equals("readMoive")){
				readMovie(request,response);
			}
			
		}catch (DataDuplicatedException e) {
			// TODO Auto-generated catch block
			throw new ServletException(e.getMessage());
		} catch (DataNotFoundException e) {
			throw new ServletException(e.getMessage());

		}

	}

	private void readMovie(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,DataNotFoundException{
		
		// TODO Auto-generated method stub
		
	}

	private void writeMovie(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,DataDuplicatedException{
		// TODO Auto-generated method stub
		String movieName = request.getParameter("title");
		String genre = request.getParameter("genre");
		String director = request.getParameter("director");
		String releaseDate = request.getParameter("releaseDate").trim();
		String synopsis = request.getParameter("synopsis");
		String photoDir = "µ¥Çò";
		int memberGrade =0;//Integer.parseInt( request.getParameter("memberGrade"));
		Movie movie = new Movie(movieName, genre, director, releaseDate, synopsis, photoDir, memberGrade);
		System.out.println(movie);
		MovieService service = new MovieServiceImpl();
		service.writeMovie(movie);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("member_Recommend.jsp");
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
