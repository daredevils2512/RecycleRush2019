package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANPIDController;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.subsystems.*;

public class Lift extends Subsystem {

    float height[] = {0, -135, -195, -300, -408, -513, -619, -615};
    float offset = 58;
    float minSetPoint = 0;

    private DigitalInput liftBottom;
    private DigitalInput liftTop;

    private WPI_TalonSRX lift1;
    private WPI_TalonSRX lift2;

    private Encoder heightEncoder;

    public void Lift() {
        liftBottom = new DigitalInput(11);
        liftTop = new DigitalInput(10);

        lift1 = new WPI_TalonSRX(8);
        lift2 = new WPI_TalonSRX(2);

        heightEncoder = new Encoder(8, 9, false);
        heightEncoder.setDistancePerPulse(1);
        heightEncoder.setPIDSourceType(PIDSourceType.kRate);
    }

    @Override
    protected void initDefaultCommand() {
    }

    public void usePIDOutput(double output) {
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
}