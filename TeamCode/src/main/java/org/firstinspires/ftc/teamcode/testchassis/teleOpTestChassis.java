package org.firstinspires.ftc.teamcode.testchassis;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;
@Disabled
@TeleOp(group = "test chassis")
public class teleOpTestChassis extends LinearOpMode {

   hardwaremapTestChassis hardware = new hardwaremapTestChassis();
    CurrentUnit amps;


    @Override
    public void runOpMode() throws InterruptedException {
        hardware.init(hardwareMap);

        waitForStart();

        // init loop
        while (!isStarted() && !isStopRequested()) {
            telemetry.addLine("this is the init loop. pluh");
        }

        // when play is pressed
        while (opModeIsActive()) {


            // Main loop to set motor power
            double x = (gamepad1.left_stick_x * -0.5);
            double y = (gamepad1.left_stick_y* -0.5) ;
            double r = (gamepad1.right_stick_x* -0.5);

//            hardware.camera.arrows();

            double frontLeftPower = y + x + r;
            double frontRightPower = y - x - r;
            double backLeftPower = y - x + r;
            double backRightPower = y + x - r;



// This is where the motors actually get the power set to them. Before, I was just
// editing a placeholder, because I can't always use the .setPower with the input data
// that is collected.
// Sets drive motors (NeveRest 40s) to the drive motor power variables
            hardware.backRightDrive.setPower(backRightPower);
            hardware.frontRightDrive.setPower(frontRightPower);
            hardware.frontLeftDrive.setPower(frontLeftPower);
            hardware.backLeftDrive.setPower(backLeftPower);

            telemetry.addData("backLeftdrive power", hardware.backLeftDrive.getPower());
            telemetry.addData("frontLeftDrive power", hardware.frontLeftDrive.getPower());
            telemetry.update();

// The end of public void loop() (Or the main loop in which you get the button states)

// NOTE: This is after the main loop, and is a method for getting one of 16 preset values for motor power.
// This portion of the program "scales" the input coming in. In this case, it's taking the input
// from the joysticks of gamepad1, and sets the Left/RightPower to the closest variable stored in
// a declared array. This allows for precision driving when moving in the lower half of the 0-1 power scale.
// While in the higher half, this allows for straighter driving.


        }

    }

    float ScaleInputDrive(float ScaleInputDrive) {
        // This is an array with the numbers that you can see. This will allow both joystick values
        // to be close (not having to be exact) and still be able to get the same power as the other
        // if needed, or if not needed, it can help the driver to be a little more exact while driving.
        double[] DriveArray = {0, .1, .15, .2, .25, .3, .4, .45, .5, .6, .7, .75, .8, .85, .9, 1};

        // For every number in this DriveArray, make sure you have the proper number below
        // ScaleInputDrive is multiplied by (DriveArray total variables) 15 because arrays start at 0, so its numbers 0-15, instead of 1-16
        int DriveIndex = (int) (ScaleInputDrive * 15);

        // This allows for "negative" numbers in the array, without having to directly enter them
        if (DriveIndex < 0) {
            DriveIndex = -DriveIndex;
        }

        double DriveScale = 0;
        if (ScaleInputDrive < 0) {
            DriveScale = -DriveArray[DriveIndex];
        } else {
            DriveScale = DriveArray[DriveIndex];
        }

        // Returns the value DriveScale, which is used with the joysticks when they are set to
        // the variables DriveLeft and DriveRight
        return (float) DriveScale;
    }
}