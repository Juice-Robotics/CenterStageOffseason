package org.firstinspires.ftc.teamcode.commands;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;

import org.firstinspires.ftc.teamcode.Robot;

public class SetIntakeAngle implements Action {
    Robot robot;
    float angle;
    public SetIntakeAngle(Robot r, float a) {
        robot = r;
        angle = a;
    }
    @Override
    public boolean run(@NonNull TelemetryPacket packet) {
        robot.intake.setAngle(angle);
        return false;
    }
}
