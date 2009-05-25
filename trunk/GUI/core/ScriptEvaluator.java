package core;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.script.ScriptEngine;
import javax.script.ScriptException;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;

import com.sun.script.javascript.RhinoScriptEngine;

import externalInterface.ExternalInterface;

public class ScriptEvaluator implements ActionListener {
	ScriptEngine engine;
	private ExternalInterface externalInterface;
	private JTextArea textArea;
	boolean autoEval = false;

	public ScriptEvaluator(ExternalInterface externalInterface,
			JTextArea textArea) {
		this.externalInterface = externalInterface;
		this.textArea = textArea;
		engine = new RhinoScriptEngine();
		engine.put("ei", this);
		externalInterface.addScriptEvaluator(this);
	}

	public void set(String command, int value) {
		externalInterface.setValue(command, value);
	}

	public int get(String command) {
		return externalInterface.getValue(command);
	}

	public Object evaluate() {
		try {
			return engine.eval(textArea.getText());
		} catch (ScriptException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getSource() instanceof JButton) {
			System.out.println(evaluate());
		} else if (arg0.getSource() instanceof JCheckBox) {
			autoEval = ((JCheckBox) arg0.getSource()).isSelected();
		}

	}

	public String getText() {
		if (autoEval)
			return textArea.getText();
		else
			return null;
	}

}
