package org.firstinspires.ftc.teamcode.TeleOp;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.RunCommand;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Subsystems.MecanumDriveTrain;
import org.firstinspires.ftc.teamcode.Subsystems.Claw;

@TeleOp(name = "EverythingTeleOp")
public class EverythingTeleOp extends CommandOpMode {

    Motor leftFront, rightFront, backLeft, backRight;
    MecanumDrive mecanumDrive;
    Servo claw, leftServo, rightServo;
    Claw clawSubsystem;
    MecanumDriveTrain driveTrain;
    GamepadEx driver, operator;

    @Override
    public void initialize() {

        claw = hardwareMap.get(Servo.class, "ps");
        leftServo = hardwareMap.get(Servo.class, "lts");
        rightServo = hardwareMap.get(Servo.class, "rts");

        leftFront = new Motor(hardwareMap, "fL", Motor.GoBILDA.RPM_435);
        rightFront = new Motor(hardwareMap, "fR", Motor.GoBILDA.RPM_435);
        backLeft = new Motor(hardwareMap, "bL", Motor.GoBILDA.RPM_435);
        backRight = new Motor(hardwareMap, "bR", Motor.GoBILDA.RPM_435);

        driver = new GamepadEx(gamepad1);
        operator = new GamepadEx(gamepad2);

        clawSubsystem = new Claw(claw, leftServo, rightServo);
        driveTrain = new MecanumDriveTrain(leftFront, rightFront, backLeft, backRight, driver);

        register(clawSubsystem, driveTrain);

        waitForStart();

        driveTrain.setDefaultCommand(new RunCommand(driveTrain::periodic, driveTrain));

        operator.getGamepadButton(GamepadKeys.Button.DPAD_RIGHT)
                .whenPressed(new InstantCommand(clawSubsystem::open));

        operator.getGamepadButton(GamepadKeys.Button.DPAD_LEFT)
                .whenPressed(new InstantCommand(clawSubsystem::close));

        operator.getGamepadButton(GamepadKeys.Button.DPAD_UP)
                .whenPressed(new InstantCommand(clawSubsystem::direction_up));

        operator.getGamepadButton(GamepadKeys.Button.DPAD_DOWN)
                .whenPressed(new InstantCommand(clawSubsystem::direction_down));

        operator.getGamepadButton(GamepadKeys.Button.Y)
                .whenPressed(new InstantCommand(clawSubsystem::zero));
    }
}
