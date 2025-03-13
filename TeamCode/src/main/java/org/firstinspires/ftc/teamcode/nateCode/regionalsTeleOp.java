package org.firstinspires.ftc.teamcode.nateCode;
//I wanted my own folder just to fit in lol
import com.qualcomm.hardware.dfrobot.HuskyLens;
import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.teamcode.qualifiers.hardwaremap;

@TeleOp(name="Regionals TeleOp")
public class regionalsTeleOp extends LinearOpMode {

    regionalsHardwareMap hardware = new regionalsHardwareMap();

    @Override
    public void runOpMode() throws InterruptedException{
        hardware.init(hardwareMap);
        hardware.huskyLens.selectAlgorithm(HuskyLens.Algorithm.COLOR_RECOGNITION);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        blinkinGreen();

        waitForStart();

        blinkinBlack();


        while (opModeIsActive()){
            darshTeleOp();
        }
    }
    public void darshTeleOp(){


        // Movement
        double y = -gamepad1.left_stick_y; // Remember, Y stick value is reversed
        double x = gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing
        double rx = gamepad1.right_stick_y;

        // Denominator is the largest motor power (absolute value) or 1
        // This ensures all the powers maintain the same ratio,
        // but only if at least one is out of the range [-1, 1]
        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
        double frontLeftPower = (y + x + rx) / denominator;
        double backLeftPower = (y - x + rx) / denominator;
        double frontRightPower = (y - x - rx) / denominator;
        double backRightPower = (y + x - rx) / denominator;

        hardware.frontRightDrive.setPower(frontRightPower);
        hardware.backRightDrive.setPower(-backRightPower);
        hardware.frontLeftDrive.setPower(frontLeftPower);
        hardware.backLeftDrive.setPower(backLeftPower);

        //Gamepad 1
            //Open Spring
            if(gamepad1.x || gamepad1.square || gamepad1.triangle || gamepad1.circle){
                //Test to see if 0 is open or close
                hardware.springLeft.setPosition(0);
                hardware.springRight.setPosition(1);
            }
            if(gamepad1.x || gamepad1.square || gamepad1.triangle || gamepad1.circle){
                hardware.springRight.setPosition(1);
                hardware.springLeft.setPosition(0);
            }

            //Extend linear lift
            if(gamepad1.left_bumper){
                //Test values
                hardware.linearLift.setTargetPosition(999);
                hardware.linearLift.setPower(1);

            }

            //Retract linear lift
            if(gamepad1.right_bumper){
                //Test Values
                hardware.linearLift.setTargetPosition(-1);
                hardware.linearLift.setPower(1);
            }

            //Rotate hanging backwards
            if(gamepad1.left_trigger >.01){
                hardware.hangingLeft.setTargetPosition(100);
                hardware.hangingRight.setTargetPosition(100);
                hardware.hangingLeft.setPower(.8);
                hardware.hangingRight.setPower(.8);
            }

            //
            //Rotate hanging forward
            if(gamepad1.right_trigger > 0.01){
                //These might be wrong values idk i'm losing my mind fix it later
                //It's like 12 am i'm bugging out
                hardware.hangingLeft.setTargetPosition(0);
                hardware.hangingRight.setTargetPosition(0);
                hardware.hangingLeft.setPower(.8);
                hardware.hangingRight.setPower(.8);
            }


        //Gamepad 2
            //Open claw
            if(gamepad2.left_trigger > 0.2) {
                hardware.claw.setPosition(0);
            }

            //Close claw
            if(gamepad2.right_trigger > 0.2) {
                hardware.claw.setPosition(1);
            }

            //Extend Horizontal Slide
            if(gamepad2.dpad_up) {
                hardware.linkage.setPosition(1);
            }

            //Retract Horizontal Slide
            if(gamepad2.dpad_down) {
                hardware.linkage.setPosition(0);
            }

            //Put at specimen height
            if(gamepad2.left_stick_button) {
                hardware.linearLift.setTargetPosition(500); //Test
                hardware.linearLift.setPower(0.8);
            }

            //Rotate arm
        hardware.armRotator.setPower(gamepad2.right_stick_y);

            //Wrist up
            if(gamepad2.left_bumper) {
                hardware.wrist.setPosition(1);
            }

            //Wrist down
            if(gamepad2.right_bumper) {
                hardware.wrist.setPosition(0);
            }

        colorRecognition();

        telemetry.update();

    }

    public void colorRecognition(){
        HuskyLens.Block[] blocks = hardware.huskyLens.blocks();
        telemetry.addData("Block Count", blocks.length);
        for (int i = 0; i < blocks.length; i++) {
            int thisColorID = blocks[i].id;

            switch(thisColorID){
                case 1:
                    //Code for if it's Blue (ID = 1)
                    telemetry.addData("Color ID","Blue");
                    blinkinBlue();
                    gamepad1.setLedColor(0,0,255,1);
                    gamepad2.setLedColor(0,0,255,1);
                    break;
                case 2:
                    //SET UP RED AS ID = 2
                    //Code for if it's Red (ID = 2)
                    telemetry.addData("Color ID", "Red");
                    blinkinRed();
                    gamepad1.setLedColor(255,0,0,1);
                    gamepad2.setLedColor(255,0,0,1);
                    break;
                case 3:
                    //SET UP YELLOW AS ID = 3
                    //Code for if it's Yellow (ID = 3)
                    telemetry.addData("Color ID","Yellow");
                    blinkinYellow();
                    gamepad1.setLedColor(225,225,0,1);
                    gamepad2.setLedColor(225,225,0,1);
                    break;
                default:
                    blinkinBlack();
                    gamepad1.setLedColor(113,202,235,1);
                    gamepad2.setLedColor(113,202,235,1);
            }
        }
    }

    public void blinkinRed(){hardware.lights.setPattern(RevBlinkinLedDriver.BlinkinPattern.RED);}
    public void blinkinGreen(){hardware.lights.setPattern(RevBlinkinLedDriver.BlinkinPattern.GREEN);}
    public void blinkinYellow(){hardware.lights.setPattern(RevBlinkinLedDriver.BlinkinPattern.YELLOW);}
    public void blinkinBlue(){hardware.lights.setPattern(RevBlinkinLedDriver.BlinkinPattern.BLUE);}
    public void blinkinBlack(){hardware.lights.setPattern(RevBlinkinLedDriver.BlinkinPattern.BLACK);}
}
