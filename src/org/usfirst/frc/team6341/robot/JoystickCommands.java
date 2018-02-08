package org.usfirst.frc.team6341.robot;

import edu.wpi.first.wpilibj.Joystick;

public class JoystickCommands {

	public JoystickCommands()
	{
		
	}
	
	Joystick stick = new Joystick(0);
	
	public boolean getB() 
	{
		boolean bButton = stick.getRawButton(2);
		return bButton;
	}
	
	
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
	 boolean TriggerPressed = stick.getTrigger();
	 boolean TriggerState = false;
	 if(TriggerPressed && !TriggerState) {
		 TriggerState = true;
	 }else if(TriggerPressed && TriggerState) {
		 TriggerState = false;
	 }
	 return TriggerState;
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
	public double getRX() {
		double RX = stick.getRawAxis(4);
		return RX;
	}
	
}
