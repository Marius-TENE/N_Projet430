package agpe;

import java.util.Date;

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
		Etablissement etablissement1 = new Etablissement("ENSp","Yaoundé-Melen","+237691103603","tefuncowm@yahoo.fr");
		agpeMetier.enregistrerEtablissement(etablissement);
		//agpeMetier.envoyerSms(new SmsRequest("+237655925875","gggg"));
		Departement depart = new Departement("informatique");
		Departement depart1 = new Departement("Mathématique");
		Departement depart3 = new Departement("Mathématique");
		Departement depart4 = new Departement("Biologie");
		depart.setEtablissement(etablissement);
		depart1.setEtablissement(etablissement);
		depart3.setEtablissement(etablissement1);
		depart4.setEtablissement(etablissement1);
		agpeMetier.enregistrerDepartement(depart); 
		Categorie cat =new Categorie("Personnel"); 
		agpeMetier.enregistrerCategorie(cat);
		Categorie cat1 =new Categorie("Administratif"); 
		agpeMetier.enregistrerCategorie(cat1);



		Utilisateur u = new Utilisateur("15Y511","TENE MBA","tefuncowm@gmail.com","15Y511", "groupe7","yde", "+237691103603", "dgf", 1, "Professeur", "Professeur", depart, "enseignant","M");
		agpeMetier.enregistrerUtilisateur(u);
		Utilisateur u1 = new Utilisateur("15y512","ZEKENG","M","zekeng@gmail.com","15y512","groupe7","Douala","+237691103603",new Date().toString(),1,"admin");
		agpeMetier.enregistrerUtilisateur(u1);

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