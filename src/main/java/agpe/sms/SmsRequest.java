package agpe.sms;

public class SmsRequest {
	private final String numeroDestinataire;
	private final String message;
	public SmsRequest(String numeroDestinataire, String message) {
		super();
		this.numeroDestinataire = numeroDestinataire;
		this.message = message;
	}
	public String getNumeroDestinataire() {
		return numeroDestinataire;
	}
	public String getMessage() {
		return message;
	}
	@Override
	public String toString() {
		return "SmsRequest [numeroDestinataire=" + numeroDestinataire + ", message=" + message + "]";
	}
	
	
}
