package core;

import javax.swing.JPanel;
import javax.swing.JSlider;

import externalInterface.ValueGetter;

public class PWMValueExposer implements ValueGetter {
	private JSlider slider;

	JSlider getSlider() {
		return slider;
	}

	public PWMValueExposer(String text, int max) {
		slider = new JSlider(JSlider.HORIZONTAL, 0, max, 0);
	}

	void setBounds(int x, int y, int width, int height) {
		slider.setBounds(x, y, width, height);
	}

	@Override
	public void setValue(int value) {
		slider.setValue(value);
	}

	public void addToJPanel(JPanel panel) {
		panel.add(slider);
	}

}
