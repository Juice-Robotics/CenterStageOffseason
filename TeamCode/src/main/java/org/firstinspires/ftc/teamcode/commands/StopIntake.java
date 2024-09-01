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
    ElapsedTime timer;
    boolean adjusted = false;
    public StopIntake(Robot r) {
        robot = r;
        timer = new ElapsedTime(ElapsedTime.Resolution.SECONDS);
    }
    @Override
    public boolean run(@NonNull TelemetryPacket packet) {
        if (robot.intakeSensor.hasPixel()[0] && robot.intakeSensor.hasPixel()[1]) {
            robot.stopIntake();
            return false;
        } else {
            if (timer.time() > 3 && !adjusted) {
                robot.intake.setAngle(Math.max((robot.intake.getAngle() - 5), 175));
                adjusted = true;
            }
            return true;
        }
    }
}
