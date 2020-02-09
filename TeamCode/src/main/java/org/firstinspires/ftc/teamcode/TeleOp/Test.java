package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Control.Crane;
import org.firstinspires.ftc.teamcode.Control.TeleOpControl;

@TeleOp(name = "Test", group = "Concept")



public class Test extends TeleOpControl {
    private ElapsedTime runtime = new ElapsedTime();
    boolean kill = false;

    @Override
    public void runOpMode() throws InterruptedException {
        boolean yToggle = false;

        //setup(runtime, Crane.setupType.teleop, Crane.setupType.drive, Crane.setupType.intake, Crane.setupType.claw, Crane.setupType.foundation);
        setup(runtime, Crane.setupType.drive);

        while (opModeIsActive()) {
            standardGamepadData();


            if (!yToggle) {
                if (g(0)) {
                    rob.driveTrainMovement(fb, Crane.movements.forward);
                } else if (g(2)) {
                    rob.driveTrainMovement(fb, Crane.movements.backward);
                } else if (g(3)) {
                    rob.driveTrainMovement(rl, Crane.movements.right);
                } else if (g(1)) {
                    rob.driveTrainMovement(rl, Crane.movements.left);
                } else if (g(4)) {
                    rob.driveTrainMovement(diagonalSpeed, Crane.movements.tl);
                } else if (g(5)) {
                    rob.driveTrainMovement(diagonalSpeed, Crane.movements.tr);
                } else if (g(6)) {
                    rob.driveTrainMovement(diagonalSpeed, Crane.movements.br);
                } else if (g(7)) {
                    rob.driveTrainMovement(diagonalSpeed, Crane.movements.bl);
                } else if (g(8)) {
                    rob.driveTrainMovement(0.3, Crane.movements.ccw);
                } else if (g(9)) {
                    rob.driveTrainMovement(0.3, Crane.movements.cw);
                } else {
                    rob.stopDrivetrain();
                }
            } else {
                if (g(0)) {
                    rob.driveTrainMovement(0.5, Crane.movements.left);
                } else if (g(2)) {
                    rob.driveTrainMovement(0.5, Crane.movements.right);
                } else if (g(3)) {
                    rob.driveTrainMovement(0.3, Crane.movements.forward);
                } else if (g(1)) {
                    rob.driveTrainMovement(0.3, Crane.movements.backward);
                } else if (g(4)) {
                    rob.driveTrainMovement(0.3, Crane.movements.tr);
                } else if (g(5)) {
                    rob.driveTrainMovement(0.3, Crane.movements.tl);
                } else if (g(6)) {
                    rob.driveTrainMovement(0.3, Crane.movements.bl);
                } else if (g(7)) {
                    rob.driveTrainMovement(0.3, Crane.movements.br);
                } else if (g(8)) {
                    rob.driveTrainMovement(0.3, Crane.movements.ccw);
                } else if (g(9)) {
                    rob.driveTrainMovement(0.3, Crane.movements.cw);
                } else {
                    rob.stopDrivetrain();
                }
            }
            telemetry.addData("speed1", fb);
            telemetry.addData("speed2", rl);
        }
     }
}
