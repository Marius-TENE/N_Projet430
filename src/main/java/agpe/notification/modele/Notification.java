package agpe.notification.modele;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Notification {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idNotification;
	private String idUtilisateur;
	private Date dateNotification;
	private String message;
	private Date dateLecture;
	private int lu;
	
	public Notification() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public Notification(String idUtilisateur, Date dateNotification, String message, int lu) {
		super();
		this.idUtilisateur = idUtilisateur;
		this.dateNotification = dateNotification;
		this.message = message;
		this.lu = lu;
	}

	public Long getIdNotification() {
		return idNotification;
	}
	public void setIdNotification(Long idNotification) {
		this.idNotification = idNotification;
	}
	
	
	public String getIdUtilisateur() {
		return idUtilisateur;
	}


	public void setIdUtilisateur(String idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}


	public Date getDateNotification() {
		return dateNotification;
	}
	public void setDateNotification(Date dateNotification) {
		this.dateNotification = dateNotification;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
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


	@Override
	public String toString() {
		return "Notification [idNotification=" + idNotification + ", idUtilisateur=" + idUtilisateur
				+ ", dateNotification=" + dateNotification + ", message=" + message + ", dateLecture=" + dateLecture
				+ ", lu=" + lu + "]";
	}
	
	
}
