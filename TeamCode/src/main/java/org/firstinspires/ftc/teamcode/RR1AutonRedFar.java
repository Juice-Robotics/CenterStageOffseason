package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "RR1AutonRedFar", group = "Autonomous")
public class RR1AutonRedFar extends LinearOpMode {
    public void runOpMode() {
        Pose2d beginPose = new Pose2d(62, -36, 0);
        MecanumDrive drive = new MecanumDrive(hardwareMap, beginPose);
        Action trajectoryAction;
        trajectoryAction = drive.actionBuilder(drive.pose)
                //.splineToConstantHeading(new Vector2d(5, -36))
                .splineToLinearHeading(new Pose2d(12, -54, Math.toRadians(90)), Math.PI/2)
                .splineToConstantHeading(new Vector2d(12, 21), Math.toRadians(-90))
                .splineToConstantHeading(new Vector2d(35, 40), Math.PI)
                .waitSeconds(1)
                .setTangent(-Math.PI)
                .splineToConstantHeading(new Vector2d(12, 24), -Math.PI/2)
                .splineToConstantHeading(new Vector2d(12, -54), Math.PI/2)
                .splineToConstantHeading(new Vector2d(12, 24), Math.PI/-2)
                .splineToConstantHeading(new Vector2d(35, 40), Math.PI)
                .build();
        waitForStart();
        if (isStopRequested()) return;
        Actions.runBlocking(trajectoryAction);
    }
}