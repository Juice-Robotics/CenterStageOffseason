package org.firstinspires.ftc.teamcode.commands;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;

import org.firstinspires.ftc.teamcode.Robot;

public class StartIntake implements Action {
    Robot robot;
    public StartIntake(Robot r) {
        robot = r;
    }
    @Override
    public boolean run(@NonNull TelemetryPacket packet) {
        robot.startIntake();
        return false;
    }
}
