package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ToggleIntake extends Command {

    @Override
    protected void execute() {
        Robot.m_intake.toggleIntake();
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}