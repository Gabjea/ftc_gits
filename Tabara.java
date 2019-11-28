package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Hardware;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="Tabara", group = "")
public class Tabara extends LinearOpMode {
    
    HardwareMecanum robot = new HardwareMecanum();
    public int k=-1;
    public int v=-1;
    @Override
    public void runOpMode() {
        
        
        robot.init(hardwareMap);
        
        
        waitForStart();
        
        while (opModeIsActive()){

           double powerY = gamepad1.left_stick_y;  // valorile de pe axa OY a joystick-ului stang
           double powerS= gamepad1.left_stick_x;
            double powerX  =  gamepad1.right_stick_x; // valorile de pe axa OX a joystick-ului stang
            double powerD  =  -gamepad1.right_stick_y;
            
           // robot.backArm.setPower(-gamepad1.left_trigger + gamepad1.right_trigger);
            /*if(gamepad1.a==true)
                robot.frontArm.setPower(0.25);
            else if(gamepad1.b==true)
                robot.frontArm.setPower(-0.35);
            else robot.frontArm.setPower(0);
          
            if(gamepad1.x){
                robot.cl.setPosition(0.5);
                robot.cr.setPosition(-0.2);
            }else if(gamepad1.y){
                robot.cl.setPosition(0.35);
                robot.cr.setPosition(0.2);
            }
            
            
            
            
            */
            
            if(gamepad1.y){
                k=k*(-1);
            }
            if(gamepad1.a){
                v=v*(-1);
            }
            
            if(gamepad2.y){
                powerY=0;
                powerS=0;
                powerD=0;
                powerX=0;
                v=-1;
                k=-1;
            }
                
                if(gamepad1.left_stick_y !=0){ // robotul se misca in fata
                    robot.FL.setPower(powerY);
                    robot.FR.setPower(powerY);
                    robot.BL.setPower(powerY);
                    robot.BR.setPower(powerY);
                }
                else if(gamepad1.right_stick_x !=0){ // robotul se roteste pe loc la stanga
                    robot.FL.setPower(-powerX);
                    robot.FR.setPower(powerX);
                    robot.BL.setPower(-powerX);
                    robot.BR.setPower(powerX);
                }
                else if(gamepad1.left_stick_x !=0){ //side
                    robot.FL.setPower(powerS);
                    robot.FR.setPower(-powerS);
                    robot.BL.setPower(-powerS);
                    robot.BR.setPower(powerS);
                }
                else if(gamepad1.right_stick_y !=0){ // diagonala
                    robot.FL.setPower(powerD);
                    
                    robot.BR.setPower(powerD);
                }
                else if(k==1){
                    robot.FL.setPower(-0.5);
                    robot.FR.setPower(0.5);
                    robot.BL.setPower(-0.5);
                    robot.BR.setPower(0.5);
                }
                else if(v==1){
                    robot.FL.setPower(-0.8);
                    robot.FR.setPower(0.8);
                    robot.BL.setPower(-0.8);
                    robot.BR.setPower(0.8);
                }
                else{ // robotul se opreste
                    robot.FL.setPower(0);
                    robot.FR.setPower(0);
                    robot.BL.setPower(0);
                    robot.BR.setPower(0);
                }
            
                
               

                telemetry.addData("PowerX: ",  "%.2f", powerX);
                telemetry.addData("PowerY: ", "%.2f", powerY);
                telemetry.addData("PowerS: ",  "%.2f", powerS);
                telemetry.addData("PowerD: ", "%.2f", powerD);
                telemetry.addData("Slow Rotate: ", k);
                telemetry.addData("Fast Rotate: ", v);
                telemetry.update();
            
            
        
        }
        
    }
}

