package core;

import javax.swing.JPanel;
import javax.swing.JProgressBar;

import externalInterface.ValueGetter;

public class AnalogValueExposer implements ValueGetter {
	private JProgressBar progressBar;

	public AnalogValueExposer(String text, int max) {
		progressBar = new JProgressBar(0, max);
		progressBar.setToolTipText(text);
	}

	@Override
	public void setValue(int value) {
		progressBar.setValue(value);
	}

	void setBounds(int x, int y, int width, int height) {
		progressBar.setBounds(x, y, width, height);
	}

	public void addToJPanel(JPanel panel) {
		// TODO Auto-generated method stub
		panel.add(progressBar);
	}
}
