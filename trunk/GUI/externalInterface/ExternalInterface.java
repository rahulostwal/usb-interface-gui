package externalInterface;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import core.ScriptEvaluator;

public class ExternalInterface extends Thread {
	private List<CommandReceiver> commandReceivers;
	private List<CommandTransmitter> commandTransmitters;
	private String fileName;
	private List<ScriptEvaluator> scriptEvaluators;

	public ExternalInterface(String fileName) {
		this.fileName = fileName;
		commandReceivers = new ArrayList<CommandReceiver>();
		commandTransmitters = new ArrayList<CommandTransmitter>();
		scriptEvaluators = new ArrayList<ScriptEvaluator>();
	}

	public void run() {
		try {
			FileInputStream fis = new FileInputStream(fileName);
			sendCommand("St", 0);
			while (true) {
				int c;
				String line = "";
				while ((c = fis.read()) != -1 && c != 0 && c != '\n') {
					line += (char) c;

				}
				handleInput(line);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int getValue(String command) {
		for (CommandReceiver commandReceiver : commandReceivers) {
			if (commandReceiver.getCommand().equals(command))
				return commandReceiver.getValue();
		}
		for (CommandTransmitter commandTransmitter : commandTransmitters) {
			if (commandTransmitter.getCommand().equals(command))
				return commandTransmitter.getValue();
		}
		return 0;
	}

	public void setValue(String command, int value) {
		for (CommandReceiver commandReceiver : commandReceivers) {
			if (commandReceiver.getCommand().equals(command))
				commandReceiver.setValue(value);
		}
		for (CommandTransmitter commandTransmitter : commandTransmitters) {
			if (commandTransmitter.getCommand().equals(command))
				commandTransmitter.setGetterValue(value);
		}
	}

	private void handleInput(String line) {
		String argv[] = line.split(" ");
		String command = argv[0];
		if (argv.length > 1) {
			int value = Integer.valueOf(argv[1]);
			setValue(command, value);
		}
		runScriptsForCommand(command);
	}

	protected void sendCommand(String command, int value) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
			String output = command + " " + value;
			bw.write(output);
			bw.newLine();
			bw.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public AnalogInput addAnalogInput(String command, ValueGetter valueExposer) {
		AnalogInput input = new AnalogInput(command, valueExposer);
		commandReceivers.add(input);
		return input;
	}

	public DigitalInput addDigitalInput(String command, ValueGetter valueExposer) {
		DigitalInput input = new DigitalInput(command, valueExposer);
		commandReceivers.add(input);
		return input;
	}

	public DigitalOutput addDigitalOutput(String command) {
		DigitalOutput output = new DigitalOutput(command, this);
		commandTransmitters.add(output);
		return output;
	}

	public PWMOutput addPWMOutput(String command) {
		PWMOutput output = new PWMOutput(command, this);
		commandTransmitters.add(output);
		return output;
	}

	private void runScriptsForCommand(String command) {
		for (ScriptEvaluator scriptEvaluator : scriptEvaluators) {
			if (scriptEvaluator.getText() != null
					&& scriptEvaluator.getText().contains(command)) {
				scriptEvaluator.evaluate();
			}
		}
	}

	public void addScriptEvaluator(ScriptEvaluator scriptEvaluator) {
		// TODO Auto-generated method stub
		scriptEvaluators.add(scriptEvaluator);
	}

}
