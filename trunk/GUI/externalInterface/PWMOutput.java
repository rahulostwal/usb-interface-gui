package externalInterface;


public class PWMOutput extends CommandTransmitter {

	public PWMOutput(String command, ExternalInterface externalInterface) {
		this.command = command;
		this.externalInterface = externalInterface;
	}
}