package org.usfirst.frc.team6341.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Solenoid;

public class PneumaticsControl {

	public PneumaticsControl() 
	{
		
	}
	
	Compressor c = new Compressor(12);
	
	Solenoid launchTreb = new Solenoid(0);//change
	
	Solenoid returnTreb = new Solenoid(1);//change
	
	JoystickCommands stick = new JoystickCommands();
	
	public void enableCompressor()
	{
		if(stick.TwelvePressed())
		{

			c.setClosedLoopControl(true);
			System.out.println("Number 12:" + stick.TwelvePressed());
		}else if(!stick.TwelvePressed())
		{
			c.setClosedLoopControl(false);
			System.out.println("Number 12:" + stick.TwelvePressed());
		}
	}
	
	public void enableSolonoid() {
		if(stick.TriggerPressed())
		{
			launchTreb.set(true);
			launchTreb.set(false);
			System.out.println("trigger:" + stick.TriggerPressed());
		}
		if(stick.TenPressed())
		{
			returnTreb.set(true);
			returnTreb.set(false);
			System.out.println("Number10:" + stick.TenPressed());
			
		} 
		
	}
	public void launchTrebuchet()
	{
		
		launchTreb.set(true);
	}
	public void retractTrebuchet()
	{
		launchTreb.set(false);
		returnTreb.set(true);
		returnTreb.set(false);
	}
}
