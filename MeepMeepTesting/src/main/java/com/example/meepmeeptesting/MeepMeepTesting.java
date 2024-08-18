package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
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
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(62, -36, 0))
                                .back(55)
                                .lineToSplineHeading(new Pose2d(12, -59, Math.toRadians(-90)))
                                .back(80)
                                .splineToConstantHeading(new Vector2d(35, 40), 0)
                                .waitSeconds(1)
                                .setTangent(-Math.PI)
                                .splineToConstantHeading(new Vector2d(12, 24), -Math.PI/2)
                                .forward(83)
                                .back(83)
                                .splineToConstantHeading(new Vector2d(35, 40), 0)
                                .build()
                );
        Image img = null;
        try { img = ImageIO.read(new File("/Users/zhimi/Downloads/field.png")); }
        //       try { img = ImageIO.read(new File("/Users/siddharth/dev/Juice/CenterStage/MeepMeepTesting/src/main/java/com/example/meepmeeptesting/Juice-CENTERSTAGE-Dark.png")); }
        catch (IOException e) {}
        meepMeep.setBackground(img)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}
//                                .back(55)
//                                .lineToSplineHeading(new Pose2d(12, -59, Math.toRadians(-90)))
//                                .back(80)
//                                .splineToConstantHeading(new Vector2d(35, 40), 0)
////                                .waitSeconds(3)
//                                .forward(90)
//                                .back(90)
//                                .splineToConstantHeading(new Vector2d(35, 40), 0)
//                                .build()