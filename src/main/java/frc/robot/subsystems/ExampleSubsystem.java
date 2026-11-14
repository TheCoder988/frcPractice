// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

public class ExampleSubsystem extends SubsystemBase {
  public SparkMax motor1 = new SparkMax(2, MotorType.kBrushless);
  public SparkMax motor2 = new SparkMax(3, MotorType.kBrushless);
  public SparkMax motor3 = new SparkMax(15, MotorType.kBrushless);
  public SparkMax motor4 = new SparkMax(6, MotorType.kBrushless);

  public RelativeEncoder motor1Encoder = motor1.getEncoder();
  public RelativeEncoder motor2Encoder = motor2.getEncoder();
  public RelativeEncoder motor3Encoder = motor3.getEncoder();
  public RelativeEncoder motor4Encoder = motor4.getEncoder();

  public SparkClosedLoopController motor1Controller = motor1.getClosedLoopController();
  public SparkClosedLoopController motor2Controller = motor2.getClosedLoopController();
  public SparkClosedLoopController motor3Controller = motor3.getClosedLoopController();
  public SparkClosedLoopController motor4Controller = motor4.getClosedLoopController();

  double joystickPos;

  /** Creates a new ExampleSubsystem. */
  public ExampleSubsystem(CommandXboxController controller) {
    joystickPos = getJoystickAngle(controller);
  }

  /**
   * Example command factory method.
   *
   * @return a command
   */
  public Command exampleMethodCommand() {
    // Inline construction of command goes here.
    // Subsystem::RunOnce implicitly requires `this` subsystem.
    return runOnce(
        () -> {
          /* one-time action goes here */
        });
  }

    public Command setAngleCommand(CommandXboxController controller) {
    // Inline construction of command goes here.
    // Subsystem::RunOnce implicitly requires `this` subsystem.
    return runOnce(
        () -> {
          if (getJoystickAngle(controller) != joystickPos){
            motor1Controller.setSetpoint(Units.radiansToRotations(getJoystickAngle(controller)) + motor1Encoder.getPosition(),ControlType.kPosition);
            motor2Controller.setSetpoint(Units.radiansToRotations(getJoystickAngle(controller)) + motor2Encoder.getPosition(),ControlType.kPosition);
            motor3Controller.setSetpoint(Units.radiansToRotations(getJoystickAngle(controller)) + motor3Encoder.getPosition(),ControlType.kPosition);
            motor4Controller.setSetpoint(Units.radiansToRotations(getJoystickAngle(controller)) + motor4Encoder.getPosition(),ControlType.kPosition);
            joystickPos = getJoystickAngle(controller);
          }
        });
  }

  public Command zero(){
    return runOnce(
      () -> {
          motor1Controller.setSetpoint(Units.radiansToRotations(0),ControlType.kPosition);
          motor2Controller.setSetpoint(Units.radiansToRotations(0),ControlType.kPosition);
          motor3Controller.setSetpoint(Units.radiansToRotations(0),ControlType.kPosition);
          motor4Controller.setSetpoint(Units.radiansToRotations(0),ControlType.kPosition);
      }
    );
  }

  public double getJoystickAngle(CommandXboxController controller){
    return Math.atan2(controller.getLeftY(), controller.getLeftX());
  }

  /**
   * An example method querying a boolean state of the subsystem (for example, a digital sensor).
   *
   * @return value of some boolean subsystem state, such as a digital sensor.
   */
  public boolean exampleCondition() {
    // Query some boolean state, such as a digital sensor.
    return false;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
