//package org.firstinspires.ftc.teamcode.qualifiers;
//
//import com.acmerobotics.roadrunner.Pose2d;
//import com.acmerobotics.roadrunner.Vector2d;
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.hardware.DcMotor;
//
//import org.firstinspires.ftc.teamcode.MecanumDrive;
//
//public class comp_field_centric extends LinearOpMode {
//    hardwaremap hardware = new hardwaremap();
//    @Override
//    public void runOpMode() throws InterruptedException {
//        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(0.0, 0.0, Math.toRadians(0)));
//        hardware.init(hardwareMap);
//
//        drive.setPoseEstimate(PoseStorage.currentPose);
//
//
//        waitForStart();
//
//        if (isStopRequested()) return;
//        while (opModeIsActive() && !isStopRequested()) {
//
//            // Field Centricity
//            Pose2d poseEstimate = drive.getPoseEstimate();
//            Vector2d input = new Vector2d(
//                    -gamepad1.left_stick_y,
//                    -gamepad1.left_stick_x
//            ).rotated(-poseEstimate.getHeading());
//
//            drive.setWeightedDrivePower(
//                    new Pose2d(
//                            input.getX(),
//                            input.getY(),
//                            -gamepad1.right_stick_x
//                    )
//            );
//
//            //Hook Motor
//
//
//
//            /**
//             * Hook Spool: gamepad1.a & gamepad1.y
//             */
//
//            //Arm Motor
//            hardware.arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//            hardware.arm.setPower(1);
//            hardware.arm.setTargetPosition(hardware.arm.getCurrentPosition() + (int)(gamepad2.left_stick_y*50));
//
//            /**
//             * Mechanism's Arm: gaempoad2.left_stick_y
//             */
//
//            //Wrist Servo
//            if (gamepad2.y) {
//                hardware.wrist.setPosition(hardware.wrist.getPosition() + 0.3);
//            } else if (gamepad2.a) {
//                hardware.wrist.setPosition(hardware.wrist.getPosition() - 0.3);
//            }
//
//            /**
//             * Wrist Servo: gamepad2.a and gamepad2.y
//             */
//
//            //Claw Servo
//            if (gamepad2.left_bumper) {
//                hardware.claw.setPosition(hardware.claw.getPosition() + 0.1);
//            } else if (gamepad2.right_bumper) {
//                hardware.claw.setPosition(hardware.claw.getPosition() - 0.1);
//            }
//
//            /**
//             * Claw Servo: gamepad2.x & b
//             */
//
//            //Hook Servo
//            if (gamepad1.x) {
//                hardware.hookServo.setPosition(hardware.wrist.getPosition());
//            } else if (gamepad1.b) {
//                hardware.hookServo.setPosition(hardware.wrist.getPosition());
//            }
//
//            /**
//             * drone mechanism == gamepad1.left and right bumper
//             */
//
//            if (gamepad1.left_bumper) {
//                hardware.drone.setPosition(1);
//            }
//            else if (gamepad1.right_bumper) {
//                hardware.drone.setPosition(-1);
//            }
//
//
//
//
//            //Telemetry
//            telemetry.addData("x", poseEstimate.getX());
//            telemetry.addData("y", poseEstimate.getY());
//            telemetry.addData("heading", poseEstimate.getHeading());
//            telemetry.addData("Hook Servo", hardware.hookServo.getPosition());
//            telemetry.addData("Wrist Servo", hardware.wrist.getPosition());
//            telemetry.addData("Arm Motor Target", hardware.arm.getTargetPosition());
//            telemetry.addData("Arm Motor Current", hardware.arm.getCurrentPosition());
//            telemetry.addLine();
//            drive.update();
//            telemetry.update();
//            /**
//             * Hook Spool: gamepad1.a & gamepad1.y
//             */
//
//        }
//    }
//}