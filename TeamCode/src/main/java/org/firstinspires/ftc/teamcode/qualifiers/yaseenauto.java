package org.firstinspires.ftc.teamcode.qualifiers;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Disabled
@Autonomous(name = "encoder auto")


public class yaseenauto extends LinearOpMode {

    /* Declare OpMode members. */
    hardwaremap robot = new hardwaremap();   // Use  hardware
//    private ElapsedTime runtime = new ElapsedTime();

    final double circumference = Math.PI * 2.95;
    final double ticks = 560;


    @Override
    public void runOpMode() {

        /*
         * Initialize the drive system variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "Ready to run");    //
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        //forward or backward (if need to go backward, reverse distance)
        driveForward(1, 6);
        driveStrafeRightorLeft(1, 6); //right
        driveStop(0);


//        driveStrafeRightorLeft(.5,24); //right
//        driveForward(.5,12);
//        driveStrafeRightorLeft(.5,-24); //right
//        driveRotate(.5,12); //rotate right
//        driveForward(.5, -24); //reverse


        telemetry.addData("Path", "Complete");
        telemetry.update();
    }

    public void driveForward(double power, double distance) {
        //reset encoders
        robot.backLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.backRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.frontLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.frontRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //rotations needed given a distance
        double rotationsNeeded = distance / circumference;

        //ticks
        int encoderTarget = (int) (rotationsNeeded * ticks);


        //set target position of encoders
        robot.backLeftDrive.setTargetPosition(encoderTarget);
        robot.backRightDrive.setTargetPosition(encoderTarget);
        robot.frontLeftDrive.setTargetPosition(encoderTarget);
        robot.frontRightDrive.setTargetPosition(encoderTarget);

        //to reverse, set encoder target to negative
        //to strafe set 2 motors to negative encoder target

        //set power
        robot.backLeftDrive.setPower(power);
        robot.backRightDrive.setPower(power);
        robot.frontLeftDrive.setPower(power);
        robot.frontRightDrive.setPower(power);


        //run to position
        robot.backLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.backRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.frontLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.frontRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while (robot.frontLeftDrive.isBusy()) {

        }


    }


    public void driveStrafeRightorLeft(double power, double distance) {
        //reset encoders
        robot.backLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.backRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.frontLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.frontRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //rotations needed given a distance
        double rotationsNeeded = distance / circumference;

        //ticks
        int encoderTarget = (int) (rotationsNeeded * ticks * 2);

        //set target position
        robot.backLeftDrive.setTargetPosition(-encoderTarget);
        robot.backRightDrive.setTargetPosition(encoderTarget);
        robot.frontLeftDrive.setTargetPosition(encoderTarget);
        robot.frontRightDrive.setTargetPosition(-encoderTarget);


        //set power
        robot.backLeftDrive.setPower(power);
        robot.backRightDrive.setPower(power);
        robot.frontLeftDrive.setPower(power);
        robot.frontRightDrive.setPower(power);

        //run to position
        robot.backLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.backRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.frontLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.frontRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while (robot.frontLeftDrive.isBusy()) {

        }
    }

//    //    public void driveRotate(double power, double distance){
////        //reset encoders
////        robot.backLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
////        robot.backRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
////        robot.frontLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
////        robot.frontRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
////
////        //rotations needed given a distance
////        double rotationsNeeded = distance / circumference;
////
////        //ticks
////        int encoderTarget = (int)(rotationsNeeded * ticks);
////
////        //set target position
////        robot.backLeftDrive.setTargetPosition(encoderTarget);
////        robot.backRightDrive.setTargetPosition(-encoderTarget);
////        robot.frontLeftDrive.setTargetPosition(encoderTarget);
////        robot.frontRightDrive.setTargetPosition(-encoderTarget);
////
////        //to reverse, set encoder target to negative
////        //to strafe set 2 motors to negative encoder target
////
////        //set power
////        robot.backLeftDrive.setPower(power);
////        robot.backRightDrive.setPower(power);
////        robot.frontLeftDrive.setPower(power);
////        robot.frontRightDrive.setPower(power);
////
////        //run to position
////        robot.backLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
////        robot.backRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
////        robot.frontLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);     robot.frontRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//
//      while (robot.frontLeftDrive.isBusy()){


    public void driveStop(double power) {
        robot.frontRightDrive.setPower(power);
        robot.frontLeftDrive.setPower(power);
        robot.backLeftDrive.setPower(power);
        robot.backRightDrive.setPower(power);
        //  robot.turretIntake.setPower(power);

    }
}

