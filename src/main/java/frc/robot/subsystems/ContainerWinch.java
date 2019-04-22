package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ContainerWinch extends Subsystem {

    private Victor containerWinchContainerPull1;
    private Victor containerWinchContainerPull2;

    public void ContainerWinch() {

        containerWinchContainerPull1 = new Victor(2);
        containerWinchContainerPull2 = new Victor(3);

    }

    @Override
    public void initDefaultCommand() {
        // setDefaultCommand();
    }

    public void setWinch(int winchNumber, float output) {
        switch (winchNumber) {
            case 1:
                containerWinchContainerPull1.set(output);
                break;
            case 2:
                containerWinchContainerPull2.set(output);
                break;
            default:
                containerWinchContainerPull1.set(0);
                containerWinchContainerPull2.set(0);
                break;
        }
    }

}