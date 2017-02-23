package authdemo;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import authdemo.hibernate.HibernateUtil;

public class HibernateInitContextListener implements ServletContextListener {

    public void contextDestroyed(ServletContextEvent sce)  {
    	HibernateUtil.getSessionFactory().close();
    }

    public void contextInitialized(ServletContextEvent sce)  { 
    	HibernateUtil.getSessionFactory();
    }	
}
