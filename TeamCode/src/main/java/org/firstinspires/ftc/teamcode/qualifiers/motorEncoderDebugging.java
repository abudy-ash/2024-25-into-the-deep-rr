package org.firstinspires.ftc.teamcode.qualifiers;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class motorEncoderDebugging extends LinearOpMode {

    double newTarget;
    double ticks = 560;


    @Override
    public void runOpMode() throws InterruptedException {
        DcMotor motor = hardwareMap.dcMotor.get("motor");
        motor.setDirection(DcMotorSimple.Direction.FORWARD);
        motor.setPower(0);
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        while (opModeIsActive()){

            if (gamepad1.a) {
                encoder(2);
            }


        }


    }

    public void encoder(int turnage) {
        newTarget = ticks/turnage;
    }
}
