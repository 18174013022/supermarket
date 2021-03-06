package com.kexin.user.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kexin.user.service.UserService;
import com.kexin.user.service.UserServiceImpl;

/**
 * Servlet implementation class RegisterUserName
 */
@WebServlet("/RegisterUser")
public class RegisterUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 设置字符集
		request.setCharacterEncoding("UTF-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		try {
			UserService userservice = new UserServiceImpl();
			List<Map<String, String>> list = userservice.fontUserName(username, password);
			// 返回list的值，在servlet里面接收list的值。如果查询的值与数据库匹配，则list.size()>0，登录成功，否则登录失败
			if (list.size() > 0) {
				// session对象传用户名
				HttpSession session = request.getSession();
				session.setAttribute("name", username);
				RequestDispatcher rr = request.getRequestDispatcher("show/success.jsp");
				rr.forward(request, response);
			} else {
				RequestDispatcher rw = request.getRequestDispatcher("show/silp.jsp");
				rw.forward(request, response);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new ServletException(e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
