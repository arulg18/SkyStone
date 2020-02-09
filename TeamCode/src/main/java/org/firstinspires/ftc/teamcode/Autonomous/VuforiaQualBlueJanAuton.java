package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.Control.AutonomousControl;
import org.firstinspires.ftc.teamcode.Control.Crane;

@Autonomous(name="Vuforia Qual Blue Jan Autonomous", group = "AA")
public class VuforiaQualBlueJanAuton extends AutonomousControl {
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException {
        //setup(runtime, Crane.setupType.drive, Crane.setupType.foundation, Crane.setupType.imu, Crane.setupType.claw, Crane.setupType.ultrasoinc, Crane.setupType.autonomous, Crane.setupType.camera, Crane.setupType.tfod);
        setup(runtime, Crane.setupType.drive, Crane.setupType.camera);

        if(opModeIsActive()){

            valueBased();

            /*
            rob.rotationservo.setPosition(1);
            valueBased();

            rob.autonDownClaw.setPosition(1);
            sleep(300);
            rob.autonGrabClaw.setPosition(0.4);
            sleep(100);
            rob.autonDownClaw.setPosition(0.7);
            sleep(100);
            rob.driveTrainEncoderMovement(0.4, 15, 5, 0, Crane.movements.right);
            rob.driveTrainEncoderMovement(0.4, 22.5, 5, 0, Crane.movements.cw);
            double dist;

            do {
                rob.driveTrainMovement(0.1, Crane.movements.backward);

                dist = rob.back.getDistance(DistanceUnit.INCH);
                telemetry.addData("in front", "%.2f in", dist);
                telemetry.update();

            }
            while (dist > 1.5 || Double.compare(dist, Double.NaN) == 0 && opModeIsActive());

            rob.foundationServo1.setPosition(0.45);
            rob.foundationServo2.setPosition(0.75);
            sleep(100);

            rob.driveTrainEncoderMovement(0.4, 22.5, 5, 0, Crane.movements.ccw);
            rob.driveTrainEncoderMovement(0.4, 15, 4, 0, Crane.movements.backward);
            rob.driveTrainEncoderMovement(0.4, 15, 4, 0, Crane.movements.right);
            rob.foundationServo1.setPosition(1);
            rob.foundationServo2.setPosition(0);



            /*


            do{
                rob.driveTrainMovement(0.3, Crane.movements.right);

                dist= rob.right.getDistance(DistanceUnit.CM);
                telemetry.addData("cm front", "%.2f cm", dist);
                telemetry.update();

            }
            while(dist > 40 || Double.compare(dist, Double.NaN) == 0 && opModeIsActive());

            rob.driveTrainEncoderMovement(0.3, 40, 4, 0, Crane.movements.right);

            do{
                rob.driveTrainMovement(0.3, Crane.movements.backward);

                dist= rob.back.getDistance(DistanceUnit.CM);
                telemetry.addData("cm front", "%.2f cm", dist);
                telemetry.update();

            }
            while(dist > 30 || Double.compare(dist, Double.NaN) == 0 && opModeIsActive());

            rob.autonDownClaw.setPosition(1);
            sleep(100);
            rob.autonGrabClaw.setPosition(0.4);
            sleep(100);
            rob.autonDownClaw.setPosition(0.5);
            sleep(100);
            rob.autonGrabClaw.setPosition(0.15);
            sleep(100);

            rob.foundationServo1.setPosition(0.45);
            rob.foundationServo2.setPosition(0.75);

            rob.driveTrainEncoderMovement(0.5, 25, 5, 0, Crane.movements.ccw);

            rob.driveTrainEncoderMovement(0.4, 20, 4, 0, Crane.movements.left);

            rob.driveTrainEncoderMovement(0.4, 20, 4, 0, Crane.movements.backward);

            rob.foundationServo1.setPosition(1);
            rob.foundationServo2.setPosition(0);

            rob.driveTrainEncoderMovement(0.4, 5, 4, 0, Crane.movements.right);

            rob.driveTrainEncoderMovement(0.4, 10, 4, 0, Crane.movements.forward);


            rob.driveTrainEncoderMovement(0.4, 20, 4, 0, Crane.movements.right);


             */






        }
    }
}
