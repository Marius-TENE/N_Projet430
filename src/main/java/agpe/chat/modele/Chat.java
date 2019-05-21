package agpe.chat.modele;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import agpe.modeles.Utilisateur;

@Entity
public class Chat {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idChat;
	private String Message;
	private Date dateEnvoi;
	private Date dateLecture;
	private int lu;
	
	@ManyToOne
	@JoinColumn(name = "matriculeExpediteur")
	private Utilisateur expediteur;
	
	@ManyToOne
	@JoinColumn(name = "matriculeRecepteur")
	private Utilisateur recepteur;

	public Chat() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Chat(String message, Date dateEnvoi, int lu, Utilisateur expediteur, Utilisateur recepteur) {
		super();
		Message = message;
		this.dateEnvoi = dateEnvoi;
		this.lu = lu;
		this.expediteur = expediteur;
		this.recepteur = recepteur;
	}

	public Chat(String message, Date dateEnvoi, int lu) {
		super();
		Message = message;
		this.dateEnvoi = dateEnvoi;
		this.lu = lu;
	}

	public Long getIdChat() {
		return idChat;
	}

	public void setIdChat(Long idChat) {
		this.idChat = idChat;
	}

	public String getMessage() {
		return Message;
	}

	public void setMessage(String message) {
		Message = message;
	}

	public Date getDateEnvoi() {
		return dateEnvoi;
	}

	public void setDateEnvoi(Date dateEnvoi) {
		this.dateEnvoi = dateEnvoi;
	}

	public Date getDateLecture() {
		return dateLecture;
	}

	public void setDateLecture(Date dateLecture) {
		this.dateLecture = dateLecture;
	}

	public int getLu() {
		return lu;
	}

	public void setLu(int lu) {
		this.lu = lu;
	}

	public Utilisateur getExpediteur() {
		return expediteur;
	}

	public void setExpediteur(Utilisateur expediteur) {
		this.expediteur = expediteur;
	}

	public Utilisateur getRecepteur() {
		return recepteur;
	}

	public void setRecepteur(Utilisateur recepteur) {
		this.recepteur = recepteur;
	}

	@Override
	public String toString() {
		return "Chat [idChat=" + idChat + ", Message=" + Message + ", dateEnvoi=" + dateEnvoi + ", dateLecture="
				+ dateLecture + ", lu=" + lu + ", expediteur=" + expediteur + "]";
	}

	
}
