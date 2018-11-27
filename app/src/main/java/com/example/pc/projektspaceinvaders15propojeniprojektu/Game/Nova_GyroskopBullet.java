package com.example.pc.projektspaceinvaders15propojeniprojektu.Game;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.AsyncTask;
import android.widget.TextView;

import com.example.pc.projektspaceinvaders15propojeniprojektu.MainActivity;

import static android.content.Context.SENSOR_SERVICE;

public class Nova_GyroskopBullet extends AsyncTask<SensorManager, Void, Void> implements SensorEventListener {

    private TextView text3;
    float[] mGravity;
    float[] mGeomagnetic;
    float[] inclineGravity = new float[3];
    float pitch;
    float roll;
    private SensorManager mSensorManager;
    private Sensor accelerometer;
    private Sensor magnetometer;
    private MainActivity mActivity;
    private Context context;
    private int ySenzor;


    public Nova_GyroskopBullet(MainActivity mActivity){
        this.context = mActivity.getApplicationContext();
        this.mActivity = mActivity;
        mSensorManager = (SensorManager) this.mActivity.getSystemService(SENSOR_SERVICE);
        accelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        magnetometer = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        initListeners();
    }

    @Override
    protected Void doInBackground(SensorManager... sensorManagers) {
        return null;
    }

    public void initListeners() {
        mSensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_FASTEST);
        mSensorManager.registerListener(this, magnetometer, SensorManager.SENSOR_DELAY_FASTEST);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            mGravity = event.values;
        } else if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            mGeomagnetic = event.values;

            if(ySenzor != isTilt1()){
                ySenzor = isTilt1();
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public int isTilt1() {
        if (mGravity != null && mGeomagnetic != null) {
            float R[] = new float[9];
            float I[] = new float[9];

            boolean success = SensorManager.getRotationMatrix(R, I, mGravity, mGeomagnetic);

            if (success) {
                float orientation[] = new float[3];
                SensorManager.getOrientation(R, orientation);

                pitch = orientation[1];
                roll = orientation[2];

                inclineGravity = mGravity.clone();

                double norm_Of_g = Math.sqrt(inclineGravity[0] * inclineGravity[0] + inclineGravity[1] * inclineGravity[1] + inclineGravity[2] * inclineGravity[2]);

                inclineGravity[0] = (float) (inclineGravity[0] / norm_Of_g);
                inclineGravity[1] = (float) (inclineGravity[1] / norm_Of_g);
                inclineGravity[2] = (float) (inclineGravity[2] / norm_Of_g);

                int inclination = (int) Math.round(Math.toDegrees(Math.acos(inclineGravity[1])));

                Float objPitch = new Float(pitch);
                Float objZero = new Float(0.0);
                Float objZeroPointTwo = new Float(0.2);
                Float objZeroPointTwoNegative = new Float(-0.2);

                int objPitchZeroResult = objPitch.compareTo(objZero);
                int objPitchZeroPointTwoResult = objZeroPointTwo.compareTo(objPitch);
                int objPitchZeroPointTwoNegativeResult = objPitch.compareTo(objZeroPointTwoNegative);
                return inclination;
            }
        }
        return 100000000;
    }

    public int getYSenzor(){
        return ySenzor;
    }

    public void vypisInt2(int str2){
        System.out.println("private void vypisInt2(int str2){"+str2);
    }


/*
    @Override
    public void onDestroy() {
        mSensorManager.unregisterListener(this);
        this.mActivity.onDestroy();
    }

    @Override
    public void onBackPressed() {
        mSensorManager.unregisterListener(this);
        this.mActivity.onBackPressed();
    }
*/

/*

//    @Override
    public void onResume() {
        initListeners();
        this.mActivity.onResume();
        mSensorManager.registerListener(this, accelerometer,SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this, magnetometer,SensorManager.SENSOR_DELAY_NORMAL);
    }
*/

    /*
    @Override
    protected void onPause() {
        mSensorManager.unregisterListener(this);
        this.mActivity.onPause();
    }
    */

}
