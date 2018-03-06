package org.usfirst.frc.team6341.robot;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Command;

public class ledDriver {
	
	
	Spark ledDriver;
	JoystickCommands ledCom;
	double ledPow = 0D;
	public ledDriver(int ledDriverPin) 
	{
		this.ledDriver = new Spark(ledDriverPin);
		this.ledCom = new JoystickCommands();
	}
	
	public void increasePow()
	{
		ledPow = ledPow + .02;
		ledDriver.set(ledPow);
	}
	public void decreasePow()
	{
		ledPow = ledPow - .02;
		ledDriver.set(ledPow);
	}
	
	
	

}
