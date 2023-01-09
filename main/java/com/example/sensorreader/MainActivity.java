package com.example.sensorreader;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView tv1=null;
   TextView tv2=null;
    TextView tv3=null;
    TextView tv4=null;
    TextView tv5=null;
    private SensorManager sensorManager;
    private Sensor gyro,grav,ori,prox;
    private Sensor temp;
    List gyrolist,gravList, orilist,proxList;


    @Override
    public final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        //gyro
        gyrolist = sensorManager.getSensorList(Sensor.TYPE_GYROSCOPE);
        if(gyrolist.size()>0){
            gyro = (Sensor)gyrolist.get(0);
            sensorManager.registerListener(selGyro, gyro, SensorManager.SENSOR_DELAY_NORMAL);
        }else{
            Toast.makeText(getBaseContext(), "Error: No Gyroscope.", Toast.LENGTH_LONG).show();
        }


        //grav
        gravList = sensorManager.getSensorList(Sensor.TYPE_GRAVITY);
        if(gravList.size()>0){
            grav = (Sensor)gravList.get(0);
            sensorManager.registerListener(selGrav, grav, SensorManager.SENSOR_DELAY_NORMAL);
        }else{
            Toast.makeText(getBaseContext(), "Error: No Gravity.", Toast.LENGTH_LONG).show();
        }

        //ori
        orilist = sensorManager.getSensorList(Sensor.TYPE_GAME_ROTATION_VECTOR);
        if(orilist.size()>0){
            ori = (Sensor)orilist.get(0);
            sensorManager.registerListener(selOri, ori, SensorManager.SENSOR_DELAY_NORMAL);
        }else{
            Toast.makeText(getBaseContext(), "Error: No Game Rotation Vector.", Toast.LENGTH_LONG).show();
        }

        //ori
        proxList = sensorManager.getSensorList(Sensor.TYPE_AMBIENT_TEMPERATURE);
        if(proxList.size()>0){
            prox = (Sensor)proxList.get(0);
            sensorManager.registerListener(selProx, prox, SensorManager.SENSOR_DELAY_NORMAL);
        }else{
            Toast.makeText(getBaseContext(), "Error: No AMBIENT TEMPERATURE.", Toast.LENGTH_LONG).show();
        }

        tv2 = (TextView) findViewById(R.id.textView2);
        tv3 = (TextView) findViewById(R.id.textView3);
        tv4 = (TextView) findViewById(R.id.textView4);
        tv5 = (TextView) findViewById(R.id.textView5);
       // tv2 = (TextView) findViewById(R.id.textView3);
       // tv1.setVisibility(View.GONE);
    }


    SensorEventListener selGyro = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            tv2.setText("Gyroscope:\nX:"+sensorEvent.values[0]+"\nY:"+sensorEvent.values[1]+"\nZ:"+sensorEvent.values[2]);
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }

    };

    SensorEventListener selGrav = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            tv3.setText("Gravity:\nX:"+sensorEvent.values[0]+"\nY:"+sensorEvent.values[1]+"\nZ:"+sensorEvent.values[2]);
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }

    };

    SensorEventListener selOri = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            tv4.setText("Game Rotation Vector:\nX:"+sensorEvent.values[0]+"\nY:"+sensorEvent.values[1]+"\nZ:"+sensorEvent.values[2]);
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }

    };

    SensorEventListener selProx = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            tv5.setText("AMBIENT TEMPERATURE:\nX:"+sensorEvent.values[0]);//+"\nY:"+sensorEvent.values[1]+"\nZ:"+sensorEvent.values[2]);
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }

    };
   /* @Override
    public final void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Do something here if sensor accuracy changes.
        //tv1.setText("\nAccuracy:"+accuracy);
        //tv1.append("\nAccuracy:"+accuracy);
    }

    @Override
    public final void onSensorChanged(SensorEvent event) {
        float lux=0,t=0;
        // The light sensor returns a single value.
        // Many sensors return 3 values, one for each axis.
        if (event.sensor.getType() == Sensor.TYPE_LIGHT) {
            lux = event.values[0];
            tv2.setText("\nLight Level:"+lux);
        } else if(Sensor.TYPE_MAGNETIC_FIELD == event.sensor.getType()) {
           t = event.values[0];
            tv1.setText("\nTemp:"+t);
        }

        //tv2.append("\nLight Level:"+lux);
        // Do something with this sensor value.
    }
*/
    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(selGyro,gyro, SensorManager.SENSOR_DELAY_UI);
        sensorManager.registerListener(selGrav,grav, SensorManager.SENSOR_DELAY_UI);
        sensorManager.registerListener(selOri,ori, SensorManager.SENSOR_DELAY_UI);
        sensorManager.registerListener(selProx,prox, SensorManager.SENSOR_DELAY_UI);
        //sensorManager.registerListener(this, temp, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(selGyro);
        sensorManager.unregisterListener(selGrav);
        sensorManager.unregisterListener(selOri);
        sensorManager.unregisterListener(selProx);
    }

}