package dao.hibernate;

import javax.transaction.Transactional;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import dao.LivreDao;
import entity.Livre;

@Repository
@Transactional
public class LivreDaoHibernate extends DaoHibernate<Integer, Livre> implements LivreDao {
	
	@Override
	public int getCount(Livre livre){
		final String HQL_QUERY = "select count(l) from Livre l  where l.titre = :titre and l.auteur = :auteur";

		Query<Number> query = factory.getCurrentSession().createQuery(HQL_QUERY, Number.class);
		query.setParameter("titre", livre.getTitre());
		query.setParameter("auteur", livre.getAuteur());
		return query.getSingleResult().intValue();
	}
}
