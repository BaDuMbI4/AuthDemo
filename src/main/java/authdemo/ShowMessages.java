package authdemo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import authdemo.hibernate.HibernateUtil;
import authdemo.hibernate.entity.MessageEntity;

public class ShowMessages extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session1 = request.getSession();
		String name1 = null;
		Cookie ck[]=request.getCookies();
		for(int i = 0; i < ck.length; i++){
			if (ck[i].getValue().substring(ck[i].getValue().length()-6, ck[i].getValue().length()).equals("cookie")){
				name1 = ck[i].getValue().substring(0, ck[i].getValue().length()-6);
				break;
			}
		}
		//проверка вывода сообщений из БД
		List<String> s2 = takeMessage(name1);
		request.setAttribute("messages", s2);
		RequestDispatcher view1 = request.getRequestDispatcher("ShowMSG.jsp");
		view1.forward(request, response);
	}


	private List<String> takeMessage(String name){
		Criteria criteria1 = HibernateUtil.getSessionFactory().openSession().createCriteria(MessageEntity.class);
		criteria1.add(Restrictions.eq("receiver", name));
		List<MessageEntity> lst1 = criteria1.list();
		List<String> msgList = new ArrayList<String>();
		for(MessageEntity ue : lst1) {
			String l = "Sender: " + ue.getSender() +"   |   "+ "Message: " + ue.getMessage() +"   |   " + "Date: " + ue.getDate();
			msgList.add(l);
		}
		return msgList;
	}
}
