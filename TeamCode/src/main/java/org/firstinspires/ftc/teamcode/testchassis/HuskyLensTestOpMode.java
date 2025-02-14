package org.firstinspires.ftc.teamcode.testchassis;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="HuskyLens Test", group="Test")
public class HuskyLensTestOpMode extends LinearOpMode {
    hardwaremapTestChassis robot = new hardwaremapTestChassis();

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            byte[] data = robot.readHuskyLensData(0x32, 5); // Read 5 bytes from HuskyLens
            telemetry.addData("HuskyLens Data", data);
            telemetry.update();
        }
    }
}
