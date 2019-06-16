package agpe.chat.web;

import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import agpe.chat.modele.Chat;
import agpe.metier.AgpeMetier;
import agpe.modeles.Utilisateur;

@Controller
public class ChatController {
	
	@Autowired
	private AgpeMetier agpeMetier;
	
	@ModelAttribute("chatForm")
	public ChatDao chat() {
		return new ChatDao();
	}
	
	@GetMapping("/chat/{fileId}")
	public ModelAndView AfficherFormulaireChat(HttpSession httpSession,@PathVariable String idDestinataire) {
		Utilisateur user = (Utilisateur) httpSession.getAttribute("user");
		ModelAndView mav = new ModelAndView();
		mav.clear();
		mav.setViewName("pages/chat");
		ArrayList<Chat> messages = agpeMetier.listeEchangeAvecUtilisateur(agpeMetier.chercherUtilisateurAvecLogin(idDestinataire),user);
		mav.addObject("message",messages);
		return mav;
		
	}
	
	@PostMapping
	@Transactional
	public void handlePassordReset(@ModelAttribute("chatForm") ChatDao form,HttpSession httpSession) {
		Chat chat = new Chat(form.getMessage(),new Date(),0,(Utilisateur) httpSession.getAttribute("user"),agpeMetier.chercherUtilisateurAvecLogin((String) httpSession.getAttribute("matricule")));
		agpeMetier.enregistrerChat(chat);
		//AfficherFormulaireChat(httpSession);
	}
}
