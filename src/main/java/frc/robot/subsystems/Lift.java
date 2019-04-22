package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANPIDController;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.subsystems.*;

public class Lift extends Subsystem {

    float height[] = {0, -135, -195, -300, -408, -513, -619, -615};
    float offset = 58;
    float minSetPoint = 0;

    private DigitalInput liftBottom;
    private DigitalInput liftTop;

    private TalonSRX lift1;
    private TalonSRX lift2;

    private Encoder heightEncoder;

    public void Lift() {
        liftBottom = new DigitalInput(11);
        liftTop = new DigitalInput(10);

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
            float motorSeting;
            if (output > 0.05) {
                if (output >= 0.1) {
                    motorSeting = (float) output;
                } else {
                    motorSeting = (float) 0.1;
                }
            } else if (output < -0.05) {
                if (output <= -0.1) {
                    motorSeting = (float) output;
                } else {
                    motorSeting = (float) -0.1;
                }
            } else {
                motorSeting = 0;
            }

        }
    }
}