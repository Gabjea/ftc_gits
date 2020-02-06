package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import com.qualcomm.hardware.bosch.BNO055IMU;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.FORWARD;
import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.REVERSE;

public class HardwareMecanum {
    /* Public OpMode members. */
    public DcMotor FL = null;
    public DcMotor FR = null;
    public DcMotor BL = null;
    public DcMotor BR = null;



 /**
     * IMU
     */
    public BNO055IMU imu;
    public Orientation angles;
    public Acceleration gravity;
    public double headingOffset = 0.0;



    /* local OpMode members. */
    HardwareMap hwMap           =  null;
    private ElapsedTime period  = new ElapsedTime();

    /* Constructor */
    public HardwareMecanum(){

    }

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;

        /**
         * Motors
         */
        //Get hardware for motors
        FL = hwMap.dcMotor.get("FL");
        BL = hwMap.dcMotor.get("BL");
        FR = hwMap.dcMotor.get("FR");
        BR = hwMap.dcMotor.get("BR");

        //Set direction to all motors
        FL.setDirection(DcMotor.Direction.FORWARD);
        BL.setDirection(DcMotor.Direction.FORWARD);
        FR.setDirection(DcMotor.Direction.REVERSE);
        BR.setDirection(DcMotor.Direction.REVERSE);

        // Set all motors to run without encoders.
        FL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        FR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        
         BL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        
        
         /**
         * IMU
         */
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit           = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit           = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.calibrationDataFile = "BNO055IMUCalibration.json"; // see the calibration sample opmode
        parameters.loggingEnabled      = true;
        parameters.loggingTag          = "IMU";
       // parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();

        // Retrieve and initialize the IMU. We expect the IMU to be attached to an I2C port
        // on a Core Device Interface Module, configured to be a sensor of type "AdaFruit IMU",
        // and named "imu".
        imu = hwMap.get(BNO055IMU.class, "imu");
        imu.initialize(parameters);
        
    }

    public void runUsingEncoders(){
       setMotorMode(DcMotor.RunMode.RUN_USING_ENCODER, FL, BL, FR, BR);
    }

    public void runWithoutEncoders(){
       setMotorMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER, FL, BL, FR, BR);
    }

    public void runToPosition(){
        setMotorMode(DcMotor.RunMode.RUN_TO_POSITION, FL, BL, FR, BR);
    }

    public void resetEncoders(){
        setMotorMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER, FL, BL, FR, BR);
    }

    protected void setMotorMode(DcMotor.RunMode mode, DcMotor... motors){
        for (DcMotor motor : motors){
            motor.setMode(mode);
        }
    }

    private double maxAbs (double... _x){
        double rez = Double.MIN_VALUE;
        for (double x : _x) {
            if (Math.abs(x) > rez) {
                rez = Math.abs(x);
            }
        }
        return rez;
    }

    public void setMotors (double _fl, double _fr, double _bl, double _br) {
        final double scale = maxAbs(1.0, _fl, _fr, _bl, _br);
        FL.setPower(_fl / scale);
        FR.setPower(_fr / scale);
        BL.setPower(_bl / scale);
        BR.setPower(_br / scale);
    }
     
    public boolean isGyroCalibrated() {
        return imu.isGyroCalibrated();
    }

    public void loop() {
        angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.RADIANS);
        gravity = imu.getGravity();
    }

    public double getRawHeading() {
        return angles.firstAngle;
    }

    public double getHeading() {
        return (getRawHeading() - headingOffset) % (2.0 * Math.PI);
    }

    public void resetHeading() {
        headingOffset = getRawHeading();
    }
    
    public double getHeadingDegrees() {
        return Math.toDegrees(getHeading());
    }

}
