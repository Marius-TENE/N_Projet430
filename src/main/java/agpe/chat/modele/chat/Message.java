package agpe.chat.modele.chat;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Message {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idMessage;
	
	@NotNull
	private String idExpediteur;
	
	@NotNull
	private String idDestinataire;
	
	private String message;
	
	@NotNull
	private Date dateExpedition;
	
	private Date dateLecture;

	public Message() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Message(Long idMessage, @NotNull String idExpediteur, @NotNull String idDestinataire, String message) {
		super();
		this.idMessage = idMessage;
		this.idExpediteur = idExpediteur;
		this.idDestinataire = idDestinataire;
		this.message = message;
	}

	public Long getIdMessage() {
		return idMessage;
	}

	public void setIdMessage(Long idMessage) {
		this.idMessage = idMessage;
	}

	public String getIdExpediteur() {
		return idExpediteur;
	}

	public void setIdExpediteur(String idExpediteur) {
		this.idExpediteur = idExpediteur;
	}

	public String getIdDestinataire() {
		return idDestinataire;
	}

	public void setIdDestinataire(String idDestinataire) {
		this.idDestinataire = idDestinataire;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getDateExpedition() {
		return dateExpedition;
	}

	public void setDateExpedition(Date dateExpedition) {
		this.dateExpedition = dateExpedition;
	}

	public Date getDateLecture() {
		return dateLecture;
	}

	public void setDateLecture(Date dateLecture) {
		this.dateLecture = dateLecture;
	}

	
}
