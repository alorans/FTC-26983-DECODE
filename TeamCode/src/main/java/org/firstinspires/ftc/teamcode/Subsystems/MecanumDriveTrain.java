package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.hardware.motors.Motor;

public class MecanumDriveTrain extends SubsystemBase {

    Motor leftFront, rightFront, leftBack, backRight; //Custom Motor object from FTCLib
    GamepadEx driver;
    MecanumDrive driveTrain; //Drivetrain object built-in with FTCLib

    public MecanumDriveTrain(Motor leftFront, Motor rightFront,
                             Motor leftBack, Motor backRight, GamepadEx driver){

        this.leftFront = leftFront;
        this.rightFront = rightFront;
        this.leftBack = leftBack;
        this.backRight = backRight;
        this.driver = driver;

        driveTrain = new MecanumDrive(leftFront, rightFront, leftBack, backRight);

    }
    /*Periodic/loop commands can be used like this:
    subsystemName.setDefaultCommand(new RunCommand(subsystemName::void, subsystemName))
     */
    @Override
    public void periodic(){

        driveTrain.driveRobotCentric(-driver.getLeftX(), driver.getRightY(), -driver.getRightX());

    }
}
