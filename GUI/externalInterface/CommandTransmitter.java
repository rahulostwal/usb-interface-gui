package externalInterface;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class CommandTransmitter implements ChangeListener, ItemListener{
	protected String command;
	protected int value;
	protected ExternalInterface externalInterface;
	protected ValueGetter valueGetter;

	public void setGetterValue(int value) {
		this.value = value;
		valueGetter.setValue(value);
	}


	public void setValueGetter(ValueGetter valueGetter) {
		this.valueGetter = valueGetter;
	}


	public String getCommand() {
		return command;
	}
	
	public int getValue() {
		return value;
	}

	public void setInterfaceValue(int value) {
		this.value = value;
		externalInterface.sendCommand(command, value);
		valueGetter.setValue(value);
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		if (e.getSource() instanceof JSlider) {
			JSlider slider = (JSlider) e.getSource();
			setInterfaceValue(slider.getValue());
		}
	}

	@Override
	public void itemStateChanged(ItemEvent arg0) {
		if (arg0.getSource() instanceof JCheckBox) {
			JCheckBox checkbox = (JCheckBox) arg0.getSource();
			int value=0;
			if(checkbox.isSelected())
				value=1;
			setInterfaceValue(value);
		}
	}

}
