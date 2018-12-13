package jp.co.mo.sensorlight;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private Sensor mSensor;
    private SensorManager mSensorManager;

    private TextView mResultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        mResultText = findViewById(R.id.result);

    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    public void onSensorChanged(SensorEvent event) {
        for (int i = 0; i < event.values.length; i++) {
            Log.e(TAG, "onSensorChanged event[" + i + "]: " + String.valueOf(event.values[i]));
            i++;
        }
        if (event.values[0] > 40) {
            mResultText.setText("Music is working");
        }
        if (event.values[0] < 10) {
            mResultText.setText("Music is stopped");
        }
    }
    
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


}
