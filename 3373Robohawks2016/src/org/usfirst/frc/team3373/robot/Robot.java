package org.usfirst.frc.team3373.robot;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.vision.AxisCamera;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.*;
import edu.wpi.first.wpilibj.CANTalon;
import com.ni.vision.NIVision.Image;
import edu.wpi.first.wpilibj.image.*;

//@author Joey Dyer, Drew Marino, Alex Iasso, Dillon Rose

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	RobotDrive myRobot;
	SuperJoystick stick;
	int autoLoopCounter;
	DigitalInput limitSwitch;
	AnalogInput pot;
	CANTalon canTalonTest;
	AxisCamera visionCamera;
	//VisionSystem visionSystem = new VisionSystem();
	//AxisCamera camera;

	
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	visionCamera = new AxisCamera("10.33.73.85");
    	myRobot = new RobotDrive(0,1);
    	stick = new SuperJoystick(0);
    	limitSwitch = new DigitalInput(0);
    	pot = new AnalogInput(0);
    	canTalonTest = new CANTalon(0);

    	}
    	//camera = new AxisCamera("10.33.73.11");
    
    /**
     * This function is run once each time the robot enters autonomous mode
     */
    public void autonomousInit() {
    	autoLoopCounter = 0;
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    	if(autoLoopCounter < 100) //Check if we've completed 100 loops (approximately 2 seconds)
		{
			myRobot.drive(-0.5, 0.0); 	// drive forwards half speed
			autoLoopCounter++;
			} else {
			myRobot.drive(0.0, 0.0); 	// stop robot
		}
    }
    
    /**
     * This function is called once each time the robot enters teleoperated mode
     */
    public void teleopInit(){
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    	myRobot.tankDrive(stick.getRawAxis(1), stick.getRawAxis(5));
    }
    
    public void testInit(){
    	//Live window is enabled by default for test mode by disabling it here, it allows the use of smartdashboard to display values
    	LiveWindow.setEnabled(false);
    	//String cameraIP = "cam0";
    	//visionSystem.Camera(cameraIP);
    	//visionSystem.Filtering(cameraIP);
    	try {
        	HSLImage image = visionCamera.getImage();
        	System.out.println("got Image");
        	image.write("/home/lvuser/image.png");
        	}catch (Exception e){
        		System.out.println("exception occured:" + e);
        	}
    }
    
    /**
     * 
     * This function is called periodically during test mode
     */
    public void testPeriodic() {    	
    	SmartDashboard.putNumber("LeftAxis: ", stick.getRawAxis(1));
    	SmartDashboard.putNumber("RightAxis: ", stick.getRawAxis(5));
    	SmartDashboard.putBoolean("Limit Switch: ", limitSwitch.get());
    	SmartDashboard.putNumber("Pot Value:", pot.getVoltage());
    	String cameraIP = "cam0";
    	//visionSystem.Filtering(cameraIP);
    	//SmartDashboard.putNumber("Particles: ", visionSystem.Filtering(cameraIP));
    	SmartDashboard.putNumber("Test Value Drew ", 12);

    	//LiveWindow.run(); This should be uncommented when LiveWindow is desired in test mode
    	
    }
    
}