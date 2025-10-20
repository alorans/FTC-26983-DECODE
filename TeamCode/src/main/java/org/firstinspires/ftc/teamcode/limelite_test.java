/*
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class limelite_test {

    private final String limelightIP = "http://10.XX.XX.11:5801"; // Replace XX.XX with your team number or Limelight IP
    private JSONObject latestData;

    public limelite_test() {
        updateData();
    }

    private void updateData() {
        try {
            URL url = new URL(limelightIP + "/json");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                content.append(line);
            }
            in.close();
            conn.disconnect();

            latestData = new JSONObject(content.toString());
        } catch (Exception e) {
            latestData = null; // No connection or error
        }
    }

    public boolean hasTarget() {
        updateData();
        if (latestData == null) return false;
        return latestData.optDouble("tv", 0.0) == 1.0;
    }

    public double getTx() {
        if (latestData == null) return 0.0;
        return latestData.optDouble("tx", 0.0);
    }

    public double getTy() {
        if (latestData == null) return 0.0;
        return latestData.optDouble("ty", 0.0);
    }

    public double getTa() {
        if (latestData == null) return 0.0;
        return latestData.optDouble("ta", 0.0);
    }


    public void updateTelemetry(OpMode opMode) {
        opMode.telemetry.addData("Limelight Has Target", hasTarget());
        opMode.telemetry.addData("Limelight Tx", getTx());
        opMode.telemetry.addData("Limelight Ty", getTy());
        opMode.telemetry.addData("Limelight Ta", getTa());
        opMode.telemetry.update();
    }
}
 */