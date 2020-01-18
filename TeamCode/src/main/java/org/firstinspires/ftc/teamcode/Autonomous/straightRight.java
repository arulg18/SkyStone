package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.Control.AutonomousControl;
import org.firstinspires.ftc.teamcode.Control.Crane;

@Autonomous(name="Right Up", group = "AAA")
public class straightRight extends AutonomousControl {
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException {

        setup(runtime, Crane.setupType.drive, Crane.setupType.foundation, Crane.setupType.claw, Crane.setupType.ultrasoinc, Crane.setupType.autonomous);
        telemetry.addLine("Start!");
        telemetry.update();


        if (opModeIsActive()){
            rob.autonDownClaw.setPosition(1);
            double dist;
            do {
                rob.driveTrainMovement(0.1, Crane.movements.right);

                dist = rob.left.getDistance(DistanceUnit.INCH);
                telemetry.addData("in front", "%.2f in", dist);
                telemetry.update();

            }
            while (dist < 4 || Double.compare(dist, Double.NaN) == 0 && opModeIsActive());

            rob.driveTrainEncoderMovement(0.3, 20, 5, 0, Crane.movements.forward);
        }


    }
}
//hello viven
////finish the sentence//// you are a nig
