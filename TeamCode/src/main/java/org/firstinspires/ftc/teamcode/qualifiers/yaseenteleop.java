package org.firstinspires.ftc.teamcode.qualifiers;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

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
        while (!isStarted()) {
            telemetry.addLine("this is the init loop. pluh");
            telemetry.update();
        }

        // when play is pressed
        while (opModeIsActive()) {
            armDebugging();
            if (gamepad2.left_stick_button) {
                hardware.arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            }
            double x = gamepad1.left_stick_x;
            double y = gamepad1.left_stick_y;
            double r = gamepad1.right_stick_x;



            double frontLeftPower = y + x + r;
            double frontRightPower = y - x - r;
            double backLeftPower = y - x + r;
            double backRightPower = y + x - r;

            hardware.frontLeftDrive.setPower(frontLeftPower * .3);
            hardware.frontRightDrive.setPower(frontRightPower * .3);
            hardware.backLeftDrive.setPower(backLeftPower * .3);
            hardware.backRightDrive.setPower(backRightPower * .3);

           // telemetry.addData("backleftDrive", hardware.backLeftDrive.getCurrent(amps));
            telemetry.update();


        }

    }

    public void armDebugging() {
        hardware.arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        hardware.arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        int armPosition = hardware.arm.getCurrentPosition();

        telemetry.addData("Arm position", armPosition);
        telemetry.update();

        double armPower = gamepad2.left_stick_y;
        hardware.arm.setPower(armPower);


    }
}