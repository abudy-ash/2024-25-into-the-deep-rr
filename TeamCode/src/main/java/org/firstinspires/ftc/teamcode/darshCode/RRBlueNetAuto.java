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
import com.acmerobotics.roadrunner.ftc.Actions;

//Non-RR
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import org.firstinspires.ftc.teamcode.MecanumDrive;
@Autonomous(name="BlueNetAutoOpMode", group="Autonomous")
public class RRBlueNetAuto extends LinearOpMode {

    DcMotorEx backLeftDrive, backRightDrive, frontLeftDrive, frontRightDrive;
    Servo claw;
    CRServo linkage, wrist;
    DcMotorEx linearLift, armRotator;
    public IMU imu;

    //Placeholder for vision replace this with the camera detection
    int visionOutputPosition = 1;



    public class CloseClaw implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            claw.setPosition(0);
            return false;
        }
    }
    public Action closeClaw() {
        return new CloseClaw();
    }

    public class OpenClaw implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            claw.setPosition(1.0);
            return false;
        }
    }

    public Action openClaw(){
        return new OpenClaw();
    }

    public void InitializationCode(){
        backRightDrive = hardwareMap.get(DcMotorEx.class, "backRight");
        backLeftDrive = hardwareMap.get(DcMotorEx.class, "backLeft");
        frontRightDrive = hardwareMap.get(DcMotorEx.class,"frontRight");
        frontLeftDrive = hardwareMap.get(DcMotorEx.class,"frontLeft");

        linearLift = hardwareMap.get(DcMotorEx.class,"linearLift");
        armRotator = hardwareMap.get(DcMotorEx.class,"armRotator");

        claw = hardwareMap.get(Servo.class, "claw");
        linkage = hardwareMap.get(CRServo.class, "linkage");
        wrist = hardwareMap.get(CRServo.class, "wrist");
    }

        //Add classes/actions here
    //https://rr.brott.dev/docs/v1-0/guides/centerstage-auto/

    @Override
    public void runOpMode() throws InterruptedException {

        InitializationCode();

        // instantiate your MecanumDrive at a particular pose.
        Pose2d initialPose = new Pose2d(30, 60, Math.toRadians(0));
        MecanumDrive drive = new MecanumDrive(hardwareMap, initialPose);

        //Creates a new trajectory
        TrajectoryActionBuilder BlueNetAuto = drive.actionBuilder(initialPose)
                // ASSUMING ONE BLOCK IS PRELOADED & PLACING INTO HIGH BUCKET
                // move to bucket for pre-loaded
                .strafeToLinearHeading(new Vector2d(54, 54), Math.toRadians(45))
                .afterDisp(1,openClaw())

                // place into bucket (extend arm and release block)
                .waitSeconds(2)
                
                // move to first block
                .splineToLinearHeading(new Pose2d(36, 25.5, Math.toRadians(0)), Math.toRadians(0))
                // grab item
                .waitSeconds(2)

                // move to bucket
                .strafeToLinearHeading(new Vector2d(54, 54), Math.toRadians(45))

                // place into bucket (extend arm and release block)
                .waitSeconds(2)

                // move to second block
                .splineToLinearHeading(new Pose2d(45, 25.5, Math.toRadians(0)), Math.toRadians(0))


                // grab item
                .waitSeconds(2)

                // move to bucket
                .strafeToLinearHeading(new Vector2d(54, 54), Math.toRadians(45))

                // place into bucket (extend arm and release block)
                .waitSeconds(2)

                // move to last block
                .splineToLinearHeading(new Pose2d(55, 25.5, Math.toRadians(0)), Math.toRadians(0))

                // grab item
                .waitSeconds(2)

                // move to bucket
                .strafeToLinearHeading(new Vector2d(54, 54), Math.toRadians(45))

                // place into bucket (extend arm and release block)
                .waitSeconds(2)

                // park
                .strafeToLinearHeading(new Vector2d(-60, 60), Math.toRadians(0))

                .endTrajectory();



        //Repeat loop while waiting for start showiing position
        while (!isStopRequested() && !opModeIsActive()) {
            int position = visionOutputPosition;
            telemetry.addData("Position during Init", position);
            telemetry.update();
        }
        //Sets the final position for start
        int startPosition = visionOutputPosition;
        telemetry.addData("Starting Position", startPosition);
        telemetry.update();
        waitForStart();

        waitForStart();

        if (isStopRequested()) return;

        //Sets which trajectory to run based on position
        Action trajectoryActionChosen;
        if (startPosition == 1) {
            trajectoryActionChosen = BlueNetAuto.build();
//        } else if (startPosition == 2) {
//
//        }

        //Runs the trajectory
        Actions.runBlocking(
                new SequentialAction(
                        trajectoryActionChosen
                )
        );

    }
}
}

