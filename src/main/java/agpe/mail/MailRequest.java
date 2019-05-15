package agpe.mail;

public class MailRequest {
	
	private String emailRecepeteur;
	private String message;
	private String objet;
	public MailRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MailRequest(String emailRecepeteur, String message, String objet) {
		super();
		this.emailRecepeteur = emailRecepeteur;
		this.message = message;
		this.objet = objet;
	}
	public MailRequest(String emailRecepeteur, String message) {
		super();
		this.emailRecepeteur = emailRecepeteur;
		this.message = message;
	}
	public String getEmailRecepeteur() {
		return emailRecepeteur;
	}
	public void setEmailRecepeteur(String emailRecepeteur) {
		this.emailRecepeteur = emailRecepeteur;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getObjet() {
		return objet;
	}
	public void setObjet(String objet) {
		this.objet = objet;
	}

	
}
