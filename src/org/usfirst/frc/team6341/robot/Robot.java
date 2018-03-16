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
	Compressor c;
	//led driver
	//Spark ledDriver; 
	
	PneumaticsControl P;
	JoystickCommands stick; 
	
	ElevatorCommands Elevator;
	IntakeCommands Intake;

	
	DriveCommands Drive;
	
	//Compressor C = new Compressor(11);
	
	//Solenoid jack = new Solenoid(0);
	//Solenoid returnjack = new Solenoid(1);
	
	
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
		
		P = new PneumaticsControl(0);
		
		stick = new JoystickCommands();
		
		CameraServer.getInstance().startAutomaticCapture();
		Elevator = new ElevatorCommands();
		Intake = new IntakeCommands(0,1,2);
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
		String[] autonomousList = {"Left", "Middle", "Right"};
		SmartDashboard.putStringArray("Auto List", autonomousList);
		String autoSelected = SmartDashboard.getString("Auto Selector", "None");
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
		
		
		P.c.setClosedLoopControl(true);
		String gameData;
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		
		if(gameData.length() > 0)
		{
			if(gameData.charAt(0) == 'L')
			{
				//If its on the left
				Drive.Turntesting(timer);
			}else  
			{
				//If its on the right
				Drive.Turntesting(timer);
				
			}
			}
		}
	
	
	
	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
			//System.out.println(stick.getPOV());
		
		P.enableCompressor();
		if(stick.getYButton())
		{
			//P.expandArms();
			P.launchTrebuchet();
			System.out.println(stick.getYButton());
		}else if(stick.getAButton())
		{
			//P.retractArms();
			P.retractTrebuchet();
			System.out.println(stick.getAButton());
		}
		
	}
	@Override
	public void teleopPeriodic() 
	{
		//P.c.setClosedLoopControl(true);
		Drive.MecanumDrive();
		//Elevator.ActuateElevator();
		Elevator.MoveElevator();
		//Elevator.ActuateElevator();
		Intake.ControlIntake();
	}
	}
	


	
	

