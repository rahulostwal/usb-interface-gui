package externalInterface;


public class AnalogInput extends CommandReceiver {

	public AnalogInput(String command, ValueGetter valueExposer) {
		this.command = command;
		this.valueExposer = valueExposer;
	}
}
