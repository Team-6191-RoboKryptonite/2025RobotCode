package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Lift;
import edu.wpi.first.wpilibj.Joystick;

public class LiftTeleopCmd extends Command {
    private final Lift lift;
    private final double speed; 
    private final Joystick joystick = new Joystick(0); 

    
    public LiftTeleopCmd(Lift lift, double speed) {
        this.lift = lift;
        this.speed = speed;
        addRequirements(lift);
    }

    @Override
    public void initialize() {}

    @Override
    public void execute() {
        if(joystick.getRawButton(1)){
            lift.setSpeed(speed);
        }
        else if(joystick.getRawButton(4)){
            lift.setSpeed(-speed);
        }
        else{
            lift.setSpeed(0);
        }
    }
    
    @Override
    public void end(boolean interrupted) {}
    
    @Override
    public boolean isFinished() {
        return false;
    }
}
