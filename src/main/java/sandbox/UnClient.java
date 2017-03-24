package sandbox;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Component
@Scope(scopeName=BeanDefinition.SCOPE_SINGLETON)
public class UnClient {
	 
	String nom;
	
	@Autowired(required=true)
	//@Qualifier("sonFournisseur")
	Fournisseur  sonFournisseur;
	
	
	@Autowired
	public UnClient(@Value("un client") String nom, @Qualifier("autreFournisseur") Fournisseur  sonFournisseur) {
		System.out.println("UnClient(String nom, Fournisseur  sonFournisseur)");
		this.nom = nom;
		this.sonFournisseur = sonFournisseur;
	}
	
	public UnClient() {System.out.println("UnClient");}
	
	
	public Fournisseur getSonFournisseur() {
		return sonFournisseur;
	}

	public void setSonFournisseur(Fournisseur sonFournisseur) {
		this.sonFournisseur = sonFournisseur;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	@Override
	public String toString() {
		return "UnClient [nom=" + nom + "]";
	}
	
	public void faitSaVie() {
		//sonFournisseur = new AutreFournisseur(12);
		System.out.println(nom + " ... je vis ma vie et sous-traite le boulot ...");
		System.out.println();
		sonFournisseur.faireQQChose();
	}
	
	
}
