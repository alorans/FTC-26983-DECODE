package org.firstinspires.ftc.teamcode.TeleOp;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.RunCommand;
import com.arcrobotics.ftclib.command.button.Trigger;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Subsystems.Arm;
import org.firstinspires.ftc.teamcode.Subsystems.MecanumDriveTrain;
import org.firstinspires.ftc.teamcode.Subsystems.Claw;

//@TeleOp needed for the class to show up in driver hub
@TeleOp(name = "EverythingTeleOp")
public class EverythingTeleOp extends CommandOpMode {

    //Define all objects from all your subsystems that you'll use
    //Also define all subsystems
    Motor leftFront, rightFront, backLeft, backRight;
    MecanumDrive mecanumDrive;
    Servo claw, leftServo, rightServo;
    Claw clawSubsystem;
    MecanumDriveTrain driveTrain;
    GamepadEx driver, operator;
    DcMotor ADM;
    Arm armSubsystem;

    @Override
    public void initialize() {

        //Define all objects
        claw = hardwareMap.get(Servo.class, "ps");
        leftServo = hardwareMap.get(Servo.class, "lts");
        rightServo = hardwareMap.get(Servo.class, "rts");

        leftFront = new Motor(hardwareMap, "fL", Motor.GoBILDA.RPM_435);
        rightFront = new Motor(hardwareMap, "fR", Motor.GoBILDA.RPM_435);
        backLeft = new Motor(hardwareMap, "bL", Motor.GoBILDA.RPM_435);
        backRight = new Motor(hardwareMap, "bR", Motor.GoBILDA.RPM_435);

        ADM = hardwareMap.get(DcMotor.class, "am");

        driver = new GamepadEx(gamepad1);
        operator = new GamepadEx(gamepad2);

        //Define subsystems
        clawSubsystem = new Claw(claw, leftServo, rightServo);
        armSubsystem = new Arm(ADM, operator);
        driveTrain = new MecanumDriveTrain(leftFront, rightFront, backLeft, backRight, driver);

        //Define Triggers
        Trigger moveUp = new Trigger(() -> operator.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER) > 0);
        Trigger moveDown = new Trigger(() -> operator.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER) > 0);

        //Register subsystems
        register(clawSubsystem, driveTrain, armSubsystem);

        waitForStart();

        //Create commands
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

        moveUp.whenActive(new InstantCommand(armSubsystem::direction_up, armSubsystem));

        moveDown.whenActive(new InstantCommand(armSubsystem::direction_down, armSubsystem));





    }
}
