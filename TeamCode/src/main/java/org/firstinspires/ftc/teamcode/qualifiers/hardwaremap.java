package org.firstinspires.ftc.teamcode.qualifiers;

import static org.firstinspires.ftc.teamcode.MecanumDrive.PARAMS;

import com.acmerobotics.roadrunner.ftc.LazyImu;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;

public class hardwaremap {
    public DcMotorEx backLeftDrive, backRightDrive, frontLeftDrive, frontRightDrive, arm, leftSpool, rightSpool;

    public Servo claw, wrist;
    public IMU imu;
    public LazyImu lazyImu;
    public WebcamName camera;

    HardwareMap hwMap;
    public RevHubOrientationOnRobot.LogoFacingDirection logoFacingDirection =
            RevHubOrientationOnRobot.LogoFacingDirection.RIGHT;
    public RevHubOrientationOnRobot.UsbFacingDirection usbFacingDirection =
            RevHubOrientationOnRobot.UsbFacingDirection.UP;

    public void hardwaremap() {


    }

    public void init(HardwareMap ahwMap) {
        hwMap = ahwMap;

        backRightDrive = hwMap.get(DcMotorEx.class, "backRight");
        backLeftDrive = hwMap.get(DcMotorEx.class, "backLeft");
        frontRightDrive = hwMap.get(DcMotorEx.class,"frontRight");
        frontLeftDrive = hwMap.get(DcMotorEx.class,"frontLeft");

        leftSpool = hwMap.get(DcMotorEx.class, "leftSpool");
        rightSpool = hwMap.get(DcMotorEx.class, "rightSpool");

        arm = hwMap.get(DcMotorEx.class, "arm");

        claw = hwMap.get(Servo.class, "claw");
        wrist = hwMap.get(Servo.class, "wrist");


        imu = hwMap.get(IMU.class,"imu");

        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(RevHubOrientationOnRobot.LogoFacingDirection.RIGHT, RevHubOrientationOnRobot.UsbFacingDirection.UP));
        imu.initialize(parameters);


       // camera = hwMap.get(WebcamName.class,"camera");

        // Motor Direction
        backRightDrive.setDirection(DcMotorEx.Direction.FORWARD);
        backLeftDrive.setDirection(DcMotorEx.Direction.FORWARD);
        frontRightDrive.setDirection(DcMotorEx.Direction.FORWARD);
        frontLeftDrive.setDirection(DcMotorSimple.Direction.REVERSE);

        arm.setDirection(DcMotorSimple.Direction.FORWARD);
        leftSpool.setDirection(DcMotorSimple.Direction.REVERSE);
        rightSpool.setDirection(DcMotorSimple.Direction.REVERSE);

        // Setting Power to 0
        backRightDrive.setPower(0);
        backLeftDrive.setPower(0);
        frontLeftDrive.setPower(0);
        frontRightDrive.setPower(0);

        arm.setPower(0);
        leftSpool.setPower(0);
        rightSpool.setPower(0);

        arm.setPower(0);

        // Running without encoder
        backRightDrive.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        backLeftDrive.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        frontLeftDrive.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        frontRightDrive.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);

        arm.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        leftSpool.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightSpool.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        // Zero Brake behavior
        backRightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontLeftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftSpool.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightSpool.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void robotPower(double power) {
        backRightDrive.setPower(power);
        backLeftDrive.setPower(power);
        frontLeftDrive.setPower(power);
        frontRightDrive.setPower(power);

        arm.setPower(power);
        leftSpool.setPower(power);
        rightSpool.setPower(power);
    }
}