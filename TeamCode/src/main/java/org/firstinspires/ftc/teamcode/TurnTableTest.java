package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp
public class TurnTableTest extends OpMode {
    ColorSensor s1 = new ColorSensor();
    ColorSensor s2 = new ColorSensor();
    ColorSensor s3 = new ColorSensor();
    StringBuilder slot1 = new StringBuilder("");
    StringBuilder slot2 = new StringBuilder("");
    StringBuilder slot3 = new StringBuilder("");
    slots sortslots = new slots(s1, s2, s3, slot1, slot2, slot3);
    static double duration = 0.5;
    static double buffer = -0.05;
    static double power = 0.25;
    String pattern = "PGP";
    int slotIndex;
    static CRServo table;

    @Override
    public void init(){
        table = hardwareMap.get(CRServo.class, "table");
        s1.init(hardwareMap, "s1");
        s2.init(hardwareMap, "s2");
        s3.init(hardwareMap, "s3");
        sortslots.update(telemetry);
        slotIndex = 0;
    }

    @Override
    public void loop(){
        boolean done = false;
        while (!goToSlot(sortslots.getSlot(pattern.substring(slotIndex, slotIndex + 1)))) {
            telemetry.addData("Gone to slot?", done);
            telemetry.update();
            long start = System.currentTimeMillis();
            table.setPower(0.1);
        }
//        do {
//            sortslots.update(telemetry);
//            int slot = sortslots.getSlot(pattern.substring(slotIndex, slotIndex + 1));
//            done = goToSlot(slot);
//            telemetry.addData("Gone to slot?", done);
//            telemetry.update();
//            if(!done){
//                long start = System.currentTimeMillis();
//                table.setPower(0.1);
//            }
//        }while (!done);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {}

        // Increment slot index
        if(slotIndex < 2) {
            slotIndex++;
        }
    }
    public static boolean goToSlot(int slot) {
        if (slot == 1){
            return true;
        }
        else if(slot == 2){
            table.setPower(power);
            try {
                Thread.sleep((long) (duration * 3000));
            } catch (InterruptedException ignored) {}
            table.setPower(0);
        }
        else if(slot == 3){
            table.setPower(power);
            try {
                Thread.sleep((long) (duration * 2000));
            } catch (InterruptedException ignored) {}
            table.setPower(0);
        }
        else{
            return false;
        }
        return true;
    }

}
