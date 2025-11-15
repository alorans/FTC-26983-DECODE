package org.firstinspires.ftc.teamcode;
//Testing
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
public class Main extends OpMode {
    DcMotor RF, LF, RB, LB, in_motor;
    static boolean previousA = false;
    static boolean previousY = false;
    boolean prevLT = false;
    boolean reverse = false;
    boolean outtakeRunning = false;

    ColorSensor s1 = new ColorSensor();
    ColorSensor s2 = new ColorSensor();
    ColorSensor s3 = new ColorSensor();

    StringBuilder slot1 = new StringBuilder("");
    StringBuilder slot2 = new StringBuilder("");
    StringBuilder slot3 = new StringBuilder("");

    slots sortslots = new slots(s1, s2, s3, slot1, slot2, slot3);

    static CRServo table;
    static double duration = 0.5; // base movement time
    static double power = 0.25;

    String pattern = "PGP";
    boolean sorting = false;
    //Turret turret;

    @Override
    public void init() {
        //turret = new Turret(hardwareMap);
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
        toggleInTake();
        sortslots.update(telemetry);

        // Toggle sorting mode when Y pressed
        if (gamepad1.y && !previousY) {
            sorting = !sorting;
        }
        previousY = gamepad1.y;

        if (sorting) {
            runSortingSequence();
            sorting = false; // run once per press
        }
        if(gamepad1.left_trigger > 0.25 && !prevLT){
            reverse = !reverse;
        }
        prevLT = gamepad1.left_trigger > 0.25;
        if(gamepad1.x){
            table.setPower(power);
        }
        else{ table.setPower(0);}

        telemetry.addData("Sorting", sorting);
        telemetry.addData("S1", slot1.toString());
        telemetry.addData("S2", slot2.toString());
        telemetry.addData("S3", slot3.toString());
        telemetry.addData("reverse?", reverse);
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

    public void toggleInTake() {
        if (gamepad1.a && !previousA) {
            outtakeRunning = !outtakeRunning;
        }
        previousA = gamepad1.a;

        if (outtakeRunning && !reverse) {
            in_motor.setPower(-1);
        }
        else if(outtakeRunning && reverse){
            in_motor.setPower(1);
        }
        else {
            in_motor.setPower(0);
        }
    }

    private void runSortingSequence() {
        for (int i = 0; i < pattern.length(); i++) {
            String targetColor = pattern.substring(i, i + 1);
            int slot = sortslots.getSlot(targetColor);

            if (slot == -1) {
                telemetry.addData("Error", "No slot found for " + targetColor);
                telemetry.update();
                continue;
            }

            goToSlot(slot);
            sleep(1); // wait one second between each ball
        }
        table.setPower(0);
    }

    public static void goToSlot(int slot) {
        if (slot == 1) {
            table.setPower(0);
        } else if (slot == 2) {
            table.setPower(power);
            sleep(0.5); // move time adjustment
            table.setPower(0);
        } else if (slot == 3) {
            table.setPower(power);
            sleep(1); // move time adjustment
            table.setPower(0);
        }
    }

    public static void sleep(double seconds) {
        long start = System.currentTimeMillis();
        long target = start + (long)(seconds * 1000);
        while (System.currentTimeMillis() < target) {
            // small yield so it doesn't hang the CPU
            try { Thread.sleep(1); } catch (InterruptedException ignored) {}
        }
    }
}