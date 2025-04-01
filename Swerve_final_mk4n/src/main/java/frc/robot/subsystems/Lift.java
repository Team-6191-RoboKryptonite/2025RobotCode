package frc.robot.subsystems;


import static edu.wpi.first.units.Units.Meters;

import org.ejml.sparse.csc.linsol.qr.LinearSolverQrLeftLooking_DSCC;

import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.CANrange;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.hardware.core.CoreCANrange;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.units.Measure;


public class Lift extends SubsystemBase {
    private TalonFX leftMotor1 = new TalonFX(8);
    private TalonFX rightMotor1 = new TalonFX(9);

    // private TalonFXConfiguration config = new TalonFXConfiguration();

    PIDController pid = new PIDController(0.5, 0.03, 0.01);
    public Lift() {
    leftMotor1.setNeutralMode(NeutralModeValue.Brake);
    rightMotor1.setNeutralMode(NeutralModeValue.Brake);
    leftMotor1.setPosition(0);
    rightMotor1.setPosition(0);
    // config.CurrentLimits.StatorCurrentLimit = 60.0;
    // config.CurrentLimits.StatorCurrentLimitEnable = true;

    // leftMotor1.getConfigurator().apply(config);
    // rightMotor1.getConfigurator().apply(config);
    }


    @Override
    public void periodic() {
        SmartDashboard.putNumber("encoder", getEncoder());
    }
    public double getEncoder(){
        return Math.abs(leftMotor1.getPosition().getValueAsDouble() / 10);
    }
    public double getSpeed(double point, double setpoint){
        double speed = pid.calculate(point, setpoint);
        return speed;
    }
    public void setSpeed(double speed) {
        leftMotor1.set(-speed * 0.3);
        rightMotor1.set(speed * 0.3);
        SmartDashboard.putNumber("speed",speed);
    }
    public double getSupplyCurrent() {
        double leftCurrent = leftMotor1.getSupplyCurrent().getValueAsDouble();
        // double rightCurrent = rightMotor.getSupplyCurrent().getValueAsDouble();
        return Math.abs(leftCurrent);
    }
    // private boolean getMiddle1Sensor(){
    //     return topSensor.get();
    // }
}

