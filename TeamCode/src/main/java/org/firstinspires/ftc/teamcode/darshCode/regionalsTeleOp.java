package org.firstinspires.ftc.teamcode.darshCode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorEx;


@TeleOp(name="Regionals TeleOp")
public class regionalsTeleOp extends OpMode {
    // Regular Movement DC Motors
    DcMotorEx backLeftDrive, backRightDrive, frontLeftDrive, frontRightDrive;

    // Intake-related DC Motors
    DcMotorEx linearLift, armRotator;

    // Intake Servos
    Servo claw;
    CRServo linkage;

    public void darshTeleOp(){

        // Movement
        double vertical;
        double horizontal;
        double pivot;

        vertical = -gamepad1.left_stick_y;
        horizontal = gamepad1.left_stick_x;
        pivot = gamepad1.right_stick_x;

        // Get Motor Speed from Joysticks
        frontRightDrive.setPower(pivot + (-vertical + horizontal));
        backRightDrive.setPower(pivot + (-vertical - horizontal));
        frontLeftDrive.setPower(pivot + (-vertical - horizontal));
        backLeftDrive.setPower(pivot + (-vertical + horizontal));

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

                // Claw Servo
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