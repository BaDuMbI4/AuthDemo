package authdemo;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import authdemo.hibernate.HibernateUtil;
import authdemo.hibernate.entity.UserEntity;
import authdemo.model.DAO;
import authdemo.model.impl.DAOimplHibernate;

public class AuthServlet extends HttpServlet {

	DAO dao = new DAOimplHibernate();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
		response.setContentType("text/html");  
		PrintWriter out=response.getWriter();  
		request.getRequestDispatcher("link.html").include(request, response);  
		String name1 = null;
		Cookie ck[]=request.getCookies();
		for(int i = 0; i < ck.length; i++){
			if (ck[i].getValue().substring(ck[i].getValue().length()-6, ck[i].getValue().length()).equals("cookie")){
				name1 = ck[i].getValue().substring(0, ck[i].getValue().length()-6);
				break;
			}
		}
		if(name1!=null){  
			out.print("<b>Welcome to Profile</b>");  
			out.println("<br>Welcome, "+ "<b>"+name1+"</b>");  
		}else{  
			out.print("Please login first");  
			request.getRequestDispatcher("index.jsp").include(request, response);  
		}  
		out.close();  
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("link.html").include(req, resp);  
		String name = req.getParameter("paramName");
		String password = req.getParameter("paramPassword");
		HttpSession session1 = req.getSession();
		if (login(name, password)) {
			Cookie ck = new Cookie("name", name+"cookie");
			ck.setMaxAge(60*60*24);
			resp.addCookie(ck);
			req.getRequestDispatcher("Main.html").include(req, resp);  
//			RequestDispatcher view = req.getRequestDispatcher("Main.html");
//			view.forward(req, resp);
		} else {
			resp.getWriter().println("Password is incorrect!");
		}
	}

	private boolean login(String name, String pass) {
		Session sessionOne = HibernateUtil.getSessionFactory().openSession();
		sessionOne.beginTransaction();
		sessionOne.getTransaction().commit();
		String passFrombase = dao.getPassword(name);

		if(pass.equals(passFrombase)) {
			return true;
		} else {
			return false;
		}
	}
}
