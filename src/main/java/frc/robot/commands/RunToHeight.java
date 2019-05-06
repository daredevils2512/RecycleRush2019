package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class RunToHeight extends Command {

    private float m_targetHeight;

    public void RunToHeight(float targetHeight) {
        requires(Robot.m_liftPID);
        this.m_targetHeight = targetHeight;
        Robot.m_liftPID.goingDown = false;
        // TODO: might not be ok to use this everywhere

    }

    @Override
    protected void initialize() {
        if (m_targetHeight <= 0) {
            Robot.m_liftPID.enable();
            Robot.m_liftPID.setSetpoint(m_targetHeight);
        }
        if (m_targetHeight > Robot.m_liftPID.getHeightEncoder()) {
            Robot.m_liftPID.goingDown = true;
            Robot.m_liftPID.retrivePIDController().setPID(0.025, 0.0, 0.0);
        } else {
            Robot.m_liftPID.retrivePIDController().setPID(0.02, 0.0005, 0.0);
        }
    }

    @Override
    protected void execute() {
        System.out.println("AAAAAAAAAHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH");
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