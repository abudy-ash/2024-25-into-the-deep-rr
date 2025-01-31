package org.firstinspires.ftc.teamcode.qualifiers;

import static java.lang.Boolean.FALSE;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;

@TeleOp
public class robotcentricmecanumdrive extends LinearOpMode {

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

            spoolAction();
            intakeAction();
            telemetryAction();

            double x = gamepad1.left_stick_x;
            double y = gamepad1.left_stick_y;
            double r = gamepad1.right_stick_x;



            double frontLeftPower = y + x + r;
            double frontRightPower = y - x - r;
            double backLeftPower = y - x + r;
            double backRightPower = y + x - r;

            hardware.frontLeftDrive.setPower(frontLeftPower);
            hardware.frontRightDrive.setPower(frontRightPower);
            hardware.backLeftDrive.setPower(backLeftPower);
            hardware.backRightDrive.setPower(backRightPower);

            // telemetry.addData("backleftDrive", hardware.backLeftDrive.getCurrent(amps));
            telemetry.update();


        }

    }

    public void spoolAction() {
        int targetPositionHorizontal = 750;
        double rightSpoolPower = gamepad2.left_stick_y;
        double leftSpoolPower = rightSpoolPower;

//        if (gamepad2.dpad_up ) {
//            hardware.leftSpool.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//            hardware.leftSpool.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//            hardware.rightSpool.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//            hardware.rightSpool.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//            hardware.leftSpool.setTargetPosition(targetPositionHorizontal);
//            hardware.rightSpool.setTargetPosition(targetPositionHorizontal);
//
//            hardware.leftSpool.setPower(0.5);
//            hardware.rightSpool.setPower(0.5);
//
//            if (rightSpoolPower>0){
//                hardware.rightSpool.setVelocity(72);
//                hardware.leftSpool.setVelocity(288);
//            }
//            if (rightSpoolPower<0){
//                hardware.rightSpool.setVelocity(288);
//                hardware.leftSpool.setVelocity(72);
//            }
//
//            hardware.leftSpool.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//            hardware.rightSpool.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//
//            hardware.rightSpool.setPower(0);
//            hardware.leftSpool.setPower(0);
//
//        }
//        else {
//            hardware.leftSpool.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//            hardware.rightSpool.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//
//            hardware.leftSpool.setPower(leftSpoolPower);
//            hardware.rightSpool.setPower(rightSpoolPower);
//
//
//
//
//
//        }


    }

    public void intakeAction() {


        if (gamepad2.left_bumper) {
            hardware.claw.setPosition(1);
        }
        else if (gamepad2.right_bumper) {
            hardware.claw.setPosition(-1);
        }

//        if (gamepad2.dpad_up) {
//            hardware.wrist.setPosition(-1);
//        }
//
//        else if (gamepad2.dpad_down) {
//            hardware.wrist.setPosition(1);
//        }
//
//        if (gamepad2.dpad_right) {
//            double currentPosWrist = hardware.wrist.getPosition();
//            hardware.wrist.setPosition(currentPosWrist+0.1);
//        }
//        if (gamepad2.dpad_left) {
//            double currentPosWrist = hardware.wrist.getPosition();
//            hardware.wrist.setPosition(currentPosWrist-0.1);
//        }

        double armPower = gamepad2.right_stick_y;
        double accomodateSpool = armPower;

//        hardware.leftSpool.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//        hardware.rightSpool.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//
//        hardware.leftSpool.setPower(accomodateSpool);
//        hardware.rightSpool.setPower(accomodateSpool);



        hardware.arm.setPower(armPower);



        double wristPosition = 0;

        if (gamepad2.dpad_up) {
            wristPosition += 0.5;
            hardware.wrist.setPosition(wristPosition);
        }

        while (gamepad2.dpad_down) {
            wristPosition -= 0.1;
            hardware.wrist.setPosition(wristPosition);
        }







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

        telemetry.addData("wrist position", hardware.wrist.getPosition());
        telemetry.addData("claw position", hardware.claw.getPosition());
        telemetry.update();
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