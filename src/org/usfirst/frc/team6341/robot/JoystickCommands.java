package org.usfirst.frc.team6341.robot;

import edu.wpi.first.wpilibj.Joystick;

public class JoystickCommands {

	public JoystickCommands()
	{
		
	}
	
	Joystick stick = new Joystick(0);
	
	
	public boolean getSideButton() 
	{
		boolean joystickButton = stick.getRawButton(3);
		return joystickButton;
	
	}
	
	public boolean TwelvePressed()
	{
		boolean twelvePressed = stick.getRawButton(8);
		return twelvePressed;
	}
	public boolean NinePressed()
	{
		boolean NinePressed = stick.getRawButton(9);
		return NinePressed;
	}
	public boolean TenPressed()
	{
		boolean TenPressed = stick.getRawButton(10);
		return TenPressed;
	}
	public boolean TriggerPressed()
	{
		boolean triggerPressed = stick.getTrigger();
		return triggerPressed;
	}
	public double getY() 
	{
		double y = stick.getY();
		return y;
	}
	public double getX()
	{
		double x = stick.getX();
		return x;
	}
	public double getTwist() 
	{
		double twist = stick.getTwist();
		return twist;
	}
}
