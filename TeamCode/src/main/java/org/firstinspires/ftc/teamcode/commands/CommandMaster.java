package org.firstinspires.ftc.teamcode.commands;

import com.acmerobotics.roadrunner.Action;

import org.firstinspires.ftc.teamcode.Robot;

public class CommandMaster {
    Robot robot;
    public CommandMaster(Robot r) {
        robot = r;
    }
    public Action startIntake() {
        return new StartIntake(robot);
    }

    public Action stopIntake() {
        return new StopIntake(robot);
    }
    public Action setIntakeAngle(float angle){
        return new SetIntakeAngle(robot, angle);
    }
}

