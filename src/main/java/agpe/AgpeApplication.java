package agpe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import agpe.metier.AgpeMetier;
import agpe.sms.Service;
import agpe.sms.SmsRequest;

@SpringBootApplication
public class AgpeApplication implements CommandLineRunner{
	
	  @Autowired 
	  private AgpeMetier agpeMetier;
	  
	  @Autowired
	  private Service service;

	 
	public static void main(String[] args) {
		SpringApplication.run(AgpeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		/*
		 * Utilisateur user = new
		 * Utilisateur("15y511","TENE MBA","Marius Firmin","tefuncowm@gmail.com",
		 * "15y511","groupe7","Yaounde","691103603","Yaounde",1,"Informatique",
		 * "Professeur","Genie Logiciel",r); agpeMetier.enregistrerUTilisateur(user);
		 */
		SmsRequest smsRequest = new SmsRequest("+237699425427","welcome to agpe application.");
		service.envoyerSms(smsRequest);
		
	}

}