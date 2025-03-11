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
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import org.firstinspires.ftc.teamcode.MecanumDrive;

@Disabled
@Autonomous(name="Five BLUE Booms", group="Autonomous")
public class RRFiveBlueBooms extends LinearOpMode {

    DcMotorEx backLeftDrive, backRightDrive, frontLeftDrive, frontRightDrive;

    // Intake-related DC Motors
    DcMotorEx linearLift, armRotator;

    // Intake Servos
    Servo claw,linkage, wrist;
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

    //Add classes/actions here
    //https://rr.brott.dev/docs/v1-0/guides/centerstage-auto/

    @Override
    public void runOpMode() throws InterruptedException {

        InitializationCode();

        // instantiate your MecanumDrive at a particular pose.
        Pose2d initialPose = new Pose2d(30, 60, Math.toRadians(270));
        MecanumDrive drive = new MecanumDrive(hardwareMap, initialPose);

        //Creates a new trajectory
        TrajectoryActionBuilder BlueNetAuto = drive.actionBuilder(initialPose)
                .strafeToLinearHeading(new Vector2d(-35,60), Math.toRadians(270))
                .strafeToLinearHeading(new Vector2d(-35,-38),Math.toRadians(270))

                .strafeToLinearHeading(new Vector2d(-46.5,-38),Math.toRadians(90))

                .strafeToLinearHeading(new Vector2d(-46.5,0),Math.toRadians(90))

                .strafeToLinearHeading(new Vector2d(-46.5,-38),Math.toRadians(90))

                .strafeToLinearHeading(new Vector2d(-57.5,-38),Math.toRadians(90))

                .strafeToLinearHeading(new Vector2d(-57.5,0),Math.toRadians(90))

                .strafeToLinearHeading(new Vector2d(-57.5,-38),Math.toRadians(90))

                .strafeToLinearHeading(new Vector2d(-61,-38),Math.toRadians(90))

                .strafeToLinearHeading(new Vector2d(-61,20),Math.toRadians(90))

                .strafeToLinearHeading(new Vector2d(-61,10),Math.toRadians(90))

                .strafeToLinearHeading(new Vector2d(-35,10),Math.toRadians(90))

                .strafeToLinearHeading(new Vector2d(-35,60),Math.toRadians(90))
                .afterTime(0,specimenHeight())
                .afterTime(0,armUp())
                .afterTime(0,extendArm())
                .afterTime(1,closeClaw())

                .strafeToLinearHeading(new Vector2d(-50,60),Math.toRadians(90))

                .splineToLinearHeading(new Pose2d(-6, 35,Math.toRadians(270)), Math.toRadians(180))
                .afterTime(0,extendArm())
                .afterTime(1,openClaw())
                .afterTime(1,retractArm())

                .waitSeconds(2)

                .setTangent(90)

                .splineToLinearHeading(new Pose2d(-50,60,Math.toRadians(270)),Math.toRadians(0))


                .endTrajectory();


        waitForStart();

        if (isStopRequested()) return;

        //Runs the trajectory
        Actions.runBlocking(BlueNetAuto.build());
    }
}


