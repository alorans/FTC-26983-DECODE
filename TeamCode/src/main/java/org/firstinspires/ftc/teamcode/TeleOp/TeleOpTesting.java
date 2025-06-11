package org.firstinspires.ftc.teamcode.TeleOp;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class TeleOpTesting extends CommandOpMode {

    //DcMotorEx adds more features to PositionControl (presets)
    DcMotorEx motor1 = hardwareMap.get(DcMotorEx.class, "m1");

    /*The difference between Motor and DcMotorEx is that you need to use the Motor object
      on the built-in MecanumDrive class to be able to use it. Just use it for drivetrain wheels */
    Motor driveTrainMotor1 = new Motor(hardwareMap, "dtm1", Motor.GoBILDA.RPM_435);

    //I highly recommend using ServoEx since it adds way more functionality and features to servos.
    ServoEx servo1 = new SimpleServo(hardwareMap, "s1",
                    0, 360, AngleUnit.DEGREES);

    //CRServo just means Continuous-Rotation servo. If you don't need presets, use this.
    CRServo CRServo1 = hardwareMap.get(CRServo.class, "cr1");

    //GamepadEx adds better functionality for operating controllers
    GamepadEx operator = new GamepadEx(gamepad2);




    @Override
    public void initialize() {

    }
}
