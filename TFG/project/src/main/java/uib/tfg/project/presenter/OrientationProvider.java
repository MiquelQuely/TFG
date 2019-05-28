/**
 * 
 */
package uib.tfg.project.presenter;

import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import java.util.ArrayList;
import java.util.List;
import uib.tfg.project.model.representation.MatrixF4x4;
import uib.tfg.project.model.representation.Quaternion;

public abstract class OrientationProvider implements SensorEventListener {
    private static volatile boolean running = false;
    /**
     * Sync-token for syncing read/write to sensor-data from sensor manager and
     * fusion algorithm
     */
    protected final Object synchronizationToken = new Object();

    /**
     * The list of sensors used by this provider
     */
    protected List<Sensor> sensorList = new ArrayList<Sensor>();

    /**
     * The matrix that holds the current rotation
     */
    protected final MatrixF4x4 currentOrientationRotationMatrix;

    /**
     * The quaternion that holds the current rotation
     */
    protected final Quaternion currentOrientationQuaternion;

    /**
     * The sensor manager for accessing android sensors
     */
    protected SensorManager sensorManager;

    /**
     * Initialises a new OrientationProvider
     * 
     * @param sensorManager
     *            The android sensor manager
     */
    public OrientationProvider(SensorManager sensorManager) {
        this.sensorManager = sensorManager;

        // Initialise with identity
        currentOrientationRotationMatrix = new MatrixF4x4();

        // Initialise with identity
        currentOrientationQuaternion = new Quaternion();
    }

    /**
     * Starts the sensor fusion (e.g. when resuming the activity)
     */
    public void start() {
        running = true;
        // enable our sensor when the activity is resumed, ask for
        // 10 ms updates.
        for (Sensor sensor : sensorList) {
            // enable our sensors when the activity is resumed, ask for
            // 20 ms updates (Sensor_delay_game)
            sensorManager.registerListener(this, sensor,
                    SensorManager.SENSOR_DELAY_GAME);
        }
    }

    /**
     * Stops the sensor fusion (e.g. when pausing/suspending the activity)
     */
    public void stop() {
        // make sure to turn our sensors off when the activity is paused
        for (Sensor sensor : sensorList) {
            sensorManager.unregisterListener(this, sensor);
        }
        running = false;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Not doing anything
    }

    /**
     * Get the current rotation of the device in the rotation matrix format (4x4 matrix)
     */
    public void getRotationMatrix(MatrixF4x4 matrix) {
        synchronized (synchronizationToken) {
            matrix.set(currentOrientationRotationMatrix);
        }
    }

    /**
     * Get the current rotation of the device in the quaternion format (vector4f)
     */
    public void getQuaternion(Quaternion quaternion) {
        synchronized (synchronizationToken) {
            quaternion.set(currentOrientationQuaternion);
        }
    }

    /**
     * Get the current rotation of the device in the Euler angles
     */
    public void getEulerAngles(float angles[]) {
        synchronized (synchronizationToken) {
            SensorManager.getOrientation(currentOrientationRotationMatrix.matrix, angles);
        }
    }

    public boolean isRunning(){
        return running;
    }


}
