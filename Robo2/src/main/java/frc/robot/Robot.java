// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.CAN;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the
 * name of this class or
 * the package after creating this project, you must also update the
 * build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private Command m_autonomousCommand;

  private RobotContainer m_robotContainer;

  private XboxController controller;

  private CANSparkMax leftMotor;
  private CANSparkMax rightMotor;
  private CANSparkMax leftBackMotor;
  private CANSparkMax rightBackMotor;

  private CANSparkMax shooter1;
  private CANSparkMax shooter2;

  @Override
  public void robotInit() {
    // Instantiate our RobotContainer. This will perform all our button bindings,
    // and put our
    // autonomous chooser on the dashboard.
    m_robotContainer = new RobotContainer();

    leftMotor = new CANSparkMax(1, MotorType.kBrushless);
    rightMotor = new CANSparkMax(3, MotorType.kBrushless);
    leftBackMotor = new CANSparkMax(2, MotorType.kBrushless);
    rightBackMotor = new CANSparkMax(4, MotorType.kBrushless);

    shooter1 = new CANSparkMax(10, MotorType.kBrushless);
    shooter2 = new CANSparkMax(11, MotorType.kBrushless);

    controller = new XboxController(0);

    leftMotor.restoreFactoryDefaults();
    rightMotor.restoreFactoryDefaults();
    leftBackMotor.restoreFactoryDefaults();
    rightBackMotor.restoreFactoryDefaults();

    leftMotor.setInverted(false);
    rightMotor.setInverted(true);
    leftBackMotor.setInverted(false);
    rightBackMotor.setInverted(true);

    shooter2.setInverted(true);

  }

  /**
   * This function is called every 20 ms, no matter the mode. Use this for items
   * like diagnostics
   * that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>
   * This runs after the mode specific periodic functions, but before LiveWindow
   * and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler. This is responsible for polling buttons, adding
    // newly-scheduled
    // commands, running already-scheduled commands, removing finished or
    // interrupted commands,
    // and running subsystem periodic() methods. This must be called from the
    // robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
  }

  /** This function is called once each time the robot enters Disabled mode. */
  @Override
  public void disabledInit() {
    // Defining speed 0 when it is disbled
    leftMotor.set(0);
    rightMotor.set(0);
    leftBackMotor.set(0);
    rightBackMotor.set(0);
  }

  @Override
  public void disabledPeriodic() {
  }

  /**
   * This autonomous runs the autonomous command selected by your
   * {@link RobotContainer} class.
   */
  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.



    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {

    // forward e turnCar -> Robot movement

    double forward = controller.getLeftY();
    double back = controller.getLeftTriggerAxis();
    double turnCar = controller.getRightX();

    // Turn -> Robot shooter
    double turn = controller.getButtonCount();

    // Defining the speed when it is on
    leftMotor.set(forward + turnCar);
    rightMotor.set(forward - turnCar);
    leftBackMotor.set(forward + turnCar);
    rightBackMotor.set(forward - turnCar );

    // if (forward > 0 && forward < 0.5) {
    //   leftMotor.set(forward  + turnCar);
    //   rightMotor.set(forward  - turnCar);
    //   leftBackMotor.set(forward  + turnCar);
    //   rightBackMotor.set(forward - turnCar);
    // } else if (back > 0) {
    //   leftMotor.set(-back + turnCar);
    //   rightMotor.set(-back - turnCar);
    //   leftBackMotor.set(-back + turnCar);
    //   rightBackMotor.set(-back - turnCar);
    // } else {
    //   leftMotor.disable();
    //   rightMotor.disable();
    //   leftBackMotor.disable();
    //   rightBackMotor.disable();
    // }

    // if (forward > 0) {

    //   leftMotor.setInverted(false);
    //   rightMotor.setInverted(true);
    //   leftBackMotor.setInverted(false);
    //   rightBackMotor.setInverted(true);

    //   leftMotor.set(forward);
    //   rightMotor.set(forward);
    //   leftBackMotor.set(forward);
    //   rightBackMotor.set(forward);
    // }
    // else if(forward == 0){
    //   leftMotor.disable();
    //   rightMotor.disable();
    //   leftBackMotor.disable();
    //   rightBackMotor.disable();
    // }
    
    
    // if (back > 0) {

    //   leftMotor.setInverted(true);
    //   rightMotor.setInverted(false);
    //   leftBackMotor.setInverted(true);
    //   rightBackMotor.setInverted(false);

    //   leftMotor.set(back);
    //   rightMotor.set(back);
    //   leftBackMotor.set(back);
    //   rightBackMotor.set(back);

    // }
    // else if(back == 0){
    //   leftMotor.disable();
    //   rightMotor.disable();
    //   leftBackMotor.disable();
    //   rightBackMotor.disable();
    // }


    // / Button A pressed > shooter motor turns on
    // if (controller.getAButtonPressed()) {
    // shooter1.set(turn);
    // shooter2.set(turn);
    // } else if (controller.getAButtonReleased()) {
    // shooter1.disable();
    // shooter2.disable();
    // }

    // if (controller.getBButtonPressed()) {
    // shooter1.set(0.3);
    // shooter2.set(0.3);
    // } else if (controller.getBButtonReleased()) {
    // shooter1.disable();
    // shooter2.disable();
    // }

  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {
  }

  /** This function is called once when the robot is first started up. */
  @Override
  public void simulationInit() {
  }

  /** This function is called periodically whilst in simulation. */
  @Override
  public void simulationPeriodic() {
  }
}
