package dao.jdbc;

import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import util.DataBaseUtil;
import dao.LivreDao;
import entity.Livre;

public class TestLivreDaoJdbc {
	
	
	
	static BeanFactory factory = new ClassPathXmlApplicationContext("spring.test.xml", TestLivreDaoJdbc.class);
	LivreDao livreDao = factory.getBean(LivreDao.class);
	
	
	@BeforeClass
	public static void clearContext() {
		DataBaseUtil.createSchema();
		
	}
	
	@Test
	public void updateTest() {
		Livre livre = factory.getBean("Amééélie Nothomb", Livre.class); //new Livre("Stupeur et tremblements",1999, "Amééélie Nothomb");
		livreDao.save(livre);
		livre = livreDao.load(livre.getId());
		livre.setAuteur("Amélie Nothomb");
		livreDao.update(livre);
		Assert.assertEquals("Amélie Nothomb", livreDao.load(livre.getId()).getAuteur());
		
		livreDao.removeAll(livreDao.loadAll());
		Assert.assertEquals(0, livreDao.loadAll().size());
	}
	
	
	@Test
	public  void daoTest() {
		Livre livre = factory.getBean("Amélie Nothomb", Livre.class);//new Livre("Stupeur et tremblements",1999, "Amélie Nothomb");
		livreDao.save(livre);
		System.out.println(livre.getId());
		livreDao.save(new Livre("L'étranger",1942, "Albert Camus"));
		livreDao.save(new Livre("Réglez-lui son compte !",1949, "Frédéric Dard"));
		livreDao.save(factory.getBean("Hergé", Livre.class));
		
		for(Livre l : livreDao.loadAll())
			System.out.println(l);
		
		Assert.assertEquals("Amélie Nothomb", livreDao.load(livre.getId()).getAuteur());
		livreDao.removeById(livre.getId());
		List<Livre> livres = livreDao.loadAll();
		Assert.assertEquals(3, livres.size());
		
		livre = factory.getBean("Hergé", Livre.class);
		livreDao.save(livre);
		Assert.assertEquals(2, livreDao.getCount(livreDao.load(livre.getId())));
		
		
		livreDao.removeAll(livreDao.loadAll());
		Assert.assertEquals(0, livreDao.loadAll().size());

	}
	
	@Test 
	public  void daoExceptionTest() {
		Livre livre = factory.getBean("Amélie Nothomb", Livre.class);
		livreDao.save(livre);
		Livre l  = livreDao.load(livre.getId());
		Assert.assertEquals("Amélie Nothomb", l.getAuteur());
		livreDao.remove(l);
		l = livreDao.load(livre.getId());
		Assert.assertNull(l);
	}
	
	@Test (expected=RuntimeException.class)
	public  void constraintViolation()  {
		Livre l = new Livre();
		livreDao.save(l); //violation de la contrainte NOT NULL --> trace d'exception sur la console !
		Assert.fail();
	}

}
