package listener;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;

import service.Bibliotheque;
import service.impl.BibliothequeImpl;
import util.DataBaseUtil;
import util.SpringContextProvider;

import dao.AdherentDao;
import dao.EmpruntDao;
import dao.LivreDao;
import dao.hibernate.AdherentDaoHibernate;
import dao.hibernate.EmpruntDaoHibernate;
import dao.hibernate.LivreDaoHibernate;


/**
 * Application Lifecycle Listener implementation class BibliothequeListener
 *
 */
public class BibliothequeListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public BibliothequeListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent event) {
    	ServletContext ctx = event.getServletContext();
		String fileName = ctx.getInitParameter("users");
		String filePath = ctx.getRealPath(fileName);
		System.out.println("path du fichier users="+filePath);
		ApplicationContext spring = SpringContextProvider.getApplicationContext();
		try {
			InputStream input = new FileInputStream(filePath);
			Properties users = new Properties();
			users.load(input);
			ctx.setAttribute("users", users);
			DataBaseUtil.createSchema();
			DataBaseUtil.populateDataBase();
			// création de l'objet de service Bibilotheque dans le servletContext
			ctx.setAttribute("bibliotheque", spring.getBean(Bibliotheque.class));
			// puis des 3 DAOs ...dans le servletContext
			ctx.setAttribute("livreDao", spring.getBean(LivreDao.class));
			ctx.setAttribute("adherentDao", spring.getBean(AdherentDao.class));
			ctx.setAttribute("empruntDao",spring.getBean(EmpruntDao.class));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0) {}
	
}
