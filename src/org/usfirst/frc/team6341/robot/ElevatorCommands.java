package org.usfirst.frc.team6341.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class ElevatorCommands {
	
	TalonSRX ElevatorMotor;
	JoystickCommands driveStick;
	double POVangle;
	
	public ElevatorCommands()
	{
		this.ElevatorMotor = new TalonSRX(10);
		this.driveStick = new JoystickCommands();
	}
	
	
	public void ActuateElevator() 
	{
		POVangle = driveStick.getPOV();
		if(POVangle == 0.0D)
		{
			ElevatorMotor.set(ControlMode.PercentOutput, 1);
		}else if(POVangle == 180.0D)
		{
			ElevatorMotor.set(ControlMode.PercentOutput, -1);
		}else ElevatorMotor.set(ControlMode.PercentOutput, 0);
	}
	

}
