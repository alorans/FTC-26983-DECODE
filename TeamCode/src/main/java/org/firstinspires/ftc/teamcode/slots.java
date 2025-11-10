package org.firstinspires.ftc.teamcode;

import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class slots {
    ColorSensor c1;
    ColorSensor c2;
    ColorSensor c3;
    StringBuilder s1;
    StringBuilder s2;
    StringBuilder s3;
    public slots(ColorSensor c1, ColorSensor c2, ColorSensor c3, StringBuilder s1, StringBuilder s2, StringBuilder s3){
        this.c1 = c1;
        this.c2 = c2;
        this.c3 = c3;
        this.s1 = s1;
        this.s2 = s2;
        this.s3 = s3;

    }

    public void update(Telemetry telemetry){
        s1.replace(0, s1.length(), c1.getDetectedColor().toString());
        s2.replace(0, s2.length(), c2.getDetectedColor().toString());
        s3.replace(0, s3.length(), c3.getDetectedColor().toString());
        toOneChar(s3);
        toOneChar(s2);
        toOneChar(s1);
    }

    public int getSlot(String color){
        //Make sure outtake slot is marked as slot 3
        if(s1.toString().equalsIgnoreCase(color)){return 1;}
        else if(s2.toString().equalsIgnoreCase(color)){return 2;}
        else if(s3.toString().equalsIgnoreCase(color)){return 3;}
        return -1;
    }
    public void toOneChar(StringBuilder  stb){
        if(stb.toString().equalsIgnoreCase("purple")){stb.replace(0, stb.length(), "p");}
        else if(stb.toString().equalsIgnoreCase("green")){stb.replace(0, stb.length(), "g");}
        else stb.replace(0, stb.length(), "");
    }
}