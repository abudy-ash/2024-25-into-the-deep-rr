package org.firstinspires.ftc.teamcode.qualifiers;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;

public class hardwaremap {
    public DcMotorEx backLeftDrive, backRightDrive, frontLeftDrive, frontRightDrive;
    public IMU imu;
    public WebcamName camera;

    HardwareMap hwMap;

    public void hardwaremap() {

    }

    public void init(HardwareMap ahwMap) {
        hwMap = ahwMap;

        backRightDrive = hwMap.get(DcMotorEx.class, "backRight");
        backLeftDrive = hwMap.get(DcMotorEx.class, "backLeft");
        frontRightDrive = hwMap.get(DcMotorEx.class,"frontRight");
        frontLeftDrive = hwMap.get(DcMotorEx.class,"frontLeft");

        imu = hwMap.get(IMU.class,"imu");
        camera = hwMap.get(WebcamName.class,"camera");

        // Motor Direction
        backRightDrive.setDirection(DcMotorEx.Direction.FORWARD);
        backLeftDrive.setDirection(DcMotorEx.Direction.FORWARD);
        frontRightDrive.setDirection(DcMotorEx.Direction.FORWARD);
        frontLeftDrive.setDirection(DcMotorSimple.Direction.FORWARD);

        // Setting Power to 0
        backRightDrive.setPower(0);
        backLeftDrive.setPower(0);
        frontLeftDrive.setPower(0);
        frontRightDrive.setPower(0);

        // Running without encoder
        backRightDrive.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        backLeftDrive.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        frontLeftDrive.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        frontRightDrive.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);

        // Zero Brake behavior
        backRightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontLeftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }
}