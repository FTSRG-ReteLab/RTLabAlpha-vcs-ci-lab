package hu.bme.mit.train.controller;

import com.google.common.collect.ArrayTable;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainUser;

public class Tachograph {

    private final TrainUser user;
    private Table<Integer,String,Integer> tachoTable;

    private int time = 0;
    private final TrainController controller;

    public Tachograph(TrainController controller, TrainUser user){

        tachoTable = HashBasedTable.create();
        this.controller = controller;
        this.user = user;

    }

    public void step(){
        tachoTable.put(time,"Speed",controller.getReferenceSpeed());
        tachoTable.put(time,"Position",user.getJoystickPosition());
        time++;
    }

    public int getSpeed(int time){
        return tachoTable.get(time,"Speed");
    }

    public int getPosition(int time){
        return tachoTable.get(time,"Position");
    }

}
