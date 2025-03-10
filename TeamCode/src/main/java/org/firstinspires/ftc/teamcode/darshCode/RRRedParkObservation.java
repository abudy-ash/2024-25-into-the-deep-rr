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
@Autonomous(name="RR Red Park Observation", group="Autonomous")
public class RRRedParkObservation extends LinearOpMode {

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
        Pose2d initialPose = new Pose2d(30, -60, Math.toRadians(90));
        MecanumDrive drive = new MecanumDrive(hardwareMap, initialPose);


        //Creates a new trajectory
        TrajectoryActionBuilder BlueParkObservation = drive.actionBuilder(initialPose)
                .strafeTo(new Vector2d(60, -60))
                .endTrajectory();


        waitForStart();

        if (isStopRequested()) return;

        Actions.runBlocking(BlueParkObservation.build());
    }
}


