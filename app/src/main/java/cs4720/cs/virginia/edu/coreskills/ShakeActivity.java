package cs4720.cs.virginia.edu.coreskills;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

/**

 Assignment Notes: For this activity, every time you shake the device, the counter
 on the screen should go up.  Note that there is no specific "shake listener,"
 unless you build your own.  I chose to add a SensorEventListener to this Activity
 to listen for accelerometer movements.  You will have to determine what's hard enough
 a movement to warrant a "shake."

 Reference:
 http://joerichard.net/android/android-shake-detector/

 */

public class ShakeActivity extends AppCompatActivity implements SensorEventListener {

    SensorManager sensorManager;
    private Sensor mAccelerometer;
    private static final float SHAKE_THRESHOLD_GRAVITY = 1.25F;

    private int mShakeCount = 0;
    TextView shakeCountTextView;
    private long lastupdate = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shake);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
        // Add code to intialize the sensorManager and accelerometer

    }

    @Override
    public void onResume() {
        super.onResume();
        sensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        // Add a line to register the Session Manager Listener

    }

    @Override
    public void onPause() {
        // Add a line to unregister the Sensor Manager
        sensorManager.unregisterListener(this);
        super.onPause();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Nothing needs to be added here.
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        shakeCountTextView = findViewById(R.id.shakeCountTextView);
        float k = event.values[0] + event.values[1] + event.values[2];
        long currentTime = System.currentTimeMillis();
        if(k > 5 && currentTime - lastupdate >= 100) {
            lastupdate = currentTime;
            // only allow one update every 100ms.

            Log.e("sensor", "shake detected w/ speed: " + k);
            Toast.makeText(this, "shake detected w/ speed: " + k, Toast.LENGTH_SHORT).show();


            mShakeCount += 1;
            Log.e("yeah", "shake" + mShakeCount);
            String count = "Shake Count: " + mShakeCount;

            shakeCountTextView.setText(count);
        }
        // Add code here to handle what happens when a sensor event occurs.

    }
}
