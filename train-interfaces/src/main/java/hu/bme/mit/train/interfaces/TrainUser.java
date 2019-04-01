package hu.bme.mit.train.interfaces;

public interface TrainUser {

	int getJoystickPosition();
	void overrideJoystickPosition(int joystickPosition);
	boolean getAlarmState();
	void setAlarmState(boolean alarmState);

}
