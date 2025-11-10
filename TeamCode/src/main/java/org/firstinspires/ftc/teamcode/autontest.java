package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import kotlin.OptionalExpectation;

@Autonomous
public class autontest extends OpMode {
    DcMotor RF;

    @Override
    public void init(){
        RF = hardwareMap.get(DcMotor.class, "RF");

    }

    @Override
    public void loop(){
        RF.setPower(1);
    }
}
