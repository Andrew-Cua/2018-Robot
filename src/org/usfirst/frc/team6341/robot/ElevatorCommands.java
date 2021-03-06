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
	
	private double ElevatorMotorValue;
	
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
			ElevatorMotor.set(ControlMode.PercentOutput, -0.5);
		}else if(POVangle == 180.0D && !bottomLimit.get())
		{
			ElevatorMotor.set(ControlMode.PercentOutput, 0.5);
		}else if(POVangle == 0.0D && topLimit.get()) 
		{
			ElevatorMotor.set(ControlMode.PercentOutput, 0);
		}else if(POVangle == 180.0D && bottomLimit.get())
		{
			ElevatorMotor.set(ControlMode.PercentOutput, 0);
		}else
		{
			ElevatorMotor.set(ControlMode.PercentOutput,0);
		}
	}
	
	public void MoveElevator()
	{
		this.ElevatorMotorValue = -driveStick.rightTrigger() + driveStick.leftTrigger();
		
		if(ElevatorMotorValue < -0.25 && !topLimit.get())
		{
			ElevatorMotor.set(ControlMode.PercentOutput, ElevatorMotorValue);
		}else if(ElevatorMotorValue > 0.25 && !bottomLimit.get())
		{
			ElevatorMotor.set(ControlMode.PercentOutput, ElevatorMotorValue);
		}else if(ElevatorMotorValue < -0.25  && topLimit.get())
		{
			ElevatorMotor.set(ControlMode.PercentOutput, 0);
		}else if(ElevatorMotorValue > 0.25 && bottomLimit.get())
		{
			ElevatorMotor.set(ControlMode.PercentOutput, ElevatorMotorValue);
		}else ElevatorMotor.set(ControlMode.PercentOutput, 0);
		
		System.out.println(ElevatorMotorValue);
	}
	

}
