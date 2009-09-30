package core;

import java.io.File;

import externalInterface.ExternalInterface;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Starting gui");
		Gui gui = new Gui();
		File findFile = null;
		int i = 0;
		do {
			findFile = new File("/dev/ttyACM" + i++);
		} while (!findFile.exists() && i < 10);
		if (findFile.exists()) {
			System.out.println("Using device: " + findFile.getAbsolutePath());
			ExternalInterface ei = new ExternalInterface(findFile
					.getAbsolutePath());
			ei.addDigitalInput("B:0", gui.addDigitalInput("Knapp 1", 100, 20));
			gui.addDigitalOutput("LED1", ei.addDigitalOutput("C:0"), 300, 20);
			ei.addDigitalInput("B:1", gui.addDigitalInput("Knapp 2", 100, 20));
			gui.addDigitalOutput("LED2", ei.addDigitalOutput("C:1"), 300, 20);
			ei.addAnalogInput("A:0", gui.addAnalogValueExposer(
					"Analogingång 1", 1023, 400, 20));
			ei.addAnalogInput("A:1", gui.addAnalogValueExposer(
					"Analogingång 2", 1023, 400, 20));
			// gui.addSlider("PWM 3", ei.addPWMOutput("P:0"), 400, 20, 255);
			// gui.addSlider("PWM 4", ei.addPWMOutput("P:1"), 400, 20, 255);
			// gui.addSlider("PWM 5", ei.addPWMOutput("P:2"), 400, 20, 255);
			// gui.addSlider("PWM 6", ei.addPWMOutput("P:7"), 400, 20, 256);
			// gui.addSlider("PWM Max", ei.addPWMOutput("PM"), 400, 20,
			// 256*256);
			// gui.addSlider("Built-In PWM2 DC", ei.addPWMOutput("PWM:2"), 400,
			// 20, 256*4);
			// gui.addSlider("Built-In PWM2 Period", ei.addPWMOutput("OPW:2"),
			// 400, 20, 256);
			gui.addSlider("PWM 0", ei.addPWMOutput("P:0"), 400, 20, 0, 9000);
			gui.addSlider("PWM 7", ei.addPWMOutput("P:7"), 400, 20, 0, 9000);
			gui.addSlider("TimerResetVal", ei.addPWMOutput("RV"), 400, 20, 5000,
					10000);
			gui.addDigitalOutput("Digital 7", ei.addDigitalOutput("D:4"), 100,
					20);
			gui.addDigitalOutput("Digital 8", ei.addDigitalOutput("D:5"), 100,
					20);
			gui.addDigitalOutput("Digital 9", ei.addDigitalOutput("D:6"), 100,
					20);
			gui.addDigitalOutput("Digital 10", ei.addDigitalOutput("D:7"), 100,
					20);
			gui.addScriptBox(ei, 450, 130);
			ei.start();
			gui.run();
		} else
			System.out.println("Couldn't find device, quitting!");
	}
}
