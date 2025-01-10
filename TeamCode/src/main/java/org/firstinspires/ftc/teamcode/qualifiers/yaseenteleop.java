package org.firstinspires.ftc.teamcode.qualifiers;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;

@TeleOp
public class yaseenteleop extends LinearOpMode {

    hardwaremap hardware = new hardwaremap();
    CurrentUnit amps;


    @Override
    public void runOpMode() throws InterruptedException {
        hardware.init(hardwareMap);

        waitForStart();

        // init loop
        while (!isStarted() && !isStopRequested()) {
            telemetry.addLine("this is the init loop. pluh");
        }

        // when play is pressed
        while (opModeIsActive()) {
            double x = gamepad1.left_stick_x;
            double y = gamepad1.left_stick_y;
            double r = gamepad1.right_stick_x;

            double frontLeftPower = y + x + r;
            double frontRightPower = y - x - r;
            double backLeftPower = y - x + r;
            double backRightPower = y + x - r;

            hardware.frontLeftDrive.setPower(frontLeftPower * .75);
            hardware.frontRightDrive.setPower(frontRightPower * .75);
            hardware.backLeftDrive.setPower(backLeftPower * .75);
            hardware.backRightDrive.setPower(backRightPower * .75);

            telemetry.addData("backleftDrive", hardware.backLeftDrive.getCurrent(amps));
            telemetry.update();
        }

    }
}