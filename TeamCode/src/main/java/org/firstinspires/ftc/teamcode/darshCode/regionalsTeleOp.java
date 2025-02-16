package org.firstinspires.ftc.teamcode.darshCode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.IMU;

@TeleOp(name="Regionals TeleOp")
public class regionalsTeleOp extends OpMode {
    // Regular Movement DC Motors
    DcMotorEx backLeftDrive, backRightDrive, frontLeftDrive, frontRightDrive;

    // Intake-related DC Motors
    DcMotorEx linearLift, armRotator;

    // Intake Servos
    Servo claw;
    CRServo linkage, wrist;

    // Sensors
    public IMU imu;

    public void darshTeleOp(){

        // Movement
        double y = -gamepad1.left_stick_y; // Remember, Y stick value is reversed
        double x = gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing
        double rx = gamepad1.right_stick_x;

        // Denominator is the largest motor power (absolute value) or 1
        // This ensures all the powers maintain the same ratio,
        // but only if at least one is out of the range [-1, 1]
        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
        double frontLeftPower = (y + x + rx) / denominator;
        double backLeftPower = (y - x + rx) / denominator;
        double frontRightPower = (y - x - rx) / denominator;
        double backRightPower = (y + x - rx) / denominator;

        frontRightDrive.setPower(frontRightPower);
        backRightDrive.setPower(backRightPower);
        frontLeftDrive.setPower(frontLeftPower);
        backLeftDrive.setPower(backLeftPower);

        // Hanging


        // CONTROLLER 2
            // Arm
                // DC Motors
                    linearLift.setPower(Math.pow(gamepad2.left_stick_y, 3)); // Joystick strength ranges from -1 to 1

                    // Get Angle from Triggers (these values range from 0-1)
                    double angleUp = Math.pow(gamepad2.left_trigger, 3);
                    double angleDown = -Math.pow(gamepad2.right_trigger, 3);
                    double armAngle = angleUp + angleDown;
                    armRotator.setPower(armAngle);

                // Arm Servo
                    double armLength = (gamepad2.right_stick_y+1)/2; // Transforms range of -1 to 1 onto servo power of 0 to 1
                    linkage.setPower(armLength);

                // Claw Servos
                    // Claw Wrist
                        if (gamepad2.dpad_down) {claw.setPosition(0);}
                        if (gamepad2.dpad_up) {claw.setPosition(1);}

                    // Claw
                        if (gamepad2.left_bumper) {claw.setPosition(0);}
                        if (gamepad2.right_bumper) {claw.setPosition(1);}


        telemetry.update();

    }

    @Override
    public void init() {
        // Initialization Code
        backRightDrive = hardwareMap.get(DcMotorEx.class, "backRight");
        backLeftDrive = hardwareMap.get(DcMotorEx.class, "backLeft");
        frontRightDrive = hardwareMap.get(DcMotorEx.class,"frontRight");
        frontLeftDrive = hardwareMap.get(DcMotorEx.class,"frontLeft");

        linearLift = hardwareMap.get(DcMotorEx.class,"linearLift");
        armRotator = hardwareMap.get(DcMotorEx.class,"armRotator");

        claw = hardwareMap.get(Servo.class, "claw");
        linkage = hardwareMap.get(CRServo.class, "linkage");
        wrist = hardwareMap.get(CRServo.class, "wrist");

        imu = hardwareMap.get(IMU.class,"imu");

        // Zero Brake behavior

        // Movement (might not be necessary/waste power)
        backRightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontLeftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // VERY IMPORTANT TO MAINTAIN POSITION
        linearLift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        armRotator.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        
        // To change motor direction: motor.setDirection(DcMotorEx.Direction.REVERSE);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

    }

    @Override
    public void loop() {

        darshTeleOp();

    }

}