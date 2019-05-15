package agpe.sms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;

@Service
public class SmsSenderImplementation implements SmsSender {
	
	private final TwilioConfiguration twilioConfiguration;
	
	@Autowired
	public SmsSenderImplementation(TwilioConfiguration twilioConfiguration) {
		this.twilioConfiguration=twilioConfiguration;
	}
	
	@Override
	public void envoyerMessage(SmsRequest smsRequest) {
		if(isPhoneNumberValid(smsRequest.getNumeroDestinataire())) {
			PhoneNumber to = new PhoneNumber(smsRequest.getNumeroDestinataire());
			PhoneNumber from = new PhoneNumber(twilioConfiguration.getTrialNumber());
			String message = smsRequest.getMessage();
			MessageCreator creator = Message.creator(to, from, message);
			creator.create();
		}
		else {
			throw new IllegalArgumentException("Numrero ["+smsRequest.getNumeroDestinataire()+"] n'est pas valide");
		
	}
	
}

	private boolean isPhoneNumberValid(String numeroDestinataire) {
		return true;
	}
}