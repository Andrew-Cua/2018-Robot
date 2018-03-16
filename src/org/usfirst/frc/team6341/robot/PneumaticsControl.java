package org.usfirst.frc.team6341.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Solenoid;

public class PneumaticsControl {

	
    Compressor c;
	
	Solenoid launchTreb;
	
	Solenoid returnTreb;
	
	Solenoid expandArms;
	Solenoid retractArms;
	
	JoystickCommands stick;
	
	public PneumaticsControl(int CompID) 
	{
		this.c = new Compressor(CompID);
		this.launchTreb = new Solenoid(0);
		this.returnTreb = new Solenoid(1);
		this.expandArms = new Solenoid(3);
		this.retractArms = new Solenoid(2);
		this.stick = new JoystickCommands();
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
		returnTreb.set(false);
		//returnTreb.set(true);
	}
	public void retractTrebuchet()
	{
		launchTreb.set(false);
		returnTreb.set(true);
	}
	public void expandArms()
	{
		expandArms.set(true);
		retractArms.set(false);
	}
	public void retractArms()
	{
		retractArms.set(true);
		expandArms.set(true);
	}
}
