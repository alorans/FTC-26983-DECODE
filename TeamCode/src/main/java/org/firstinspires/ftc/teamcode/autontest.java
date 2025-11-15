package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.util.concurrent.TimeUnit;

import kotlin.OptionalExpectation;

@Autonomous
public class autontest extends OpMode {
    DcMotor RF;
    DcMotor LF;
    DcMotor RB;
    DcMotor LB;
    int i;
    ElapsedTime timer;


    @Override
    public void init(){
        RF = hardwareMap.get(DcMotor.class, "RF");
        LF = hardwareMap.get(DcMotor.class, "LF");
        RB = hardwareMap.get(DcMotor.class, "RB");
        LB = hardwareMap.get(DcMotor.class, "LB");
        i = 0;
        timer = new ElapsedTime(ElapsedTime.Resolution.SECONDS);

    }

    @Override
    public void loop(){
        if(timer.time(TimeUnit.SECONDS)> 1){
            move(1);
        }
    }

    public void move(double vertical) {
        double horizontal = gamepad1.left_stick_x;
        double pivot = gamepad1.right_stick_x;

        RF.setPower(pivot + (vertical + horizontal));
        RB.setPower(pivot + (vertical - horizontal));
        LF.setPower(-pivot + (vertical - horizontal));
        LB.setPower(-pivot + (vertical + horizontal));
    }
}
