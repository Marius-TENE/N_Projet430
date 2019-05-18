package agpe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import agpe.mail.MailRequest;
import agpe.metier.AgpeMetier;
import agpe.modeles.Categorie;
import agpe.modeles.Departement;
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
		
		Departement depart = new Departement("informatique");
		agpeMetier.enregistrerDepartement(depart);
		Categorie cat =new Categorie("Personnel");
		agpeMetier.enregistrerCategorie(cat);
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