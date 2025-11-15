package org.firstinspires.ftc.teamcode.command_based.opmodes;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.hardware.DcMotor;

import dev.nextftc.core.commands.Command;
import dev.nextftc.core.components.BindingsComponent;
import dev.nextftc.core.components.SubsystemComponent;
import dev.nextftc.ftc.Gamepads;
import dev.nextftc.ftc.NextFTCOpMode;
import dev.nextftc.ftc.components.BulkReadComponent;
import dev.nextftc.hardware.driving.FieldCentric;
import dev.nextftc.hardware.driving.MecanumDriverControlled;
import dev.nextftc.hardware.impl.MotorEx;
import dev.nextftc.hardware.impl.IMUEx;
import dev.nextftc.hardware.impl.Direction;
import org.firstinspires.ftc.teamcode.command_based.subsystems.Intake;
import org.firstinspires.ftc.teamcode.command_based.subsystems.Sorter;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "DON'T USE THIS")
public class TeleOp extends NextFTCOpMode {
    public TeleOp() {
        addComponents(
                new SubsystemComponent(Intake.INSTANCE),
                new SubsystemComponent(Sorter.INSTANCE),
                // Add components for efficient sensor reading and managing control bindings
                BulkReadComponent.INSTANCE,
                BindingsComponent.INSTANCE
        );
    }

    private final MotorEx frontLeftMotor = new MotorEx("LF");
    private final MotorEx frontRightMotor = new MotorEx("RF").reversed();
    private final MotorEx backLeftMotor = new MotorEx("LB");
    private final MotorEx backRightMotor = new MotorEx("RB").reversed();
    private final IMUEx imu = new IMUEx("imu", Direction.LEFT, Direction.UP).zeroed();

    @Override
    public void onStartButtonPressed() {
        // Set drivetrain motors to float on zero power
        frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        backLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        backRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        // Create Mecanum drivetrain
        Command driverControlled = new MecanumDriverControlled(
                frontLeftMotor,
                frontRightMotor,
                backLeftMotor,
                backRightMotor,
                Gamepads.gamepad1().leftStickY(),
                Gamepads.gamepad1().leftStickX().invert(),
                Gamepads.gamepad1().rightStickX(),
                new FieldCentric(imu)
        );
        driverControlled.schedule();

        // Spin intake when A is pressed
        Gamepads.gamepad1().a()
                .whenBecomesTrue(Intake.INSTANCE.on)
                .whenBecomesFalse(Intake.INSTANCE.off);

        // Spin table when B is pressed
        Gamepads.gamepad1().b()
                .whenBecomesTrue(Sorter.INSTANCE.on)
                .whenBecomesFalse(Sorter.INSTANCE.off);
    }
}