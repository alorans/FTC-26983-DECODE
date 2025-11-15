package org.firstinspires.ftc.teamcode.command_based;

import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;
import dev.nextftc.ftc.ActiveOpMode;

public class ColorSensor {
    private final NormalizedColorSensor colorSensor;

    // Constructor that uses NextFTC ActiveOpMode to get hardware
    public ColorSensor(String name) {
        colorSensor = ActiveOpMode.hardwareMap().get(NormalizedColorSensor.class, name);
        colorSensor.setGain(15);
    }

    public enum ArtifactColor {
        GREEN,
        PURPLE,
        OTHER
    }

    public ArtifactColor getColor(){
        NormalizedRGBA colors = colorSensor.getNormalizedColors();
        float normRed = colors.red / colors.alpha;
        float normGreen = colors.green / colors.alpha;
        float normBlue = colors.blue / colors.alpha;

        // Convert to HSV
        float[] hsv = new float[3];
        android.graphics.Color.RGBToHSV(
            (int)(normRed * 255),
            (int)(normGreen * 255),
            (int)(normBlue * 255),
            hsv
        );

        float hue = hsv[0];        // 0-360
        float saturation = hsv[1]; // 0-1
        float value = hsv[2];      // 0-1

        // Check for minimum saturation/value to avoid noise
        if (saturation < 0.3 || value < 0.2) {
            return ArtifactColor.OTHER;
        }

        // Check hue ranges
        if (hue >= 90 && hue <= 150) {
            return ArtifactColor.GREEN;
        } else if (hue >= 270 && hue <= 330) {
            return ArtifactColor.PURPLE;
        } else {
            return ArtifactColor.OTHER;
        }
    }
}
