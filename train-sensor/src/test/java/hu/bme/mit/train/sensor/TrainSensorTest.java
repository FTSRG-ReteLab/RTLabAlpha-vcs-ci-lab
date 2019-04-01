package hu.bme.mit.train.sensor;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainSensor;
import hu.bme.mit.train.interfaces.TrainUser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class TrainSensorTest {

    TrainController mockTC;
    TrainUser mockTU;
    TrainSensor ts;


    @Before public void init() {
        // creating a mock for the TrainController class
        mockTC = mock(TrainController.class);
        // creating a mock for the TrainUser class
        mockTU = mock(TrainUser.class);
        // injecting the mock into the unit under test
        ts = new TrainSensorImpl(mockTC, mockTU);
    }

    @Test public void absoluteAlarmTest() {
        //Arrange
        when(mockTU.getAlarmState()).thenReturn(false).thenReturn(true).thenReturn(true);

        //Act
        ts.overrideSpeedLimit(50);
        ts.overrideSpeedLimit(501);
        ts.overrideSpeedLimit(-1);

        //Assert
        verify(mockTU, times(2)).setAlarmState(true);
        verify(mockTU, times(1)).setAlarmState(false);
        verify(mockTC, times(1)).setSpeedLimit(50);
    }

    @Test public void relativeAlarmTest() {
        //Arrange
        when(mockTU.getAlarmState()).thenReturn(true).thenReturn(false);
        when(mockTC.getReferenceSpeed()).thenReturn(400).thenReturn(200).thenReturn(90);

        //Act
        ts.overrideSpeedLimit(400);
        ts.overrideSpeedLimit(90);
        ts.overrideSpeedLimit(40);

        //Assert
        verify(mockTU, times(1)).setAlarmState(false);
        verify(mockTU, times(2)).setAlarmState(true);

    }
}