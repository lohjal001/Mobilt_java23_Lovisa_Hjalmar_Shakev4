package com.example.mobilt_java23_lovisa_hjalmar_shakev4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor sensor;

    ImageView armadillo;

    TextView textX;
    TextView textY;
    TextView textZ;

    ProgressBar progressX;
    ProgressBar progressY;
    ProgressBar progressZ;

    SeekBar seekX;
    SeekBar seekY;
    SeekBar seekZ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        armadillo = findViewById(R.id.imageView);

        textX = findViewById(R.id.textViewX);
        textY = findViewById(R.id.textViewY);
        textZ = findViewById(R.id.textViewZ);

        progressX = findViewById(R.id.progressBarX);
        progressY = findViewById(R.id.progressBarY);
        progressZ = findViewById(R.id.progressBarZ);

        seekX = findViewById(R.id.seekBarX);
        seekY = findViewById(R.id.seekBarY);
        seekZ = findViewById(R.id.seekBarZ);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float x = sensorEvent.values[0];
            float y = sensorEvent.values[1];
            float z = sensorEvent.values[2];

            textX.setText("X : " + (int) x);
            textY.setText("Y : " + (int) y);
            textZ.setText("Z : " + (int) z);

            progressX.setProgress((int) Math.abs(x) * 10);
            progressY.setProgress((int) Math.abs(y) * 10);
            progressZ.setProgress((int) Math.abs(z) * 10);

            seekX.setProgress((int) Math.abs(x) * 10);
            seekY.setProgress((int) Math.abs(y) * 10);
            seekZ.setProgress((int) Math.abs(z) * 10);

            armadillo.setRotation(x * 10);

            if (x > 3){
                Toast toast = Toast.makeText(getApplicationContext(), "I'm getting dizzy!", Toast.LENGTH_LONG);
                toast.show();

            }

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onResume() {
        super.onResume();
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this, sensor);
    }

    @Override
    public void onStop() {
        super.onStop();
        sensorManager.unregisterListener(this, sensor);
    }
}