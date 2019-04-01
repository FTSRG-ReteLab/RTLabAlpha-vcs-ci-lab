package hu.bme.mit.train.system;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Environment extends JFrame{

    private TrainSystem trainSystem = new TrainSystem();

    public static void main(String[] args){
        Environment env = new Environment();
        Timer time = new Timer(1000, env::stepTime);

        //User thread
        JSlider joystick = new JSlider(-10,10,0);
        env.add(joystick);

        //Train system thread
        new Thread(() -> {
            while(true){
                if(!time.isRunning())
                    time.start();

                env.setJoystick(joystick.getValue());
            }
        }).start();


        System.out.println("START");
        env.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        env.pack();
        env.setVisible(true);

    }

    Environment(){
        trainSystem.getController().setSpeedLimit(20);
    }

    public void stepTime(ActionEvent e){
        System.out.println("--------------TIME STEP--------------");
        trainSystem.getController().followSpeed();
        System.out.println("Joystick position is: " + trainSystem.getUser().getJoystickPosition());
        System.out.println("Reference Speed is: " + trainSystem.getController().getReferenceSpeed());
    }

    public void setJoystick(int pos){
        trainSystem.getUser().overrideJoystickPosition(pos);
    }

}
