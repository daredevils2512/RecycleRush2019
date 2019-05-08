package frc.robot.subsystems;

import javax.xml.validation.SchemaFactoryLoader;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANPIDController;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.livewindow.LiveWindowSendable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.commands.ManualLift;
import frc.robot.subsystems.*;

public class LiftPID extends PIDSubsystem {

    public float height[] = {0, -135, -195, -300, -408, -513, -619, -615};
    public float offset = 58;
    public float minSetPoint = 0;

    private DigitalInput liftBottom = new DigitalInput(11);
    private DigitalInput liftTop = new DigitalInput(10);

    private WPI_TalonSRX lift1 = new WPI_TalonSRX(8);
    private WPI_TalonSRX lift2 = new WPI_TalonSRX(2);
    public boolean goingDown;

    private Encoder heightEncoder = new Encoder(8, 9, false);

    public LiftPID() {

        super("LiftPID", 0.0, 0.0, 0.0);
        setAbsoluteTolerance(4.0);
        getPIDController().setContinuous(false);

    }

    @Override
    protected double returnPIDInput() {
        return heightEncoder.pidGet();
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new ManualLift());
    }

    protected void usePIDOutput(double output) {
        if ((!liftBottom.get() || (output < 0 && Robot.m_intake.solenoidGet() == Value.kForward))
         && ((!liftTop.get() || Robot.m_oi.extreme.getRawButton(7)) || output > 0 )) {
            float motorSetting;
            if (output > 0.05) {
                if (output >= 0.1) {
                    motorSetting = (float) output;
                } else {
                    motorSetting = (float) 0.1;
                }
            } else if (output < -0.05) {
                if (output <= -0.1) {
                    motorSetting = (float) output;
                } else {
                    motorSetting = (float) -0.1;
                }
            } else {
                motorSetting = 0;
            }
            lift1.pidWrite(-motorSetting);
            lift2.pidWrite(motorSetting);
        } else {
            lift1.set(0);
            lift2.set(0);
        }
    }

    public void setMotor(float velocity) {
        if (!liftBottom.get() && ((!liftTop.get() || Robot.m_oi.extreme.getRawButton(7)) || velocity < 0)) {
            if (heightEncoder.getRaw() >= -2 && Robot.m_intake.solenoidGet() == Value.kReverse) {
                lift1.set(velocity);
                lift2.set(-velocity);
            } else {
                lift1.set(0);
                lift2.set(0);
            }
        } else {
            if ((velocity > 0 && Robot.m_intake.solenoidGet() == Value.kForward) && ((!liftTop.get() ||
             Robot.m_oi.extreme.getRawButton(7)) || velocity < 0)) {
                 lift1.set(velocity);
                 lift1.set(-velocity);
             } else {
                 lift1.set(0);
                 lift2.set(0);
             }
        }
    }

    public void runLift(double speed) {
        lift1.set(speed);
        lift2.set(speed);
    }

    public PIDController retrivePIDController() {
        return getPIDController();
    }

    public int getHeightEncoder() {
        return this.heightEncoder.getRaw();
    }
}