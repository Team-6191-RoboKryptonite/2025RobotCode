package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Climber;

public class ClimberCmd extends Command {
    private final Climber climber;
    private final double setpoint;

    public ClimberCmd(Climber climber, double setpoint) {
        this.climber = climber;
        this.setpoint = setpoint;
        addRequirements(climber);
    }

    @Override
    public void initialize() {
        double point = climber.getEncoder();
        while (Math.abs(point - setpoint) > 0.5) {
            point = climber.getEncoder();
            double speed = climber.getSpeed(point, setpoint);
            climber.setClimberSpeed(speed);}
        end(isFinished());
    }

    @Override
    public void execute() {
    }
    
    @Override
    public void end(boolean interrupted) {
        climber.setClimberSpeed(0);
    }
    
    @Override
    public boolean isFinished() {
        return true;
    }
}
