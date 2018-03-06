package org.usfirst.frc.team6341.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;

public class ElevatorCommands {
	
	TalonSRX ElevatorMotor;
	JoystickCommands driveStick;
	double POVangle;
	DigitalInput topLimit;
	DigitalInput bottomLimit;
	
	public ElevatorCommands()
	{
		this.ElevatorMotor = new TalonSRX(10);
		this.driveStick = new JoystickCommands();
		this.topLimit = new DigitalInput(0);
		this.bottomLimit = new DigitalInput(1);
	}
	
	
	public void ActuateElevator() 
	{
		POVangle = driveStick.getPOV();
		if(POVangle == 0.0D && !topLimit.get())
		{
			ElevatorMotor.set(ControlMode.PercentOutput, 1);
		}else if(POVangle == 180.0D && !bottomLimit.get())
		{
			ElevatorMotor.set(ControlMode.PercentOutput, -1);
		}else if(POVangle == 0.0D && topLimit.get()) 
		{
			ElevatorMotor.set(ControlMode.PercentOutput, 0);
		}else if(POVangle == 180.0D && bottomLimit.get())
		{
			ElevatorMotor.set(ControlMode.PercentOutput, 0);
		}
	}
	

}
