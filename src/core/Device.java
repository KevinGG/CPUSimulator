package core;

import java.util.logging.Logger;

import javax.swing.JOptionPane;

import core.CPU.CPUStatus;

public class Device {
	private static Device device = null;
	
	private static final Logger logger = Logger.getLogger(Device.class.getName());
	
	private Device() {
		logger.info("Booting Device: " + this.hashCode());
	}

	public static Device getInstance() {
		if (device == null) {
			device = new Device();
		}
		
		return device;
	}
	
	public String getCharacter() {
		//String in = Main.input.getText();
	    String in = JOptionPane.showInputDialog(null,
                "Enter a character to input",
                "Input Device",
                JOptionPane.WARNING_MESSAGE);
		logger.info("Acquired input from device: " + in);
		Main.input.setText("");
		return in;
	}
	
	public void setCharacter(String s) {
		Main.output.setText(Main.output.getText() + s + " ");
		logger.info("Set output to device: " + s);
	}
}
