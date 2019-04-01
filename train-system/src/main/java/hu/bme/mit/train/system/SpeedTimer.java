package hu.bme.mit.train.system;

import hu.bme.mit.train.interfaces.TrainController;

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class SpeedTimer {

    private TrainSystem system = new TrainSystem();

    public static void main(String[] args){
        SpeedTimer speedTimer = new SpeedTimer();
        Timer timer = new Timer();
        speedTimer.system.getController().setSpeedLimit(5);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Scanner sc = new Scanner(System.in);
                int i = sc.nextInt();
                speedTimer.system.getUser().overrideJoystickPosition(i);

                speedTimer.system.getController().followSpeed();
                System.out.println(speedTimer.system.getController().getReferenceSpeed());
            }
        }, 0,1000);
    }
}
