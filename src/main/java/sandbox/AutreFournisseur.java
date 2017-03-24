package sandbox;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component   //id = autreFournisseur
public class AutreFournisseur implements Fournisseur {
	
	int matricule;
	
	public AutreFournisseur() {}

	@Autowired
	public AutreFournisseur(@Value("12") int matricule) {
		super();
		this.matricule = matricule;
	}
	
	@Override
	public String toString() {
		return "UnFournisseur [matricule =" + matricule + "]";
	}

	public int getNom() {
		return matricule;
	}

	public void setNom(int matricule) {
		this.matricule = matricule;
	}

	@Override
	public void faireQQChose() {
		System.out.println(matricule + " fait quelque chose de mieux ...!");

	}

}
