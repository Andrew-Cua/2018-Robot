package org.usfirst.frc.team6341.robot;

import edu.wpi.first.wpilibj.Spark;

public class IntakeCommands {
	
	Spark leftMotor;
	Spark rightMotor;
	Spark centerMotor;
	JoystickCommands driveStick;
	
	
	public IntakeCommands(int leftMotorID, int rightMotorID, int centerMotorID) 
	{
		this.leftMotor = new Spark(leftMotorID);
		this.rightMotor = new Spark(rightMotorID);
		this.centerMotor = new Spark(centerMotorID);
		this.driveStick = new JoystickCommands();
	}
	
	public void ControlIntake() {
		if(driveStick.R1()) 
		{
			leftMotor.set(1);
			rightMotor.set(-1);
		}else if(driveStick.L1()) 
		{
			leftMotor.set(-1);
			rightMotor.set(1);
		}else {
			leftMotor.set(0);
			rightMotor.set(0);
		}
	}
	private void raiseIntake()
	{
		centerMotor.set(1);
	}
	private void lowerIntake()
	{
		centerMotor.set(-1);
	}
	
	public void controlIntakePos()
	{
		double PovAngle = driveStick.getPOV();
		
		if(PovAngle == 0.0D)
		{
			raiseIntake();
		}else if(PovAngle == 180.D)
		{
			lowerIntake();
		}else centerMotor.set(0);
	}

}
