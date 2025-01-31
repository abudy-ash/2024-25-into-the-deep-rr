package org.firstinspires.ftc.teamcode.qualifiers;

import static java.lang.Boolean.TRUE;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.qualifiers.hardwaremap;

@TeleOp
public class FieldCentricMecanumTeleop extends LinearOpMode {
    hardwaremap hardware = new hardwaremap();
    @Override
    public void runOpMode() throws InterruptedException {
        hardware.init(hardwareMap);

        waitForStart();

        while (!isStarted() && !isStopRequested()) {
            hardware.leftSpool.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            hardware.rightSpool.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            hardware.leftSpool.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            hardware.rightSpool.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            telemetry.addLine("this is the init loop i think ");
            telemetry.addLine("If you can read this message, then this means that the spool encoders have been reset");
            telemetry.update();
        }


        while (opModeIsActive()) {
            FieldCentricMecanumdDrive();
            spoolAction();
            intakeAction();
            telemetryAction();
            //drawerSlideDebugging();

        }
    }

    public void FieldCentricMecanumdDrive() {
        double y = gamepad1.left_stick_y; // Remember, Y stick value is reversed
        double x = gamepad1.left_stick_x;
        double rx = gamepad1.right_stick_x;

        // This button choice was made so that it is hard to hit on accident,
        // it can be freely changed based on preference.
        // The equivalent button is start on Xbox-style controllers.
        if (gamepad1.left_stick_button) {
            hardware.imu.resetYaw();
        }

        double botHeading = hardware.imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);


        // Rotate the movement direction counter to the bot's rotation
        double rotX = x * Math.cos(-botHeading) - y * Math.sin(-botHeading);
        double rotY = x * Math.sin(-botHeading) + y * Math.cos(-botHeading);

        rotX = rotX * 1.1;  // Counteract imperfect strafing

        // Denominator is the largest motor power (absolute value) or 1
        // This ensures all the powers maintain the same ratio,
        // but only if at least one is out of the range [-1, 1]
        double denominator = Math.max(Math.abs(rotY) + Math.abs(rotX) + Math.abs(rx), 1);
        double frontLeftPower = (rotY + rotX + rx) / denominator;
        double backLeftPower = (rotY - rotX + rx) / denominator;
        double frontRightPower = (rotY - rotX - rx) / denominator;
        double backRightPower = (rotY + rotX - rx) / denominator;

        hardware.frontLeftDrive.setPower(frontLeftPower);
        hardware.backLeftDrive.setPower(backLeftPower);
        hardware.frontRightDrive.setPower(frontRightPower);
        hardware.backRightDrive.setPower(backRightPower);

    }

    public void spoolAction() {
        int targetPositionHorizontal = 750;
        double rightSpoolPower = gamepad2.left_stick_y;
        double leftSpoolPower = rightSpoolPower;

        if (gamepad2.dpad_up ) {
            hardware.leftSpool.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            hardware.leftSpool.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            hardware.rightSpool.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            hardware.rightSpool.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            hardware.leftSpool.setTargetPosition(targetPositionHorizontal);
            hardware.rightSpool.setTargetPosition(targetPositionHorizontal);

            hardware.leftSpool.setPower(0.5);
            hardware.rightSpool.setPower(0.5);

            if (rightSpoolPower>0){
                hardware.rightSpool.setVelocity(72);
                hardware.leftSpool.setVelocity(288);
            }
            if (rightSpoolPower<0){
                hardware.rightSpool.setVelocity(288);
                hardware.leftSpool.setVelocity(72);
            }

            hardware.leftSpool.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            hardware.rightSpool.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            hardware.rightSpool.setPower(0);
            hardware.leftSpool.setPower(0);

        }
        else {
            hardware.leftSpool.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            hardware.rightSpool.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

            hardware.leftSpool.setPower(leftSpoolPower);
            hardware.rightSpool.setPower(rightSpoolPower);





        }


    }

    public void intakeAction() {



        if (gamepad2.left_bumper) {
            hardware.claw.setPosition(1);
        }
        else if (gamepad2.right_bumper) {
            hardware.claw.setPosition(-1);
        }

        if (gamepad2.dpad_up) {
            hardware.wrist.setPosition(-1);
        }

        else if (gamepad2.dpad_down) {
            hardware.wrist.setPosition(1);
        }

        double armPower = gamepad2.right_stick_y;
        hardware.arm.setPower(armPower);

    }

    public void drawerSlideDebugging() {
        int targetPosition =  750;
        int maxPosition = 750;
        if (gamepad1.dpad_down) {
            hardware.leftSpool.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            hardware.rightSpool.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            hardware.leftSpool.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            hardware.rightSpool.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            telemetry.addLine("ENCODERS RESET");
            telemetry.update();

        }


        if (gamepad1.dpad_up) {
            hardware.leftSpool.setTargetPosition(targetPosition);
            hardware.rightSpool.setTargetPosition(targetPosition);

            hardware.leftSpool.setPower(0.5);
            hardware.rightSpool.setPower(0.5);

            hardware.leftSpool.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            hardware.rightSpool.setMode(DcMotor.RunMode.RUN_TO_POSITION);


                hardware.rightSpool.setPower(0);
                hardware.leftSpool.setPower(0);




        }

        if (gamepad1.dpad_left) {


        }


        telemetry.addData("leftSpool Encoder Count:", hardware.leftSpool.getCurrentPosition());
        telemetry.addData("rightSpool Encoder Count:", hardware.rightSpool.getCurrentPosition());
        telemetry.update();

    }

    public void telemetryAction() {
//        telemetry.addData("robot's heading: ", hardware.imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES));
//        telemetry.addData("backLeftDrive Velocity", hardware.backLeftDrive.getVelocity());
//        telemetry.addData("frontLeftDrive Velocity", hardware.frontLeftDrive.getVelocity());
//        telemetry.addData("frontRightDrive Velocity", hardware.frontRightDrive.getVelocity());
//        telemetry.addData("backRightDrive Velocity", hardware.backRightDrive.getVelocity());
//        telemetry.addData("backLeftDrive EncoderPosition", hardware.backLeftDrive.getCurrentPosition());

        telemetry.addData("leftSpoolpower" , hardware.leftSpool.getPower());
        telemetry.addData("rightSpoolpower" , hardware.rightSpool.getPower());
        telemetry.update();
    }
}
