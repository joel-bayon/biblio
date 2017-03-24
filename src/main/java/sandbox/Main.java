package sandbox;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
	static SessionFactory sf;
	public static void main(String[] args) {

		ConfigurableApplicationContext factory = new ClassPathXmlApplicationContext("/spring/spring.bean.xml");
		System.out.println("*** debut de l'appli ****");
		
		UnClient client = factory.getBean(UnClient.class);
		System.out.println(client==factory.getBean(UnClient.class));
		client.faitSaVie();
		factory.close();
		
		Session session =  sf.openSession();  //????
		session = sf.getCurrentSession();  //????

	}

}
