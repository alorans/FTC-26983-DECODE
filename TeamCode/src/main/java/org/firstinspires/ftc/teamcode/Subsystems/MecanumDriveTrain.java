package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.hardware.motors.Motor;

public class MecanumDriveTrain extends SubsystemBase {

    Motor leftFront, rightFront, leftBack, backRight;
    GamepadEx driver;
    MecanumDrive driveTrain;

    public MecanumDriveTrain(Motor leftFront, Motor rightFront,
                             Motor leftBack, Motor backRight, GamepadEx driver){

        this.leftFront = leftFront;
        this.rightFront = rightFront;
        this.leftBack = leftBack;
        this.backRight = backRight;
        this.driver = driver;

        driveTrain = new MecanumDrive(leftFront, rightFront, leftBack, backRight);

    }

    @Override
    public void periodic(){

        driveTrain.driveRobotCentric(-driver.getLeftX(), driver.getRightY(), -driver.getRightX());

    }
}
