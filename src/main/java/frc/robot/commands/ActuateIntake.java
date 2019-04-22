package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ActuateIntake extends Command {
    public enum intakePos {
        open,
        close
    }

    private intakePos m_intakePosition;

    public ActuateIntake(intakePos intakePosition) {
        m_intakePosition = intakePosition;
    }

    @Override
    protected void execute() {
        if(m_intakePosition == intakePos.open) {
            Robot.m_intake.intakeOpen();
        } else {
            Robot.m_intake.intakeClose();
        }
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}