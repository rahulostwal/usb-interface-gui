package externalInterface;


public class CommandReceiver {
	protected String command;
	protected int value;
	protected ValueGetter valueExposer;

	public String getCommand() {
		return command;
	}

	public int getValue() {
		// TODO Auto-generated method stub
		return value;
	}
	public void setValue(int value) {
		this.value = value;
		valueExposer.setValue(value);
	}


}
