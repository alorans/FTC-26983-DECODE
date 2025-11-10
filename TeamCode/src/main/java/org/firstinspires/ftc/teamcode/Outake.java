package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class Outake extends OpMode {
    DcMotor out_motor;
    boolean running = false;
    boolean previousA = false;

    @Override
    public void init(){
        out_motor = hardwareMap.get(DcMotor.class, "out_motor");

    }

    @Override
    public void loop(){
        if (gamepad1.a && !previousA) {
            running = !running; // toggle the state
        }
        previousA = gamepad1.a; // remember button state

        if (running) {
            out_motor.setPower(0.3);
            telemetry.addData("running", running);
            telemetry.update();
        }
        else {
            out_motor.setPower(0);
        }
    }
}
