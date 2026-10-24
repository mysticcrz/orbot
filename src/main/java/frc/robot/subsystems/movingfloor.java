package frc.robot.subsystems;

import static edu.wpi.first.units.Units.RotationsPerSecond;

import com.ctre.phoenix6.controls.VelocityTorqueCurrentFOC;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class movingfloor extends SubsystemBase{
    private TalonFX motor = new TalonFX(0);

  
    private VelocityTorqueCurrentFOC velocityrequest = new VelocityTorqueCurrentFOC(0);

    public void setVelocity(AngularVelocity velocity){
        motor.setControl(velocityrequest.withVelocity(velocity)); }
    
    public Command EnabledCommand(AngularVelocity velocity){
        return runOnce(() -> this.setVelocity(velocity));

        }

        public Command disableCommand() {
            return runOnce(() ->this.setVelocity(RotationsPerSecond.of(0)));
        }
}
