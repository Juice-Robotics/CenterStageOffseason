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
        Pose2d beginPose = new Pose2d(0, 0, 0);
        MecanumDrive drive = new MecanumDrive(hardwareMap, beginPose);
        Action trajectoryAction;
        trajectoryAction = drive.actionBuilder(drive.pose)

                .build();
        waitForStart();
        if (isStopRequested()) return;
        Actions.runBlocking(trajectoryAction);
    }
}