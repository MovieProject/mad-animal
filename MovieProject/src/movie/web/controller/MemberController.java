package movie.web.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import movie.business.domain.Member;
import movie.business.exception.DataDuplicatedException;
import movie.business.exception.DataNotFoundException;
import movie.business.service.MemberService;
import movie.business.service.MemberServiceImpl;
import movie.util.MovieUtil;

/**
 * Servlet implementation class MemberController
 */
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");

		try {
			if (action.equals("login")) {
				login(request, response);
			} else if (action.equals("logout")) {
				logout(request, response);
			} else if (action.equals("select")) {
				selectMember(request, response);
			} else if (action.equals("update")) {
				updateMember(request, response);
			} else if (action.equals("remove")) {
				removeMember(request, response);
			} else if (action.equals("register")) {
				registerMember(request, response);
			}
		} catch (DataDuplicatedException e) {
			// TODO Auto-generated catch block
			throw new ServletException(e.getMessage());
		} catch (DataNotFoundException e) {
			throw new ServletException(e.getMessage());

		}

	}

	private void registerMember(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			DataDuplicatedException {

	}

	private void removeMember(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			DataNotFoundException {

	}

	private void updateMember(HttpServletRequest request,
			HttpServletResponse response) {

	}

	private void selectMember(HttpServletRequest request,
			HttpServletResponse response)  throws ServletException, IOException,DataNotFoundException{

	}

	private void logout(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{
		HttpSession session = request.getSession(false);
		if(session!=null){
			session.removeAttribute("loginMember");
			session.invalidate();
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// TODO Auto-generated method stub
		String memberID = request.getParameter("memberID");
		String password = request.getParameter("password");
		MemberService service = new MemberServiceImpl();
		Member member = service.loginCheck(memberID, password);
		System.out.println(member);
		int check = member.getLoginCheck();
		
		
		if(check == MovieUtil.VALID_MEMBER){
			HttpSession session = request.getSession();
			session.setAttribute("loginMember", member);
			RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
			dispatcher.forward(request, response);
		}else{
			String loginErrorMsg =null;
			if(check == MovieUtil.INVALID_ID){
				loginErrorMsg = "아이디 ㄴㄴ";
			}else if(check == MovieUtil.INVALID_PASSWORD){
				loginErrorMsg = "패스워드  ㄴㄴ";
			}
			request.setAttribute("loginErrorMsg", loginErrorMsg);
			RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
			dispatcher.forward(request, response);
			
		}

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
