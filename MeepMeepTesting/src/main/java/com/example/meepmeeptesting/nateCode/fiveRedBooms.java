package com.example.meepmeeptesting.nateCode;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

import java.util.Vector;


public class fiveRedBooms {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);


        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .build();

        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(24, -60, Math.toRadians(90)))


                .strafeToLinearHeading(new Vector2d(35,-60), Math.toRadians(90))
                .strafeToLinearHeading(new Vector2d(35,38),Math.toRadians(90))

                .strafeToLinearHeading(new Vector2d(46.5,38),Math.toRadians(-90))

                .strafeToLinearHeading(new Vector2d(46.5,0),Math.toRadians(270))

                .strafeToLinearHeading(new Vector2d(46.5,38),Math.toRadians(270))

                .strafeToLinearHeading(new Vector2d(57.5,38),Math.toRadians(270))

                .strafeToLinearHeading(new Vector2d(57.5,0),Math.toRadians(270))

                .strafeToLinearHeading(new Vector2d(57.5,38),Math.toRadians(270))

                .strafeToLinearHeading(new Vector2d(61,38),Math.toRadians(270))

                .strafeToLinearHeading(new Vector2d(61,-20),Math.toRadians(270))

                .strafeToLinearHeading(new Vector2d(61,-10),Math.toRadians(270))

                .strafeToLinearHeading(new Vector2d(35,-10),Math.toRadians(270))

                .strafeToLinearHeading(new Vector2d(35,-60),Math.toRadians(270))

                .strafeToLinearHeading(new Vector2d(50,-60),Math.toRadians(270))

                .splineToLinearHeading(new Pose2d(6, -35,Math.toRadians(90)), Math.toRadians(180))

                .waitSeconds(2)

                .setTangent(180)

                .splineToLinearHeading(new Pose2d(50,-60,Math.toRadians(90)),Math.toRadians(0))









                .build());


        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}