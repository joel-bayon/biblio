package dao.jpa;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import dao.LivreDao;
import entity.Livre;


@Repository
@Transactional
public class LivreDaoJpa extends DaoJpa<Integer, Livre> implements LivreDao {
	
	
	@Override
	public int getCount(Livre livre) {
		final String requete = "select count(l) from Livre l where l.auteur = :auteur " +
				"and l.titre = :titre and l.parution = :parution";

		TypedQuery<Number> query = em
				.createQuery(requete, Number.class);
		query.setParameter("auteur", livre.getAuteur());
		query.setParameter("titre", livre.getTitre());
		query.setParameter("parution", livre.getParution());
		return  query.getSingleResult().intValue();
	}

	
	
}
