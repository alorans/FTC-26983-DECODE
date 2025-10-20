## Using Sample OpModes

The easiest way to create your own OpMode is to copy a Sample OpMode and make it your own.

Sample OpModes are here:
  FtcRobotController/java/org.firstinspires.ftc.robotcontroller/external/samples

Copy OpModes here to use them (the same folder this readme is in):
  TeamCode/java/org.firstinspires.ftc.teamcode

## PedroPathing Tuning OpMode

This repository comes with the PedroPathing tuning OpMode under "pedroPathing/Tuning.java".
To tune your robot, upload the project and follow the [PedroPathing tuning instructions](https://pedropathing.com/docs/pathing/tuning). 

## Declaring OpModes

Every OpMode is declared with a number of **class annotations**. For example:

```
 @TeleOp(name="Template: Linear OpMode", group="Linear Opmode")
 @Disabled
```

Comment out or delete the ``@Disabled`` annotation to make your OpMode visible.

- ``name`` defines the OpMode name that will appear on your driver station.
- ``group`` can be used to organize your driver station's list of OpModes.
