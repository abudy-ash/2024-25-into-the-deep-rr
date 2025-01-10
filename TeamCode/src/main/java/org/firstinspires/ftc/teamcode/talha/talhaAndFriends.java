package org.firstinspires.ftc.teamcode.talha;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;
import org.firstinspires.ftc.teamcode.qualifiers.hardwaremap;

public class talhaAndFriends extends OpMode {
    double talhaMaritalstatus = 0;
    hardwaremap robot = new hardwaremap();
    CurrentUnit unit;
    @Override
    public void init() {
        telemetry.addLine("Talha is gay: 1-Y, 0-N");
        telemetry.addData("Talha Marital Status: ", talhaMaritalstatus);
        telemetry.update();
        robot.init(hardwareMap);

    }

    @Override
    public void loop() {
        telemetry.addLine("Talha is gay: 1-Y, 0-N");
        telemetry.addData("Talha Marital Status: ", robot.backLeftDrive.getCurrent(unit));
        if (robot.backLeftDrive.getCurrent(unit) > 0) {
            gay();
        }
        telemetry.update();
    }
    public void gay() {
        robot.robotPower(0);
    }
}
