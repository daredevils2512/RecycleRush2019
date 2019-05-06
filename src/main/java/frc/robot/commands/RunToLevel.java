package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.commands.*;

public class RunToLevel extends Command {
    
    private int m_level;
    // private boolean goingDown;
    public RunToLevel(int level) {

        requires(Robot.m_liftPID);
        this.m_level = level;
        Robot.m_liftPID.goingDown = false;
    }

    @Override
    protected void initialize() {
        Robot.m_liftPID.enable();
        if (m_level >= 0 && m_level < 8) {
            Robot.m_liftPID.setSetpoint(Robot.m_liftPID.height[m_level]);
            if (m_level == 1) {
                Robot.m_intake.setCooperation(true);
            }
        } else {
            Robot.m_liftPID.disable();
        }
        if (Robot.m_liftPID.height[m_level] > Robot.m_liftPID.getHeightEncoder()) {
            Robot.m_liftPID.goingDown = true;
            Robot.m_liftPID.retrivePIDController().setPID(0.025, 0.0, 0.0);
        } else {
            Robot.m_liftPID.retrivePIDController().setPID(0.02, 0.0005, 0.0);
        }
    }
    
    @Override
    protected boolean isFinished() {
        return Robot.m_liftPID.onTarget();
    }

    @Override
    protected void end() {
        Robot.m_liftPID.disable();
        Robot.m_liftPID.setMotor(0);
    }

    @Override
    protected void interrupted() {
        Robot.m_liftPID.disable();
        Robot.m_liftPID.setMotor(0);
    }

}