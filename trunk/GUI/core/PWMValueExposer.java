package core;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

import externalInterface.ValueGetter;

public class PWMValueExposer implements ValueGetter {
	private JSlider slider;
	private JLabel label;
	
	JSlider getSlider() {
		return slider;
	}

	public PWMValueExposer(String text, int min, int max) {
		slider = new JSlider(JSlider.HORIZONTAL, min, max, min);
		label = new JLabel("0");
	}

	void setBounds(int x, int y, int width, int height) {
		slider.setBounds(x, y, width-50, height);
		label.setBounds(width-40, y, 40, height);
	}

	@Override
	public void setValue(int value) {
		slider.setValue(value);
		label.setText(Integer.toString(value));
	}

	public void addToJPanel(JPanel panel) {
		panel.add(slider);
		panel.add(label);
	}

}
