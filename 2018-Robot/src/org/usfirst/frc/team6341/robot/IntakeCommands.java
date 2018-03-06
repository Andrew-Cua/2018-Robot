package org.usfirst.frc.team6341.robot;

import edu.wpi.first.wpilibj.Spark;

public class IntakeCommands {
	
	Spark LeftMotor;
	Spark rightMotor;
	JoystickCommands driveStick;
	
	
	public IntakeCommands() 
	{
		this.LeftMotor = new Spark(0);
		this.rightMotor = new Spark(1);
		this.driveStick = new JoystickCommands();
	}
	
	public void ControlIntake() {
		if(driveStick.R1()) 
		{
			LeftMotor.set(1);
			rightMotor.set(-1);
		}else if(driveStick.L1()) 
		{
			LeftMotor.set(-1);
			rightMotor.set(1);
		}
	}

}
