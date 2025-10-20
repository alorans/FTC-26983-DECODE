package org.firstinspires.ftc.teamcode;

import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

public class ColorSensorTest extends OpMode {

    ColorSensor bench = new ColorSensor();


    @Override
    public void init(){
        bench.init(hardwareMap);
    }

    @Override
    public void loop() {
        bench.getDetectedColor(telemetry);
    }

}
