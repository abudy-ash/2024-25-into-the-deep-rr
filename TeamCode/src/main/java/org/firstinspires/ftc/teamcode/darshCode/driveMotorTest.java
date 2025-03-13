package org.firstinspires.ftc.teamcode.darshCode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.IMU;

@Disabled
@TeleOp(name="Motor Test")

public class driveMotorTest extends LinearOpMode {

    DcMotorEx frontRightDrive;



    public void runOpMode(){

        frontRightDrive = hardwareMap.get(DcMotorEx.class,"frontRight");
        telemetry.addData("Init","True");
        telemetry.update();

        waitForStart();

        while(opModeIsActive()){
            frontRightDrive.setPower(100);
            frontRightDrive.setDirection(DcMotorEx.Direction.FORWARD);
            telemetry.addData("Motor Driving","True");
            telemetry.update();
        }
    }




}
