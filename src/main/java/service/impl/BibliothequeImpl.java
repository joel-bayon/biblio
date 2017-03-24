package service.impl;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import service.Bibliotheque;
import dao.AdherentDao;
import dao.EmpruntDao;
import dao.LivreDao;
import entity.Adherent;
import entity.BusinessException;
import entity.Emprunt;
import entity.Livre;


@Service
@Transactional
public class BibliothequeImpl implements Bibliotheque {
	final int maxLivreIdentique;
	final int maxEmpruntAdherent;
	
	@Autowired 
	LivreDao livreDao;
	@Autowired 
	AdherentDao adherentDao;
	@Autowired 
	EmpruntDao empruntDao;
	
	@Autowired
	public BibliothequeImpl(@Value("${"
			+ "}") Integer maxLivreIdentique, @Value("${maxEmpruntAdherent}") Integer maxEmpruntAdherent) {
		this.maxLivreIdentique = maxLivreIdentique; 
		this.maxEmpruntAdherent = maxEmpruntAdherent;
	}

	@Override
	public int getMaxEmpruntAdherent() { 
		return maxEmpruntAdherent;
	}
	
	@Override
	public int getMaxLivreIdentique() {
		return maxLivreIdentique;
	}
	
	@Override
	public int ajouterLivre(Livre livre)  {
		//RG : maxLivreIdentique ?
		if(livreDao.getCount(livre) == maxLivreIdentique ) 
			throw new BusinessException("BibliothequeImpl.ajouterLivre", null);
		livreDao.save(livre);
		return livre.getId();
	}


	@Override
	public void retirerLivre(int idLivre) {
		//RG : livre vacant ?
		if(empruntDao.getEmpruntEnCoursByLivre(idLivre) != null) 
			throw new BusinessException("BibliothequeImpl.retirerLivre", null); 
		//détruire d'abord les anciens emprunts puis le live ....
		empruntDao.removeAll(empruntDao.getAllByLivre(idLivre));
		livreDao.removeById(idLivre);
	}

	@Override
	public int ajouterAdherent(Adherent adherent) {
		//RG est déjà Present ?
		if(adherentDao.isPresent(adherent))
			throw new BusinessException("BibliothequeImpl.ajouterAdherent", null); 
		adherentDao.save(adherent);
		return adherent.getId();
	}


	@Override
	public void retirerAdherent(int idAdherent) {
		//RG : adherent vacant ?
		if(empruntDao.getEmpruntsEnCoursByAdherent(idAdherent).size() > 0)
			throw new BusinessException("BibliothequeImpl.retirerAdherent", null); 
		//détruire d'abord les anciens emprunts puis l'adhérent ....
		empruntDao.removeAll(empruntDao.getAllByAdherent(idAdherent));
		adherentDao.removeById(idAdherent);	
	}

	@Override
	public void emprunterLivre(int idLivre, int idAdherent) {
		//RG : maxEmpruntAdherent ?
		if( empruntDao.getAllByAdherent(idAdherent).size() == maxEmpruntAdherent)
			throw new BusinessException("BibliothequeImpl.emprunterLivre", null); 
		//RG : livre déjà emprunté !
		if(empruntDao.getEmpruntEnCoursByLivre(idLivre) != null)
			throw new BusinessException("BibliothequeImpl.emprunterLivre", null); 

		empruntDao.save(new Emprunt(livreDao.load(idLivre), adherentDao.load(idAdherent)));
	}
	
	@Override
	public void restituerLivre(int idLivre, int idAdherent) {
		// RG : un emprunt doit exist� avec le couple idLivre/idAdherent
		Emprunt emprunt = empruntDao.getEmpruntEnCoursByLivre(idLivre);
		if(emprunt == null || emprunt.getAdherent().getId() != idAdherent)
			throw new BusinessException("BibliothequeImpl.restituerLivre", null);  /// A finir ...
		emprunt.setFin(new Date());
		empruntDao.update(emprunt);
		
	}

	@Override
	public void transfererEmprunt(int idAdherentPrecedent, int idLivre,
			int idAdherentSuivant) {
		restituerLivre(idLivre, idAdherentPrecedent);
		emprunterLivre(idLivre, idAdherentSuivant);
		
	}

	public LivreDao getLivreDao() {
		return livreDao;
	}

	public void setLivreDao(LivreDao livreDao) {
		this.livreDao = livreDao;
	}

	public AdherentDao getAdherentDao() {
		return adherentDao;
	}

	public void setAdherentDao(AdherentDao adherentDao) {
		this.adherentDao = adherentDao;
	}

	public EmpruntDao getEmpruntDao() {
		return empruntDao;
	}

	public void setEmpruntDao(EmpruntDao empruntDao) {
		this.empruntDao = empruntDao;
	}
	
	

	
}
