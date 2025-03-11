package org.firstinspires.ftc.teamcode.nateCode;

import com.qualcomm.hardware.dfrobot.HuskyLens;
import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.IMU;

@TeleOp(name="Regionals TeleOp")
public class regionalsTeleOp extends OpMode {

    HuskyLens huskyLens;

    RevBlinkinLedDriver lights;

    // Regular Movement DC Motors
    DcMotorEx backLeftDrive, backRightDrive, frontLeftDrive, frontRightDrive;

    // Intake-related DC Motors
    DcMotorEx linearLift, armRotator, hangingLeft, hangingRight;

    // Intake Servos
    Servo claw, springLeft, springRight, linkage, wrist;

    // Sensors
    public IMU imu;

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

        frontRightDrive.setPower(-frontRightPower);
        backRightDrive.setPower(-backRightPower);
        frontLeftDrive.setPower(-frontLeftPower);
        backLeftDrive.setPower(-backLeftPower);

        //Gamepad 1
            //Open Spring
            if(gamepad1.x || gamepad1.square || gamepad1.triangle || gamepad1.circle){
                //Test to see if 0 is open or close
                springLeft.setPosition(0);
                springRight.setPosition(0);
            }

            //Extend linear lift
            if(gamepad1.left_bumper){
                //Test values
                linearLift.setTargetPosition(1);

            }

            //Retract linear lift
            if(gamepad1.right_bumper){
                //Test Values
                linearLift.setTargetPosition(0);
            }

            //Rotate hanging backwards
            if(gamepad1.left_trigger > 0.5){
                hangingLeft.setTargetPosition(1);
                hangingRight.setTargetPosition(1);
            }

            //
            //Rotate hanging forward
            if(gamepad1.right_trigger > 0.5){
                //These might be wrong values idk i'm losing my mind fix it later
                //It's like 12 am i'm bugging out
                hangingLeft.setTargetPosition(0);
                hangingRight.setTargetPosition(0);
            }


        //Gamepad 2
            //Open claw
            if(gamepad2.left_trigger > 0.5){
                //Test if 0 or 1
                claw.setPosition(0);
            }

            //Close claw
            if(gamepad2.right_trigger > 0.5){
                claw.setPosition(1);
            }

            //Extend Horizontal Slide
            if(gamepad2.dpad_up){
                //Test if it's 0 or 1
                linkage.setPosition(1);
            }

            //Retract Horizontal Slide
            if(gamepad2.dpad_down){
                linkage.setPosition(0);
            }

            if(gamepad2.left_stick_button){
                //Find value
                linearLift.setTargetPosition(1);
            }

            //Rotate arm
            armRotator.setPower(gamepad2.right_stick_y);

            //Wrist up
            if(gamepad2.left_bumper){
                wrist.setPosition(1);
            }

            //Wrist down
            if(gamepad2.right_bumper){
                wrist.setPosition(0);
            }

        colorRecognition();

        telemetry.update();

    }

    @Override
    public void init() {

        //Camera
        huskyLens = hardwareMap.get(HuskyLens.class,"huskyLens");
        huskyLens.selectAlgorithm(HuskyLens.Algorithm.COLOR_RECOGNITION);
//
//        //Initialized the LED Lights
        lights = hardwareMap.get(RevBlinkinLedDriver.class,"lights");
        blinkinGreen();


        // Initialization Code
        backRightDrive = hardwareMap.get(DcMotorEx.class, "backRight");
        backLeftDrive = hardwareMap.get(DcMotorEx.class, "backLeft");
        frontRightDrive = hardwareMap.get(DcMotorEx.class,"frontRight");
        frontLeftDrive = hardwareMap.get(DcMotorEx.class,"frontLeft");
        hangingLeft = hardwareMap.get(DcMotorEx.class, "hangingLeft");
        hangingRight = hardwareMap.get(DcMotorEx.class, "hangingRight");

        linearLift = hardwareMap.get(DcMotorEx.class,"linearLift");
        armRotator = hardwareMap.get(DcMotorEx.class,"armRotator");

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
        
        // To change motor direction: motor.setDirection(DcMotorEx.Direction.REVERSE);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

    }

    public void colorRecognition(){
        HuskyLens.Block[] blocks = huskyLens.blocks();
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

    public void blinkinRed(){
        lights.setPattern(RevBlinkinLedDriver.BlinkinPattern.RED);
    }
    public void blinkinGreen(){
        lights.setPattern(RevBlinkinLedDriver.BlinkinPattern.GREEN);
    }
    public void blinkinYellow(){
        lights.setPattern(RevBlinkinLedDriver.BlinkinPattern.YELLOW);
    }
    public void blinkinBlue(){
        lights.setPattern(RevBlinkinLedDriver.BlinkinPattern.BLUE);
    }
    public void blinkinBlack(){
        lights.setPattern(RevBlinkinLedDriver.BlinkinPattern.BLACK);
    }

    @Override
    public void start(){
        //For led
//        blinkinBlack();
    }

    @Override
    public void loop() {

        darshTeleOp();

    }

}
