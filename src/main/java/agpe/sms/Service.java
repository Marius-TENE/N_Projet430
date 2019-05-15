package agpe.sms;

@org.springframework.stereotype.Service
public class Service {
	private final SmsSenderImplementation smsSenderImplementation;

	public Service(SmsSenderImplementation smsSenderImplementation) {
		this.smsSenderImplementation = smsSenderImplementation;
	}
	
	public void envoyerSms(SmsRequest smsRequest) {
		smsSenderImplementation.envoyerMessage(smsRequest);
	}
	
}
