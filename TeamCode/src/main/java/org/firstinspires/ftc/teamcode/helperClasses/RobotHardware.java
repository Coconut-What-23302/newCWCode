package org.firstinspires.ftc.teamcode.helperClasses;

//import com.qualcomm.robotcore.hardware.AnalogInput;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.openftc.easyopencv.OpenCvWebcam;

/**
 * This is NOT an opmode.
 * This class is used to define all the specific hardware for a single robot.
 */

public class RobotHardware
{


    /* Public OpMode members. */
    public DcMotor frontLeft     = null;
    public DcMotor backLeft      = null;
    public DcMotor frontRight    = null;
    public DcMotor backRight    = null;

    public DcMotor clawArm = null;
    public DcMotor hangLeadScrewMotor = null;
    public DcMotor hangPivotMotor = null;

    public Servo   leftClaw    = null;
    public Servo     rightClaw   = null;
    public Servo clawWrist = null;
    public TouchSensor touchSensor = null;





    // final variables

    public class ClawPos {
        public static final double RIGHT_OPEN = 0.75;
         public static final double LEFT_OPEN =  0.67;
        public static final double RIGHT_CLOSE = 0.45;
         public static final double LEFT_CLOSE = 1.0;
    }


    public final int clawFullDown = 641;

    public final int clawArmFirstBack = 270;






    //    public AnalogInput potentiometer  = null;
    HardwareMap    hwMap              = null;

    /* Constructor */
    public RobotHardware() {

    }

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;

        // Define and initialize motors
        frontLeft  = hwMap.dcMotor.get("frontLeft");
        backLeft  = hwMap.dcMotor.get("backLeft");
        frontRight = hwMap.dcMotor.get("frontRight");
        backRight = hwMap.dcMotor.get("backRight");
        clawArm = hwMap.dcMotor.get("clawArm");
        hangLeadScrewMotor = hwMap.dcMotor.get("hangRaise");
        hangPivotMotor = hwMap.dcMotor.get("hangPivot");

        touchSensor = hwMap.get(TouchSensor.class, "touch");




        // Set direction for all motors
        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.REVERSE);
        frontRight.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.FORWARD);
        clawArm.setDirection(DcMotor.Direction.FORWARD);
        hangLeadScrewMotor.setDirection(DcMotor.Direction.FORWARD);
        hangPivotMotor.setDirection(DcMotor.Direction.REVERSE);







        // Set all motors to zero power
        frontLeft.setPower(0);
        backLeft.setPower(0);
        frontRight.setPower(0);
        backRight.setPower(0);
        hangLeadScrewMotor.setPower(0);
//        hangPivotMotor.setPower(0);
        clawArm.setPower(0);

        clawArm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);






        // Set all motors to use brake mode
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        clawArm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        hangLeadScrewMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        hangPivotMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);








        // Set almost all motors to run with encoders
        frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        clawArm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        hangLeadScrewMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        hangPivotMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        clawArm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        clawArm.setTargetPosition(0);
//        clawArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);




        // Define and initialize all installed servos
        leftClaw = hwMap.servo.get("leftClaw");
        rightClaw = hwMap.servo.get("rightClaw");
        clawWrist = hwMap.servo.get("clawWrist");


        // set servo default pos;



        // define and init sensors


    }

    /**
     * boardPixelServoPos Sets Spike Servo position
     *  Sets servo direction up/down | true = up, false = down
     */
    public int clawArmPosition (boolean position) {
        if(position) {
            return (clawArmFirstBack);
        } else {
            return (clawFullDown);
        }
    }


    /** Class to set the  individual position of the claw
     * @param side true = left, false = right
     * @param pos true = open, false = close
     */
    public void clawPosSingle (boolean side, boolean pos) {
    if(side == true) {
        if(pos == true) {
            leftClaw.setPosition(ClawPos.LEFT_OPEN);
        } else if(pos == false) {
            leftClaw.setPosition(ClawPos.LEFT_CLOSE);
        }
    } else if(side == false) {
        if(pos == true) {
            rightClaw.setPosition(ClawPos.RIGHT_OPEN);
        } else if(pos == false) {
            rightClaw.setPosition(ClawPos.RIGHT_CLOSE);
    }
    }


    }

    /**
     * Class to set the position of both claws
     * @param position true = open, false = close
     */
    public void clawPosBoth (boolean position) {
       if(position) {
           leftClaw.setPosition(ClawPos.LEFT_OPEN);
           rightClaw.setPosition(ClawPos.RIGHT_OPEN);
       } else if(!position) {
           leftClaw.setPosition(ClawPos.LEFT_CLOSE);
           rightClaw.setPosition(ClawPos.RIGHT_CLOSE);
       }
    }

    public enum Marcos {
        INTAKEPOS,
        DRIVEUNDERCENTERPOS,
        ROW1AUTOPOS,
        DEFUALTPOS,
        ROW3POS,
        ROW5POS,
        ROW7MAXPOS,
        // Add more values as needed
    }

    public void clawMarcos(Marcos macro) {
        switch(macro) {
            case INTAKEPOS:
            case DRIVEUNDERCENTERPOS:
                clawArm.setTargetPosition(640);
                clawArm.setPower(0.2);
                clawWrist.setPosition(0.3972);
                break;
            case ROW1AUTOPOS:
                clawArm.setTargetPosition(561);
                clawArm.setPower(0.2);
                clawWrist.setPosition(0.26);
                break;
            case ROW3POS:
                clawArm.setTargetPosition(493);
                clawArm.setPower(0.2);
                clawWrist.setPosition(0.327);
                break;
            case ROW5POS:
                clawArm.setTargetPosition(393);
                clawArm.setPower(0.2);
                clawWrist.setPosition(0.445);
                break;
            case ROW7MAXPOS:
                clawArm.setTargetPosition(350);
                clawArm.setPower(0.);
                clawWrist.setPosition(0.52);
                break;
            case DEFUALTPOS:
                clawArm.setTargetPosition(1);
                clawArm.setPower(0.4);
                clawWrist.setPosition(0.39);
                break;



    }
    }






}