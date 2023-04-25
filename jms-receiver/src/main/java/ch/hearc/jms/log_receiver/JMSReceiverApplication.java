package ch.hearc.jms.log_receiver;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.JmsListener;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
public class JMSReceiverApplication {

	@Autowired
	ObjectMapper mapper;

	static Logger logger;

	public static void main(String[] args) {
		logger = LoggerFactory.getLogger(JMSReceiverApplication.class);

		SpringApplication.run(JMSReceiverApplication.class, args);
	}

	/**
	 * Listener jms avec conversion json
	 * 
	 * @param jsonMessage
	 * @throws JMSException
	 */
	@JmsListener(destination = "${spring.activemq.json-queue}")
	public void readInprogressJsonMessage(final Message jsonMessage) throws JMSException {

		String messageData = null;

		if (jsonMessage instanceof TextMessage) {
			Log log = null;

			TextMessage textMessage = (TextMessage) jsonMessage;
			messageData = textMessage.getText();

			try {
				log = mapper.readValue(messageData, Log.class);
				logger.info(log.toString());

			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}
}
