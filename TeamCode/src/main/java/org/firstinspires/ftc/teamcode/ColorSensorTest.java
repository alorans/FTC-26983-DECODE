package org.firstinspires.ftc.teamcode;

import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class ColorSensorTest extends OpMode {

    ColorSensor bench = new ColorSensor();
    ColorSensor.DetectedColor detectedColor;

    @Override
    public void init(){
        bench.init(hardwareMap, "color_sensor");
    }

    @Override
    public void loop() {
        detectedColor = bench.getDetectedColor();
        telemetry.addData("Color detected", detectedColor);
        telemetry.update();
    }

}
