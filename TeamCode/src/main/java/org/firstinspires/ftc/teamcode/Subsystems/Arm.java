package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

public class Arm extends SubsystemBase {

    //Define motors/controllers that'll be used
    DcMotor ADM; //DcMotor are the motors used in drivetrains and big mechanisms
    GamepadEx operator; //Gamepads are the controllers. If you need controller inputs, you'll need it in here.

    //Constructor to define presets and parameters (needed objects)
    public Arm(DcMotor ADM, GamepadEx operator) {

        //Needed so it works in teleop file
        this.ADM = ADM;
        this.operator = operator;

        ADM.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        ADM.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    //Methods (voids) are your commands. This is where you write what presets/modes you want your mechanism to have
    public void direction_up() {

        if (Math.abs(operator.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER)) > 0) {
            ADM.setPower(.5);
        } else {
            ADM.setPower(0);
        }

    }

    public void direction_down() {

        if (Math.abs(operator.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER)) > 0) {
            ADM.setPower(-0.5);
        } else {
            ADM.setPower(0);
        }
    }
}

