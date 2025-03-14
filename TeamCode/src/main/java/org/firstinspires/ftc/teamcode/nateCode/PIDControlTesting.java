package org.firstinspires.ftc.teamcode.nateCode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

public class PIDControlTesting extends LinearOpMode {

    DcMotorEx frontLeft, frontRight, backLeft, backRight;

    double integralSum = 0;
    double Kp = 0;
    double Ki = 0;
    double Kd = 0;

    private BNO055IMU imu;

    ElapsedTime timer = new ElapsedTime();
    private double lastError = 0;

    @Override
    public void runOpMode() throws InterruptedException {

        frontLeft = hardwareMap.get(DcMotorEx.class,"frontLeft");
        frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        frontRight = hardwareMap.get(DcMotorEx.class,"frontRight");
        frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        backLeft = hardwareMap.get(DcMotorEx.class,"backLeft");
        backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        backRight = hardwareMap.get(DcMotorEx.class,"backRight");
        backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        backRight.setDirection(DcMotorSimple.Direction.REVERSE);

        imu = hardwareMap.get(BNO055IMU.class,"imu");
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.mode = BNO055IMU.SensorMode.IMU;
        parameters.angleUnit = BNO055IMU.AngleUnit.RADIANS;
        imu.initialize(parameters);


        telemetry.addData("Status","Initialized");
        telemetry.update();


        waitForStart();

        double referenceAngle = Math.toRadians(90);

        while(opModeIsActive()){
            double power = PidControl(referenceAngle, imu.getAngularOrientation().firstAngle);

            frontLeft.setPower(power);
            frontRight.setPower(power);
            backLeft.setPower(power);
            backRight.setPower(power);
        }

    }

    public double PidControl(double reference, double state){
        double error = angleWrap(reference - state);
        integralSum += error * timer.seconds();
        double derivative = (error - lastError) / timer.seconds();
        lastError = error;

        timer.reset();

        double output = (error * Kp) + (derivative * Kd) + (integralSum * Ki);
        return output;
    }

    public double angleWrap(double radians){
        while(radians > Math.PI){
            radians -= 2 * Math.PI;
        }
        while(radians < -Math.PI){
            radians += 2 * Math.PI;
        }
        return radians;
    }
}
