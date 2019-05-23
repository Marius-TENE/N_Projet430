package agpe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import agpe.metier.AgpeMetier;
import agpe.modeles.Categorie;
import agpe.modeles.Departement;
import agpe.modeles.Etablissement;
import agpe.modeles.Utilisateur;
import agpe.sms.SmsRequest;

@SpringBootApplication
public class AgpeApplication implements CommandLineRunner{
	
	@Autowired 
	private AgpeMetier agpeMetier;
	
	 
	public static void main(String[] args) {
		SpringApplication.run(AgpeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Etablissement etablissement = new Etablissement("ENS","Yaoundé-Melen","+237691103603","tefuncowm@yahoo.fr");
		agpeMetier.enregistrerEtablissement(etablissement);
		Departement depart = new Departement("informatique");
		depart.setEtablissement(etablissement);
		agpeMetier.enregistrerDepartement(depart); 
		Categorie cat =new Categorie("Personnel"); 
		agpeMetier.enregistrerCategorie(cat);



		Utilisateur u = new Utilisateur("15Y511","TENE MBA","tefuncowm@gmail.com","15Y511", "groupe7","yde", "+237691103603", "dgf", 1, "Professeur", "Professeur", depart, "enseignant","M");
		Utilisateur u1 = new
				Utilisateur("15Y512","ZEKENG","tefuncowm@yahoo.com","15Y512", "groupe7","doula", "+237697973191", "dgff", 1, "Docteur", "Docteur", depart, "admin","M");
		agpeMetier.enregistrerUtilisateur(u1); agpeMetier.enregistrerUtilisateur(u);

		/*
		 * Utilisateur ut = new
		 * Utilisateur("15Y511","TENE MBA","MARIUS","tefuncowm@gmail.com","15Y511",
		 * "groupe7","yde","+237691103603","uhgk",1,1,"Prof","fkdn",depart);
		 * 
		 * agpeMetier.enregistrerUTilisateur(ut);
		 */

		/*
		 * SmsRequest smsRequest = new
		 * SmsRequest("+237691103603","welcome to agpe application.");
		 * agpeMetier.envoyerSms(smsRequest);
		 */

		//		
		//		MailRequest mailRequest = new MailRequest("tefuncowm@gmail.com","Welcome to agpe application","registration on agpe");
		//		MailRequest mailRequest1 = new MailRequest("fredjordan288@gmail.com","Welcome to agpe application","registration on agpe");
		//		
		//		agpeMetier.envoyerMail(mailRequest);
		//		agpeMetier.envoyerMail(mailRequest1);

	}

}