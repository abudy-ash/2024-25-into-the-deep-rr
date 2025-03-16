package org.firstinspires.ftc.teamcode.regionals;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.hardware.dfrobot.HuskyLens;
import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;

@TeleOp(name = "TeleOp Testing")
public class RegTeleOpTesting extends LinearOpMode {

    int linearMin = 0;
    double baseline = 0.2;

    regionalsHardwaremap hardware = new regionalsHardwaremap();

    @Override
    public void runOpMode() throws InterruptedException{

        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        hardware.init(hardwareMap);

        hardware.huskyLens.selectAlgorithm(HuskyLens.Algorithm.COLOR_RECOGNITION);


        telemetry.addData("Status", "Initialized");
        hardware.linkage.setPosition(1);
        telemetry.update();



        blinkinGreen();

        waitForStart();

        hardware.linearLift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        linearMin = hardware.linearLift.getCurrentPosition();


        blinkinBlack();


        while (opModeIsActive()){
            darshTeleOp();
        }
    }
    public void darshTeleOp(){

        telemetry.addData("Lift Start Pos:", linearMin);
        telemetry.addData("Lift Pos", hardware.linearLift.getCurrentPosition());

//        boolean liftUp = gamepad1.left_bumper;
//        boolean liftDown = gamepad1.right_bumper;

        double lift = -gamepad2.left_stick_y;
        double liftSpeed;

        if(lift<0){
            liftSpeed = 0.5*lift;
        }
        else{
            liftSpeed = lift;
        }

        if(hardware.linearLift.getCurrentPosition() >= linearMin){
            hardware.linearLift.setPower(liftSpeed);
        } else {
            hardware.linearLift.setPower(Math.pow(liftSpeed, 2));
        }

//        if(lift != 0){
//
//        } else{
//            hardware.linearLift.setPower(baseline);
//        }





        boolean hangingBack = gamepad1.left_trigger > 0;
        boolean hangingForward = gamepad1.right_trigger > 0;
        boolean openClaw = gamepad2.left_trigger > 0;
        boolean closeClaw = gamepad2.right_trigger > 0;
        boolean extendSlide = gamepad2.dpad_up;
        boolean retractSlide = gamepad2.dpad_down;
        boolean specimenHeight = gamepad2.left_stick_button;
        float rotateArm = -gamepad2.right_stick_y;
        boolean wristUp = gamepad2.left_bumper;
        boolean wristDown = gamepad2.right_bumper;


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

        hardware.frontRightDrive.setPower(frontRightPower);
        hardware.backRightDrive.setPower(backRightPower); //change to reverse in hw map
        hardware.frontLeftDrive.setPower(frontLeftPower);
        hardware.backLeftDrive.setPower(backLeftPower);

        //Gamepad 1
        //Open Spring
        if(gamepad1.dpad_up){
            //Test to see if 0 is open or close
            hardware.springLeft.setPosition(-0.7);
            hardware.springRight.setPosition(0.7);
        }


        //Rotate hanging backwards
        if(hangingBack){
            gamepad1.rumble(100);
            gamepad2.rumble(100);
            hardware.hangingLeft.setPower(1);
            hardware.hangingRight.setPower(1);
            hardware.hangingLeft.setTargetPosition(0);
            hardware.hangingRight.setTargetPosition(0);
        }

        //
        //Rotate hanging forward
        if(hangingForward){
            gamepad1.rumble(100);
            gamepad2.rumble(100);
            hardware.hangingLeft.setPower(1);
            hardware.hangingRight.setPower(1);
            hardware.hangingRight.setTargetPosition(700);
            hardware.hangingRight.setTargetPosition(700);
            hardware.hangingLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            hardware.hangingRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            hardware.hangingRight.setTargetPosition(1000);
            hardware.hangingRight.setTargetPosition(1000);
        }


        //Gamepad 2
        //Open claw
        if(openClaw) {
            hardware.claw.setPosition(0.9);
        }

        //Close claw
        if(closeClaw) {
            hardware.claw.setPosition(-1);
        }

        //Extend Horizontal Slide
        if(extendSlide) {
            hardware.linkage.setPosition(0.25);
        }

        //Retract Horizontal Slide
        if(retractSlide) {
            hardware.linkage.setPosition(0.75);
        }

        //Put at specimen height
        // there is a ~1.15 voltage loss at max height, when reached, use ohms law to calculate the current given that reistance is 470 ohms
        if(specimenHeight) {
            while(hardware.linearLift.getCurrent(CurrentUnit.valueOf("MILLIAMPS")) < 2.45) {
                hardware.linearLift.setPower(1);
            }
            hardware.linearLift.setPower(0);
        }

        // Rotate arm
        hardware.armRotator.setPower(rotateArm);

        //Wrist up
        if(wristUp) {
            hardware.wrist.setPosition(1);
        }

        //Wrist down
        if(wristDown) {
            hardware.wrist.setPosition(0);
        }

        colorRecognition();
        telemetry.addData("backRightDrive", hardware.backRightDrive.getVelocity());
        telemetry.addLine();
        telemetry.addLine();
        telemetry.addLine();

        telemetry.addData("linearlift current draw", hardware.linearLift.getCurrent(CurrentUnit.valueOf("MILLIAMPS")));
        telemetry.addData("springLeft Pose", hardware.springLeft.getPosition());
        telemetry.addData("springRight Pose", hardware.springRight.getPosition());
        telemetry.addData("linkage Pose", hardware.linkage.getPosition());
        telemetry.addData("lift Power", hardware.linearLift.getPower());
        telemetry.addData("Claw", hardware.claw.getPosition());
        telemetry.addData("Lift position", hardware.linearLift.getCurrentPosition());
        telemetry.addData("Mode",hardware.linearLift.getMode());
        telemetry.addData("Arm power", hardware.armRotator.getPower());
        telemetry.addData("LinearPos", hardware.linearLift.getCurrentPosition());
        telemetry.addData("LinearMin", linearMin);
        telemetry.addData("armRotator", rotateArm);
        telemetry.addData("hangingLeft", hardware.hangingLeft.getCurrentPosition());
        telemetry.addData("hangingRight", hardware.hangingRight.getCurrentPosition());

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
                    gamepad1.setLedColor(0,0,255,1000);
                    gamepad2.setLedColor(0,0,255,1000);
                    break;
                case 2:
                    //SET UP RED AS ID = 2
                    //Code for if it's Red (ID = 2)
                    telemetry.addData("Color ID", "Red");
                    blinkinRed();
                    gamepad1.setLedColor(255,0,0,1000);
                    gamepad2.setLedColor(255,0,0,1000);
                    break;
                case 3:
                    //SET UP YELLOW AS ID = 3
                    //Code for if it's Yellow (ID = 3)
                    telemetry.addData("Color ID","Yellow");
                    blinkinYellow();
                    gamepad1.setLedColor(225,225,0,1000);
                    gamepad2.setLedColor(225,225,0,1000);
                    break;
                default:
                    blinkinBlack();
                    gamepad1.setLedColor(113,202,235,1000);
                    gamepad2.setLedColor(113,202,235,1000);
            }
        }
    }

    public void blinkinRed(){
        hardware.lights.setPattern(RevBlinkinLedDriver.BlinkinPattern.RED);
    }
    public void blinkinGreen(){hardware.lights.setPattern(RevBlinkinLedDriver.BlinkinPattern.GREEN);}
    public void blinkinYellow(){hardware.lights.setPattern(RevBlinkinLedDriver.BlinkinPattern.YELLOW);}
    public void blinkinBlue(){hardware.lights.setPattern(RevBlinkinLedDriver.BlinkinPattern.BLUE);}
    public void blinkinBlack(){hardware.lights.setPattern(RevBlinkinLedDriver.BlinkinPattern.BLACK);}
}
