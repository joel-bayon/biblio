package dao.hibernate;

import javax.transaction.Transactional;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import dao.AdherentDao;
import entity.Adherent;

@Repository
@Transactional
public class AdherentDaoHibernate extends DaoHibernate<Integer, Adherent> implements AdherentDao {

	@Override
	public boolean isPresent(Adherent adherent) {
		final String HQL_SELECT = "from Adherent a where a.nom = :nom and a.prenom = :prenom and a.email = :email";
		Query<Adherent> query = factory.getCurrentSession().createQuery(HQL_SELECT, Adherent.class);
		query.setParameter("nom", adherent.getNom());
		query.setParameter("prenom", adherent.getPrenom());
		query.setParameter("email", adherent.getEmail());
		return query.getResultList().size()!= 0 ;	
	}
}
