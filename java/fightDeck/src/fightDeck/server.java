package fightDeck;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import jssc.SerialPort;
import jssc.SerialPortException;

public class server {

	// Serial Port Variables
	SerialPort mySerialPort;
	
	//The following comment is the location for linux machines. Change the number to the port number of your device. HINT: Check device manager or arduino IDE
	// String serialPortLocation = "/dev/ttyACM0";
	
	//The following line is the location for Window's Machines. Change the number to the port number of your device. HINT: Check device manager or arduino IDE
	String serialPortLocation = "COM3";
	
	//SerialPort Settings
	int[] serialPortSettings = new int[] { SerialPort.BAUDRATE_115200, SerialPort.DATABITS_8, SerialPort.STOPBITS_1,
			SerialPort.PARITY_NONE, SerialPort.FLOWCONTROL_NONE };

	String data;
	char myChar;

	public static void main(String[] args) 
	{
		// Allows us to use stuff without making everything static. 
		server myserver = new server();
	}

	public server() 
	{
		//Initialize serial port
		initSerialPort();
		
		//Read Data
		readFromSerial();
	}
	
	//Read data from serial port
	public void readFromSerial()
	{
		try 
		{
			//Since this is a server and will always be running we need an infinite loop
			while (true) 
			{

				//Read from serial port
				myChar = (char) mySerialPort.readBytes(1)[0];
				
				//Store data read by serial port
				data += myChar;
				
				//When a new line character is read do stuff
				if (data.endsWith("\n")) 
				{
					test();
					data = data.trim();
					
					//Reset the string storing input from arduino to prepare for next round of input.
					data = "";
				}
			}
		} 
		catch (SerialPortException e) 
		{
			//We crashed for some reason
			e.printStackTrace();
		}
	}

	//Setup the serial port. There is no need to alter any of this code. 
	public void initSerialPort() 
	{
		try 
		{
			mySerialPort = new SerialPort(serialPortLocation);
			mySerialPort.openPort();
			mySerialPort.setParams(serialPortSettings[0], serialPortSettings[1], serialPortSettings[2], serialPortSettings[3]);
			mySerialPort.setFlowControlMode(SerialPort.FLOWCONTROL_NONE);
		} 
		catch (SerialPortException e) 
		{
			e.printStackTrace();
		}
	}

	//KEYBOARD STUFF. This is the magical keyboard typing section that makes shit happen. 
	public void test() 
	{
		try {
			Robot robot = new Robot();

			// Simulate a key press by printing "I am alive" in caps.
			robot.keyPress(KeyEvent.VK_I);
			robot.keyRelease(KeyEvent.VK_I);
			robot.keyPress(KeyEvent.VK_SPACE);
			robot.keyRelease(KeyEvent.VK_SPACE);
			robot.keyPress(KeyEvent.VK_A);
			robot.keyRelease(KeyEvent.VK_A);
			robot.keyPress(KeyEvent.VK_M);
			robot.keyRelease(KeyEvent.VK_M);
			robot.keyPress(KeyEvent.VK_SPACE);
			robot.keyRelease(KeyEvent.VK_SPACE);
			robot.keyPress(KeyEvent.VK_A);
			robot.keyRelease(KeyEvent.VK_A);
			robot.keyPress(KeyEvent.VK_L);
			robot.keyRelease(KeyEvent.VK_L);
			robot.keyPress(KeyEvent.VK_I);
			robot.keyRelease(KeyEvent.VK_I);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyPress(KeyEvent.VK_E);
			robot.keyRelease(KeyEvent.VK_E);

		} 
		catch (AWTException e) 
		{
			//We crashed for some reason
			e.printStackTrace();
		}
	}
}
