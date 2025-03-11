package org.firstinspires.ftc.teamcode.nateCode;

//RR Specific

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;

//Non-RR
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.MecanumDrive;
@Autonomous(name="RR Red Park Observation", group="Autonomous")
public class RRRedParkObservation extends LinearOpMode {

    DcMotorEx backLeftDrive, backRightDrive, frontLeftDrive, frontRightDrive;
    Servo claw,linkage, wrist;
    DcMotorEx linearLift, armRotator;
    public IMU imu;
    public class CloseClaw implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            claw.setPosition(0);
            return false;
        }
    }

    public class OpenClaw implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            claw.setPosition(1);
            return false;
        }
    }


    public class ExtendLift implements Action{
        @Override
        public boolean run(@NonNull TelemetryPacket packet){
            linearLift.setTargetPosition(1);
            return false;
        }
    }


    public class RetractLift implements Action{
        @Override
        public boolean run(@NonNull TelemetryPacket packet){
            linearLift.setTargetPosition(0);
            return false;
        }
    }


    public class WristUp implements Action{
        @Override
        public boolean run(@NonNull TelemetryPacket packet){
            wrist.setPosition(1);
            return false;
        }
    }


    public class WristDown implements Action{
        @Override
        public boolean run(@NonNull TelemetryPacket packet){
            wrist.setPosition(0);
            return false;
        }
    }


    public class SpecimenHeight implements Action{
        @Override
        public boolean run(@NonNull TelemetryPacket packet){
            //find value
            linearLift.setTargetPosition(1);
            return false;
        }
    }

    public class ExtendArm implements Action{
        @Override
        public boolean run(@NonNull TelemetryPacket packet){
            linkage.setPosition(1);
            return false;
        }
    }

    public class RetractArm implements Action{
        @Override
        public boolean run(@NonNull TelemetryPacket packet){
            linkage.setPosition(0);
            return false;
        }
    }

    public class ArmUp implements Action{
        @Override
        public boolean run(@NonNull TelemetryPacket packet){
            armRotator.setTargetPosition(1);
            return false;
        }
    }

    public class ArmDown implements Action{
        @Override
        public boolean run(@NonNull TelemetryPacket packet){
            armRotator.setTargetPosition(0);
            return false;
        }
    }

    public Action closeClaw() {return new CloseClaw();}
    public Action openClaw(){return new OpenClaw();}
    public Action extendLift() {return new ExtendLift();}
    public Action retractLift() {return new RetractLift();}
    public Action wristUp() {return new WristUp();}
    public Action wristDown() {return new WristDown();}
    public Action specimenHeight() {return new SpecimenHeight();}
    public Action extendArm() {return new ExtendArm();}
    public Action retractArm() {return new ExtendArm();}
    public Action armUp() {return new ArmUp();}
    public Action armDown() {return new ArmDown();}
    public void InitializationCode(){
        backRightDrive = hardwareMap.get(DcMotorEx.class, "backRight");
        backLeftDrive = hardwareMap.get(DcMotorEx.class, "backLeft");
        frontRightDrive = hardwareMap.get(DcMotorEx.class,"frontRight");
        frontLeftDrive = hardwareMap.get(DcMotorEx.class,"frontLeft");

        linearLift = hardwareMap.get(DcMotorEx.class,"linearLift");
        armRotator = hardwareMap.get(DcMotorEx.class,"armRotator");

        claw = hardwareMap.get(Servo.class, "claw");
        linkage = hardwareMap.get(Servo.class, "linkage");
        wrist = hardwareMap.get(Servo.class, "wrist");
    }

    @Override
    public void runOpMode() throws InterruptedException {

        InitializationCode();

        // instantiate your MecanumDrive at a particular pose.
        Pose2d initialPose = new Pose2d(24, -60, Math.toRadians(90));
        MecanumDrive drive = new MecanumDrive(hardwareMap, initialPose);


        //Creates a new trajectory
        TrajectoryActionBuilder RedParkObservation = drive.actionBuilder(initialPose)
                .afterTime(0,specimenHeight())

                // ASSUMING ONE BLOCK IS PRELOADED & PLACING INTO HIGH BUCKET
                // move to bucket for pre-loaded
                .strafeToLinearHeading(new Vector2d(6, -35), Math.toRadians(90))
                .afterTime(0,extendArm())
                .afterTime(1,openClaw())
                .afterTime(1,retractArm())

                // place into bucket (extend arm and release block)
                .strafeToLinearHeading(new Vector2d(35, -55), Math.toRadians(90))
                .afterTime(0,retractLift())
                .afterTime(0,armDown())
                .afterTime(0,closeClaw())

                .strafeToLinearHeading(new Vector2d(35,-10), Math.toRadians(90))

                .strafeToLinearHeading(new Vector2d(46.5,-10),Math.toRadians(270))

                .strafeToLinearHeading(new Vector2d(46.5,-60),Math.toRadians(270))

                .strafeToLinearHeading(new Vector2d(46.5,-10),Math.toRadians(270))

                .strafeToLinearHeading(new Vector2d(57.5,-10),Math.toRadians(270))

                .strafeToLinearHeading(new Vector2d(57.5,-60),Math.toRadians(270))

                .strafeToLinearHeading(new Vector2d(57.5,-10),Math.toRadians(270))

                .strafeToLinearHeading(new Vector2d(61,-10),Math.toRadians(270))

                .strafeToLinearHeading(new Vector2d(61,-60),Math.toRadians(270))

                .endTrajectory();


        waitForStart();

        if (isStopRequested()) return;

        Actions.runBlocking(RedParkObservation.build());
    }
}


