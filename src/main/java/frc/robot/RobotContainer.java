// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import static edu.wpi.first.units.Units.Rotations;
import static edu.wpi.first.units.Units.RotationsPerSecond;

import java.util.ArrayDeque;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.feeder;
import frc.robot.subsystems.flywheel;
import frc.robot.subsystems.movingfloor;
import frc.robot.subsystems.turret;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class RobotContainer {
  public CommandXboxController controller = new CommandXboxController(0);
  private final movingfloor movingfloor = new movingfloor();
  private final flywheel flywheel = new flywheel();
  private final turret turret = new turret();
  private final feeder feeder = new feeder();
  private final CameraControl cameraControl = new CameraControl(turret);

  public RobotContainer() {
    SmartDashboard.putNumber("turretrotations", 0);
    configureBindings();

  }

  private void configureBindings() {
    movingfloor.setDefaultCommand(movingfloor.disableCommand());
    flywheel.setDefaultCommand(flywheel.setVelocityCommand(RotationsPerSecond.of(0)));
    feeder.setDefaultCommand(feeder.disableCommand());
    turret.setDefaultCommand(cameraControl.followTag());
    controller.rightTrigger().toggleOnTrue(flywheel.setVelocityCommand(RotationsPerSecond.of(0)));
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
