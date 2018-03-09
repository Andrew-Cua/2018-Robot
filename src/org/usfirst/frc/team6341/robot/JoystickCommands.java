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
	
	public double getPOV()
	{
		double POVstate = stick.getPOV();
		return POVstate;
	}
	public boolean R1() {
		boolean R1Pressed = stick.getRawButton(6);//Replace
		return R1Pressed;
	}
	public boolean L1() {
		boolean L1Pressed = stick.getRawButton(5);//Replace
		return L1Pressed;
	}
	public double leftTrigger() {
		double leftTriggerValue = stick.getRawAxis(2);
		return leftTriggerValue;
		
	}
	public double rightTrigger() {
		double rightTriggerValue = stick.getRawAxis(3);
		return rightTriggerValue;
	}
}
