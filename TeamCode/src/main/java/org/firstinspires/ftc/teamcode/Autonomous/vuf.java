package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.teamcode.Control.AutonomousControl;
import org.firstinspires.ftc.teamcode.Control.Crane;

import static org.firstinspires.ftc.robotcore.external.navigation.AngleUnit.DEGREES;
import static org.firstinspires.ftc.robotcore.external.navigation.AxesOrder.XYZ;
import static org.firstinspires.ftc.robotcore.external.navigation.AxesReference.EXTRINSIC;

@Autonomous(name="Vuf", group = "basic")

public class vuf extends AutonomousControl {
    private ElapsedTime runtime = new ElapsedTime();

    private boolean target = false;

    boolean moving1 = false;
    boolean moving2 = false;
    boolean moving3 = false;
    boolean stop = false;
    boolean straight = false;
    int x = 0;
    int y = 0;
    int blockNumber = 0;

    public void identify1() throws InterruptedException {
        for (VuforiaTrackable trackable : rob.allTrackables) {
            ((VuforiaTrackableDefaultListener) trackable.getListener()).setPhoneInformation(rob.robotFromCamera, rob.parameters.cameraDirection);
        }

        rob.targetsSkyStone.activate();
        while (opModeIsActive() && !target && !moving1) {
            for (VuforiaTrackable trackable : rob.allTrackables) {
                if (((VuforiaTrackableDefaultListener) trackable.getListener()).isVisible()) {
                    if (trackable.getName().equals("Stone Target")) {
                        OpenGLMatrix robotLocationTransform = ((VuforiaTrackableDefaultListener) trackable.getListener()).getUpdatedRobotLocation();
                        if (robotLocationTransform != null) {
                            rob.lastLocation = robotLocationTransform;
                        }
                        telemetry.addData("good", "none");
                        // express position (translation) of robot in inches.
                        VectorF translation = rob.lastLocation.getTranslation();
                        telemetry.addData("Pos (in)", "{X, Y, Z} = %.1f, %.1f, %.1f",
                                translation.get(0) / rob.mmPerInch, translation.get(1) / rob.mmPerInch, translation.get(2) / rob.mmPerInch);

                        // express the rotation of the robot in degrees.
                        Orientation rotation = Orientation.getOrientation(rob.lastLocation, EXTRINSIC, XYZ, DEGREES);
                        telemetry.addData("Rot (deg)", "{Roll, Pitch, Heading} = %.0f, %.0f, %.0f", rotation.firstAngle, rotation.secondAngle, rotation.thirdAngle);

                        if (((translation.get(1) / rob.mmPerInch) > 0.95)) {
                            rob.driveTrainMovement(0.02, Crane.movements.forward);
                        } else if ((translation.get(1) / rob.mmPerInch) < -1) {
                            rob.driveTrainMovement(0.02, Crane.movements.backward);
                        }
                        else{
                            rob.stopDrivetrain();
                            moving1= true;
                        }
/*
                        if (((translation.get(1) / rob.mmPerInch) < -1 && !moving2) && ((VuforiaTrackableDefaultListener) trackable.getListener()).isVisible()) {
                            moving2 = true;
                            rob.driveTrainMovement(0.05, Crane.movements.backward);
                        } else if((translation.get(1) / rob.mmPerInch) > -1 && moving2) {
                            moving2 = false;
                            rob.stopDrivetrain();
                        }

                        if (((translation.get(0) / rob.mmPerInch) < -5  && !moving3) && ((VuforiaTrackableDefaultListener) trackable.getListener()).isVisible()) {
                            moving3 = true;
                            rob.driveTrainMovement(0.1, Crane.movements.left);
                            x++;

                        }else if ((translation.get(0) / rob.mmPerInch) > -5 && moving3){
                            moving3 = false;
                            rob.stopDrivetrain();
                            y++;
                        }


 */
                    }
                    telemetry.addData("x: ", x);
                    telemetry.addData("y: ", y);
                    telemetry.update();

                } else {
                    telemetry.addData("bad", "none");
                }
            }

            telemetry.update();
        }
    }

    public void identify2() throws InterruptedException {
        for (VuforiaTrackable trackable : rob.allTrackables) {
            ((VuforiaTrackableDefaultListener) trackable.getListener()).setPhoneInformation(rob.robotFromCamera, rob.parameters.cameraDirection);
        }

        rob.targetsSkyStone.activate();
        while (opModeIsActive() && !target && !moving2) {
            for (VuforiaTrackable trackable : rob.allTrackables) {
                if (((VuforiaTrackableDefaultListener) trackable.getListener()).isVisible()) {
                    if (trackable.getName().equals("Stone Target")) {
                        OpenGLMatrix robotLocationTransform = ((VuforiaTrackableDefaultListener) trackable.getListener()).getUpdatedRobotLocation();
                        if (robotLocationTransform != null) {
                            rob.lastLocation = robotLocationTransform;
                        }
                        telemetry.addData("good", "none");
                        // express position (translation) of robot in inches.
                        VectorF translation = rob.lastLocation.getTranslation();
                        telemetry.addData("Pos (in)", "{X, Y, Z} = %.1f, %.1f, %.1f",
                                translation.get(0) / rob.mmPerInch, translation.get(1) / rob.mmPerInch, translation.get(2) / rob.mmPerInch);

                        // express the rotation of the robot in degrees.
                        Orientation rotation = Orientation.getOrientation(rob.lastLocation, EXTRINSIC, XYZ, DEGREES);
                        telemetry.addData("Rot (deg)", "{Roll, Pitch, Heading} = %.0f, %.0f, %.0f", rotation.firstAngle, rotation.secondAngle, rotation.thirdAngle);
/*
                        if (((translation.get(1) / rob.mmPerInch) > 1)) {
                            moving1 = true;
                            rob.driveTrainMovement(0.05, Crane.movements.forward);
                        } else if((translation.get(1) / rob.mmPerInch) < 1) {
                            moving1 = false;
                            rob.stopDrivetrain();
                        }
*/

                        if (((translation.get(1) / rob.mmPerInch) < -1)) {
                            rob.driveTrainMovement(0.02, Crane.movements.backward);
                        } else if ((translation.get(1) / rob.mmPerInch) > -1) {
                            rob.stopDrivetrain();
                            moving2 = true; }
                        /*

                        if (((translation.get(0) / rob.mmPerInch) < -5  && !moving3) && ((VuforiaTrackableDefaultListener) trackable.getListener()).isVisible()) {
                            moving3 = true;
                            rob.driveTrainMovement(0.1, Crane.movements.left);
                            x++;

                        }else if ((translation.get(0) / rob.mmPerInch) > -5 && moving3){
                            moving3 = false;
                            rob.stopDrivetrain();
                            y++;
                        }


 */

                    }
                    telemetry.addData("x: ", x);
                    telemetry.addData("y: ", y);
                    telemetry.update();

                } else {
                    telemetry.addData("bad", "none");
                }
            }

            telemetry.update();
        }
    }

    public void identify3() throws InterruptedException {
        for (VuforiaTrackable trackable : rob.allTrackables) {
            ((VuforiaTrackableDefaultListener) trackable.getListener()).setPhoneInformation(rob.robotFromCamera, rob.parameters.cameraDirection);
        }

        rob.targetsSkyStone.activate();
        while (opModeIsActive() && !target && !moving3) {
            for (VuforiaTrackable trackable : rob.allTrackables) {
                if (((VuforiaTrackableDefaultListener) trackable.getListener()).isVisible()) {
                    if (trackable.getName().equals("Stone Target")) {
                        OpenGLMatrix robotLocationTransform = ((VuforiaTrackableDefaultListener) trackable.getListener()).getUpdatedRobotLocation();
                        if (robotLocationTransform != null) {
                            rob.lastLocation = robotLocationTransform;
                        }
                        telemetry.addData("good", "none");
                        // express position (translation) of robot in inches.
                        VectorF translation = rob.lastLocation.getTranslation();
                        telemetry.addData("Pos (in)", "{X, Y, Z} = %.1f, %.1f, %.1f",
                                translation.get(0) / rob.mmPerInch, translation.get(1) / rob.mmPerInch, translation.get(2) / rob.mmPerInch);

                        // express the rotation of the robot in degrees.
                        Orientation rotation = Orientation.getOrientation(rob.lastLocation, EXTRINSIC, XYZ, DEGREES);
                        telemetry.addData("Rot (deg)", "{Roll, Pitch, Heading} = %.0f, %.0f, %.0f", rotation.firstAngle, rotation.secondAngle, rotation.thirdAngle);
/*
                        if (((translation.get(1) / rob.mmPerInch) > 1)) {
                            moving1 = true;
                            rob.driveTrainMovement(0.05, Crane.movements.forward);
                        } else if((translation.get(1) / rob.mmPerInch) < 1) {
                            moving1 = false;
                            rob.stopDrivetrain();
                        }


                        if (((translation.get(1) / rob.mmPerInch) < -1)) {
                            rob.driveTrainMovement(0.05, Crane.movements.backward);
                        } else if((translation.get(1) / rob.mmPerInch) > -1) {
                            rob.stopDrivetrain();
                            moving2 = true;
                        }
                        */

                        if (((translation.get(0) / rob.mmPerInch) < -4)) {
                            rob.driveTrainMovement(0.05, Crane.movements.left);
                            straight = true;

                        } else if ((translation.get(0) / rob.mmPerInch) > -3) {
                            rob.driveTrainMovement(0.05, Crane.movements.right);
                            straight = true;

                        }
                        else {
                            rob.stopDrivetrain();
                            moving3=true;
                        }


                    }

                } else {
                    telemetry.addData("bad", "none");
                }
            }

            telemetry.update();
        }
    }

    public void goOn() throws InterruptedException {
        for (VuforiaTrackable trackable : rob.allTrackables) {
            ((VuforiaTrackableDefaultListener) trackable.getListener()).setPhoneInformation(rob.robotFromCamera, rob.parameters.cameraDirection);
        }

        rob.targetsSkyStone.activate();
        while (opModeIsActive() && !target && !stop) {
            for (VuforiaTrackable trackable : rob.allTrackables) {
                if (((VuforiaTrackableDefaultListener) trackable.getListener()).isVisible()) {
                    stop = true;
                    telemetry.addData("oooo", "none");
                    rob.stopDrivetrain();
                    sleep(5000);
                }else {
                    //rob.driveTrainEncoderMovement(0.1, 3, 2, 0, Crane.movements.forward);
                    //sleep(5000);
                    rob.driveTrainMovement(0.02, Crane.movements.forward);
                }

                telemetry.update();
            }
        }

    }

    public void valueBased() throws InterruptedException{
        double dist = 0;
        switch(blockNumber){
            case 1:
                do {
                    rob.driveTrainMovement(0.1, Crane.movements.forward);

                    dist = rob.front.getDistance(DistanceUnit.CM);
                    telemetry.addData("cm front", "%.2f cm", dist);
                    telemetry.update();

                }
                while (dist > 35 || Double.compare(dist, Double.NaN) == 0 && opModeIsActive());
                identify1();
                identify3();
                clawDown();
            case 2:
                do {
                    rob.driveTrainMovement(0.1, Crane.movements.forward);

                    dist = rob.front.getDistance(DistanceUnit.CM);
                    telemetry.addData("cm front", "%.2f cm", dist);
                    telemetry.update();

                }
                while (dist > 28 || Double.compare(dist, Double.NaN) == 0 && opModeIsActive());
                identify1();
                identify3();
                clawDown();
            case 3:
                do {
                    rob.driveTrainMovement(0.1, Crane.movements.forward);

                    dist = rob.front.getDistance(DistanceUnit.CM);
                    telemetry.addData("cm front", "%.2f cm", dist);
                    telemetry.update();

                }
                while (dist > 21 || Double.compare(dist, Double.NaN) == 0 && opModeIsActive());
                identify1();
                identify3();
                clawDown();
        }
    }

    public void clawDown() throws InterruptedException{
        rob.autonGrabClaw.setPosition(0.4);
        sleep(100);
        rob.autonDownClaw.setPosition(1);
        sleep(500);
        rob.autonGrabClaw.setPosition(0.15);
        sleep(100);
        rob.autonDownClaw.setPosition(0.5);
        sleep(100);
    }


        @Override
        public void runOpMode () throws InterruptedException {

            setup(runtime, Crane.setupType.camera, Crane.setupType.drive, Crane.setupType.autonomous, Crane.setupType.claw, Crane.setupType.ultrasoinc);
            telemetry.addLine("Start!");
            telemetry.update();
            double dist = 0;
            //goOn();
            //rob.driveTrainEncoderMovement(0.4, 24, 5, 0, Crane.movements.left);

            identify1();
            identify3();

            rob.targetsSkyStone.deactivate();

        }
    }

