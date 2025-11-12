package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
public class Main extends OpMode {
    DcMotor RF, LF, RB, LB, in_motor;
    boolean previousA = false;
    boolean previousY = false;
    boolean outtakeRunning = false;

    ColorSensor s1 = new ColorSensor();
    ColorSensor s2 = new ColorSensor();
    ColorSensor s3 = new ColorSensor();

    StringBuilder slot1 = new StringBuilder("");
    StringBuilder slot2 = new StringBuilder("");
    StringBuilder slot3 = new StringBuilder("");

    slots sortslots = new slots(s1, s2, s3, slot1, slot2, slot3);

    CRServo table;
    double duration = 0.5;   // baseline time unit in seconds
    double power = 0.25;

    String pattern = "PGP";
    int slotIndex = 0;
    boolean sorting = false;

    // movement state for non-blocking timing
    boolean movingToSlot = false;
    long startTimeMs = 0;
    int currentTargetSlot = -1;
    double currentTargetDuration = 0.0;

    @Override
    public void init() {
        RF = hardwareMap.get(DcMotor.class, "RF");
        LF = hardwareMap.get(DcMotor.class, "LF");
        RB = hardwareMap.get(DcMotor.class, "RB");
        LB = hardwareMap.get(DcMotor.class, "LB");
        in_motor = hardwareMap.get(DcMotor.class, "in_motor");
        table = hardwareMap.get(CRServo.class, "table");

        RF.setDirection(DcMotorSimple.Direction.REVERSE);
        RB.setDirection(DcMotorSimple.Direction.REVERSE);

        s1.init(hardwareMap, "s1");
        s2.init(hardwareMap, "s2");
        s3.init(hardwareMap, "s3");

        // initialize slot strings once
        sortslots.update(telemetry);

        telemetry.addData("Status", "Initialized");
        telemetry.update();
    }

    @Override
    public void loop() {
        // drive and intake controls
        move();
        toggleOutTake();

        // constantly update sensor readings (so slot strings are fresh)
        sortslots.update(telemetry);

        // toggle sorting mode when Y is pressed (edge detect)
        if (gamepad1.y && !previousY) {
            sorting = !sorting;
            slotIndex = 0;
            movingToSlot = false;
            currentTargetSlot = -1;
        }
        previousY = gamepad1.y;

        // main sorting state machine (non-blocking)
        if (sorting) {
            handleSortingStep();
        } else {
            // ensure table is stopped when not sorting
            table.setPower(0);
        }

        // telemetry for debugging
        telemetry.addData("sorting", sorting);
        telemetry.addData("slotIndex", slotIndex);
        telemetry.addData("targetSlot", currentTargetSlot);
        telemetry.addData("s1", slot1.toString());
        telemetry.addData("s2", slot2.toString());
        telemetry.addData("s3", slot3.toString());
        telemetry.update();
    }

    public void move() {
        double vertical = -gamepad1.left_stick_y;
        double horizontal = gamepad1.left_stick_x;
        double pivot = gamepad1.right_stick_x;

        RF.setPower(pivot + (-vertical + horizontal));
        RB.setPower(pivot + (-vertical - horizontal));
        LF.setPower(-pivot + (-vertical - horizontal));
        LB.setPower(-pivot + (-vertical + horizontal));
    }

    // make running persistent between loop() calls
    public void toggleOutTake() {
        if (gamepad1.a && !previousA) {
            outtakeRunning = !outtakeRunning;
        }
        previousA = gamepad1.a;

        if (outtakeRunning) {
            in_motor.setPower(0.3);
        } else {
            in_motor.setPower(0);
        }
    }

    // single non-blocking step of the sorter
    private void handleSortingStep() {
        // if we're not currently moving to a slot, start the next movement
        if (!movingToSlot) {
            if (slotIndex >= pattern.length()) {
                // finished pattern
                sorting = false;
                table.setPower(0);
                return;
            }

            String targetColor = pattern.substring(slotIndex, slotIndex + 1); // "P" or "G"
            currentTargetSlot = sortslots.getSlot(targetColor);

            if (currentTargetSlot == -1) {
                // sensor didn't return expected value; stop sorting safely
                telemetry.addData("Error", "Target slot not found for color: " + targetColor);
                sorting = false;
                table.setPower(0);
                return;
            }

            // determine how long to run the table for this slot
            if (currentTargetSlot == 1) {
                currentTargetDuration = 0.0;
            } else if (currentTargetSlot == 2) {
                currentTargetDuration = duration * 3.0;
            } else if (currentTargetSlot == 3) {
                currentTargetDuration = duration * 2.0;
            } else {
                currentTargetDuration = 0.0;
            }

            // start motion if needed
            if (currentTargetDuration > 0.0) {
                table.setPower(power);
            } else {
                table.setPower(0);
            }

            startTimeMs = System.currentTimeMillis();
            movingToSlot = true;
            return;
        }

        // if moving, check elapsed time
        long now = System.currentTimeMillis();
        double elapsedSeconds = (now - startTimeMs) / 1000.0;

        if (elapsedSeconds >= currentTargetDuration) {
            // reached slot
            table.setPower(0);
            movingToSlot = false;
            slotIndex++;

            // if pattern finished, stop sorting
            if (slotIndex >= pattern.length()) {
                sorting = false;
                currentTargetSlot = -1;
            }
        } else {
            // still moving — leave table power as set
        }
    }

    // kept per request; fixed the bug (compares to start + seconds*1000)
    // not used for long blocking in this implementation, but present if you want
    public static void sleep(double seconds) {
        long start = System.currentTimeMillis();
        long target = start + (long)(seconds * 1000.0);
        while (System.currentTimeMillis() < target) {
            // busy-wait intentionally small — avoid huge CPU hog by yielding
            try { Thread.sleep(1); } catch (InterruptedException e) { Thread.currentThread().interrupt(); break; }
        }
    }
}