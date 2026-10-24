package frc.robot.subsystems;

import static edu.wpi.first.units.Units.Rotations;

import java.util.function.Supplier;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.PositionTorqueCurrentFOC;
import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class turret extends SubsystemBase {
    private TalonFX motor = new TalonFX(5);
    private Angle minposition = Rotations.of(-1);
    private Angle maxposition = Rotations.of(1);

    private PositionTorqueCurrentFOC positionrequest = new PositionTorqueCurrentFOC(0);

    public turret(){

        TalonFXConfiguration config = new TalonFXConfiguration();
        config.Slot0.kP = 2;
        config.Slot0.kD = 0.3;
        motor.getConfigurator().apply(config);
    }

    public void setPosition(Angle angle){
        while(angle.lt(minposition)){
            angle = angle.plus(Rotations.of(1));
        }
        motor.setControl(positionrequest.withPosition(angle.times(312.5))); 
    
    }


    public Command setPositionCommand(Angle angle) {
        return runOnce(() -> this.setPosition(angle));

    }

    public Command setPositionCommand(Supplier < Angle > angleSupplier ){
        return run(()->setPosition(angleSupplier.get() ));
    }
 
    public Angle getPosition(){
        return motor.getPosition().getValue().div(312.5);
    }
}
