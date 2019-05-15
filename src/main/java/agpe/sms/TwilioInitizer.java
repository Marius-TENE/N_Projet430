package agpe.sms;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.twilio.Twilio;

@Configuration
public class TwilioInitizer {
	private final static Logger LOGGER= org.slf4j.LoggerFactory.getLogger(TwilioInitizer.class);
	private final TwilioConfiguration twilioConfiguration;
	
	@Autowired
	public TwilioInitizer(TwilioConfiguration twilioConfiguration) {
		this.twilioConfiguration = twilioConfiguration;
		Twilio.init(
				twilioConfiguration.getAccountSid(),
				twilioConfiguration.getAutToken()
		);
		LOGGER.info("Twilio a été initialisé..... avec le compte sid {}",twilioConfiguration.getAccountSid());;
	}
	
}
