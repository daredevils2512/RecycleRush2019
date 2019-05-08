/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ToteScoot extends Command {

  double intake;
  double turn;
  double m_speed;

  public ToteScoot(double speed) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    m_speed = speed;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    intake = (Robot.m_oi.xTremeX() * m_speed);
    turn = (Robot.m_oi.xTremeZ() * m_speed);

    if (Math.abs(intake) > 0.3) {
      Robot.m_intake.intake(intake, intake);
    } else if (Math.abs(turn) > 0.3) {
      Robot.m_intake.intake(-turn, turn);
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
