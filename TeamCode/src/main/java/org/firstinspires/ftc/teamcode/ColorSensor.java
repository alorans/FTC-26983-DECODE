package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class ColorSensor {
    NormalizedColorSensor colorSensor;


    public enum DetectedColor{
        GREEN,

        PURPLE,

        UNKNOWN
    }

    public void init(HardwareMap hwMap, String name){
        colorSensor = hwMap.get(NormalizedColorSensor.class, name);
        colorSensor.setGain(15);
    }

    public DetectedColor getDetectedColor(){

        NormalizedRGBA colors = colorSensor.getNormalizedColors();
        float normRed, normGreen, normBlue;
        normRed = colors.red / colors.alpha;
        normGreen = colors.green / colors.alpha;
        normBlue = colors.blue / colors.alpha;
        /*
            if (normRed > rmax) {rmax = normRed;}
            if (normRed < rmin) {rmin = normRed;}
            if (normBlue > bmax) {bmax = normBlue;}
            if (normBlue < bmin) {bmin = normBlue;}
            if (normGreen > gmax) {gmax = normGreen;}
            if (normGreen < gmin) {gmin = normGreen;}

        }
        telemetry.addData("Red range", ( "" + rmin + "," + rmax));
        telemetry.addData("Blue range", ( "" +bmin + "," + bmax));
        telemetry.addData("Green range", ( "" +gmin + "," + gmax));
        telemetry.update();



        telemetry.addData("red", normRed);
        telemetry.addData("green", normGreen);
        telemetry.addData("blue", normBlue);
        telemetry.update();

        GREEN = 0.03 - 0.13, 0.1 - 0.4, 0.17 - 0.5
        PURPLE = 0.08 - 0.3, 0.15 - 0.61, 0.09 - 0.31
        */
        if(normRed >0.03 && normRed <0.13 && normBlue >0.1 && normBlue < 0.4 && normGreen >0.17 && normGreen <0.5){
            return DetectedColor.GREEN;
        }
        if(normRed > 0.1 && normRed < 0.31 && normBlue > 0.21 && normBlue < 0.61 && normGreen > 0.09 && normGreen < 0.31){
            return DetectedColor.PURPLE;
        }

        return DetectedColor.UNKNOWN;
    }
}
