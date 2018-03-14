package org.usfirst.frc.team6341.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Solenoid;

public class PneumaticsControl {

	
    Compressor c;
	
	Solenoid launchTreb;
	
	Solenoid returnTreb;
	
	JoystickCommands stick;
	
	public PneumaticsControl(int CompID) 
	{
		c = new Compressor(CompID);
		launchTreb = new Solenoid(0);
		returnTreb = new Solenoid(1);
		stick = new JoystickCommands();
	}
	
	
	
	public void enableCompressor()
	{
		if(stick.getB())
		{

			c.setClosedLoopControl(true);
			System.out.println("Number 12:" + stick.TwelvePressed());
		}else if(!stick.getB())
		{
			c.setClosedLoopControl(false);
			System.out.println("Number 12:" + stick.TwelvePressed());
		}
	}
	
	public void launchTrebuchet()
	{
		
		launchTreb.set(true);
		launchTreb.set(false);
	}
	public void retractTrebuchet()
	{
		returnTreb.set(false);
		returnTreb.set(true);
	}
}
