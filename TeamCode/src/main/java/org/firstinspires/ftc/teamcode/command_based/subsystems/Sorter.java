package org.firstinspires.ftc.teamcode.command_based.subsystems;

import dev.nextftc.core.commands.utility.LambdaCommand;
import dev.nextftc.core.subsystems.Subsystem;
import dev.nextftc.hardware.impl.CRServoEx;
import dev.nextftc.core.commands.Command;

import org.firstinspires.ftc.teamcode.command_based.ColorSensor;
import org.firstinspires.ftc.teamcode.command_based.ColorSensor.ArtifactColor;

public class Sorter implements Subsystem {
    public static final Sorter INSTANCE = new Sorter();
    private Sorter() { }

    private final CRServoEx table = new CRServoEx("table");
    private final ColorSensor[] colorSensors = {
        new ColorSensor("s1"),
        new ColorSensor("s2"),
        new ColorSensor("s3")
    };

    public final Command on = new LambdaCommand().setStart(() -> { table.setPower(0.3); }).requires(this).named("SorterSpin");
    public final Command off = new LambdaCommand().setStart(() -> { table.setPower(0.0); }).requires(this).named("SorterSpin");
}

//    public void toSlot(int n) {
//        if (n < 0 || n >= 3) throw new IllegalArgumentException("Bad slot index: " + n);
//        double currentPos = table.
//
//    }

//    public ArtifactColor readSlot(int n) {
//        if (n < 0 || n >= 3) throw new IllegalArgumentException("Bad slot index: " + n);
//        return colorSensors[n].getColor();
//    }

//    /**
//     * Positive is clockwise
//     * Negative is counterclockwise
//     */
//    private Angle shortestPath(Angle from, Angle to) {
//        double minAngle = Math.min(Math.abs(from.inDeg - to.inDeg), Math.abs(to.inDeg - from.inDeg));
//        int multiplier =
//    }

//    @Override
//    public void initialize() {
//        table.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//    }

//    ----INTERFACE----
//    toSlot(n) = spin to position n
//    readSlot(n) = read the color at a position n
//