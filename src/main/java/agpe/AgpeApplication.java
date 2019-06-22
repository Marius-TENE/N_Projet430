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

@SpringBootApplication
public class AgpeApplication implements CommandLineRunner{
	
	@Autowired 
	private AgpeMetier agpeMetier;
	
	 
	public static void main(String[] args) {
		SpringApplication.run(AgpeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		String nomEtab[] = {"ENSP","ENS","FS","FASLH","IUT","FMSB"};
		Etablissement etab[] = new Etablissement[6];
		for(int i=0;i<5;i++) {
			etab[i]=new Etablissement(nomEtab[i],"Ydé Cameroun","+237691103603",nomEtab[i]+"@gmail.com");
			agpeMetier.enregistrerEtablissement(etab[i]);
		}
		
		Departement depart[]=new Departement[10];
		String nomdepart[] = {"Informatique","Bioloie","Mathématique","Anglais","Histoire","Geographie","LMF","Anthropologie","Tourisme et Hôtellerie","Philosophie"};
		int j=0,k=0;
		for(int i=0;i<9;i++) {
			if(j>1) {
				j=0;
				k=k+1;
			}
			depart[i]=new Departement(nomdepart[i],etab[k]);
			agpeMetier.enregistrerDepartement(depart[i]);
			j=j+1;
		}
		
		
		Categorie cat =new Categorie("Personnel"); 
		agpeMetier.enregistrerCategorie(cat);
		Categorie cat1 =new Categorie("Administratif"); 
		agpeMetier.enregistrerCategorie(cat1);
		Categorie cat3 =new Categorie("Missions"); 
		agpeMetier.enregistrerCategorie(cat3);
		Categorie cat4 =new Categorie("Distictions"); 
		agpeMetier.enregistrerCategorie(cat4);

		//admin
		Utilisateur admin = new Utilisateur("15y511","Marius TENE","M","tefuncowm@gmail.com","admin","admin","Yaoundé","+237691103603",new Date().toString(),1,"admin");
		agpeMetier.enregistrerUtilisateur(admin);
		
		
		Utilisateur user[] = new Utilisateur[40];
		
		k=0;
		j=0;
		String nom[]= {"TENE MBA","ABOUBAKAR","ZEKENG","AWONO","DOUNGUE","TIGOTSAP","BENGA","ARRIFORM","NKOLO","AMANA"
				       ,"MAKOUBA","DJOMO","KAMTO","NGAFI","DJAMKOU","DJOUTIEU","NDONGO","JEUTSA","TCHIYA","MAFFOK"};
		String prenom[]= {"Mariius","Fâtimah","Darryl","Pierrette","Modeste","Stéphane","Alice","Chouchou","Daniel","Falone"
			       ,"Christelle","Mabelle","Ulrich","Goodlove","Ornela","Marlène","Chantale","Ines","Loriane","Jovanie"};
		
		for(int i=0;i<20;i++) {
			if(j>1) {
				k=k+1;
				j=0;
			}
			user[i]=new Utilisateur("15Y52"+String.valueOf(i).toString(), nom[i], prenom[i],"tefuncowm@gmail.com","15Y52"+String.valueOf(i).toString(), "password","Ydé-Cameoun","+237691103603","18-02-1976",1,"Professeur","Fondamentale",null, depart[k],"enseignant","M");
			agpeMetier.enregistrerUtilisateur(user[i]);
			j=j+1;
		}
		
	}

}