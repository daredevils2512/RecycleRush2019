/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class Compressors extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public Compressor compressor;

  @Override
  public void initDefaultCommand() {

    this.compressor = new Compressor();
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void toggleCompressor() {
    if (compressor.getClosedLoopControl()) {
      compressor.stop();
    } else {
      compressor.start();
    }
  }
}
