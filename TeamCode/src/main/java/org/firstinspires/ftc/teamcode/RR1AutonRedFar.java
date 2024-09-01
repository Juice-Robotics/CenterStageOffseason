package org.firstinspires.ftc.teamcode;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SleepAction;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "RR1AutonRedFar", group = "Autonomous")
public class RR1AutonRedFar extends LinearOpMode {
    Robot robot;
    public void runOpMode() {
        robot = new Robot(hardwareMap, true);
        Pose2d beginPose = new Pose2d(-36, -62, Math.toRadians(-90));

        MecanumDrive drive = new MecanumDrive(hardwareMap, beginPose);
        Action spike = drive.actionBuilder(drive.pose)
                .setTangent(Math.PI/2)
                .splineToConstantHeading(new Vector2d(-36, -12), Math.PI/2) // move to set spike
                .build();
        Action stack = drive.actionBuilder(new Pose2d(-36, -12,-Math.PI/2))
                .setTangent(Math.PI/2)
                .splineToLinearHeading(new Pose2d(-67.3, -12, Math.PI), Math.PI) // pickup stack
                .build();
        Action backdrop1 = drive.actionBuilder(new Pose2d(-67.3, -12, Math.PI))
                .setTangent(0)
                .splineToConstantHeading(new Vector2d(35, -12), 0) // stack to backdrop area
                .splineToConstantHeading(new Vector2d(46.25, -38), Math.PI/-2) // spline in front of backdrop
                .waitSeconds(0.5)
                .build();
        Action stack1 = drive.actionBuilder(new Pose2d(46.15, -36,Math.PI))
                .setTangent(Math.PI/2)
                .splineToConstantHeading(new Vector2d(35, -12), Math.PI) //
                .splineToLinearHeading(new Pose2d(-67.4, -11.5, Math.PI), Math.PI) // backdrop to stack

                .build();
        Action backdrop2 = drive.actionBuilder(new Pose2d(-67.4, -11.5, Math.PI))
                .setTangent(Math.PI)
                .setTangent(0)
                .splineToConstantHeading(new Vector2d(35, -12), 0) // stack to backdrop area
                .splineToConstantHeading(new Vector2d(47, -38), Math.PI/-2) // spline in front of backdrop

                .build();
        telemetry.addData("is","starting");
        telemetry.update();
        waitForStart();

        if (isStopRequested()) return;

        Actions.runBlocking(
            new ParallelAction(
                    new SequentialAction(
                        spike,
                        //stack,
                        new ParallelAction(
                            stack,
                            new SequentialAction(
                                    robot.commands.startIntake(),
                                    new SleepAction(1.5),
                                    robot.commands.setIntakeAngle(193)
                            )
                        ),

                    new SleepAction(1),
                    robot.commands.stopIntake(),
                    new ParallelAction(
                            backdrop1,
                            new SequentialAction(
                                    new SleepAction(3),
                                    robot.commands.depositPreset(),
                                    new SleepAction(1.2),
                                    robot.commands.clawOpen(),
                                    new SleepAction(0.25)
                            )
                    ),
                    new ParallelAction(
                            stack1,
                            new SequentialAction(
                                    new SleepAction(1),
                                    robot.commands.startIntake(),
                                    new SleepAction(0.5),
                                    robot.commands.setIntakeAngle(175)
                            )

                    ),
                    new SleepAction(1),
                    robot.commands.stopIntake(),
                    new ParallelAction(
                        backdrop2,
                        new SequentialAction(
                            new SleepAction(3),
                            robot.commands.depositPreset(),
                            new SleepAction(1.2),
                            robot.commands.clawOpen()
                        )
                    )
            ),
                    robot.commands.subsystemUpdate(this::isStopRequested)
        ));
    }
}