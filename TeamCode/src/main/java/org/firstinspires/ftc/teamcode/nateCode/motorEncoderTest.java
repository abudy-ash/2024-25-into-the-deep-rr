package org.firstinspires.ftc.teamcode.nateCode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@TeleOp(name="Encoder Test",group="Testing")
public class motorEncoderTest extends LinearOpMode {

    regionalsHardwareMap hardware = new regionalsHardwareMap();

    public void runOpMode() throws InterruptedException{
        hardware.init(hardwareMap);
        hardware.linearLift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        int position;

        telemetry.addData("Status","Initialized");
        telemetry.update();

        waitForStart();

        while(opModeIsActive()){
            position = hardware.linearLift.getCurrentPosition();
            telemetry.addData("Position:",position);
            telemetry.update();
        }
    }
}
