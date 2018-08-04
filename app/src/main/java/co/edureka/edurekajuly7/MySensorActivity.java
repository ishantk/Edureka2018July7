package co.edureka.edurekajuly7;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

public class MySensorActivity extends AppCompatActivity implements SensorEventListener{

    TextView txtData;

    SensorManager sensorManager;
    Sensor sensor;

    TextToSpeech tts;
    boolean isTTSEnabled = true;

    // Voice to Text
    SpeechRecognizer speechRecognizer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_sensor);

        txtData = findViewById(R.id.textViewData);

        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        //sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);

        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status == TextToSpeech.ERROR){
                    Toast.makeText(MySensorActivity.this,"TTS Not Available",Toast.LENGTH_LONG).show();
                    isTTSEnabled = false;
                }
            }
        });
    }


    public void clickHandler(View view){
        sensorManager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        float[] values = event.values;

        float x = values[0];
        float y = values[1];
        float z = values[2];

        float cal = ( (x*x)+(y*y)+(z*z) ) / (SensorManager.GRAVITY_EARTH*SensorManager.GRAVITY_EARTH);

        if(cal >= 3){
            txtData.setText("Deice Shaken");
            sensorManager.unregisterListener(this); // No More Listening to Events

            String dateTime = "Its "+new Date();

            tts.speak(dateTime,TextToSpeech.QUEUE_FLUSH,null);

        }else{
            txtData.setText("Data: "+x+" : "+y+" : "+z);
        }

        /*float value = event.values[0];
        if(value == 0){
            txtData.setText("North Direction...");
        }//.....
        */
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
