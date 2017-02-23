package authdemo;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;

import authdemo.hibernate.HibernateUtil;
import authdemo.hibernate.entity.MessageEntity;

public class HeaderClass extends HttpServlet{
	
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
//			RequestDispatcher view = request.getRequestDispatcher("Main.html");
//			view.forward(request, response);
			request.getRequestDispatcher("Main.html").include(request, response); 
		}else{  
			out.print("Please log in first");  
			request.getRequestDispatcher("index.jsp").include(request, response);  
		}  
		out.close(); 
	}  
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");  
		PrintWriter out=resp.getWriter();  
		req.getRequestDispatcher("link.html").include(req, resp);  
		
		String name = req.getParameter("receiver");
		String text = req.getParameter("message");
		HttpSession session1 = req.getSession();
		String name1 = null;
		Cookie ck[]=req.getCookies();
		for(int i = 0; i < ck.length; i++){
			if (ck[i].getValue().substring(ck[i].getValue().length()-6, ck[i].getValue().length()).equals("cookie")){
				name1 = ck[i].getValue().substring(0, ck[i].getValue().length()-6);
				break;
			}
		}
		if (createDb(name, text, name1)) {
			out.print("Your message has been delivered.");
			req.getRequestDispatcher("Main.html").include(req, resp); 
		};
	}
	
	private boolean createDb(String name, String text, String name1) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		//Привязка даты
		Date now = new Date();
		String date = DateFormat.getDateTimeInstance().format(now);
		
		MessageEntity emp = new MessageEntity();
		emp.setSender(name1);
		emp.setReceiver(name);
		emp.setMessage(text);
		emp.setDate(date);
		
		session.save(emp);
		
		session.getTransaction().commit();
		HibernateUtil.shutdown();
		return true;
	}
	
}
