package agpe.portfolio.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import agpe.chat.modele.Chat;
import agpe.metier.AgpeMetier;
import agpe.modeles.Utilisateur;
import agpe.notification.modele.Notification;
import agpe.portfolio.modele.Piece;

@Controller
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private AgpeMetier agpeMetier;
    
   
    @PostMapping("/uploadFile")
    public String uploadFile(@RequestParam("file") MultipartFile file,HttpSession httpSession,String nom,String idCategorie) {
    	System.out.print("\n\nLien : "+idCategorie+"\n\n");
    	Utilisateur user = (Utilisateur) httpSession.getAttribute("user");
    	Piece dbFile = agpeMetier.enregistrerPiece(file,user,agpeMetier.retournerCategorie(Integer.valueOf(idCategorie).intValue()).get(),nom,user.getMatricule());
    	return "redirect:/enseignant";
		/*
		 * return new UploadFileResponse(dbFile.getNomPiece(), fileDownloadUri,
		 * file.getContentType(), file.getSize());
		 */
    }
    
    @Secured(value = {"ROLE_admin"})
    @PostMapping("/admin/uploadFile/{matricule}")
    public ModelAndView uploadFile_Admin(@RequestParam("file") MultipartFile file,String nom,String idCategorie,@PathVariable String matricule,HttpSession httpSession) {
    	//System.out.print("\n\nMatricule: "+matricule);
    	ModelAndView mav = new ModelAndView();
    	mav.clear();
    	
    	Utilisateur user = agpeMetier.chercherUtiliateurAvecMatricule(matricule).get();
    	Utilisateur user1 = (Utilisateur) httpSession.getAttribute("user");
    	Piece dbFile = agpeMetier.enregistrerPiece(file,user,agpeMetier.retournerCategorie(Integer.valueOf(idCategorie).intValue()).get(),nom,user.getMatricule());
    	mav.setViewName("redirect:/admin/portfolio_"+matricule);
    	
    	ArrayList<Notification> dernierenotification = agpeMetier.notificationsRecus(user1);
		ArrayList<Notification> notificationNonLu = agpeMetier.notificationsNonLus(user1);
		ArrayList<Chat> messageNonsLus = agpeMetier.listeMessageNonLu(user1);
		ArrayList<Chat> dernierMessages = agpeMetier.cinqDernierMessages(user1);
		int nbre_message_non_lus = messageNonsLus.size();
		
		int nbre_notification_non_lu = notificationNonLu.size();
		System.out.print("ok \n\n" + nbre_notification_non_lu+"\n\n\n");
		ArrayList<Notification> cinqdernieresNotification = new ArrayList<Notification>();
		if(dernierenotification.size()>5) {
			for(int i=0;i<5;i++) {
				cinqdernieresNotification.add(dernierenotification.get(i));
			}
		}
		else {
			cinqdernieresNotification=dernierenotification;
		}
		mav.addObject("nbre_notifs", cinqdernieresNotification.size());
		mav.addObject("notifications",cinqdernieresNotification);
		mav.addObject("nbre_notif", nbre_notification_non_lu);
		mav.addObject("messages", dernierMessages);
		mav.addObject("user",user);
		mav.addObject("nbre_messages", nbre_message_non_lus);
		
    	return mav;
    }
    
    @GetMapping("/telechargement/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long fileId) {
      
        Piece  dbFile = agpeMetier.chercherPiece(fileId);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(dbFile.getExtensionPiece()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dbFile.getNomPiece() + "\"")
                .body(new ByteArrayResource(dbFile.getData()));
    }
    
    @GetMapping("/supprimer/{fileId}")
    public String supprimerPiece(@PathVariable Long fileId) {
    	System.out.print("\n\nfdsbfs\n\n");
        Piece  dbFile = agpeMetier.chercherPiece(fileId);
        agpeMetier.deletePiece(dbFile,dbFile.getUtilisateur().getMatricule());
        return "redirect:/enseignant";
    }
    
    @Secured(value = "ROLE_admin")
    @GetMapping("/supprimerAdmin/{fileId}")
    public ModelAndView supprimerPiece2(@PathVariable Long fileId) {
    	ModelAndView mav = new ModelAndView();
    	mav.clear();
        Piece  dbFile = agpeMetier.chercherPiece(fileId);
        mav.setViewName("redirect:/admin/portfolio_"+dbFile.getUtilisateur().getMatricule());
        agpeMetier.deletePiece(dbFile,dbFile.getUtilisateur().getMatricule());
        return mav;
    }

}
