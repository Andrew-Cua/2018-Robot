package org.usfirst.frc.team6341.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class DriveCommands {
	
	TalonSRX backRight, backLeft, frontRight, frontLeft;
	JoystickCommands driveStick;
	
	double x, y, r, POV;
	
	public DriveCommands(int backRightID, int backLeftID, int frontRightID, int frontLeftID) 
	{
		this.backRight = new TalonSRX(backRightID);
		this.backLeft = new TalonSRX(backLeftID);
		this.frontRight = new TalonSRX(frontRightID);
		this.frontLeft = new TalonSRX(frontLeftID);
		this.driveStick = new JoystickCommands();
	}
	
	
	public void MecanumDrive() 
	{
		 x = driveStick.getX();
		 y = driveStick.getY();
		 r = driveStick.getRX();
		 POV = driveStick.getPOV();
		
		double frontLeftPwr, frontRightPwr, backLeftPwr, backRightPwr;
		if(driveStick.getPOV() < 0 && !driveStick.getSideButton()) {
		backRightPwr  =  y - x + r;//OLD| y + r - x|NEW|y + r + x|//frontleft 
		backLeftPwr = -y - x + r;//OLD|-y + r - x|NEW|y - r - x|//frontRight
		frontRightPwr   =  y + x + r;//OLD| y + r + x|NEW|y + r - x|//backLeft 
		frontLeftPwr  = -y + x + r;//OLD|-y + r + x|NEW|y - r + x|//backRight
		power( backLeftPwr*0.5, backRightPwr*0.5, frontLeftPwr*0.5, frontRightPwr*0.5 );
	
		}else if(!driveStick.getSideButton())
		{
			if(POV == 90.0D) 
			{
				backRightPwr =  -0.5;
				backLeftPwr = -0.5;
				frontRightPwr =   0.5;
				frontLeftPwr =  0.5;
				power( backLeftPwr, backRightPwr, frontLeftPwr, frontRightPwr );
			}else if(POV == 270.0D)
			{
				backRightPwr = 0.5;
				backLeftPwr = 0.5;
				frontRightPwr = -0.5;
				frontLeftPwr = -0.5;
				power( backLeftPwr, backRightPwr, frontLeftPwr, frontRightPwr );
			}
		}else if(driveStick.getSideButton()) 
		{
			if(POV == 90.0D) 
			{
				backRightPwr =  -0.5;
				backLeftPwr = 0.01;
				frontRightPwr =   -0.01;
				frontLeftPwr =  0.5;
				power( backLeftPwr, backRightPwr, frontLeftPwr, frontRightPwr );
			}else if(POV == 270.0D)
			{
				backRightPwr = -0.01;
				backLeftPwr = 0.5;
				frontRightPwr = -0.5;
				frontLeftPwr = 0.01;
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

}
