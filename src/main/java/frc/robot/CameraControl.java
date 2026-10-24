package frc.robot;

import static edu.wpi.first.units.Units.Degrees;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.turret;

public class CameraControl {
    turret turret;
    NetworkTableEntry tx;
    public CameraControl(turret turret){
        this.turret = turret;
        tx = NetworkTableInstance.getDefault().getTable("limelight-dev").getEntry("tx");

    }
    
    public Command followTag(){
        return turret.setPositionCommand(() -> turret.getPosition().plus(Degrees.of(tx.getDouble(0))));
    }
}
