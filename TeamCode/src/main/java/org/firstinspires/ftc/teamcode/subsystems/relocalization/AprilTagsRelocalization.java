package org.firstinspires.ftc.teamcode.subsystems.relocalization;

import android.util.Size;

import com.acmerobotics.roadrunner.Pose2d;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagMetadata;
import org.firstinspires.ftc.vision.apriltag.AprilTagPoseFtc;
import org.firstinspires.ftc.vision.apriltag.AprilTagPoseRaw;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

import java.util.List;

public class AprilTagsRelocalization {
    AprilTagProcessor processor;
    AprilTagDetection aprilTagDetection;
    List<AprilTagDetection> aprilTagDetections;
    int aprilTagID;

    double fcameraOffsetX = 0.5;
    double fcameraOffsetY= -7.875;
    double fcameraOffsetRotation = -90.0;

    double rcameraOffsetX = 0.0;
    double rcameraOffsetY= 0.0;
    double rcameraOffsetRotation = 0.0;

    public AprilTagsRelocalization(AprilTagProcessor processor) {
        this.processor = processor;
    }

    public void detectBackdrop() {
        aprilTagDetections = processor.getDetections();

        for (AprilTagDetection laprilTagDetection : aprilTagDetections) {
            if (laprilTagDetection.metadata != null && laprilTagDetection.id == 5 || laprilTagDetection.metadata != null && laprilTagDetection.id == 2) {
                aprilTagID = laprilTagDetection.id;
                aprilTagDetection = laprilTagDetection;
            }
        }
    }

    public AprilTagPoseFtc getRelativePose() {
        if (aprilTagDetection == null) {
            // return null if no tag in sight
            return null;
        }
        return aprilTagDetection.ftcPose;
    }

    public Pose2d getAbsolutePose2d(Pose2d robotPose) {
        AprilTagPoseFtc tagRPose = getRelativePose();
        if (tagRPose == null) {
            // return current estimate if cannot see tags
            return robotPose;
        }
        Pose2d tagPose;

        if (aprilTagDetection.id == 5) {
            tagPose = new Pose2d(32 -fcameraOffsetX- tagRPose.x, 52-fcameraOffsetY - tagRPose.y, robotPose.heading.toDouble());
        } else {
            tagPose = new Pose2d(29 - tagRPose.x, -48 - tagRPose.y, robotPose.heading.toDouble());
        }
        aprilTagDetection = null;

        return tagPose;
    }

    public Pose2d getAbsolutePose2dWithHeading(Pose2d robotPose) {
        AprilTagPoseFtc tagRPose = getRelativePose();
        if (tagRPose == null) {
            // return current estimate if cannot see tags
            return robotPose;
        }

        Pose2d tagPose;

        if (aprilTagDetection.id == 5) {
            tagPose = new Pose2d(32 -fcameraOffsetX- tagRPose.x, 52-fcameraOffsetY - tagRPose.y, Math.toRadians(tagRPose.yaw-90));
        } else {
            tagPose = new Pose2d(29 - tagRPose.x, -48 - tagRPose.y, Math.toRadians(tagRPose.yaw-90));
        }
        aprilTagDetection = null;

        return tagPose;
    }
}
