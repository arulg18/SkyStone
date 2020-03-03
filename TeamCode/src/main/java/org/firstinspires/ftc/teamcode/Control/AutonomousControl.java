package org.firstinspires.ftc.teamcode.Control;

import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;

import java.util.List;

import static org.firstinspires.ftc.robotcore.external.navigation.AngleUnit.DEGREES;
import static org.firstinspires.ftc.robotcore.external.navigation.AxesOrder.XYZ;
import static org.firstinspires.ftc.robotcore.external.navigation.AxesReference.EXTRINSIC;

public abstract class AutonomousControl extends Central {


    public void identify1RED(int blockNumber) throws InterruptedException {
        for (VuforiaTrackable trackable : rob.allTrackables) {
            ((VuforiaTrackableDefaultListener) trackable.getListener()).setPhoneInformation(rob.robotFromCamera, rob.parameters.cameraDirection);
        }

        double currTime = runtime.milliseconds();

        rob.targetsSkyStone.activate();
        while (opModeIsActive() && !rob.target && !rob.moving1 && runtime.milliseconds() - currTime < 7000) {

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
                            rob.driveTrainMovement(0.02, Crane.movements.backward);
                        } else if ((translation.get(1) / rob.mmPerInch) < -1.2) {
                            rob.driveTrainMovement(0.02, Crane.movements.forward);
                        }
                        else{
                            rob.stopDrivetrain();
                            rob.moving1= true;
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
                    telemetry.addData("x: ", rob.x);
                    telemetry.addData("y: ", rob.y);
                    // telemetry.update();

                } else {
                    telemetry.addData("bad", "none");
                }
            }

            telemetry.update();

            double dist = rob.back.getDistance(DistanceUnit.INCH);
            if(dist > (35 - (blockNumber - 1) * 7.5)){
                rob.driveTrainMovement(0.02, Crane.movements.backward);
                telemetry.addData("moveBackwardrUltra: ", dist);

            }
            else if (dist < (34 - (blockNumber - 1) * 7.5)){
                rob.driveTrainMovement(0.02, Crane.movements.forward);
                telemetry.addData("moveForwardrUltra: ", dist);

            }
            else {
                rob.stopDrivetrain();
                rob.moving1 = true;
                telemetry.addData("StopUltrarFB: ", dist);
            }
        }


    }

    public void identify3RED() throws InterruptedException {
        for (VuforiaTrackable trackable : rob.allTrackables) {
            ((VuforiaTrackableDefaultListener) trackable.getListener()).setPhoneInformation(rob.robotFromCamera, rob.parameters.cameraDirection);
        }

        double currTime = runtime.milliseconds();


        rob.targetsSkyStone.activate();
        while (opModeIsActive() && !rob.target && !rob.moving3 && runtime.milliseconds() - currTime < 7000) {

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

                        if (((translation.get(0) / rob.mmPerInch) < -3.1)) {
                            rob.driveTrainMovement(0.08, Crane.movements.left);
                            rob.straight = true;

                        } else if ((translation.get(0) / rob.mmPerInch) > -2.9)  {
                            rob.driveTrainMovement(0.08, Crane.movements.right);
                            rob.straight = true;

                        }
                        else {
                            rob.stopDrivetrain();
                            rob.moving3=true;
                        }


                    }

                } else {
                    telemetry.addData("bad2", "none");
                }
            }



            double dist = rob.left.getDistance(DistanceUnit.INCH);
            if(dist > 3){
                rob.driveTrainMovement(0.08, Crane.movements.left);
                telemetry.addData("moveLeftUltra: ", dist);

            }
            else if (dist < 2){
                rob.driveTrainMovement(0.08, Crane.movements.right);
                telemetry.addData("moveRightUltra: ", dist);

            }
            else {
                rob.stopDrivetrain();
                rob.moving3 = true;
                telemetry.addData("StopUltra: ", dist);

            }

            telemetry.update();
        }

        rob.driveTrainEncoderMovement(0.3, 2, 4, 0, Crane.movements.forward);


    }


    public void identify1(int blockNumber) throws InterruptedException {
        for (VuforiaTrackable trackable : rob.allTrackables) {
            ((VuforiaTrackableDefaultListener) trackable.getListener()).setPhoneInformation(rob.robotFromCamera, rob.parameters.cameraDirection);
        }

        double currTime = runtime.milliseconds();

        rob.targetsSkyStone.activate();
        while (opModeIsActive() && !rob.target && !rob.moving1 && runtime.milliseconds() - currTime < 7000) {

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
                            rob.driveTrainMovement(0.5, Crane.movements.forward);
                        } else if ((translation.get(1) / rob.mmPerInch) < -1.2) {
                            rob.driveTrainMovement(0.5, Crane.movements.backward);
                        } else {
                            rob.stopDrivetrain();
                            rob.moving1 = true;
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
                    telemetry.addData("x: ", rob.x);
                    telemetry.addData("y: ", rob.y);
                    // telemetry.update();

                } else {
                    telemetry.addData("bad", "none");
                }
            }

            telemetry.update();
/*
            double dist = rob.front.getDistance(DistanceUnit.INCH);
            if(dist > (32.5 - (blockNumber - 1) * 7.5)){
                rob.driveTrainMovement(0.02, Crane.movements.forward);
                telemetry.addData("moveForwardUltra: ", dist);

            }
            else if (dist < (31.5 - (blockNumber - 1) * 7.5)){
                rob.driveTrainMovement(0.02, Crane.movements.backward);
                telemetry.addData("moveBackwardUltra: ", dist);

            }
            else {
                rob.stopDrivetrain();
                rob.moving1 = true;
                telemetry.addData("StopUltraFB: ", dist);
            }
        }
*/


        }
    }



    public void identify2() throws InterruptedException {
        for (VuforiaTrackable trackable : rob.allTrackables) {
            ((VuforiaTrackableDefaultListener) trackable.getListener()).setPhoneInformation(rob.robotFromCamera, rob.parameters.cameraDirection);
        }

        rob.targetsSkyStone.activate();
        while (opModeIsActive() && !rob.target && !rob.moving2) {
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
                            rob.moving2 = true; }
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
                    telemetry.addData("x: ", rob.x);
                    telemetry.addData("y: ", rob.y);
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

        double currTime = runtime.milliseconds();


        rob.targetsSkyStone.activate();
        while (opModeIsActive() && !rob.target && !rob.moving3 && runtime.milliseconds() - currTime < 7000) {

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

                        if (((translation.get(0) / rob.mmPerInch) < -5.1)) {
                            rob.driveTrainMovement(0.5, Crane.movements.left);
                            rob.straight = true;

                        } else if ((translation.get(0) / rob.mmPerInch) > -4.9)  {
                            rob.driveTrainMovement(0.5, Crane.movements.right);
                            rob.straight = true;

                        }
                        else {
                            rob.stopDrivetrain();
                            rob.moving3=true;
                        }


                    }

                } else {
                    telemetry.addData("bad2", "none");
                }
            }


/*
            double dist = rob.left.getDistance(DistanceUnit.INCH);
            if(dist > 5){
                rob.driveTrainMovement(.5, Crane.movements.left);
                telemetry.addData("moveLeftUltra: ", dist);

            }
            else if (dist < 4){
                rob.driveTrainMovement(0.5, Crane.movements.right);
                telemetry.addData("moveRightUltra: ", dist);

            }
            else {
                rob.stopDrivetrain();
                rob.moving3 = true;
                telemetry.addData("StopUltra: ", dist);

            }

 */

            telemetry.update();

        }


    }

    public void goOn() throws InterruptedException {
        for (VuforiaTrackable trackable : rob.allTrackables) {
            ((VuforiaTrackableDefaultListener) trackable.getListener()).setPhoneInformation(rob.robotFromCamera, rob.parameters.cameraDirection);
        }

        rob.targetsSkyStone.activate();
        while (opModeIsActive() && !rob.target && !rob.stop) {
            for (VuforiaTrackable trackable : rob.allTrackables) {
                if (((VuforiaTrackableDefaultListener) trackable.getListener()).isVisible()) {
                    rob.stop = true;
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

    public void valueBasedRed() throws InterruptedException{
        double dist = 0;
        double currTime = runtime.milliseconds();
        while (rob.tfod != null && rob.blockNumber == 0 && runtime.milliseconds() - currTime < 1500) {
            // getUpdatedRecognitions() will return null if no new information is available since
            // the last time that call was made.
            List<Recognition> updatedRecognitions = rob.tfod.getUpdatedRecognitions();
            if (updatedRecognitions != null) {
                telemetry.addData("# Object Detected", updatedRecognitions.size());
                if (updatedRecognitions.size() == 2 && updatedRecognitions.get(0).getLabel().equals("Stone") && updatedRecognitions.get(1).getLabel().equals("Stone")){
                    rob.blockNumber = 3;
                }
                // step through the list of recognitions and display boundary info.
                int i = 0;
                for (Recognition recognition : updatedRecognitions) {
                    if (recognition.getLabel().equals("Skystone")){
                        if (recognition.getRight() < 250){
                            rob.blockNumber = 3;
                        }
                        else if (recognition.getRight() < 450){
                            rob.blockNumber = 2;
                        }
                        else if (recognition.getRight() < 650){
                            rob.blockNumber = 1;
                        }
                    }
                    telemetry.addData(String.format("label (%d)", i), recognition.getLabel());
                    telemetry.addData(String.format("  left,top (%d)", i), "%.03f , %.03f",
                            recognition.getLeft(), recognition.getTop());
                    telemetry.addData(String.format("  right,bottom (%d)", i), "%.03f , %.03f",
                            recognition.getRight(), recognition.getBottom());
                }
                telemetry.update();
            }
        }

        if (rob.blockNumber == 0){
            telemetry.addLine("BlockNumber: DEFAULT");
        }
        else if (rob.blockNumber == 1){
            telemetry.addLine("BlockNumber: LEFT");
        }
        else if (rob.blockNumber ==2){
            telemetry.addLine("BlockNumber: CENTER");

        }
        else if (rob.blockNumber == 3){
            telemetry.addLine("BlockNumber: RIGHT");
        }


        telemetry.update();
        sleep(100);


        rob.driveTrainEncoderMovement(0.5, .5, 5, 0, Crane.movements.left);

        switch(rob.blockNumber){

            case 1:
                do {
                    rob.driveTrainMovement(0.15, Crane.movements.forward);

                    dist = rob.back.getDistance(DistanceUnit.INCH);
                    telemetry.addData("in front", "%.2f in", dist);
                    telemetry.update();

                }
                while (dist < 29.5 || Double.compare(dist, Double.NaN) == 0 && opModeIsActive());

                break;
            case 2:
                do {
                    rob.driveTrainMovement(0.15, Crane.movements.backward);

                    dist = rob.back.getDistance(DistanceUnit.INCH);
                    telemetry.addData("in front", "%.2f in", dist);
                    telemetry.update();

                }
                while (dist > 28 || Double.compare(dist, Double.NaN) == 0 && opModeIsActive());

                break;
            case 3:
                do {
                    rob.driveTrainMovement(0.5, Crane.movements.backward);

                    dist = rob.back.getDistance(DistanceUnit.INCH);
                    telemetry.addData("in front", "%.2f in", dist);
                    telemetry.update();

                }
                while (dist > 21 || Double.compare(dist, Double.NaN) == 0 && opModeIsActive());

                break;
            case 0:
                rob.driveTrainEncoderMovement(0.2, 30, 3, 0, Crane.movements.left);
                rob.blockNumber = 2;
                break;
        }
        rob.stopDrivetrain();
        identify1RED(rob.blockNumber);
        identify3RED();
        if (!rob.moving3){
            return;
        }
        /*
        clawDown();
        rob.autonDownClaw.setPosition(0.6);
        do {
            rob.driveTrainMovement(0.1, Crane.movements.right);

            dist = rob.right.getDistance(DistanceUnit.INCH);
            telemetry.addData("in front", "%.2f in", dist);
            telemetry.update();

        }
        while (dist > 20 || Double.compare(dist, Double.NaN) == 0 && opModeIsActive());
        rob.driveTrainEncoderMovement(0.1, 51, 5, 0, Crane.movements.forward);


         */

       /* rob.driveTrainEncoderMovement(0.1, 38, 5, 0, Crane.movements.forward);
        do {
            rob.driveTrainMovement(0.1, Crane.movements.forward);

            dist = rob.front.getDistance(DistanceUnit.INCH);
            telemetry.addData("in front", "%.2f in", dist);
            telemetry.update();

        }
        while (dist > 24 || Double.compare(dist, Double.NaN) == 0 && opModeIsActive());

        do {
            rob.driveTrainMovement(0.1, Crane.movements.left);

            dist = rob.left.getDistance(DistanceUnit.INCH);
            telemetry.addData("in front", "%.2f in", dist);
            telemetry.update();

        }
        while (dist > 1.5 || Double.compare(dist, Double.NaN) == 0 && opModeIsActive());*/
        // rob.driveTrainEncoderMovement(0.1, 70, 5, 0, Crane.movements.backward);
        //    rob.driveTrainEncoderMovement(0.1, 20, 5, 0, Crane.movements.left);

    }

    public void valueBased() throws InterruptedException{
        double dist = 0;
        double currTime = runtime.milliseconds();
        while (rob.tfod != null && rob.blockNumber == 0 && runtime.milliseconds() - currTime < 1500) {
            // getUpdatedRecognitions() will return null if no new information is available since
            // the last time that call was made.
            List<Recognition> updatedRecognitions = rob.tfod.getUpdatedRecognitions();
            if (updatedRecognitions != null) {
                telemetry.addData("# Object Detected", updatedRecognitions.size());
                // step through the list of recognitions and display boundary info.
                int i = 0;
                if (updatedRecognitions.size() == 2) {

                    float skystone = -1;
                    float stone1 = -1;
                    float stone2 = -1;
                    for (Recognition recognition : updatedRecognitions) {
                        if (recognition.getLabel().equals("Skystone")) {
                            skystone = recognition.getLeft();
                        }
                        else if (recognition.getLabel().equals("Stone")){
                            if (stone1 == -1){
                                stone1 = recognition.getLeft();
                            }else {
                                stone2 = recognition.getLeft();
                            }
                        }
                        telemetry.addData(String.format("label (%d)", i), recognition.getLabel());
                        telemetry.addData(String.format("  left,top (%d)", i), "%.03f , %.03f",
                                recognition.getLeft(), recognition.getTop());
                        telemetry.addData(String.format("  right,bottom (%d)", i), "%.03f , %.03f",
                                recognition.getRight(), recognition.getBottom());
                    }
                    telemetry.update();

                    if (skystone == -1){
                        rob.blockNumber = 3;
                    }
                    else {
                        if (skystone > stone1){
                            rob.blockNumber = 2;
                        }
                        else {
                            rob.blockNumber = 1;
                        }
                    }
                }
                else if (updatedRecognitions.size() == 3){
                    float skystone = -1;
                    float stone1 = -1;
                    float stone2 = -1;
                    for (Recognition recognition : updatedRecognitions) {
                        if (recognition.getLabel().equals("Skystone")) {
                            skystone = recognition.getLeft();
                        }
                        else if (recognition.getLabel().equals("Stone")){
                            if (stone1 == -1){
                                stone1 = recognition.getLeft();
                            }else {
                                stone2 = recognition.getLeft();
                            }
                        }
                        telemetry.addData(String.format("label (%d)", i), recognition.getLabel());
                        telemetry.addData(String.format("  left,top (%d)", i), "%.03f , %.03f",
                                recognition.getLeft(), recognition.getTop());
                        telemetry.addData(String.format("  right,bottom (%d)", i), "%.03f , %.03f",
                                recognition.getRight(), recognition.getBottom());
                    }
                    telemetry.update();

                    if (skystone > stone1 && skystone > stone2){
                        rob.blockNumber = 3;
                    }
                    else if (skystone > stone1 || skystone > stone2){
                        rob.blockNumber = 2;
                    }
                    else {
                        rob.blockNumber = 1;
                    }
                }
            }
        }

        if (rob.blockNumber == 0){
            telemetry.addLine("BlockNumber: DEFAULT");
        }
        else if (rob.blockNumber == 1){
            telemetry.addLine("BlockNumber: LEFT");
        }
        else if (rob.blockNumber == 2){
            telemetry.addLine("BlockNumber: CENTER");

        }
        else if (rob.blockNumber ==3){
            telemetry.addLine("BlockNumber: RIGHT");
        }


        telemetry.update();
        sleep(200);



        switch(rob.blockNumber){

            case 1:
                do {
                    rob.driveTrainMovement(0.5, Crane.movements.backward);

                    dist = rob.back.getDistance(DistanceUnit.INCH);
                    telemetry.addData("in front", "%.2f in", dist);
                    telemetry.update();

                }
                while (dist < 40 || Double.compare(dist, Double.NaN) == 0 && opModeIsActive());

                break;
            case 2:
                do {
                    rob.driveTrainMovement(0.15, Crane.movements.forward);

                    dist = rob.back.getDistance(DistanceUnit.INCH);
                    telemetry.addData("in front", "%.2f in", dist);
                    telemetry.update();

                }
                while (dist > 28 || Double.compare(dist, Double.NaN) == 0 && opModeIsActive());

                break;
            case 3:
                do {
                    rob.driveTrainMovement(0.5, Crane.movements.forward);

                    dist = rob.back.getDistance(DistanceUnit.INCH);
                    telemetry.addData("in front", "%.2f in", dist);
                    telemetry.update();

                }
                while (dist > 21 || Double.compare(dist, Double.NaN) == 0 && opModeIsActive());

                break;
            case 0:
                rob.driveTrainEncoderMovement(0.5, 10, 3, 0, Crane.movements.backward);
                rob.blockNumber = 1;
                break;
        }
        rob.stopDrivetrain();
        identify3();
        identify1(rob.blockNumber);
        if (!rob.moving3){
            return;
        }
        /*
        clawDown();
        do {
            rob.driveTrainMovement(0.5, Crane.movements.right);

            dist = rob.back.getDistance(DistanceUnit.INCH);
            telemetry.addData("in front", "%.2f in", dist);
            telemetry.update();

        }
        while (dist > 23 || Double.compare(dist, Double.NaN) == 0 && opModeIsActive());
        rob.driveTrainEncoderMovement(0.5, 38, 5, 0, Crane.movements.backward);
        /*
        do {
            rob.driveTrainMovement(0.1, Crane.movements.backward);

            dist = rob.back.getDistance(DistanceUnit.INCH);
            telemetry.addData("in front", "%.2f in", dist);
            telemetry.update();

        }
        while (dist > 24 || Double.compare(dist, Double.NaN) == 0 && opModeIsActive());

        do {
            rob.driveTrainMovement(0.1, Crane.movements.left);

            dist = rob.left.getDistance(DistanceUnit.INCH);
            telemetry.addData("in front", "%.2f in", dist);
            telemetry.update();

        }
        */

    //    while (dist > 1.5 || Double.compare(dist, Double.NaN) == 0 && opModeIsActive());
        // rob.driveTrainEncoderMovement(0.1, 70, 5, 0, Crane.movements.backward);
        //    rob.driveTrainEncoderMovement(0.1, 20, 5, 0, Crane.movements.left);

    }

    public void clawDown() throws InterruptedException{
        rob.autonGrabClaw.setPosition(0.4);
        sleep(1000);
        rob.autonDownClaw.setPosition(1);
        sleep(1000);
        rob.autonGrabClaw.setPosition(0.10);
        sleep(1000);
        rob.autonDownClaw.setPosition(0.5);
        sleep(1000);
    }

//hi
}
