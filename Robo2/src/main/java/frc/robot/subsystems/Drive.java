/* 
package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drive extends SubsystemBase {

    private CANSparkMax leftFrontMotor = new CANSparkMax(1, MotorType.kBrushless);
    private CANSparkMax leftBackMotor = new CANSparkMax(2, MotorType.kBrushless);

    private CANSparkMax rightFrontMotor = new CANSparkMax(3, MotorType.kBrushless);
    private CANSparkMax rightBackMotor = new CANSparkMax(4, MotorType.kBrushless);

    private DifferentialDrive drive = new DifferentialDrive(leftFrontMotor, rightFrontMotor);

    public Drive() {
        leftFrontMotor.restoreFactoryDefaults();
        leftBackMotor.restoreFactoryDefaults();
        rightFrontMotor.restoreFactoryDefaults();
        rightBackMotor.restoreFactoryDefaults();

        leftBackMotor.follow(leftFrontMotor);
        rightBackMotor.follow(rightFrontMotor);
        drive.setSafetyEnabled(false);


    }


}
*/