package externalInterface;


public class DigitalOutput extends CommandTransmitter{

	public DigitalOutput(String command,
			ExternalInterface externalInterface) {
		this.command = command;
		this.externalInterface = externalInterface;		
	}
}
