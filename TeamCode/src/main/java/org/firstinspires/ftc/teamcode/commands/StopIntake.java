package org.firstinspires.ftc.teamcode.commands;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Robot;

import java.util.concurrent.TimeUnit;

public class StopIntake implements Action {
    Robot robot;
    public StopIntake(Robot r) {
        robot = r;
    }
    @Override
    public boolean run(@NonNull TelemetryPacket packet) {
        if (robot.intakeSensor.hasPixel()[0] && robot.intakeSensor.hasPixel()[1]) {
            robot.stopIntake();
            return false;
        } else {
            return true;
        }
    }
}
