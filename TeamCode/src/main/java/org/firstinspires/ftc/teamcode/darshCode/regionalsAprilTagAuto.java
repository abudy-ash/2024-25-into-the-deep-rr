package org.firstinspires.ftc.teamcode.darshCode;

import com.qualcomm.hardware.dfrobot.HuskyLens;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.internal.system.Deadline;

import java.util.concurrent.TimeUnit;

@Disabled
@Autonomous(name="HuskyLens AprilTag", group="Test")
public class regionalsAprilTagAuto extends LinearOpMode{

    HuskyLens huskyLens;
    final int READ_PERIOD = 1;

    @Override
    public void runOpMode(){
        huskyLens = hardwareMap.get(HuskyLens.class,"huskyLens");

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        Deadline rateLimit = new Deadline(READ_PERIOD, TimeUnit.SECONDS);
        rateLimit.expire();

        huskyLens.selectAlgorithm(HuskyLens.Algorithm.TAG_RECOGNITION); //Sets it as April Tag

        waitForStart();

        while(opModeIsActive()){
            if (!rateLimit.hasExpired()){
                continue;
            }
            rateLimit.reset();

            HuskyLens.Block[] blocks = huskyLens.blocks();
            telemetry.addData("Block Count", blocks.length);
            for (int i = 0; i < blocks.length; i++) {
                int thisTagID = blocks[i].id;

                switch(thisTagID){
                    case 1:
                        //Code for Blue Alliance Left (ID = 1)
                        telemetry.addData("Tag:","Blue Alliance Left");
                        class BlueNet {
                            public void main(String[] args) {
                                RRBlueNetAuto myClassInstante = new RRBlueNetAuto();
                                myClassInstante.runOpMode();
                            }
                        }
                        break;
                    case 2:
                        //Code for Blue Alliance Right (ID = 2)
                        telemetry.addData("Tag:", "Blue Alliance Right");
                        class BlueObservation {
                            public void main(String[] args) {
                                RRBlueParkObservation myClassInstante = new RRBlueParkObservation();
                                myClassInstante.runOpMode();
                            }
                        }
                        break;
                    case 3:
                        //Code for Red Alliance Left (ID = 3)
                        telemetry.addData("Tag:", "Red Alliance Left");
                        class RedNet {
                            public void main(String[] args) {
                                RRRedNetAuto myClassInstante = new RRRedNetAuto();
                                myClassInstante.runOpMode();
                            }
                        }
                        break;
                    case 4 :
                        //Code for Red Alliance Right (ID = 4)
                        telemetry.addData("Tag:","Red Alliance Right");
                        class RedObservation {
                            public void main(String[] args) {
                                RRRedParkObservation myClassInstante = new RRRedParkObservation();
                                myClassInstante.runOpMode();
                            }
                        }
                        break;
                }
            }
            telemetry.update();
        }

    }
}
