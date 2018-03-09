 package org.usfirst.frc.team6341.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Timer;
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
	Timer timer;
	
	//led driver
	Spark ledDriver; 
	
	PneumaticsControl P;
	JoystickCommands stick; 
	
	ElevatorCommands Elevator;
	IntakeCommands Intake;

	
	DriveCommands Drive;
	
	
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
		
		Drive = new DriveCommands(1, 0, 3, 2);
		
		//P = new PneumaticsControl();
		
		stick = new JoystickCommands();
		
		CameraServer.getInstance().startAutomaticCapture();
		Elevator = new ElevatorCommands();
		Intake = new IntakeCommands();
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
		timer = new Timer();
		timer.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		
		String gameData;
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		if(gameData.length() > 0)
		{
			if(gameData.charAt(0) == 'L')
			{
				//from middle left
				Drive.StartLeft(timer);
			}else 
			{
				//from middle to right
				Drive.LastStand(timer);
			}
			}
		}
	
	
	
	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
			//System.out.println(stick.getPOV());
		double value = -stick.rightTrigger() + stick.leftTrigger();
		System.out.println(value);
		System.out.println(stick.rightTrigger());
		
	}
	@Override
	public void teleopPeriodic() 
	{
		//P.c.setClosedLoopControl(true);
		Drive.MecanumDrive();
		Elevator.ActuateElevator();
		Intake.ControlIntake();
	}
	
	/*public void MecCall() {
		double x = stick.getX();
		double y = stick.getY();
		double r = stick.getRX();
		double FrontLeftSpeed,FrontRightSpeed,RearLeftSpeed,RearRightSpeed;
		if(!stick.getSideButton())
		{
		    FrontLeftSpeed =  y ; // y + r - x |Proposed Fix Not Tested | x + y + r | OLD
		    FrontRightSpeed= -y ; //-y + r - x |Proposed Fix Not Tested | x - y + r | OLD 
		    RearLeftSpeed =   y ; // y + r + x |Proposed Fix Not Tested |-x + y + r | OLD
		    RearRightSpeed = -y ; //-y + r + x |Proposed Fix Not Tested |- x -y + r | OLD
            power( FrontLeftSpeed*0.5, FrontRightSpeed*0.5, RearLeftSpeed*0.5, RearRightSpeed*0.5 );
            System.out.println("FrontLeftSpeed:" + FrontLeftSpeed);
            System.out.println("FrontRightSpeed:"+ FrontRightSpeed);
            System.out.println(RearLeftSpeed);
            System.out.println(RearRightSpeed);
		}else if(stick.getSideButton()) {
			FrontLeftSpeed  = x ;
		    FrontRightSpeed = -x ;
		    RearLeftSpeed   = -x ;
		    RearRightSpeed  = x ;
		    
		    power( FrontLeftSpeed*0.5, FrontRightSpeed*0.5, RearLeftSpeed*0.5, RearRightSpeed*0.5 );
		}
	}
	public void MecCallTwo() {
		
		} */
	}
	


	
	

