package org.firstinspires.ftc.teamcode.commands;



import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Robot;

import java.util.concurrent.Callable;

public class SubsystemUpdates implements Action {
    Robot robot;
    Callable<Boolean> isStopRequested;
    ElapsedTime timer;
    public SubsystemUpdates(Robot r, Callable<Boolean> isr) {
        robot = r;
        isStopRequested = isr;
        timer = new ElapsedTime();
    }
    @Override
    public boolean run(@NonNull TelemetryPacket packet) {
            try {
                if (isStopRequested.call()) return false;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            robot.slides.update();
            robot.antiJam();
            packet.put("Slides Target: ", robot.slides.target);
            packet.put("Slides Position: ", robot.slides.getPos());
            return true;
    }
}
