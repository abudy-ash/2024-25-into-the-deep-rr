package org.firstinspires.ftc.teamcode.regionals;

//RR Specific

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;

//Non-RR
import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.MecanumDrive;
@Autonomous(name="RR Red Observation", group="Autonomous")
public class RRRedParkObservation extends LinearOpMode {

    regionalsHardwaremap hardware = new regionalsHardwaremap();
    public class CloseClaw implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            hardware.claw.setPosition(0);
            return false;
        }
    }

    public class OpenClaw implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            hardware.claw.setPosition(1);
            return false;
        }
    }


    public class ExtendLift implements Action{
        @Override
        public boolean run(@NonNull TelemetryPacket packet){
            hardware.linearLift.setTargetPosition(999);
            hardware.linearLift.setPower(0.5);
            return false;
        }
    }


    public class RetractLift implements Action{
        @Override
        public boolean run(@NonNull TelemetryPacket packet){
            hardware.linearLift.setTargetPosition(0);
            hardware.linearLift.setPower(0.5);
            return false;
        }
    }


    public class WristUp implements Action{
        @Override
        public boolean run(@NonNull TelemetryPacket packet){
            hardware.wrist.setPosition(1);
            return false;
        }
    }


    public class WristDown implements Action{
        @Override
        public boolean run(@NonNull TelemetryPacket packet){
            hardware.wrist.setPosition(0);
            return false;
        }
    }


    public class SpecimenHeight implements Action{
        @Override
        public boolean run(@NonNull TelemetryPacket packet){
            //find value
            hardware.linearLift.setTargetPosition(500);
            hardware.linearLift.setPower(0.5);
            return false;
        }
    }

    public class ExtendArm implements Action{
        @Override
        public boolean run(@NonNull TelemetryPacket packet){
            hardware.linkage.setPosition(1);
            return false;
        }
    }

    public class RetractArm implements Action{
        @Override
        public boolean run(@NonNull TelemetryPacket packet){
            hardware.linkage.setPosition(0);
            return false;
        }
    }

    public class ArmUp implements Action{
        @Override
        public boolean run(@NonNull TelemetryPacket packet){
            hardware.armRotator.setTargetPosition(999);
            hardware.armRotator.setPower(0.5);
            return false;
        }
    }

    public class ArmDown implements Action{
        @Override
        public boolean run(@NonNull TelemetryPacket packet){
            hardware.armRotator.setTargetPosition(0);
            hardware.armRotator.setPower(0.5);
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

    @Override
    public void runOpMode() throws InterruptedException {
        hardware.init(hardwareMap);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        blinkinGreen();

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

        blinkinPink();

        if (isStopRequested()) return;

        Actions.runBlocking(RedParkObservation.build());
    }

    public void blinkinGreen() {hardware.lights.setPattern(RevBlinkinLedDriver.BlinkinPattern.GREEN);}
    public void blinkinPink(){hardware.lights.setPattern(RevBlinkinLedDriver.BlinkinPattern.HOT_PINK);}

}


