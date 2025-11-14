package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class TurretTest extends OpMode {
    Turret turret;
    LimeLightCamera limelight;
    @Override
    public void init(){
        turret = new Turret(hardwareMap);
        limelight = new LimeLightCamera(hardwareMap);
    }

    @Override
    public void loop(){
        turret.shoot(limelight.getVars(telemetry).getTy());
    }
}
