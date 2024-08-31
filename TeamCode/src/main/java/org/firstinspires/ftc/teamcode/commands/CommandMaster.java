package org.firstinspires.ftc.teamcode.commands;

import com.acmerobotics.roadrunner.Action;

import org.firstinspires.ftc.teamcode.Robot;

import java.util.concurrent.Callable;

public class CommandMaster {
    Robot robot;
    public CommandMaster(Robot r) {
        robot = r;
    }

    public Action startIntake() {return new StartIntake(robot);
    }
    public Action stopIntake() {
        return new StopIntake(robot);
    }
    public Action setIntakeAngle(float angle){
        return new SetIntakeAngle(robot, angle);
    }
    public Action depositPreset() {return new DepositPreset(robot);}
    public Action clawOpen() {return new ClawOpen(robot);}
    public Action subsystemUpdate(Callable<Boolean> isStopRequested) {return new SubsystemUpdates(robot, isStopRequested);}
}

