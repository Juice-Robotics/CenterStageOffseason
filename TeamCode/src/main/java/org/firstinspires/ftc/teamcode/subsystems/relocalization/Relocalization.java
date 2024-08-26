package org.firstinspires.ftc.teamcode.subsystems.relocalization;

import com.acmerobotics.roadrunner.Pose2d;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;

public class Relocalization {
    public AprilTagsRelocalization aprilTags;

    public Relocalization() {
//        aprilTags =  new AprilTagsRelocalization();
    }

    public Pose2d relocalizeUsingBackdrop(Pose2d currentPose) {
        aprilTags.detectBackdrop();
        return aprilTags.getAbsolutePose2d(currentPose);
    }
}
