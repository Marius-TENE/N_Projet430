package agpe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import agpe.metier.AgpeMetier;
import agpe.modeles.Role;
import agpe.modeles.Utilisateur;

@SpringBootApplication
public class AgpeApplication implements CommandLineRunner{
	
	  @Autowired 
	  private AgpeMetier agpeMetier;

	 
	public static void main(String[] args) {
		SpringApplication.run(AgpeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Role r = new Role("Enseignant");
		Role r1 = new Role("Sspe");
		Role r2 = new Role("Admin");
		agpeMetier.ajouterRole(r);
		agpeMetier.ajouterRole(r1);
		agpeMetier.ajouterRole(r2);
			
		Utilisateur user = new Utilisateur("15y511","TENE MBA","Marius Firmin","tefuncowm@gmail.com",
				"15y511","groupe7","Yaounde","691103603","Yaounde",1,"Informatique","Professeur","Genie Logiciel",r);
		agpeMetier.enregistrerUTilisateur(user);
		
	}

}