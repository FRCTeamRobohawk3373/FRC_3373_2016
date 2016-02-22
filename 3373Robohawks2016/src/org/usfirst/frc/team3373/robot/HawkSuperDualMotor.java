package org.usfirst.frc.team3373.robot;

public class HawkSuperDualMotor{

	HawkSuperMotor motor1;
	HawkSuperMotor motor2;
	double height;
	
	public HawkSuperDualMotor(int deviceNumber, int encoderMin, int encoderMax, int maxPercent, int minPercent, double travelRange, double maxSpeedChange, int motorDirection, int deviceNumber2, int encoderMin2, int encoderMax2, int maxPercent2, int minPercent2, double travelRange2, double maxSpeedChange2, int motorDirection2) {
		motor1 = new HawkSuperMotor(deviceNumber, encoderMin, encoderMax, maxPercent, minPercent, travelRange, maxSpeedChange, motorDirection);
		motor2 = new HawkSuperMotor(deviceNumber2, encoderMin2, encoderMax2, maxPercent2, minPercent2, travelRange2, maxSpeedChange2, motorDirection2);
	}
	public void goToHeight(double targetHeight){
		if(!(motor1.currentHeight>motor2.currentHeight+.02)&& !(motor2.currentHeight>motor1.currentHeight+.02)){
		motor1.goToHeight(targetHeight);
		motor2.goToHeight(targetHeight);
		}else if(motor1.currentHeight>motor2.currentHeight+.02 && motor1.getSpeed()>=0){
			motor1.set(.4);
			motor2.set(.5);
		}else if(motor2.currentHeight>motor1.currentHeight+.02 && motor2.getSpeed()>=0){
			motor2.set(.4);
			motor1.set(.5);
		}else if(motor1.currentHeight<motor2.currentHeight-.02 && motor1.getSpeed()<=0){
			motor1.set(-.4);
			motor2.set(-.5);
		}else if(motor2.currentHeight<motor1.currentHeight-.02 && motor2.getSpeed()<=0){
			motor2.set(-.4);
			motor1.set(-.5);
		}
	}
	public void set(double speed){
		motor1.set(speed);
		motor2.set(speed);
	}
	public void manualUp(){
		goToHeight(motor1.travel);
	}
	public void manualDown(){
		goToHeight(0);
	}
	public double getHeight(){
		height = motor1.currentHeight;
		return height;
	}

}