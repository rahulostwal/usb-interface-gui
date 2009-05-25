package externalInterface;


public class DigitalInput extends CommandReceiver {

	public DigitalInput(String command, ValueGetter valueExposer) {
		this.command = command;
		this.valueExposer = valueExposer;
	}
}