package agpe.chat.web;

public class ChatDao {
	
	private String message;

	public ChatDao(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
