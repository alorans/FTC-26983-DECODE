package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
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

        RF.setDirection(DcMotorSimple.Direction.REVERSE);
        RB.setDirection(DcMotorSimple.Direction.REVERSE);
        i = 0;
        timer = new ElapsedTime(ElapsedTime.Resolution.SECONDS);

    }

    @Override
    public void loop(){
        if(timer.time(TimeUnit.SECONDS)> 0.5){
            move(1);
        }
        else{move(0);}
    }

    public void move(double vertical) {

        RF.setPower(vertical);
        RB.setPower(vertical);
        LF.setPower(vertical);
        LB.setPower(vertical);
    }
}
