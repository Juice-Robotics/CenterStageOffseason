package org.firstinspires.ftc.teamcode.commands;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;

import org.firstinspires.ftc.teamcode.Robot;

public class DepositPreset implements Action {
    Robot robot;
    public DepositPreset(Robot r) {
        robot = r;
    }
    @Override
    public boolean run(@NonNull TelemetryPacket packet) {
        robot.autoCycleDepositPreset();
        return false;
    }
}