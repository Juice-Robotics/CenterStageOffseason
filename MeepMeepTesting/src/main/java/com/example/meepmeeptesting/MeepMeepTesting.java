package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MeepMeepTesting {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(600);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .build();

        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(-36, -62, Math.toRadians(-90)))
                .setTangent(Math.PI/2)
                .splineToConstantHeading(new Vector2d(-36, -12), Math.PI/2)
                .splineToLinearHeading(new Pose2d(-60, -12, Math.PI), Math.PI)
                .setTangent(0)
                .splineToConstantHeading(new Vector2d(35, -12), 0)
                .splineToConstantHeading(new Vector2d(48, -36), Math.PI/-2)
                .waitSeconds(0.5)

                .splineToConstantHeading(new Vector2d(35, -12), Math.PI)
                .setTangent(Math.PI)
                .splineToLinearHeading(new Pose2d(-60, -12, Math.PI), Math.PI)
                .setTangent(0)
                .splineToConstantHeading(new Vector2d(35, -12), 0)
                .splineToConstantHeading(new Vector2d(48, -36), Math.PI/-2)

                .build());
       
        meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_OFFICIAL)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}