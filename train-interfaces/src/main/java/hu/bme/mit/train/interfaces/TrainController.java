package hu.bme.mit.train.interfaces;

public interface TrainController {

	void followSpeed();

	int getReferenceSpeed();

	void setSpeedLimit(int speedLimit, boolean isLower);

	void setJoystickPosition(int joystickPosition);

}
