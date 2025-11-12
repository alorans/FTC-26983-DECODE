package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
public class Main extends OpMode {
    DcMotor RF, LF, RB, LB, in_motor;
    static boolean previousA, previousY;
    ColorSensor s1 = new ColorSensor();
    ColorSensor s2 = new ColorSensor();
    ColorSensor s3 = new ColorSensor();
    StringBuilder slot1 = new StringBuilder("");
    StringBuilder slot2 = new StringBuilder("");
    StringBuilder slot3 = new StringBuilder("");
    slots sortslots = new slots(s1, s2, s3, slot1, slot2, slot3);

    static CRServo table;
    static double duration = 0.5;
    static double power = 0.25;

    String pattern = "PGP";
    int slotIndex = 0;
    boolean sorting = false;
    boolean movingToSlot = false;
    long startTime = 0;
    int currentTargetSlot = -1;

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

        sortslots.update(telemetry);
        telemetry.addData("Status", "Initialized");
        telemetry.update();
    }

    @Override
    public void loop() {
        move();
        toggleOutTake();
        sortslots.update(telemetry);

        // toggle sorting mode
        if (gamepad1.y && !previousY) {
            sorting = !sorting;
            slotIndex = 0;
            movingToSlot = false;
        }
        previousY = gamepad1.y;

        if (sorting) {
            handleSorting();
        } else {
            table.setPower(0);
        }

        telemetry.addData("Sorting", sorting);
        telemetry.addData("SlotIndex", slotIndex);
        telemetry.addData("TargetSlot", currentTargetSlot);
        telemetry.addData("S1", slot1.toString());
        telemetry.addData("S2", slot2.toString());
        telemetry.addData("S3", slot3.toString());
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

    public void toggleOutTake() {
        boolean running = false;
        if (gamepad1.a && !previousA) {
            running = !running;
        }
        previousA = gamepad1.a;

        if (running) {
            in_motor.setPower(0.3);
        } else {
            in_motor.setPower(0);
        }
    }

    private void handleSorting() {
        // if we are not currently moving to a slot, find next one
        if (!movingToSlot && slotIndex < pattern.length()) {
            String targetColor = pattern.substring(slotIndex, slotIndex + 1);
            currentTargetSlot = sortslots.getSlot(targetColor);
            if (currentTargetSlot == -1) {
                sorting = false; // invalid color
                return;
            }

            startTime = System.currentTimeMillis();
            movingToSlot = true;
            if (currentTargetSlot != 1) table.setPower(power);
        }

        // if currently moving, check if done
        if (movingToSlot) {
            double elapsed = (System.currentTimeMillis() - startTime) / 1000.0;
            double targetDuration = 0;

            if (currentTargetSlot == 1) targetDuration = 0;
            else if (currentTargetSlot == 2) targetDuration = duration * 3;
            else if (currentTargetSlot == 3) targetDuration = duration * 2;

            if (elapsed >= targetDuration) {
                table.setPower(0);
                movingToSlot = false;
                slotIndex++;

                if (slotIndex >= pattern.length()) {
                    sorting = false;
                }
            }
        }
    }
}