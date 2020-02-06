package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.robotcore.external.navigation.Position;

import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Hardware;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="Tabara", group = "")

public class Tabara extends LinearOpMode {
    
    //HardwareMecanum robot = new HardwareMecanum();
    public DcMotor FL = null;
    public DcMotor FR = null;
    public DcMotor BL = null;
    public DcMotor BR = null;
    
    //public DcMotor Brat =null;
    
    public Servo claw1=null;
    public Servo claw2=null;
    
    
    public double FORWARD_SPEED=0;
    public double SIDE_SPEED=0;
    public double ROTATION_SPEED=0;
    public static final double SPEED_ADJUST=.5;
    
    @Override
    public void runOpMode() {
        
        
        //robot.init(hardwareMap);
        /**
         * Motors
         */
        //Get hardware for motors
        FL = hardwareMap.dcMotor.get("FL");
        BL = hardwareMap.dcMotor.get("BL");
        FR = hardwareMap.dcMotor.get("FR");
        BR = hardwareMap.dcMotor.get("BR");

      //  Brat=hardwareMap.dcMotor.get("Brat");
        //claw1= hardwareMap.servo.get("claw1");
        //claw2= hardwareMap.servo.get("claw2");
        
        //claw1.setPosition(0);
        //claw2.setPosition(0);
        


        //Set direction to all motors
        FL.setDirection(DcMotor.Direction.REVERSE);
        BL.setDirection(DcMotor.Direction.REVERSE);
        FR.setDirection(DcMotor.Direction.FORWARD);
        BR.setDirection(DcMotor.Direction.FORWARD);

        // Set all motors to run without encoders.
        FL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        FR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        
        waitForStart();
        
        while (opModeIsActive()){

           FORWARD_SPEED= -gamepad1.left_stick_y;  // valorile de pe axa OY a joystick-ului stang
           SIDE_SPEED= gamepad1.left_stick_x;
           ROTATION_SPEED= gamepad1.right_stick_x;
            
        
            /*if(gamepad1.a==true)
                robot.frontArm.setPower(0.25);
            else if(gamepad1.b==true)
                robot.frontArm.setPower(-0.35);
            else robot.frontArm.setPower(0);
          */
           
                
            if(gamepad1.y == true){
        //        claw1.setPosition(1);
          //      claw2.setPosition(1);
            }
            else if(gamepad1.a == true){
                //claw1.setPosition(0);
                //claw2.setPosition(0);
            }
                
                
                
     //       FL.setPower((FORWARD_SPEED + SIDE_SPEED + ROTATION_SPEED)*SPEED_ADJUST);    
       //     FR.setPower((FORWARD_SPEED - SIDE_SPEED - ROTATION_SPEED)*SPEED_ADJUST);     
         //   BL.setPower((FORWARD_SPEED - SIDE_SPEED + ROTATION_SPEED)*SPEED_ADJUST); 
          //  BR.setPower((FORWARD_SPEED + SIDE_SPEED - ROTATION_SPEED)*SPEED_ADJUST); 
              FL.setPower((FORWARD_SPEED + ROTATION_SPEED+ (-gamepad1.left_trigger+gamepad1.right_trigger)*1.25)*SPEED_ADJUST);
              FR.setPower((FORWARD_SPEED-ROTATION_SPEED)*SPEED_ADJUST);
              BL.setPower((FORWARD_SPEED+ROTATION_SPEED)*SPEED_ADJUST);
              BR.setPower((FORWARD_SPEED-ROTATION_SPEED+ (-gamepad1.left_trigger+gamepad1.right_trigger)*1.25)*SPEED_ADJUST);
              if(gamepad1.dpad_right == true){
                FL.setPower(SPEED_ADJUST);
                FR.setPower(-SPEED_ADJUST);
                BL.setPower(-SPEED_ADJUST-.2);
                //BR.setPower(0.55);
                BR.setPower(SPEED_ADJUST+.2);
              }
              else if(gamepad1.dpad_left == true){
                FL.setPower(-SPEED_ADJUST);
                FR.setPower(SPEED_ADJUST);
                BL.setPower(SPEED_ADJUST+.2);
                //BR.setPower(-0.55);
                BR.setPower(-SPEED_ADJUST-.2);
              }
              else if (gamepad1.dpad_up == true){
                  BR.setPower(1.0);
                  
              }
              
          /* if(gamepad1.y == true){
                claw1.setPosition(0.5);
                claw2.setPosition(0.5);
            }
            else if(gamepad2.a == true){
                //claw1.setPosition(0);
                //claw2.setPosition(0);
            }
           */
           
          //  robot.tm.setPower(gamepad1.left_trigger-gamepad1.right_trigger);
          //Brat.setPower((gamepad2.left_trigger-gamepad2.right_trigger)*0.3);
 
            
               
                telemetry.addData("FL: ", FL.getCurrentPosition());
                telemetry.addData("BL: ", BL.getCurrentPosition());
                telemetry.addData("FR: ", FR.getCurrentPosition());
                telemetry.addData("BR: ", BR.getCurrentPosition());

               telemetry.addData("FL: ", FL.getPower());
                telemetry.addData("BL: ", BL.getPower());
                telemetry.addData("FR: ", FR.getPower());
                telemetry.addData("BR: ", BR.getPower());
                
            
            telemetry.update();
        
        }
        
    }
}
