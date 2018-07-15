package co.edureka.edurekajuly7;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MyConfigActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_config);
        Log.i("MyConfigActivity","==onCreate==");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("MyConfigActivity","==onDestroy==");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        //Log.i("MyConfigActivity","==onConfigurationChanged==");

        if(newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            Log.i("MyConfigActivity","==onConfigurationChanged - PORTRAIT MODE==");
        }

        if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Log.i("MyConfigActivity", "==onConfigurationChanged - LANDSCAPE MODE==");
        }

    }
}
