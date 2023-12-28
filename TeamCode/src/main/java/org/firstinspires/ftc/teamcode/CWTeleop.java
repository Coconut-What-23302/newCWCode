package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.helperClasses.RobotHardware;

@TeleOp
public class CWTeleop extends LinearOpMode {
    RobotHardware robot = new RobotHardware();

    @Override



    // toggle vars

    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap);

        boolean ArmCheck = false;
        boolean boardServoCheck = false;
        boolean intakeCheck = false;
        boolean manualOverride = false;
        boolean clawMoveCheck = false;
        int armPoisition = 0;
        boolean ArmCheck2 = true;
        boolean clawCheck2 = false;
        boolean clawCheck = false;
        boolean leftClawCheck = false;
        boolean leftClawCheck2 = false;
        boolean rightClawCheck = false;
        boolean rightClawCheck2 = false;
        boolean macroCheck = false;
        boolean macroCheck2 = false;
        boolean macro1Check = false;
        boolean macro1Check2 =false;

        // Initialize claw arm position

        waitForStart();

        if (isStopRequested()) return;

//        robot.clawArm.setPower(.5);

        while (opModeIsActive()) {






        //driving


            // drive inputs
            double y = -gamepad1.left_stick_y;
            double x = gamepad1.left_stick_x * 1.1;
            double rx = gamepad1.right_stick_x;
            // drive input end





           // mechanum drive code
            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
            double frontLeftPower = (y + x + rx) / denominator;
            double backLeftPower = (y - x + rx) / denominator;
            double frontRightPower = (y - x - rx) / denominator;
            double backRightPower = (y + x - rx) / denominator;
            // mechanum drive code end



            double hangPivotPower = gamepad2.left_stick_y * 0.5;

            // power constant
            robot.frontLeft.setPower(frontLeftPower * .75);
            robot.backLeft.setPower(backLeftPower * .75);
            robot.frontRight.setPower(frontRightPower * .75);
            robot.backRight.setPower(backRightPower * .75);


            robot.hangPivotMotor.setPower(hangPivotPower);


            int armPosition = 0;
//            if (gamepad1.left_bumper || gamepad1.right_bumper) {
//                if (gamepad1.left_bumper) {
//                    armPosition += 1;
//                } else if (gamepad1.right_bumper) {
//                    armPosition -= 1;
//                }
//
//                robot.clawArm.setTargetPosition(armPosition);
//            }

//            if (gamepad1.left_stick_button && gamepad1.right_stick_button){
//                robot.clawArm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//                armPoisition = 0;
//                robot.clawArm.setTargetPosition(0);
//                robot.clawArm.setPower(0.5);
//                robot.clawArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//            }


            /** Claw Code
             * 1. Press X to toggle claw
             * 2. Press Dpad Left to toggle left claw
             * 3. Press Dpad Right to toggle right claw
             */
            if (gamepad1.x && !clawCheck) {
                clawCheck = true;
            }

            if (!gamepad1.x && clawCheck) {
                clawCheck = false;
                clawCheck2 = !clawCheck2;

                robot.clawPosBoth(clawCheck2);
            }


            if (gamepad1.left_bumper && !leftClawCheck) {
                leftClawCheck = true;
            }

            if (!gamepad1.left_bumper && leftClawCheck) {
                leftClawCheck = false;
                leftClawCheck2 = !leftClawCheck2;

                robot.clawPosSingle(true, leftClawCheck2);
            }

            if(gamepad1.right_bumper && !rightClawCheck) {
                rightClawCheck = true;
            }

            if(!gamepad1.right_bumper && rightClawCheck) {
                rightClawCheck = false;
                rightClawCheck2 = !rightClawCheck2;

                robot.clawPosSingle(false, rightClawCheck2);
            }

// claw code end here





            if (gamepad2.dpad_up || gamepad1.dpad_up) {
                robot.hangLeadScrewMotor.setPower(1);
            } else if (gamepad2.dpad_down || gamepad1.dpad_down) {
                robot.hangLeadScrewMotor.setPower(-1);
            } else {
                robot.hangLeadScrewMotor.setPower(0);
            }

            if(gamepad1.left_trigger > .5) {
                robot.clawMarcos(RobotHardware.Marcos.ROW7MAXPOS);
                robot.clawArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            }

            if(gamepad1.a) {
                robot.clawMarcos(RobotHardware.Marcos.INTAKEPOS);
                robot.clawArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            }

            if(gamepad1.right_trigger > .5) {
                robot.clawMarcos(RobotHardware.Marcos.ROW1AUTOPOS);
                robot.clawArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            }

            if(gamepad1.b) {
                robot.clawMarcos(RobotHardware.Marcos.ROW3POS);
                robot.clawArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            }
            if(gamepad1.y) {
                robot.clawMarcos(RobotHardware.Marcos.ROW5POS);
                robot.clawArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            }



//            if (gamepad1.y && !macroCheck) {
//                macroCheck = true;
//            }
//
//            if (!gamepad1.y && macroCheck) {
//                macroCheck = false;
//                macroCheck2 = !macroCheck2;
//
//                if(macroCheck2) {
//                    robot.clawMarcos(RobotHardware.Marcos.FIRSTROWORAUTOPOS);
//                    robot.clawArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//                } else {
//                    robot.clawMarcos(RobotHardware.Marcos.INTAKEPOS);
//                    robot.clawArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//                }
//            }
//
//            if (gamepad1.a && !macro1Check) {
//                macro1Check = true;
//            }
//
//            if (!gamepad1.a && macro1Check) {
//                macro1Check = false;
//                macro1Check2 = !macro1Check2;
//
//                if(macro1Check2) {
//                    robot.clawMarcos(RobotHardware.Marcos.MAXPOS);
//                    robot.clawArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//                } else {
//                    robot.clawMarcos(RobotHardware.Marcos.ALITTLEHIGHER);
//                    robot.clawArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//                }
//            }




//            // doc manuel control of wrist
//            if(gamepad1.right_trigger > .5) {
//                robot.clawWrist.setPosition(robot.clawWrist.getPosition() + .01);
//            }
//
//            if(gamepad1.left_trigger > .5) {
//                robot.clawWrist.setPosition(robot.clawWrist.getPosition() - .01);
//            }


            telemetry.addData("Arm Position", robot.clawArm.getCurrentPosition());
            telemetry.addData("wrist position", robot.clawWrist.getPosition());
            telemetry.addData("hang pivot position", robot.hangPivotMotor.getCurrentPosition());
            telemetry.update();


            }
        }
    }


