package ch.hearc.jms.log_receiver;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Log {
	private String timestamp;
	private String action;
	private String ressource;
	private String adress;

	@Override
	public String toString() {
		return "[" + timestamp + "]:" + adress + " performed " + action + " on " + ressource;
	}
}
