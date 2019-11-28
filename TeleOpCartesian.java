
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="TeleOpCartesian", group="ro057")

public class TeleOpCartesian extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    HardwareMecanum robot = new HardwareMecanum();
    int calibrateCount = 0;
    boolean arcade = false;
  
    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        
        telemetry.addData("Status", "Initialized");
        telemetry.addData("Arcade Mode (a)", arcade ? "YES" : "no.");
        telemetry.addData("Gyro Ready?", robot.isGyroCalibrated() ? "YES" : "no");
        telemetry.update();
        
        waitForStart();
        while (opModeIsActive()) {
            robot.loop();
            
            //double r = Math.hypot(gamepad1.left_stick_x, gamepad1.left_stick_y);
            //double robotAngle = Math.atan2(gamepad1.left_stick_y, gamepad1.left_stick_x) - Math.PI/4.0;
            //double rightX = -gamepad1.right_stick_x;
            
            //final double v1 = r * Math.sin(robotAngle) + rightX;
            //final double v2 = r * Math.cos(robotAngle) - rightX;
            //final double v3 = r * Math.cos(robotAngle) + rightX;
            //final double v4 = r * Math.sin(robotAngle) - rightX;
            
            if (gamepad1.x){
                robot.resetHeading();
            }
            
            if (gamepad1.a){
                arcade = !arcade;
            }
            
            final double x = Math.pow(gamepad1.left_stick_x, 3.0);
            final double y = Math.pow(gamepad1.left_stick_y, 3.0);
    
            final double rotation = Math.pow(gamepad1.right_stick_x, 3.0);
            final double direction = Math.atan2(x, y) + (arcade ? robot.getHeading() : 0.0);
            final double speed = Math.min(1.0, Math.sqrt(x * x + y * y));
    
            final double v1 = speed * Math.sin(direction + Math.PI / 4.0) + rotation;
            final double v2 = speed * Math.cos(direction + Math.PI / 4.0) - rotation;
            final double v3 = speed * Math.cos(direction + Math.PI / 4.0) + rotation;
            final double v4 = speed * Math.sin(direction + Math.PI / 4.0) - rotation;

            
            robot.FL.setPower(v1);
            robot.FR.setPower(v2);
            robot.BL.setPower(v3);
            robot.BR.setPower(v4);
            
            telemetry.addLine("Values");
            telemetry.addData("v1", v1);
            telemetry.addData("v2", v2);
            telemetry.addData("v3", v3);
            telemetry.addData("v4", v4);
            
            telemetry.addLine("=======");
            telemetry.addData("Heading (reset: x)", robot.getHeadingDegrees());
            telemetry.addData("Arcade Mode (a)", arcade ? "YES" : "no");

            telemetry.update();
        }
    }
}
