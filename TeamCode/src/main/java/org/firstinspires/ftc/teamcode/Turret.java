package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Turret {
    DcMotor outtake;

    public Turret(HardwareMap hardwareMap) {
        outtake = hardwareMap.get(DcMotor.class, "turret");
    }

    public void shoot(double ty){
        double distance = getDistance(ty);
    }

    public double getDistance(double ty){
        return((2.4583 - 0.875) / Math.tan(Math.toRadians(15 + ty)));
    }
}