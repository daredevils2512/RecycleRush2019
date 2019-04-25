package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class Intake extends Subsystem {

    private Victor leftIntake;
    private Victor rightIntake;
    private DoubleSolenoid solenoid;
    private boolean cooperation = false;

    public Intake() {
        leftIntake = new Victor(RobotMap.leftIntakeID);
        rightIntake = new Victor(RobotMap.rightIntakeID);
        solenoid = new DoubleSolenoid(0, 1);
    }

    @Override
    protected void initDefaultCommand() {
        
    }

    public void intake(double intakeSpeed) {
        leftIntake.set(intakeSpeed);
        rightIntake.set(intakeSpeed);
    }

    public boolean getCooperation() {
        return cooperation;
    }

    public DoubleSolenoid.Value solenoidGet() {
        return this.solenoid.get();
    }

    public void intakeOpen() {
        solenoid.set(Value.kForward);
    }

    public void intakeClose() {
        solenoid.set(Value.kReverse);
    }

    public void toggleIntake() {
        if(solenoid.get() == Value.kForward) {
            intakeClose();
        } else {
            intakeOpen();
        }
    }

    public void setCooperation(Boolean coop) {
        cooperation = coop;
    }
}