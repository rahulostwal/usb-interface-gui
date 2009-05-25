package core;

import javax.swing.JCheckBox;
import javax.swing.JPanel;

import externalInterface.ValueGetter;

public class DigitalValueExposer implements ValueGetter {
	private JCheckBox checkBox;

	JCheckBox getCheckBox(){
		return checkBox;
	}
	
	public DigitalValueExposer(String text) {
		checkBox = new JCheckBox();
		checkBox.setText(text);
	}

	void setBounds(int x, int y, int width, int height) {
		checkBox.setBounds(x, y, width, height);
	}

	@Override
	public void setValue(int value) {
		if (value == 0)
			checkBox.setSelected(false);
		else
			checkBox.setSelected(true);
	}

	public void addToJPanel(JPanel panel) {
		panel.add(checkBox);
		
	}

}
