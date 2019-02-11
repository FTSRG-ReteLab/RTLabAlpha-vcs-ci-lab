package hu.bme.mit.train.system;

import hu.bme.mit.train.controller.Tachograph;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainSensor;
import hu.bme.mit.train.interfaces.TrainUser;
import hu.bme.mit.train.system.TrainSystem;

public class TrainSystemTest {

	TrainController controller;
	TrainSensor sensor;
	TrainUser user;
	Tachograph table;

	@Before
	public void before() {
		TrainSystem system = new TrainSystem();
		controller = system.getController();
		sensor = system.getSensor();
		user = system.getUser();
		table = new Tachograph(controller,user);

		sensor.overrideSpeedLimit(50);
	}
	
	@Test
	public void OverridingJoystickPosition_IncreasesReferenceSpeed() {
		sensor.overrideSpeedLimit(10);

		Assert.assertEquals(0, controller.getReferenceSpeed());
		
		user.overrideJoystickPosition(5);

		controller.followSpeed();
		Assert.assertEquals(5, controller.getReferenceSpeed());
		controller.followSpeed();
		Assert.assertEquals(10, controller.getReferenceSpeed());
		controller.followSpeed();
		Assert.assertEquals(10, controller.getReferenceSpeed());
	}

	@Test
	public void OverridingJoystickPositionToNegative_SetsReferenceSpeedToZero() {
		user.overrideJoystickPosition(4);
		controller.followSpeed();
		user.overrideJoystickPosition(-5);
		controller.followSpeed();
		Assert.assertEquals(0, controller.getReferenceSpeed());
	}

	@Test
	public void ContinousAccelerationTest(){
		sensor.overrideSpeedLimit(8);
		user.overrideJoystickPosition(3);
		controller.followSpeed();
		Assert.assertEquals(3, controller.getReferenceSpeed());
		user.overrideJoystickPosition(6);
		controller.followSpeed();
		Assert.assertEquals(8, controller.getReferenceSpeed());
	}

	@Test
	public void TachoTest(){

		user.overrideJoystickPosition(5);
		controller.followSpeed();
		table.step();
		Assert.assertEquals(5,table.getSpeed(0));
		Assert.assertEquals(5,table.getPosition(0));


	}

}
