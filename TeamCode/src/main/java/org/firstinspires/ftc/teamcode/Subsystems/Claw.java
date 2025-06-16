package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.Servo;

public class Claw extends SubsystemBase {

    Servo claw, leftServo, rightServo; //Servos are small motors that are usually used to power small mechanisms

    public Claw(Servo claw_position, Servo leftServo, Servo rightServo){
        this.claw = claw_position;
        this.leftServo = leftServo;
        this.rightServo = rightServo;

        claw_position.setDirection(Servo.Direction.FORWARD);
        rightServo.setDirection(Servo.Direction.REVERSE);
        rightServo.scaleRange(0, 0.5);

    }

    public void open(){
        claw.setPosition(1); //Full rotation is 1. 180 degrees is 0.5

    }

    public void close(){
        claw.setPosition(0);

    }

    public void direction_up() {
        rightServo.setDirection(Servo.Direction.REVERSE);
        leftServo.setDirection(Servo.Direction.FORWARD);
        leftServo.setPosition(1);
        rightServo.setPosition(1);
    }

    public void direction_down() {

        rightServo.setDirection(Servo.Direction.REVERSE);
        leftServo.setDirection(Servo.Direction.FORWARD);
        leftServo.setPosition(0.4);
        rightServo.setPosition(0.4);
    }

    public void zero() {

        rightServo.setDirection(Servo.Direction.REVERSE);
        leftServo.setDirection(Servo.Direction.FORWARD);
        leftServo.setPosition(0);
        rightServo.setPosition(0);
    }
    }
