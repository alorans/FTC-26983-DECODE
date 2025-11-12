//package org.firstinspires.ftc.teamcode;
//
//import com.qualcomm.robotcore.eventloop.opmode.OpMode;
//import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//import com.qualcomm.robotcore.hardware.DcMotor;
//import com.qualcomm.robotcore.hardware.DcMotorSimple;
//
//public class MechanumDrive extends OpMode {
//    DcMotor RF;
//    DcMotor LF;
//    DcMotor RB;
//    DcMotor LB;
//
//    public void move(){
//        double vertical = -gamepad1.left_stick_y;
//        double horizontal = gamepad1.left_stick_x;
//        double pivot =  gamepad1.right_stick_x;
//
//        RF.setPower(pivot + (-vertical + horizontal));
//        RB.setPower(pivot + (-vertical - horizontal));
//        LF.setPower(-pivot + (-vertical - horizontal));
//        LB.setPower(-pivot + (-vertical + horizontal));
//
//    }
//
//    public void init(){
//        RF = hardwareMap.get(DcMotor.class, "RF");
//        LF = hardwareMap.get(DcMotor.class, "LF");
//        RB = hardwareMap.get(DcMotor.class, "RB");
//        LB = hardwareMap.get(DcMotor.class, "LB");
//
//        RF.setDirection(DcMotorSimple.Direction.REVERSE);
//        RB.setDirection(DcMotorSimple.Direction.REVERSE);
//
//    }
//
//    public void loop(){
//        move();
//    }
//
//}
