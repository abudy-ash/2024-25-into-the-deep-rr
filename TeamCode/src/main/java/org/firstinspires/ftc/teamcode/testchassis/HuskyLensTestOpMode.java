package org.firstinspires.ftc.teamcode.testchassis;

import com.qualcomm.hardware.dfrobot.HuskyLens;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.internal.system.Deadline;

import java.util.concurrent.TimeUnit;

@TeleOp(name="HuskyLens Test", group="Test")
public class HuskyLensTestOpMode extends LinearOpMode {
//    hardwaremapTestChassis robot = new hardwaremapTestChassis();

    HuskyLens huskyLens;
    final int READ_PERIOD = 1;



    @Override
    public void runOpMode() {
        huskyLens = hardwareMap.get(HuskyLens.class,"huskyLens");

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        Deadline rateLimit = new Deadline(READ_PERIOD, TimeUnit.SECONDS);
        rateLimit.expire();

        huskyLens.selectAlgorithm(HuskyLens.Algorithm.COLOR_RECOGNITION);


        waitForStart();

        while (opModeIsActive()) {
            if (!rateLimit.hasExpired()){
                continue;
            }
            rateLimit.reset();

            HuskyLens.Block[] blocks = huskyLens.blocks();
            telemetry.addData("Block Count", blocks.length);
            for (int i = 0; i < blocks.length; i++) {
                int thisColorID = blocks[i].id;

                switch(thisColorID){
                    case 1:
                        //Code for if it's Blue (ID = 1)
                        telemetry.addData("Color ID","Blue");
                        break;
                    case 2:
                        //Code for if it's Tan (ID = 2)
                        telemetry.addData("Color ID", "Tan");
                        break;
                }
            }
            telemetry.update();
        }
    }
}
