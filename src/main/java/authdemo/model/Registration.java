package authdemo.model;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;

import authdemo.hibernate.HibernateUtil;
import authdemo.hibernate.entity.UserEntity;

public class Registration extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String regName = request.getParameter("regName");
		String regPassword = request.getParameter("regPassword");
		String reg = registration(regName, regPassword);
		response.getWriter().println(reg);	
	}

	private String registration(String name, String pass) {
		Session sessionOne = HibernateUtil.getSessionFactory().openSession();
		sessionOne.beginTransaction();
		String registr = "";
		try{
			if(pass.trim().length() == 0){
				registr = "The password that you've entered is incorrect. Repeat please.";
			} else {
				UserEntity user = new UserEntity();
				user.setName(name);
				user.setPassword(pass);
				sessionOne.save(user);
				sessionOne.getTransaction().commit();
				registr = "WELCOME! You are succefully registrated! Please, log in.";
			}
		}catch(ConstraintViolationException e){
			registr = "Sorry, username " + "<b>"+name+"</b>"+ " already exists";
		}
		return registr;
	}
}
