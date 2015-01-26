import java.io.IOException;
import java.io.Serializable;

@SuppressWarnings("serial")
public class CommandTask implements Runnable, Serializable {
	private String command;
	public CommandTask(String command) {
		this.command = command; 
	}
	public void run() {
		try {
			Runtime.getRuntime().exec(command);
		} catch (IOException e) {} 
	}
}