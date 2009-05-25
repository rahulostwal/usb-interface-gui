package core;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import externalInterface.DigitalOutput;
import externalInterface.ExternalInterface;
import externalInterface.PWMOutput;
import externalInterface.ValueGetter;

@SuppressWarnings("serial")
public class Gui extends JFrame {
	JPanel jPanel;
	int currentY = 10;
	int currentX = 10;
	int maxRowHeight = 0;

	Gui() {
		jPanel = new JPanel();
		jPanel.setLayout(null);
		this.setSize(500, 450);
		this.add(jPanel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	void setXYForItem(int width, int height) {
		if (currentX + width > this.getWidth()) {
			newRow(height);
		} else {
			if (maxRowHeight < height)
				maxRowHeight = height;
		}
	}

	public void newRow(int height) {
		if (maxRowHeight < height)
			maxRowHeight = height;
		currentY += maxRowHeight + 10;
		currentX = 10;
		maxRowHeight = 0;
	}

	public AnalogValueExposer addAnalogValueExposer(String text, int max,
			int width, int height) {
		AnalogValueExposer analog = new AnalogValueExposer(text, max);
		setXYForItem(width, height);
		analog.setBounds(currentX, currentY, width, height);
		currentX += width;
		analog.addToJPanel(jPanel);
		return analog;
	}

	public void run() {

		setVisible(true);
	}

	public ValueGetter addDigitalInput(String text, int width, int height) {
		DigitalValueExposer digital = new DigitalValueExposer(text);
		setXYForItem(width, height);
		digital.setBounds(currentX, currentY, width, height);
		currentX += width;
		digital.addToJPanel(jPanel);
		return digital;
	}

	public void addSlider(String text, PWMOutput pwmOutput, int width,
			int height) {
		PWMValueExposer exposer = new PWMValueExposer(text, 255);
		setXYForItem(width, height);
		exposer.setBounds(currentX, currentY, width, height);
		currentX += width;
		exposer.getSlider().addChangeListener(pwmOutput);
		pwmOutput.setValueGetter(exposer);
		exposer.addToJPanel(jPanel);
	}

	public ValueGetter addDigitalOutput(String text, DigitalOutput output,
			int width, int height) {
		DigitalValueExposer exposer = new DigitalValueExposer(text);
		setXYForItem(width, height);
		exposer.setBounds(currentX, currentY, width, height);
		currentX += width;
		exposer.addToJPanel(jPanel);
		output.setValueGetter(exposer);
		exposer.getCheckBox().addItemListener(output);
		return exposer;
	}

	public void addScriptBox(ExternalInterface ei, int width, int height) {
		JTextArea text = new JTextArea();
		JButton button = new JButton();
		JCheckBox checkBox = new JCheckBox();
		button.setText("Evaluate!");
		checkBox.setText("Auto eval");
		ScriptEvaluator scriptEvaluator = new ScriptEvaluator(ei, text);
		button.addActionListener(scriptEvaluator);
		checkBox.addActionListener(scriptEvaluator);
		setXYForItem(width, 0);
		text.setBounds(currentX, currentY, width - 120, height);
		currentX += width - 120;
		button.setBounds(currentX, currentY, 120, 25);
		checkBox.setBounds(currentX, currentY + 30, 120, 25);
		jPanel.add(text);
		jPanel.add(button);
		jPanel.add(checkBox);
		currentX += width;
	}
}
