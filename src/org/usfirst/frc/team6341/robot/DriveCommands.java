package org.usfirst.frc.team6341.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class DriveCommands {
	
	TalonSRX backRight, backLeft, frontRight, frontLeft;
	JoystickCommands driveStick;
	
	double x, y, r, POV;
	
	double backLeftPwr, backRightPwr, frontLeftPwr, frontRightPwr;
	
	public DriveCommands(int backRightID, int backLeftID, int frontRightID, int frontLeftID) 
	{
		this.backRight = new TalonSRX(backRightID);
		this.backLeft = new TalonSRX(backLeftID);
		this.frontRight = new TalonSRX(frontRightID);
		this.frontLeft = new TalonSRX(frontLeftID);
		this.driveStick = new JoystickCommands();
		setRamp(0,0);
	}
	
	
	private void setRamp(float timeToFull, int timeout)
	{
		backRight.configOpenloopRamp(timeToFull, timeout);
		backLeft.configOpenloopRamp(timeToFull, timeout);
		frontRight.configOpenloopRamp(timeToFull, timeout);
		frontLeft.configOpenloopRamp(timeToFull, timeout);
		
	}
	
	public void MecanumDrive() 
	{
		 x = driveStick.getX();
		 y = driveStick.getY();
		 r = driveStick.getRX();
		 POV = driveStick.getPOV();
		
		if(driveStick.getPOV() < 0 && !driveStick.getSideButton()) {
			backRightPwr  =  y - x + r;//OLD| y + r - x|NEW|y + r + x|//frontleft 
			backLeftPwr = -y - x + r;//OLD|-y + r - x|NEW|y - r - x|//frontRight
			frontRightPwr   =  y + x + r;//OLD| y + r + x|NEW|y + r - x|//backLeft 
			frontLeftPwr  = -y + x + r;//OLD|-y + r + x|NEW|y - r + x|//backRight
			PowSens(backLeftPwr, backRightPwr, frontLeftPwr, frontRightPwr, 2); //DONT GO ABOVE 2!!
			//power( backLeftPwr, backRightPwr, frontLeftPwr, frontRightPwr);
	
		}else if(!driveStick.getSideButton())
		{
			if(POV == 90.0D) 
			{
				strafe("left");
			}else if(POV == 270.0D)
			{
				strafe("right");
			}
		}else if(driveStick.getSideButton()) 
		{
			if(POV == 90.0D) 
			{
				backRightPwr =  -0.5;
				backLeftPwr = 0.11;
				frontRightPwr =   -0.11;
				frontLeftPwr =  0.5;
				power( backLeftPwr, backRightPwr, frontLeftPwr, frontRightPwr );
			}else if(POV == 270.0D)
			{
				backRightPwr = -0.11;
				backLeftPwr = 0.5 ;
				frontRightPwr = -0.5;
				frontLeftPwr = 0.11;
				power( backLeftPwr, backRightPwr, frontLeftPwr, frontRightPwr );
			}else power(0, 0, 0, 0);
		}
	}
	
	
	private void power(double rl, double rr, double fl, double fr) 
	{
		fl = limit( fl );
		fr = limit( fr );
		rl = limit( rl );
		rr = limit( rr );
		//fl = -fl;
		//rl = -rl;
		

		backRight.set( ControlMode.PercentOutput, rr );
		frontRight.set( ControlMode.PercentOutput, fr );
		backLeft.set( ControlMode.PercentOutput, rl );
		frontLeft.set( ControlMode.PercentOutput, fl );
	}
	
	protected double limit(double value) {
		return Math.min(1, Math.max(value, -1));
	}
	
	private void strafe(String direction)
	{
		if(direction.equalsIgnoreCase("left"))
		{
			backRightPwr =  -0.5;
			backLeftPwr = -0.5;
			frontRightPwr =   0.5;
			frontLeftPwr =  0.5;
		}else 
		{
			backRightPwr =  0.5;
			backLeftPwr = 0.5;
			frontRightPwr =   -0.5;
			frontLeftPwr =  -0.5;
		}power( backLeftPwr, backRightPwr, frontLeftPwr, frontRightPwr );
	}

	
	private void PowSens(double rl, double rr, double fl, double fr, int sensitivty) {
		double[] Pwr; 
		Pwr = new double[4];
		Pwr[0] = rl;
		Pwr[1] = rr;
		Pwr[2] = fl;
		Pwr[3] = fr;
		
		double CurrentPow;
		
		for(int i = 0; i < Pwr.length ; i++)
		{
			if(Pwr[i] < 0D)
			{
				CurrentPow = Pwr[i];
				CurrentPow = Math.pow(CurrentPow, sensitivty);
				Pwr[i] = -CurrentPow;
			}else if(Pwr[i] >= 0D)
			{
				CurrentPow = Pwr[i];
				CurrentPow = Math.pow(CurrentPow, sensitivty);
				Pwr[i] = CurrentPow;
			}
		}
		power( Pwr[0], Pwr[1], Pwr[2], Pwr[3]);
		
		
	}
}
