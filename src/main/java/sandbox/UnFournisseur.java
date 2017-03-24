package sandbox;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;


@Component("sonFournisseur")
@Primary
public class UnFournisseur implements Fournisseur {
	
	@Value("le fournisseur")
	String nom;
	
	public UnFournisseur() {}

	public UnFournisseur(String nom) {
		super();
		this.nom = nom;
	}
	
	@Override
	public String toString() {
		return "UnFournisseur [nom=" + nom + "]";
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	@Override
	public void faireQQChose() {
		System.out.println(nom + " fait quelque chose ...!");

	}

}
