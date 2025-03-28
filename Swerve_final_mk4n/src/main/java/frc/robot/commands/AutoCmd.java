package frc.robot.commands;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Intake_shooter;
import frc.robot.subsystems.Lift;

public class AutoCmd extends Command {
    private final Lift lift;
    private final Intake_shooter intake_shooter;
    private final double height;

    public AutoCmd(Lift lift, Intake_shooter intake_shooter, double height) {
        this.intake_shooter = intake_shooter;
        this.lift = lift;
        this.height = height;
        addRequirements(intake_shooter);
        addRequirements(lift);
    }

    @Override
    public void initialize() {
        // lift up
        while (lift.getSpeed(lift.getEncoder(), height) > 0.05) {
            lift.setSpeed(0.8);
        }
        lift.setSpeed(0);

        // shoot
        double pretime = Timer.getFPGATimestamp();
        double nowtime = Timer.getFPGATimestamp();
        while (nowtime - pretime <= 1) {
            lift.setSpeed(0);
            intake_shooter.setSpeed(-0.3);
            nowtime = Timer.getFPGATimestamp();
        }
        intake_shooter.setSpeed(0);

        // lift down
        while (lift.getSpeed(lift.getEncoder(), 0.01) > 0.01) {
            lift.setSpeed(0.8);
        }
        lift.setSpeed(0);

        end(isFinished());
    }

    @Override
    public void execute() {

    }
    
    @Override
    public void end(boolean interrupted) {
        intake_shooter.setSpeed(0);
        lift.setSpeed(0);
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    // robotContainer:
    //      private final AutoCmd autoCmd = new AutoCmd(mlift, intake_shooter, 1.2);
    //      private final Coral_intake_cmd coral_intake_cmd = new Coral_intake_cmd(intake_shooter, 0.3);

    //      NamedCommands.registerCommand("Auto1", autoCmd);
    //      NamedCommands.registerCommand("intake", coral_intake_cmd);
}
