/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import frc.robot.RobotMap;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class Drivetrain extends Subsystem {
  private WPI_TalonSRX drivetrainSpeedController1;
  private WPI_TalonSRX drivetrainSpeedController2;
  private WPI_TalonSRX drivetrainSpeedController3;
  private WPI_TalonSRX drivetrainSpeedController4;
  private SpeedControllerGroup leftTalonGroup;
  private SpeedControllerGroup rightTalonGroup;
  private MecanumDrive drivetrain;

  public Drivetrain() {
    drivetrainSpeedController1 = new WPI_TalonSRX(RobotMap.leftTalonID);
    drivetrainSpeedController2 = new WPI_TalonSRX(RobotMap.leftRearTalonID);
    drivetrainSpeedController3 = new WPI_TalonSRX(RobotMap.rightTalonID);
    drivetrainSpeedController4 = new WPI_TalonSRX(RobotMap.rightRearTalonID);

    drivetrain = new MecanumDrive(drivetrainSpeedController2, drivetrainSpeedController1,
     drivetrainSpeedController3, drivetrainSpeedController4);

    drivetrainSpeedController1.setInverted(false);
    drivetrainSpeedController2.setInverted(false);
    drivetrainSpeedController3.setInverted(true);
    drivetrainSpeedController4.setInverted(true);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new frc.robot.commands.MecanumDrive());
  }

  public void drive(double ySpeed, double xSpeed, double zRotation) {
    drivetrain.driveCartesian(ySpeed, xSpeed, zRotation);
  }
}
