package org.firstinspires.ftc.teamcode.darshCode;

//RR Specific
import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.Trajectory;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.TrajectoryBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.VelConstraint;
import com.acmerobotics.roadrunner.ftc.Actions;

//Non-RR
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import org.firstinspires.ftc.teamcode.MecanumDrive;
@Autonomous(name="RR Blue Park Observation", group="Autonomous")
public class RRBlueParkObservation extends LinearOpMode {

    DcMotorEx backLeftDrive, backRightDrive, frontLeftDrive, frontRightDrive;



    public void InitializationCode(){
        backRightDrive = hardwareMap.get(DcMotorEx.class, "backRight");
        backLeftDrive = hardwareMap.get(DcMotorEx.class, "backLeft");
        frontRightDrive = hardwareMap.get(DcMotorEx.class,"frontRight");
        frontLeftDrive = hardwareMap.get(DcMotorEx.class,"frontLeft");
    }

    @Override
    public void runOpMode() throws InterruptedException {

        InitializationCode();

        // instantiate your MecanumDrive at a particular pose.
        Pose2d initialPose = new Pose2d(-24, 60, Math.toRadians(270));
        MecanumDrive drive = new MecanumDrive(hardwareMap, initialPose);


        //Creates a new trajectory
        TrajectoryActionBuilder BlueParkObservation = drive.actionBuilder(initialPose)
                // ASSUMING ONE BLOCK IS PRELOADED & PLACING INTO HIGH BUCKET
                // move to bucket for pre-loaded
                .strafeToLinearHeading(new Vector2d(-6, 35), Math.toRadians(270))

                // place into bucket (extend arm and release block)
                .waitSeconds(2)

                .strafeToLinearHeading(new Vector2d(-35, 55), Math.toRadians(270))

                .strafeToLinearHeading(new Vector2d(-35,10), Math.toRadians(270))

                .strafeToLinearHeading(new Vector2d(-46.5,10),Math.toRadians(90))

                .strafeToLinearHeading(new Vector2d(-46.5,60),Math.toRadians(90))

                .strafeToLinearHeading(new Vector2d(-46.5,10),Math.toRadians(90))

                .strafeToLinearHeading(new Vector2d(-57.5,10),Math.toRadians(90))

                .strafeToLinearHeading(new Vector2d(-57.5,60),Math.toRadians(90))

                .strafeToLinearHeading(new Vector2d(-57.5,10),Math.toRadians(90))

                .strafeToLinearHeading(new Vector2d(-61,10),Math.toRadians(90))

                .strafeToLinearHeading(new Vector2d(-61,60),Math.toRadians(90))
                .endTrajectory();


        waitForStart();

        if (isStopRequested()) return;

        Actions.runBlocking(BlueParkObservation.build());
    }
}


