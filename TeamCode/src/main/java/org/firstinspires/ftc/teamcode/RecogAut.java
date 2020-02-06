package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;

public class RecogAut {
        public DcMotor FL = null;
        public DcMotor BL = null;
        public DcMotor FR = null;
        public DcMotor BR = null;

        static final double     COUNTS_PER_MOTOR_REV    = 383.6 ;    // eg: TETRIX Motor Encoder
        static final double     DRIVE_GEAR_REDUCTION    = 1.0 ;     // This is < 1.0 if geared UP
        static final double     WHEEL_DIAMETER_INCHES   = 4.0 ;     // For figuring circumference
        static final double     CM_IN_INCH              = 0.3937007874;
        static final double     DRIVE_CORECTION         = 0.9345794392;
        static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) / (WHEEL_DIAMETER_INCHES * 3.1415) * CM_IN_INCH;
        double                  SPEED                   = 0.4;
        static final double     SPEED_INCREMENT         = 0.01;


        public void SkystoneRecog(){

            //Drive 20 cm for recognition
            int newFL, newBL, newFR, newBR;
            newFL = (int)((FL.getCurrentPosition() + (int)(20 * COUNTS_PER_INCH))*DRIVE_CORECTION);
            newFR = (int)((FR.getCurrentPosition() + (int)(20 * COUNTS_PER_INCH))*DRIVE_CORECTION);
            newBL = (int)((BL.getCurrentPosition() + (int)(20 * COUNTS_PER_INCH))*DRIVE_CORECTION);
            newBR = (int)((BR.getCurrentPosition() + (int)(20 * COUNTS_PER_INCH))*DRIVE_CORECTION);

            FL.setTargetPosition(newFL);
            FR.setTargetPosition(newFR);
            BL.setTargetPosition(newBL);
            BR.setTargetPosition(newBR);


            FL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            FR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            BL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            BR.setMode(DcMotor.RunMode.RUN_TO_POSITION);


            FL.setPower(SPEED);
            FR.setPower(SPEED);
            BL.setPower(SPEED);
            BR.setPower(SPEED);

            while(FL.isBusy() && FR.isBusy() && BL.isBusy() && BR.isBusy()){

                if(FL.getCurrentPosition() >= newFL/2 || FR.getCurrentPosition() >= newFR/2 || BL.getCurrentPosition() >= newBL/2 || BR.getCurrentPosition() >= newBR/2){
                    SPEED-=SPEED_INCREMENT;
                    // Set motors power again
                }


            }
            // Activate Vuforia



    }

    public void Rotate(){

    }

    public void PlaceSkystone(){

    }



}
