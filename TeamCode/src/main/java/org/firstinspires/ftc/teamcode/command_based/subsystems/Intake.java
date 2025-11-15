package org.firstinspires.ftc.teamcode.command_based.subsystems;

import dev.nextftc.core.commands.Command;
import dev.nextftc.core.commands.utility.LambdaCommand;
import dev.nextftc.core.subsystems.Subsystem;
import dev.nextftc.hardware.impl.MotorEx;

public class Intake implements Subsystem {
    public static final Intake INSTANCE = new Intake();
    private Intake() { }

    private final MotorEx motor = new MotorEx("in_motor").reversed();

    public final Command off = new LambdaCommand().setStart(() -> { motor.setPower(0.0); }).requires(this).named("IntakeOff");
    public final Command on = new LambdaCommand().setStart(() -> { motor.setPower(1.0); }).requires(this).named("IntakeOn");
}