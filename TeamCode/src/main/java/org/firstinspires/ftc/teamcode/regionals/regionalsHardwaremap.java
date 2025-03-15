package org.firstinspires.ftc.teamcode.regionals;

import com.qualcomm.hardware.dfrobot.HuskyLens;
import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;

public class regionalsHardwaremap {
    HuskyLens huskyLens;

    RevBlinkinLedDriver lights;

    // Regular Movement DC Motors
    DcMotorEx backLeftDrive, backRightDrive, frontLeftDrive, frontRightDrive;

    // Intake-related DC Motors
    DcMotorEx linearLift, armRotator, hangingLeft, hangingRight;

    // Intake Servos
    Servo claw, springLeft, springRight, linkage, wrist;

    HardwareMap hardwareMap;

    // Sensors
    public IMU imu;

    public void init(HardwareMap ahwMap){
        hardwareMap = ahwMap;

        //Camera
        huskyLens = hardwareMap.get(HuskyLens.class,"huskyLens");
//
//        //Initialized the LED Lights
        lights = hardwareMap.get(RevBlinkinLedDriver.class,"lights");


        // Initialization Code
        backRightDrive = hardwareMap.get(DcMotorEx.class, "backRight");
        backLeftDrive = hardwareMap.get(DcMotorEx.class, "backLeft");
        frontRightDrive = hardwareMap.get(DcMotorEx.class,"frontRight");
        frontLeftDrive = hardwareMap.get(DcMotorEx.class,"frontLeft");

        hangingLeft = hardwareMap.get(DcMotorEx.class, "hangingLeft");
        hangingLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        hangingLeft.setTargetPosition(0);
        hangingLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        hangingRight = hardwareMap.get(DcMotorEx.class, "hangingRight");
        hangingRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        hangingRight.setTargetPosition(0);
        hangingRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        linearLift = hardwareMap.get(DcMotorEx.class,"linearLift");
        linearLift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        linearLift.setTargetPosition(0);
//        linearLift.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        armRotator = hardwareMap.get(DcMotorEx.class,"armRotator");
        armRotator.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        claw = hardwareMap.get(Servo.class, "claw");
        linkage = hardwareMap.get(Servo.class, "linkage");
        wrist = hardwareMap.get(Servo.class, "wrist");
        springLeft = hardwareMap.get(Servo.class,"springLeft");
        springRight = hardwareMap.get(Servo.class,"springRight");


        imu = hardwareMap.get(IMU.class,"imu");


        // Movement (might not be necessary/waste power)
        backRightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontLeftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // VERY IMPORTANT TO MAINTAIN POSITION
        linearLift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        armRotator.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        hangingLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        hangingRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        backRightDrive.setDirection(DcMotorSimple.Direction.REVERSE);
    }


}
