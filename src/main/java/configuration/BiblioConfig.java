package configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import dao.AdherentDao;
import dao.EmpruntDao;
import dao.LivreDao;
import service.Bibliotheque;
import service.impl.BibliothequeImpl;

@Configuration
@ImportResource("classpath:/spring/spring.jpa.xml")
public class BiblioConfig {
	
	@Bean
	public Bibliotheque bibliotheque(
			@Value("${maxLivreIdentique}") Integer maxLivreIdentique, 
			@Value("${maxEmpruntAdherent}") Integer maxEmpruntAdherent,
			LivreDao livreDao, EmpruntDao empruntDao, AdherentDao adherentDao) {
		BibliothequeImpl biblio = new BibliothequeImpl(maxLivreIdentique, maxEmpruntAdherent);
		biblio.setAdherentDao(adherentDao);
		biblio.setEmpruntDao(empruntDao);
		biblio.setLivreDao(livreDao);
		return biblio;
	}

}
