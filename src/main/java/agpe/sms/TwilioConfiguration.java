package agpe.sms;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("twilio")
public class TwilioConfiguration {
	private String accountSid;
	private String autToken;
	private String trialNumber;
	public TwilioConfiguration() {
		super();
		
	}
	public String getAccountSid() {
		return accountSid;
	}
	public void setAccountSid(String accountSid) {
		this.accountSid = accountSid;
	}
	public String getAutToken() {
		return autToken;
	}
	public void setAutToken(String autToken) {
		this.autToken = autToken;
	}
	public String getTrialNumber() {
		return trialNumber;
	}
	public void setTrialNumber(String trialNumber) {
		this.trialNumber = trialNumber;
	}
	
	
	
}
