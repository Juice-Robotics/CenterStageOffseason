package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.MecanumDrive;
@Autonomous(name = "AutonTest", group = "Autonomous")
public final class AutonTest extends LinearOpMode {
    @Override
    public void runOpMode(){
        Pose2d beginPose = new Pose2d(0, 0, 0);
        MecanumDrive drive = new MecanumDrive(hardwareMap, beginPose);
        Action trajectoryAction;
        trajectoryAction = drive.actionBuilder(drive.pose)
                .splineToLinearHeading(new Pose2d(30, 30, 0), 0)
                .waitSeconds(5)
                .splineToLinearHeading(new Pose2d(0, 0, 0), -Math.PI/2)
                .build();
        waitForStart();
        if (isStopRequested()) return;
        Actions.runBlocking(trajectoryAction);

    }
}
