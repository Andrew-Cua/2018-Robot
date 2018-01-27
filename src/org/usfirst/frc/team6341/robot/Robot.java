 package org.usfirst.frc.team6341.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	final String defaultAuto = "Default";
	final String customAuto = "My Auto";
	String autoSelected;
	SendableChooser<String> chooser = new SendableChooser<>();
	
	TalonSRX frontLeft = new TalonSRX(1);
	TalonSRX frontRight = new TalonSRX(0);
	TalonSRX backLeft = new TalonSRX(3);
	TalonSRX backRight = new TalonSRX(2);
	
	Compressor c = new Compressor(12);
	Solenoid Jack = new Solenoid(2);
	Solenoid ReturnJack = new Solenoid(1);
	JoystickCommands stick2;
	Joystick stick = new Joystick(0);
	
	
	
	private void power(double left, double right) {
			left = -limit(left);
			right = limit(right); // Right motors need to be reversed

			frontLeft.set(ControlMode.PercentOutput, left);
			backLeft.set(ControlMode.PercentOutput,left);
			frontRight.set(ControlMode.PercentOutput,right);
			backRight.set(ControlMode.PercentOutput,right);
		
	}
	
	private void power(double fl, double fr, double rl, double rr) 
	{
		fl = limit( fl );
		fr = limit( fr );
		rl = limit( rl );
		rr = limit( rr );
		//fl = -fl;
		//rl = -rl;
		

		frontLeft.set( ControlMode.PercentOutput, fl );
		backLeft.set( ControlMode.PercentOutput, rl );
		frontRight.set( ControlMode.PercentOutput, fr );
		backRight.set( ControlMode.PercentOutput, rr );
	}
	
	protected double limit(double value) {
		return Math.min(1, Math.max(value, -1));
	}
	
	//Drive functions
	

	

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		chooser.addDefault("Default Auto", defaultAuto);
		chooser.addObject("My Auto", customAuto);
		SmartDashboard.putData("Auto choices", chooser);
		
		CameraServer.getInstance().startAutomaticCapture();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString line to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional comparisons to the
	 * switch structure below with additional strings. If using the
	 * SendableChooser make sure to add them to the chooser code above as well.
	 */
	@Override
	public void autonomousInit() {
		autoSelected = chooser.getSelected();
		// autoSelected = SmartDashboard.getString("Auto Selector",
		// defaultAuto);
		System.out.println("Auto selected: " + autoSelected);
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		switch (autoSelected) {
		case customAuto:
			// Put custom auto code here
			break;
		case defaultAuto:
		default:
			// Put default auto code here
			break;
		}
	}

	/**
	 * This function is called periodically during operator control
	 */
	
	public void Attempt1() 
	{
		
		double move = stick.getY();
		double rotate = stick.getTwist();
		double left,right;
		
		if (move == 0 && rotate == 0)
		{ // No movement
			power(0, 0);
			return;
		}
		if (rotate == 0) 
		{ // Moving straight
			power(move, move);
			return;
		}

		if (move > 0.0)
		{
			System.out.println("Backward");
			if (rotate > 0.0) 
			{
				left = move - rotate;
				right = Math.max(move, rotate);
			}
			else 
			{
				left = Math.max(move, -rotate);
				right = move + rotate;
			}
		} 
		else 
		{
			System.out.println("Foward");
			if (rotate > 0.0) 
			{
				left = move;
				right = move*(1 + rotate);
			} 
			else 
			{
				left = move*(1 - rotate);
				right = move;
			}
		}
		power(left*.1,right*.1);
		System.out.println("Move:"+move);
		System.out.println("Rotate" + rotate);
		System.out.println("Left:"+left);
		System.out.println("right:" + right);
		
	}
	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		enableCompressor();
		enableSolonoid();
		System.out.println(c.enabled());
		ReturnJack.set(true);
		ReturnJack.set(false);
	}
	@Override
	public void teleopPeriodic() 
	{
		//Attempt1();
		MecCall();
	}
	
	public void MecCall() {
		double x = stick.getX();
		double y = stick.getY();
		double r = stick.getTwist();
		double FrontLeftSpeed,FrontRightSpeed,RearLeftSpeed,RearRightSpeed;
		if(!getSideButton())
		{
		    FrontLeftSpeed =  x + y + r;
		    FrontRightSpeed = x - y + r;
		    RearLeftSpeed =  -x + y + r;
		    RearRightSpeed = -x - y + r;
            power( FrontLeftSpeed*0.5, FrontRightSpeed*0.5, RearLeftSpeed*0.5, RearRightSpeed*0.5 );
            System.out.println("FrontLeftSpeed:" + FrontLeftSpeed);
            System.out.println("FrontRightSpeed:"+ FrontRightSpeed);
            System.out.println(RearLeftSpeed);
            System.out.println(RearRightSpeed);
		}else if(getSideButton()) {
			FrontLeftSpeed =  x ;
		    FrontRightSpeed = x ;
		    RearLeftSpeed =  -x ;
		    RearRightSpeed = -x ;
		    
		    power( FrontLeftSpeed*0.5, FrontRightSpeed*0.5, RearLeftSpeed*0.5, RearRightSpeed*0.5 );
		}
	}


	
	public void enableCompressor()
	{
		if(TwelvePressed())
		{

			c.setClosedLoopControl(true);
		}else if(!TwelvePressed())
		{
			c.setClosedLoopControl(false);
		}
	}
	public void enableSolonoid() {
		if(TriggerPressed())
		{
			Jack.set(true);
			Jack.set(false);
			System.out.println(TriggerPressed());
		}
		if(TenPressed())
		{
			System.out.println("wiring issue");
			ReturnJack.set(true);
			ReturnJack.set(false);
			System.out.println(TenPressed());
			
		} 
		
	}
	public boolean getSideButton() 
	{
		boolean joystickButton = stick.getRawButton(3);
		return joystickButton;
	
	}
	
	public boolean TwelvePressed()
	{
		boolean twelvePressed = stick.getRawButton(8);
		return twelvePressed;
	}
	public boolean NinePressed()
	{
		boolean NinePressed = stick.getRawButton(9);
		return NinePressed;
	}
	public boolean TenPressed()
	{
		boolean TenPressed = stick.getRawButton(10);
		return TenPressed;
	}
	public boolean TriggerPressed()
	{
		boolean triggerPressed = stick.getTrigger();
		return triggerPressed;
	}
}
