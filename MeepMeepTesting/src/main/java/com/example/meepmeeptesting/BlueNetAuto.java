package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Rotation2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;


public class BlueNetAuto {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);


        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .build();

        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(30, 60, Math.toRadians(0)))

                // ASSUMING ONE BLOCK IS PRELOADED & PLACING INTO HIGH BUCKET
                // move to bucket for pre-loaded
                .strafeToLinearHeading(new Vector2d(54, 54), Math.toRadians(45))
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




                .build());


        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}